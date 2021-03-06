package gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.impl;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import gov.va.med.pharmacy.peps.common.exception.InterfaceException;
import gov.va.med.pharmacy.peps.common.utility.PropertyUtility;
import gov.va.med.pharmacy.peps.common.vo.CalculatedDoseVo;
import gov.va.med.pharmacy.peps.common.vo.DrugCheckResultsVo;
import gov.va.med.pharmacy.peps.common.vo.DrugCheckVo;
import gov.va.med.pharmacy.peps.common.vo.DrugDoseCheckResultVo;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.PerformDrugDoseCheckCapability;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.utility.DoseStatusUtility;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.utility.MessageConversionUtility;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.utility.StringUtility;

import firstdatabank.database.FDBDataManager;
import firstdatabank.database.FDBException;
import firstdatabank.dif.CalculatedDoseValue;
import firstdatabank.dif.Classification;
import firstdatabank.dif.Classifications;
import firstdatabank.dif.DOSEScreenResult;
import firstdatabank.dif.DOSEScreenResults;
import firstdatabank.dif.DispensableGeneric;
import firstdatabank.dif.DoseRoutes;
import firstdatabank.dif.FDBClassificationsType;
import firstdatabank.dif.FDBDOSESource;
import firstdatabank.dif.ScreenDrugs;
import firstdatabank.dif.Screening;


/**
 * Perform a drug dosage check.
 */
public class PerformDrugDoseCheckCapabilityImpl implements PerformDrugDoseCheckCapability {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
        .getLogger(PerformDrugDoseCheckCapabilityImpl.class);

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.#####"); // format numbers similar to FDB
    private static final int MAX_DECIMALS = 5;

    private static final String ORAL_ROUTE_PROPERTY = "ORAL_ROUTE_ID";
    private static final String TOPICAL_ROUTE_PROPERTY = "TOPICAL_ROUTE_ID";
    private static final String ANTINEOPLASTICS_CLASS_PROPERTY = "ANTINEOPLASTICS_CLASS_ID";

    private static final String ORAL_ROUTE_ID = PropertyUtility.getProperty(PerformDrugDoseCheckCapabilityImpl.class,
        ORAL_ROUTE_PROPERTY);
    private static final String TOPICAL_ROUTE_ID = PropertyUtility.getProperty(PerformDrugDoseCheckCapabilityImpl.class,
        TOPICAL_ROUTE_PROPERTY);
    private static final String ANTINEOPLASTICS_CLASS_ID = PropertyUtility.getProperty(
        PerformDrugDoseCheckCapabilityImpl.class, ANTINEOPLASTICS_CLASS_PROPERTY);

    private Screening screening;
    private FDBDataManager fdbDataManager;

    /**
     * Empty constructor.
     */
    public PerformDrugDoseCheckCapabilityImpl() {
        super();

        DECIMAL_FORMAT.setMaximumFractionDigits(MAX_DECIMALS);
    }

    /**
     * Perform a drug dosage check.
     * 
     * @param screenDrugs FDB ScreenDrugs used as input to dose check call
     * @param drugMap Map of the combined String of IEN and order number to DrugCheckVo. The combined String of IEN and order
     *            number is stored in the FDB ScreenDrug's custom ID attribute.
     * @param prospectiveOnly boolean true if check should only be done on prospective drugs
     * @param ageInDays long age of patient in days
     * @param weightInKg double weight of patient in kilograms
     * @param bodySurfaceAreaInSqM double body surface area of patient in square meters
     * @param customTables boolean true if customized tables to be used in the check
     * @return DrugDoseCheckResultsVo containing results from FDB
     */
    public final DrugCheckResultsVo<DrugDoseCheckResultVo> performDrugDoseCheck(ScreenDrugs screenDrugs,
            Map<String, DrugCheckVo> drugMap, boolean prospectiveOnly, long ageInDays, double weightInKg,
            double bodySurfaceAreaInSqM, boolean customTables) {

        FDBDOSESource source;
        String category;

        if (customTables) {
            source = FDBDOSESource.fdbDSCustomDRCAndMinMax;
            category = DrugCheckVo.VA_CUSTOM_CATEGORY;
        }
        else {
            source = FDBDOSESource.fdbDSDRCAndMinMax;
            category = "";
        }

        try {
            DOSEScreenResults fdbResults = screening.DOSEScreen(screenDrugs, prospectiveOnly, ageInDays, weightInKg,
                bodySurfaceAreaInSqM, source, category);

            return convertResults(fdbResults, screenDrugs, drugMap);
        }
        catch (FDBException e) {
            throw new InterfaceException(e, InterfaceException.INTERFACE_ERROR, InterfaceException.DRUG_DATA_VENDOR);
        }
    }

