package gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.impl;

import firstdatabank.database.FDBDataManager;
import firstdatabank.database.FDBException;
import firstdatabank.database.FDBSQLException;
import firstdatabank.dif.DRCLookupResult;
import firstdatabank.dif.DRCLookupResults;
import firstdatabank.dif.DispensableDrug;
import firstdatabank.dif.FDBDispensableDrugLoadType;
import firstdatabank.dif.FDBDosingSource;
import firstdatabank.dif.FDBMedicalConditionType;
import firstdatabank.dif.FDBSource;
import firstdatabank.dif.MMLookupResult;
import firstdatabank.dif.MMLookupResults;
import firstdatabank.dif.NeoDRCLookupResult;
import firstdatabank.dif.NeoDRCLookupResults;
import gov.va.med.pharmacy.peps.common.vo.DecimalUnitVo;
import gov.va.med.pharmacy.peps.common.vo.DoseRangeResultKey;
import gov.va.med.pharmacy.peps.common.vo.DoseRangeResultVo;
import gov.va.med.pharmacy.peps.common.vo.DosingInfoResultsVo;
import gov.va.med.pharmacy.peps.common.vo.DosingInfoVo;
import gov.va.med.pharmacy.peps.common.vo.DosingNotFoundVo;
import gov.va.med.pharmacy.peps.common.vo.IntegerUnitVo;
import gov.va.med.pharmacy.peps.common.vo.MinMaxResultVo;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.DosingInfoCapability;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation to retrieve Dosing Information.
 * 
 * @author zzzzzzduffg
 * 
 */
public class DosingInfoCapabilityImpl implements DosingInfoCapability {

    private static final Log LOGGER = LogFactory.getLog(DosingInfoCapabilityImpl.class);
    private FDBDataManager fdbDataManager;

    private static final String EMPTY_STRING = "";

    /**
     * Processes the dosing information request.
     * 
     * @param dosingInfoRequests a collection of dosing information requests.
     * @return DosingInfoResultsVo
     */
    @Override
    public final DosingInfoResultsVo processDosingInfoRequest(Collection<DosingInfoVo> dosingInfoRequests) {

        DosingInfoResultsVo results = new DosingInfoResultsVo();

        for (DosingInfoVo dosingInfoVo : dosingInfoRequests) {
            try {
                synchronized (firstdatabank.dif.GlobalObjectManager.class) { // FDB
                                                                             // locking
                                                                             // contention
                    DispensableDrug dispensableDrug = newDispensableDrugInstance();
                    dispensableDrug.load(dosingInfoVo.getGcnSeqNo().longValue(), FDBDispensableDrugLoadType.fdbDDLTGCNSeqNo,
                            EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);

                    dosingInfoVo.setDispensableDrugName(dispensableDrug.getName());
                    dosingInfoVo.setDispensableDrugDescription(dispensableDrug.getDescription());
                    processDoseRanges(dosingInfoVo, dispensableDrug);
                    processMinMax(dosingInfoVo, dispensableDrug);
                    processNeonatalDoseRanges(dosingInfoVo, dispensableDrug);
                    results.getDosingInfo().add(dosingInfoVo);
                }
            } catch (FDBSQLException fse) {
                LOGGER.error("An error occurred accessing the FDB DIF Database.  " + fse.getMessage());
                results.getDosingNotFound().add(createDosingNotFound(dosingInfoVo, fse.getMessage()));
            } catch (Exception e) {
                results.getDosingNotFound().add(createDosingNotFound(dosingInfoVo, e.getMessage()));
            }
        }
        return results;
    }

    /**
     * Creates and returns a DosingNotFoundVo object.
     * 
     * @param dosingInfoVo the GCN Sequence Number and FDBDX being processed
     * @param notFoundReason the message why the Dosing was not found
     * @return DosingNotFoundVo
     */
    private DosingNotFoundVo createDosingNotFound(DosingInfoVo dosingInfoVo, String notFoundReason) {
        DosingNotFoundVo notFound = new DosingNotFoundVo();
        notFound.setGcnSeqNo(dosingInfoVo.getGcnSeqNo());
        notFound.setFdbdx(dosingInfoVo.getFdbdx());
        notFound.setNotFoundReason(notFoundReason);
        return notFound;
    }
    
