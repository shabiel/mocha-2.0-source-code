package gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import gov.va.med.pharmacy.peps.common.exception.InterfaceException;
import gov.va.med.pharmacy.peps.common.vo.DrugCheckVo;
import gov.va.med.pharmacy.peps.common.vo.OrderCheckResultsVo;
import gov.va.med.pharmacy.peps.common.vo.OrderCheckVo;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.PerformDrugChecksCapability;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.PerformDrugDoseCheckCapability;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.PerformDrugDrugCheckCapability;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.PerformDrugTherapyCheckCapability;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.utility.DoseTypeUtility;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.utility.RouteUtility;

import firstdatabank.database.FDBDataManager;
import firstdatabank.database.FDBException;
import firstdatabank.dif.FDBDrugType;
import firstdatabank.dif.FDBUnknownIDException;
import firstdatabank.dif.ScreenDrug;
import firstdatabank.dif.ScreenDrugs;


/**
 * Convert drugs into FDB ScreenDrugs and call the individual drug check capabilities to perform the checks.
 */
public class PerformDrugChecksCapabilityImpl implements PerformDrugChecksCapability {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
        .getLogger(PerformDrugChecksCapabilityImpl.class);

    private PerformDrugDoseCheckCapability performDrugDoseCheckCapability;
    private PerformDrugDrugCheckCapability performDrugDrugCheckCapability;
    private PerformDrugTherapyCheckCapability performDrugTherapyCheckCapability;

    private FDBDataManager fdbDataManager;

    /**
     * Convert drugs into FDB ScreenDrugs and call the individual drug check capabilities to perform the checks.
     * 
     * @param requestVo OrderCheckVo requesting checks
     * @return OrderCheckResultsVo with results from drug data vendor
     */
    public final OrderCheckResultsVo performDrugChecks(OrderCheckVo requestVo) {
        OrderCheckResultsVo results = new OrderCheckResultsVo();
        ScreenDrugs screenDrugs = newScreenDrugsInstance();
        ScreenDrugs doseCheckScreenDrugs = newScreenDrugsInstance();

        Map<String, DrugCheckVo> drugMap = new HashMap<String, DrugCheckVo>();

        int uniqueId = 0;

        for (DrugCheckVo drug : requestVo.getDrugsToScreen()) {
            try {
                ScreenDrug screenDrug = toScreenDrug(drug);

                // To be sure to make all drugs unique as GCN sequence number, IEN, and order number are not unique,
                // use an internal incremented unique ID.
                drugMap.put(String.valueOf(uniqueId), drug);
                screenDrug.setCustomID(String.valueOf(uniqueId));
                uniqueId++;

                // Dose checks only get prospective drugs with dosing information
                if (drug.isProspective() && drug.isDoseCheck()) {
                    doseCheckScreenDrugs.addItem(screenDrug);
                }

                screenDrugs.addItem(screenDrug);
            }
            catch (FDBUnknownIDException e) {
                LOG.warn("Drug with GCN Sequence Number " + drug.getGcnSeqNo() + " was not checked");
                results.getDrugsNotChecked().add(drug);
            }
            catch (FDBException e) {
                throw new InterfaceException(e, InterfaceException.INTERFACE_ERROR, InterfaceException.DRUG_DATA_VENDOR);
            }
        }

        if (requestVo.isDrugDoseCheck()) {
            results.setDrugDoseCheckResults(performDrugDoseCheckCapability.performDrugDoseCheck(doseCheckScreenDrugs,
                drugMap, requestVo.isProspectiveOnly(), requestVo.getAgeInDays(), requestVo.getWeightInKg(), requestVo
                    .getBodySurfaceAreaInSqM(), requestVo.isCustomTables()));
        }

        if (requestVo.isDrugDrugCheck()) {
            results.setDrugDrugCheckResults(performDrugDrugCheckCapability.performDrugDrugCheck(screenDrugs, drugMap,
                requestVo.isProspectiveOnly(), requestVo.isCustomTables()));
        }

        if (requestVo.isDrugTherapyCheck()) {
            results.setDrugTherapyCheckResults(performDrugTherapyCheckCapability.performDrugTherapyCheck(screenDrugs,
                drugMap, requestVo.isProspectiveOnly(), requestVo.isDuplicateAllowance(), requestVo.isCustomTables()));
        }

        return results;
    }

    /**
     * Convert DrugCheckVO drug into a ScreenDrug
     * 
     * @param drug Drug to convert
     * @return ScreenDrug from VO
     * @throws FDBException if error loading ScreenDrug from VO
     */
    private ScreenDrug toScreenDrug(DrugCheckVo drug) throws FDBException {
        synchronized (firstdatabank.dif.GlobalObjectManager.class) { // FDB locking contention
            ScreenDrug screenDrug = newScreenDrugInstance();
            screenDrug.load(drug.getGcnSeqNo(), FDBDrugType.fdbDTGCNSeqNo);
            screenDrug.setProspective(drug.isProspective());

            if (StringUtils.isNotBlank(drug.getDrugName())) {
                screenDrug.setDescription(drug.getDrugName());
            }

            if (drug.isDoseCheck()) {
                screenDrug.setDoseRoute(RouteUtility.routeNameToId(drug.getDoseRoute()));
                screenDrug.setDoseType(DoseTypeUtility.doseTypeNameToId(drug.getDoseType()));
                screenDrug.setDoseInfo(drug.getSingleDoseAmount(), drug.getDoseUnit(), drug.getDoseRate(), drug
                    .getFrequency(), drug.getDuration(), drug.getDurationRate());
            }

            return screenDrug;
        }
    }

    /**
     * Provide a new instance of ScreenDrugs.
     * 
     * @return new instance of ScreenDrugs
     */
    protected final ScreenDrugs newScreenDrugsInstance() {
        return new ScreenDrugs(fdbDataManager);
    }

    /**
     * Provide a new instance of ScreenDrug.
     * 
     * @return new instance of ScreenDrug
     */
    protected final ScreenDrug newScreenDrugInstance() {
        return new ScreenDrug(fdbDataManager);
    }

    /**
     * Sets the Perform Drug Dose Check Capability.
     * 
     * @param performDrugDoseCheckCapability performDrugDoseCheckCapability property
     */
    public final void setPerformDrugDoseCheckCapability(PerformDrugDoseCheckCapability performDrugDoseCheckCapability) {
        this.performDrugDoseCheckCapability = performDrugDoseCheckCapability;
    }

    /**
     * Sets the Perform Drug Drug Check Capability.
     * 
     * @param performDrugDrugCheckCapability performDrugDrugCheckCapability property
     */
    public final void setPerformDrugDrugCheckCapability(PerformDrugDrugCheckCapability performDrugDrugCheckCapability) {
        this.performDrugDrugCheckCapability = performDrugDrugCheckCapability;
    }

    /**
     * Sets the Perform Drug Therapy Check Capability.
     * 
     * @param performDrugTherapyCheckCapability performDrugTherapyCheckCapability property
     */
    public final void setPerformDrugTherapyCheckCapability(
            PerformDrugTherapyCheckCapability performDrugTherapyCheckCapability) {
        this.performDrugTherapyCheckCapability = performDrugTherapyCheckCapability;
    }

    /**
     * Sets the FDB Data Manager.
     * 
     * @param fdbDataManager fdbDataManager property
     */
    public final void setFdbDataManager(FDBDataManager fdbDataManager) {
        this.fdbDataManager = fdbDataManager;
    }
}