    /**
     * Convert from the results received into Collection of value objects
     * 
     * @param screenResults return from FDB call to dose check
     * @param screenDrugs FDB ScreenDrugs used as input to dose check call
     * @param drugMap Map of the combined String of IEN and order number to DrugCheckVo. The combined String of IEN and order
     *            number is stored in the FDB ScreenDrug's custom ID attribute.
     * @return DrugCheckResultsVo results converted from Drug Data Vendor return containing DrugDoseCheckResultVO for checks
     * @throws FDBException if error retrieveing data
     */
    private DrugCheckResultsVo<DrugDoseCheckResultVo> convertResults(DOSEScreenResults screenResults,
                                                                     ScreenDrugs screenDrugs,
                                                                     Map<String, DrugCheckVo> drugMap) throws FDBException {

        DrugCheckResultsVo<DrugDoseCheckResultVo> vo = new DrugCheckResultsVo<DrugDoseCheckResultVo>();

        // Set messages
        vo.setMessages(MessageConversionUtility.toDrugCheckMessages(screenResults.getMessages(), drugMap, screenDrugs));

        vo.setChecks(new ArrayList<DrugDoseCheckResultVo>(screenResults.count()));

        for (int i = 0; i < screenResults.count(); i++) {
            DOSEScreenResult result = screenResults.item(i);
            DrugDoseCheckResultVo check = new DrugDoseCheckResultVo();
            check.setCustom(FDBDOSESource.fdbDSCustom.equals(result.getDoseSource()));
            check.setDrug(drugMap.get(screenDrugs.get(result.getDrugIndex()).getCustomID()));

            setChemoInjectable(check);

            // Set range dose
            check.setRangeDoseHigh(DECIMAL_FORMAT.format(result.doseRecord().getDoseHigh()));

            if (StringUtils.isNotBlank(result.getDoseRecord().getDoseHighUnit())) {
                check.setRangeDoseHigh(check.getRangeDoseHigh() + " " + result.doseRecord().getDoseHighUnit().trim());
            }

            check.setRangeDoseLow(DECIMAL_FORMAT.format(result.doseRecord().getDoseLow()));

            if (StringUtils.isNotBlank(result.getDoseRecord().getDoseLowUnit())) {
                check.setRangeDoseLow(check.getRangeDoseLow() + " " + result.doseRecord().getDoseLowUnit().trim());
            }

            check.setRangeDoseMessage(StringUtility.nullToEmptyString(result.getRangeDoseMessage()));
            check.setRangeDoseStatus(DoseStatusUtility.convert(result.getRangeDoseStatus()));
            check.setRangeDoseStatusCode(String.valueOf(result.getRangeDoseStatus().toInt()));

            // Set single dose
            check.setSingleDoseMax(DECIMAL_FORMAT.format(result.doseRecord().getMaxSingleDose()));

            if (StringUtils.isNotBlank(result.getDoseRecord().getMaxSingleDoseUnit())) {
                check.setSingleDoseMax(check.getSingleDoseMax() + " " + result.doseRecord().getMaxSingleDoseUnit().trim());
            }

            check.setSingleDoseMessage(StringUtility.nullToEmptyString(result.getSingleDoseMessage()));
            check.setSingleDoseStatus(DoseStatusUtility.convert(result.getSingleDoseStatus()));
            check.setSingleDoseStatusCode(String.valueOf(result.getSingleDoseStatus().toInt()));

            if (!Double.isNaN(result.getDoseRecord().getFrequencyHigh())) {
                check.setDoseFrequencyHigh(Double.toString(result.getDoseRecord().getFrequencyHigh()));
            }
            
            if (!Double.isNaN(result.getDoseRecord().getFrequencyLow())) {
                check.setDoseFrequencyLow(Double.toString(result.getDoseRecord().getFrequencyLow()));
            }
            
            // Set duration
            check.setDurationMessage(StringUtility.nullToEmptyString(result.getDurationMessage()));
            check.setDurationStatus(DoseStatusUtility.convert(result.getDurationStatus()));
            check.setDurationStatusCode(String.valueOf(result.getDurationStatus().toInt()));

            // Set frequency
            check.setFrequencyMessage(StringUtility.nullToEmptyString(result.getFrequencyMessage()));
            check.setFrequencyStatus(DoseStatusUtility.convert(result.getFrequencyStatus()));
            check.setFrequencyStatusCode(String.valueOf(result.getFrequencyStatus().toInt()));

            // Set daily dose
            check.setDailyDoseMessage(StringUtility.nullToEmptyString(result.getDailyDoseMessage()));
            check.setDailyDoseStatus(DoseStatusUtility.convert(result.getDailyDoseStatus()));
            check.setDailyDoseStatusCode(String.valueOf(result.getDailyDoseStatus().toInt()));

            // Set max daily dose
            check.setMaxDailyDoseMessage(StringUtility.nullToEmptyString(result.getMaxDailyDoseMessage()));
            check.setMaxDailyDoseStatus(DoseStatusUtility.convert(result.getMaxDailyDoseStatus()));
            check.setMaxDailyDoseStatusCode(String.valueOf(result.getMaxDailyDoseStatus().toInt()));

            // Set max lifetime dose
            check.setMaxLifetimeDose(DECIMAL_FORMAT.format(result.doseRecord().getMaxLifetimeDose()));

            if (StringUtils.isNotBlank(result.getDoseRecord().getMaxLifetimeDoseUnit())) {
                check.setMaxLifetimeDose(check.getMaxLifetimeDose() + " "
                    + result.doseRecord().getMaxLifetimeDoseUnit().trim());
            }

            check.setMaxLifetimeOrderMessage(StringUtility.nullToEmptyString(result.getMaxLifetimeOrderMessage()));
            check.setMaxLifetimeOrderStatus(DoseStatusUtility.convert(result.getMaxLifetimeOrderStatus()));
            check.setMaxLifetimeOrderStatusCode(String.valueOf(result.getMaxLifetimeOrderStatus().toInt()));

            // Set dose high
            check.setDoseHigh(DECIMAL_FORMAT.format(result.getDoseRecord().getDoseHigh()));
            check.setDoseHighUnit(StringUtility.nullToEmptyString(result.getDoseRecord().getDoseHighUnit()));

            // Set dose low
            check.setDoseLow(DECIMAL_FORMAT.format(result.getDoseRecord().getDoseLow()));
            check.setDoseLowUnit(StringUtility.nullToEmptyString(result.getDoseRecord().getDoseLowUnit()));

            // Set dose form high
            check.setDoseFormHigh(DECIMAL_FORMAT.format(result.getDoseRecord().getDoseFormHigh()));
            check.setDoseFormHighUnit(StringUtility.nullToEmptyString(result.getDoseRecord().getDoseFormHighUnit()));

            // Set dose form low
            check.setDoseFormLow(DECIMAL_FORMAT.format(result.getDoseRecord().getDoseFormLow()));
            check.setDoseFormLowUnit(StringUtility.nullToEmptyString(result.getDoseRecord().getDoseFormLowUnit()));

            // Set route description
            check.setDoseRouteDescription(StringUtility.nullToEmptyString(result.getDoseRecord().getDoseRouteDescription()));

            check.setMaxSingleNTEDose(DECIMAL_FORMAT.format(result.getDoseRecord().getMaxSingleNTEDose()));
            check.setMaxSingleNTEDoseUnit(StringUtility.nullToEmptyString(result.getDoseRecord().getMaxSingleNTEDoseUnit()));
            
            check.setMaxSingleNTEDoseForm(DECIMAL_FORMAT.format(result.getDoseRecord().getMaxSingleNTEDoseForm()));
            check.setMaxSingleNTEDoseFormUnit(StringUtility.nullToEmptyString(
                    result.getDoseRecord().getMaxSingleNTEDoseFormUnit()));
            
            // Calculated Dose values
            check.setDaily(convertToCalculatedDoseVo(result.getDailyDoseCalculatedValue()));
            check.setMaxDaily(convertToCalculatedDoseVo(result.getMaxDailyDoseCalculatedValue()));
            check.setRangeLow(convertToCalculatedDoseVo(result.getRangeDoseCalculatedLow()));
            check.setRangeHigh(convertToCalculatedDoseVo(result.getRangeDoseCalculatedHigh()));
            check.setMaxLifetime(convertToCalculatedDoseVo(result.getMaxLifetimeDoseCalculatedValue()));
            check.setMaxLifetimeOrder(convertToCalculatedDoseVo(result.getMaxLifetimeOrderCalculatedValue()));
            check.setSingle(convertToCalculatedDoseVo(result.getSingleDoseCalculatedValue()));
            
            // Add the current order check to the Collection of all order checks
            vo.getChecks().add(check);
        }

        return vo;
    }