    /**
     * Retrieves and processes any Dose Ranges.
     * 
     * @param dosingInfoVo the requests
     * @param dispensableDrug the dispensable drug
     * @param filteredResults a map of results
     * @param source the FDB source to use
     */
    private void processDoseRanges(DosingInfoVo dosingInfoVo, DispensableDrug dispensableDrug,
            Map<DoseRangeResultKey, DoseRangeResultVo> filteredResults, FDBDosingSource source) {
        try {
            DRCLookupResults dosingResults = dispensableDrug.getDRCDosing(EMPTY_STRING, EMPTY_STRING, dosingInfoVo.getFdbdx(),
                    0L, FDBMedicalConditionType.fdbMCTFDBDX, EMPTY_STRING, source, EMPTY_STRING);
            final int dosingResultsLen = dosingResults.count();
            if (dosingResultsLen > 0) {
                for (int i = 0; i < dosingResultsLen; i++) {
                    createDosingResult(dosingInfoVo, dosingResults.item(i), filteredResults);
                }
            }
        } catch (FDBException fue) {
            LOGGER.info(fue.getMessage(), fue);
        }
    }

    /**
     * An overridden method to allow for fewer parameters.
     * 
     * @param dosingInfoVo the requests
     * @param dispensableDrug the dispensable drug
     */
    private void processDoseRanges(DosingInfoVo dosingInfoVo, DispensableDrug dispensableDrug) {
        Map<DoseRangeResultKey, DoseRangeResultVo> filteredResults = new HashMap<DoseRangeResultKey, DoseRangeResultVo>();

        processDoseRanges(dosingInfoVo, dispensableDrug, filteredResults, FDBDosingSource.fdbDSCustomOnly);
        processDoseRanges(dosingInfoVo, dispensableDrug, filteredResults, FDBDosingSource.fdbDSFDBOnly);

        for (DoseRangeResultVo vo : filteredResults.values()) {
            dosingInfoVo.getDoseRangeResults().add(vo);
        }
    }

    /**
     * Finds and retrieves any MinMax Dose Ranges.
     * 
     * @param dosingInfoVo the requests
     * @param dispensableDrug the dispensable drug
     */
    private void processMinMax(DosingInfoVo dosingInfoVo, DispensableDrug dispensableDrug) {
        try {
            MMLookupResults minMaxResults = dispensableDrug.getMinMaxDosing(0L);
            final int minMaxResultsLen = minMaxResults.count();
            if (minMaxResultsLen > 0) {
                for (int i = 0; i < minMaxResultsLen; i++) {
                    createMinMaxResult(dosingInfoVo, minMaxResults.item(i));
                }
            }
        } catch (FDBException fue) {
            LOGGER.info(fue.getMessage(), fue);
        }
    }

    /**
     * Finds and retrieves any NeoNatal Dose Ranges.
     * 
     * @param dosingInfoVo the requests
     * @param dispensableDrug the dispensable drug
     */
    private void processNeonatalDoseRanges(DosingInfoVo dosingInfoVo, DispensableDrug dispensableDrug) {
        try {
            NeoDRCLookupResults lookupResults = dispensableDrug.getNeoDRCDosing(EMPTY_STRING, EMPTY_STRING,
                    dosingInfoVo.getFdbdx(), 0L, 0L, 0L, FDBMedicalConditionType.fdbMCTFDBDX, EMPTY_STRING,
                    FDBDosingSource.fdbDSCustomWithFDBDefault, EMPTY_STRING);
            final int lookupResultsLen = lookupResults.count();
            if (lookupResultsLen > 0) {
                for (int i = 0; i < lookupResultsLen; i++) {
                    createNeoDrcResult(dosingInfoVo, lookupResults.item(i));
                }
            }
        } catch (FDBException fue) {
            LOGGER.info(fue.getMessage(), fue);
        }
    }

    /**
     * Creates a DoseRangeResultVo from a DRCLookupResult.
     * 
     * @param dosingInfoVo the dosing info retrieved.
     * @param dosingResult the found dose range
     * @param filteredResults a map of results
     */
    private void createDosingResult(DosingInfoVo dosingInfoVo, DRCLookupResult dosingResult,
            Map<DoseRangeResultKey, DoseRangeResultVo> filteredResults) {
        DoseRangeResultKey key = buildDoseRangeKey(dosingResult);
        if (!filteredResults.containsKey(key)) {

            DoseRangeResultVo dr = new DoseRangeResultVo();

            dr.setCustom(dosingResult.getSource() == FDBSource.fdbSFDB ? false : true);
            dr.setAgeHighInDays(dosingResult.getAgeInDaysHigh());
            dr.setAgeLowInDays(dosingResult.getAgeInDaysLow());
            dr.setBsaRequired(dosingResult.getBSARequired());
            dr.setCrclThreshold(new IntegerUnitVo(convertToInteger(dosingResult.getCrClThreshold()), dosingResult
                    .getCrClThresholdUnit()));
            dr.setCategory(dosingResult.getCategory());
            dr.setDoseFormHigh(new DecimalUnitVo(dosingResult.getDoseFormHigh(), dosingResult.getDoseFormHighUnit()));
            dr.setDoseFormLow(new DecimalUnitVo(dosingResult.getDoseFormLow(), dosingResult.getDoseFormLowUnit()));
            dr.setDoseHigh(new DecimalUnitVo(dosingResult.getDoseHigh(), dosingResult.getDoseHighUnit()));
            dr.setDoseLow(new DecimalUnitVo(dosingResult.getDoseLow(), dosingResult.getDoseLowUnit()));
            dr.setDoseRouteDescription(dosingResult.getDoseRouteDescription());
            dr.setDoseRouteId(dosingResult.getDoseRouteID());
            dr.setDoseTypeDescription(dosingResult.getDoseTypeDescription());
            dr.setDoseTypeId(dosingResult.getDoseTypeID());
            dr.setDurationHigh(dosingResult.getDurationHigh());
            dr.setDurationLow(dosingResult.getDurationLow());
            dr.setFrequencyHigh(dosingResult.getFrequencyHigh());
            dr.setFrequencyLow(dosingResult.getFrequencyLow());
            dr.setHalfLifeUnit(dosingResult.getHalfLifeUnit());
            dr.setHepaticImpairment(dosingResult.getHepaticImpairment());
            dr.setHighEliminationHalfLife(dosingResult.getHighEliminationHalfLife());
            dr.setHitIndicationDescription(dosingResult.getHitIndicationDescription());
            dr.setHitIndicationId(dosingResult.getHitIndicationID());
            dr.setIndicationDescription(dosingResult.getIndicationDescription());
            dr.setIndicationId(dosingResult.getIndicationID());
            dr.setIndicationIdType(dosingResult.getIndicationIDType());
            dr.setIntlDoseRouteDescription(dosingResult.getIntlDoseRouteDescription());
            dr.setIntlDoseTypeDescription(dosingResult.getIntlDoseTypeDescription());
            dr.setIntlHitIndicationDescription(dosingResult.getIntlHitIndicationDescription());
            dr.setLowEliminationHalfLife(dosingResult.getLowEliminationHalfLife());
            dr.setMaxDailyDose(new DecimalUnitVo(dosingResult.getMaxDailyDose(), dosingResult.getMaxDailyDoseUnit()));
            dr.setMaxDailyDoseForm(new DecimalUnitVo(dosingResult.getMaxDailyDoseForm(), 
                    dosingResult.getMaxDailyDoseFormUnit()));
            dr.setMaxDuration(dosingResult.getMaxDuration());
            dr.setMaxLifetimeDose(new DecimalUnitVo(dosingResult.getMaxLifetimeDose(), dosingResult.getMaxLifetimeDoseUnit()));
            dr.setMaxLifetimeDoseForm(new DecimalUnitVo(dosingResult.getMaxLifetimeDoseForm(), dosingResult
                    .getMaxLifetimeDoseFormUnit()));
            dr.setMaxSingleDose(new DecimalUnitVo(dosingResult.getMaxSingleDose(), dosingResult.getMaxSingleDoseUnit()));
            dr.setMaxSingleDoseForm(new DecimalUnitVo(dosingResult.getMaxSingleDoseForm(), dosingResult
                    .getMaxSingleDoseFormUnit()));
            dr.setRenalImpairment(dosingResult.getRenalImpairment());
            dr.setWeightRequired(dosingResult.getWeightRequired());
            dr.setMaxSingleNTEDose(new DecimalUnitVo(dosingResult.getMaxSingleNTEDose(), 
                    dosingResult.getMaxSingleNTEDoseUnit()));
            dr.setMaxSingleNTEDoseForm(new DecimalUnitVo(dosingResult.getMaxSingleNTEDoseForm(), 
                    dosingResult.getMaxSingleNTEDoseFormUnit()));
            filteredResults.put(key, dr);
        }
    }