    /**
     * Converts a FDB CalculatedDoseValue object to a CalculatedDoseVo object.
     * 
     * @param calculatedDoseValue the FDB CalculatedDoseValue object
     * @return CalculatedDoseVo
     */
    private CalculatedDoseVo convertToCalculatedDoseVo(CalculatedDoseValue calculatedDoseValue) {
        CalculatedDoseVo calculatedDoseVo = new CalculatedDoseVo();
        
        calculatedDoseVo.setDatabaseValue(DECIMAL_FORMAT.format(calculatedDoseValue.getDatabaseValue()));
        calculatedDoseVo.setDoseValue(DECIMAL_FORMAT.format(calculatedDoseValue.getDoseValue()));
        calculatedDoseVo.setPercentError(Integer.toString(calculatedDoseValue.getPercentError()));
        calculatedDoseVo.setUnitOfMeasure(calculatedDoseValue.getUnitOfMeasure());
        
        return calculatedDoseVo;
    }
    /**
     * A drug is a chemo injectable if its route is not ORAL and not TOPICAL and it has an ETC classification or ultimate
     * parent classification of ANTINEOPLASTICS.
     * <p>
     * If there is an error loading the DispensableDrug or checking if it is a chemo injectable, assume that it is not.
     * 
     * @param drugDoseCheckResult {@link DrugDoseCheckResultVo} with drug's GCN populated
     */
    private void setChemoInjectable(DrugDoseCheckResultVo drugDoseCheckResult) {
        boolean chemoInjectable = false;

        try {
            DispensableGeneric dispensableGeneric = newDispensableGenericInstance();
            dispensableGeneric.load(Long.valueOf(drugDoseCheckResult.getDrug().getGcnSeqNo()), "", "");

            boolean notOralOrTopical = true;

            DoseRoutes doseRoutes = dispensableGeneric.getDoseRoutes();

            for (int i = 0; i < doseRoutes.count(); i++) {
                String id = doseRoutes.item(i).getID();

                if (ORAL_ROUTE_ID.equals(id) || TOPICAL_ROUTE_ID.equals(id)) {
                    notOralOrTopical = false;
                    break;
                }
            }

            if (notOralOrTopical) {
                Classifications classifications = dispensableGeneric.getClassifications(FDBClassificationsType.fdbCTETC);

                for (int i = 0; i < classifications.count(); i++) {
                    Classification classification = classifications.item(i);
                    Classification ultimateParent = classification.getUltimateParent();

                    if (ANTINEOPLASTICS_CLASS_ID.equals(classification.getID())
                        || (ultimateParent != null && ANTINEOPLASTICS_CLASS_ID.equals(ultimateParent.getID()))) {

                        chemoInjectable = true;
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            LOG.warn("Unable to determine if GCN " + drugDoseCheckResult.getDrug().getGcnSeqNo()
                + " is a chemo injectable. Defaulting value to false.", e);
        }

        drugDoseCheckResult.setChemoInjectable(chemoInjectable);
    }

    /**
     * Sets the Screening object to use.
     * 
     * @param screening property
     */
    public final void setScreening(Screening screening) {
        this.screening = screening;
    }

    /**
     * Provide a new instance of DispensableGeneric.
     * 
     * @return new instance of DispensableGeneric
     */
    protected final DispensableGeneric newDispensableGenericInstance() {
        return new DispensableGeneric(fdbDataManager);
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