    /**
     * Builds the key from the DRCLookupResult.
     * 
     * @param dosingResult the Dose Range that was found.
     * @return DoseRangeResultKey
     */
    private DoseRangeResultKey buildDoseRangeKey(DRCLookupResult dosingResult) {
        DoseRangeResultKey key = new DoseRangeResultKey();
        key.setAgeHighInDays(dosingResult.getAgeInDaysHigh());
        key.setAgeLowInDays(dosingResult.getAgeInDaysLow());
        key.setDoseRouteId(dosingResult.getDoseRouteID());
        key.setDoseTypeId(dosingResult.getDoseTypeID());
        key.setIndicationId(dosingResult.getIndicationID());
        return key;
    }

    /**
     * Creates a MinMax result object.
     * 
     * @param dosingInfoVo the Dosing info being returned.
     * @param minMaxResult the MinMax dose range from FDB.
     */
    private void createMinMaxResult(DosingInfoVo dosingInfoVo, MMLookupResult minMaxResult) {
        MinMaxResultVo mm = new MinMaxResultVo();
        dosingInfoVo.getMinMaxResults().add(mm);

        mm.setAgeInDaysHigh(minMaxResult.getAgeInDaysHigh());
        mm.setAgeInDaysLow(minMaxResult.getAgeInDaysLow());
        mm.setBsaRequired(minMaxResult.getBSARequired());
        mm.setDoseFormHigh(minMaxResult.getDoseFormHigh());
        mm.setDoseFormHighUnit(minMaxResult.getDoseFormHighUnit());
        mm.setDoseFormLow(minMaxResult.getDoseFormLow());
        mm.setDoseFormLowUnit(minMaxResult.getDoseFormLowUnit());
        mm.setDoseHigh(minMaxResult.getDoseHigh());
        mm.setDoseHighUnit(minMaxResult.getDoseHighUnit());
        mm.setDoseLow(minMaxResult.getDoseLow());
        mm.setDoseLowUnit(minMaxResult.getDoseLowUnit());
        mm.setMaxDailyDose(minMaxResult.getMaxDailyDose());
        mm.setMaxDailyDoseForm(minMaxResult.getMaxDailyDoseForm());
        mm.setMaxDailyDoseFormUnit(minMaxResult.getMaxDailyDoseFormUnit());
        mm.setMaxDailyDoseUnit(minMaxResult.getMaxDailyDoseUnit());
        mm.setResultType(minMaxResult.getResultType());
        mm.setWarningCode(minMaxResult.getWarningCode());
        mm.setWarningMessage(minMaxResult.getWarningMessage());
        mm.setWeightRequired(minMaxResult.getWeightRequired());
    }

    /**
     * Creates a NeoNatal result object.
     * 
     * @param dosingInfoVo the Dosing info being returned.
     * @param dosingResult the NeoNatal dose range from FDB.
     */
    private void createNeoDrcResult(DosingInfoVo dosingInfoVo, NeoDRCLookupResult dosingResult) {
        DoseRangeResultVo dr = new DoseRangeResultVo();
        dosingInfoVo.getNeonatalDoseRangeResults().add(dr);

        dr.setCustom(dosingResult.getSource() == FDBSource.fdbSFDB ? false : true);
        dr.setAgeHighInDays(dosingResult.getAgeInDaysHigh());
        dr.setAgeLowInDays(dosingResult.getAgeInDaysLow());
        dr.setBsaRequired(dosingResult.getBSARequired());
        dr.setCrclThreshold(new IntegerUnitVo(convertToInteger(dosingResult.getCrClThreshold()), dosingResult
                .getCrClThresholdUnit()));
        dr.setCategory(dosingResult.getCategory());
        dr.setDoseFormHigh(new DecimalUnitVo(dosingResult.getDoseFormHigh(), dosingResult.getDoseFormHighUnit()));
        dr.setDoseFormLow(new DecimalUnitVo(dosingResult.getDoseFormLow(), dosingResult.getDoseFormLowUnit()));
        dr.setDoseHigh(new DecimalUnitVo(dosingResult.getDoseHigh(), dosingResult.getDoseHighUnit()));
        dr.setDoseLow(new DecimalUnitVo(dosingResult.getDoseLow(), dosingResult.getDoseLowUnit()));
        dr.setDoseRouteDescription(dosingResult.getDoseRouteDescription());
        dr.setDoseRouteId(dosingResult.getDoseRouteID());
        dr.setDoseTypeDescription(dosingResult.getDoseTypeDescription());
        dr.setDoseTypeId(dosingResult.getDoseTypeID());
        dr.setDurationHigh(dosingResult.getDurationHigh());
        dr.setDurationLow(dosingResult.getDurationLow());
        dr.setFrequencyHigh(dosingResult.getFrequencyHigh());
        dr.setFrequencyLow(dosingResult.getFrequencyLow());
        dr.setHalfLifeUnit(dosingResult.getHalfLifeUnit());
        dr.setHepaticImpairment(dosingResult.getHepaticImpairment());
        dr.setHighEliminationHalfLife(dosingResult.getHighEliminationHalfLife());
        dr.setHitIndicationDescription(dosingResult.getHitIndicationDescription());
        dr.setHitIndicationId(Long.valueOf(dosingResult.getHitIndicationID()).intValue());
        dr.setIndicationDescription(dosingResult.getIndicationDescription());
        dr.setIndicationId(dosingResult.getIndicationID());
        dr.setIndicationIdType(dosingResult.getIndicationIDType());
        dr.setIntlDoseRouteDescription(dosingResult.getIntlDoseRouteDescription());
        dr.setIntlDoseTypeDescription(dosingResult.getIntlDoseTypeDescription());
        dr.setIntlHitIndicationDescription(dosingResult.getIntlHitIndicationDescription());
        dr.setLowEliminationHalfLife(dosingResult.getLowEliminationHalfLife());
        dr.setMaxDailyDose(new DecimalUnitVo(dosingResult.getMaxDailyDose(), dosingResult.getMaxDailyDoseUnit()));
        dr.setMaxDailyDoseForm(new DecimalUnitVo(dosingResult.getMaxDailyDoseForm(), dosingResult.getMaxDailyDoseFormUnit()));
        dr.setMaxDuration(dosingResult.getMaxDuration());
        dr.setMaxLifetimeDose(new DecimalUnitVo(dosingResult.getMaxLifetimeDose(), dosingResult.getMaxLifetimeDoseUnit()));
        dr.setMaxLifetimeDoseForm(new DecimalUnitVo(dosingResult.getMaxLifetimeDoseForm(), dosingResult
                .getMaxLifetimeDoseFormUnit()));
        dr.setMaxSingleDose(new DecimalUnitVo(dosingResult.getMaxSingleDose(), dosingResult.getMaxSingleDoseUnit()));
        dr.setMaxSingleDoseForm(new DecimalUnitVo(dosingResult.getMaxSingleDoseForm(), 
                dosingResult.getMaxSingleDoseFormUnit()));
        dr.setRenalImpairment(dosingResult.getRenalImpairment());
        dr.setWeightRequired(dosingResult.getWeightRequired());
        dr.setMaxSingleNTEDose(new DecimalUnitVo(dosingResult.getMaxSingleNTEDose(), 
                dosingResult.getMaxSingleNTEDoseUnit()));
        dr.setMaxSingleNTEDoseForm(new DecimalUnitVo(dosingResult.getMaxSingleNTEDoseForm(), 
                dosingResult.getMaxSingleNTEDoseFormUnit()));
    }

    /**
     * Converts a long value to an Integer object.
     * 
     * @param value the long value to convert
     * @return Integer
     */
    private Integer convertToInteger(long value) {
        Long intValue = Long.valueOf(value);
        return intValue.intValue();
    }

    /**
     * Provide a new instance of DispensableDrug.
     * 
     * @return new instance of DispensableDrug
     */
    protected final DispensableDrug newDispensableDrugInstance() {
        return new DispensableDrug(fdbDataManager);
    }

    /**
     * Sets the FDB Data Manager to use.
     * 
     * @param manager the FDB Data Manager.
     */
    public final void setFdbDataManager(FDBDataManager manager) {
        fdbDataManager = manager;
    }

}
