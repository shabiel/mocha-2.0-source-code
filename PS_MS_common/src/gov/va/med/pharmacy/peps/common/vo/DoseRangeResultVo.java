package gov.va.med.pharmacy.peps.common.vo;

import firstdatabank.dif.FDBMedicalConditionType;
import firstdatabank.dif.IString;

/**
 * A POJO to hold Dose Range Results.
 * 
 * @author zzzzzzduffg
 * 
 */
public final class DoseRangeResultVo extends ValueObject {

    private static final long serialVersionUID = 1L;

    private Boolean custom;

    private Long ageHighInDays;
    private Long ageLowInDays;
    private Boolean bsaRequired;
    private String halfLifeUnit;
    private String category;

    private Boolean hepaticImpairment;
    private Double highEliminationHalfLife;
    private Double lowEliminationHalfLife;
    private Long maxDuration;
    private Boolean renalImpairment;
    private Boolean weightRequired;

    private String doseRouteDescription;
    private String doseRouteId;
    private IString intlDoseRouteDescription;

    private String doseTypeDescription;
    private String doseTypeId;
    private IString intlDoseTypeDescription;

    private IntegerUnitVo crclThreshold;

    private DecimalUnitVo doseFormHigh;
    private DecimalUnitVo doseFormLow;

    private DecimalUnitVo doseHigh;
    private DecimalUnitVo doseLow;
    private DecimalUnitVo maxDailyDose;
    private DecimalUnitVo maxDailyDoseForm;
    private DecimalUnitVo maxLifetimeDose;
    private DecimalUnitVo maxLifetimeDoseForm;
    private DecimalUnitVo maxSingleDose;
    private DecimalUnitVo maxSingleDoseForm;

    private Long durationHigh;
    private Long durationLow;
    private Double frequencyHigh;
    private Double frequencyLow;

    private String hitIndicationDescription;
    private Integer hitIndicationId;
    private IString intlHitIndicationDescription;

    private String indicationDescription;
    private String indicationId;
    private FDBMedicalConditionType indicationIdType;

    private DecimalUnitVo maxSingleNTEDose;
    private DecimalUnitVo maxSingleNTEDoseForm;

    /**
     * Returns the Custom value.
     * 
     * @return Boolean
     */
    public Boolean getCustom() {
        return custom;
    }

    /**
     * Sets the Custom value.
     * 
     * @param custom
     *            the value to set
     */
    public void setCustom(Boolean custom) {
        this.custom = custom;
    }

    /**
     * Returns the AgeHighInDays value.
     * 
     * @return Long
     */
    public Long getAgeHighInDays() {
        return ageHighInDays;
    }

    /**
     * Sets the AgeHighInDays value.
     * 
     * @param ageHighInDays
     *            the value to set
     */
    public void setAgeHighInDays(Long ageHighInDays) {
        this.ageHighInDays = ageHighInDays;
    }

    /**
     * Returns the AgeLowInDays value.
     * 
     * @return Long
     */
    public Long getAgeLowInDays() {
        return ageLowInDays;
    }

    /**
     * Sets the AgeLowInDays value.
     * 
     * @param ageLowInDays the value to set
     */
    public void setAgeLowInDays(Long ageLowInDays) {
        this.ageLowInDays = ageLowInDays;
    }

    /**
     * Returns the BsaRequired value.
     * 
     * @return Boolean
     */
    public Boolean getBsaRequired() {
        return bsaRequired;
    }

    /**
     * Sets the BsaRequired value.
     * 
     * @param bsaRequired the value to set
     */
    public void setBsaRequired(Boolean bsaRequired) {
        this.bsaRequired = bsaRequired;
    }

    /**
     * Returns the HalfLifeUnit value.
     * 
     * @return String
     */
    public String getHalfLifeUnit() {
        return halfLifeUnit;
    }

    /**
     * Sets the HalfLifeUnit value.
     * 
     * @param halfLifeUnit the value to set
     */
    public void setHalfLifeUnit(String halfLifeUnit) {
        this.halfLifeUnit = halfLifeUnit;
    }

    /**
     * Returns the Category value.
     * 
     * @return String
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the Category value.
     * 
     * @param category the value to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the HepaticImpairment value.
     * 
     * @return Boolean
     */
    public Boolean getHepaticImpairment() {
        return hepaticImpairment;
    }

    /**
     * Sets the HepaticImpairment value.
     * 
     * @param hepaticImpairment the value to set
     */
    public void setHepaticImpairment(Boolean hepaticImpairment) {
        this.hepaticImpairment = hepaticImpairment;
    }

    /**
     * Returns the HighEliminationHalfLife value.
     * 
     * @return Double
     */
    public Double getHighEliminationHalfLife() {
        return highEliminationHalfLife;
    }

    /**
     * Sets the HighEliminationHalfLife value.
     * 
     * @param highEliminationHalfLife the value to set
     */
    public void setHighEliminationHalfLife(Double highEliminationHalfLife) {
        this.highEliminationHalfLife = highEliminationHalfLife;
    }

    /**
     * Returns the LowEliminationHalfLife value.
     * 
     * @return Double
     */
    public Double getLowEliminationHalfLife() {
        return lowEliminationHalfLife;
    }

    /**
     * Sets the LowEliminationHalfLife value.
     * 
     * @param lowEliminationHalfLife the value to set
     */
    public void setLowEliminationHalfLife(Double lowEliminationHalfLife) {
        this.lowEliminationHalfLife = lowEliminationHalfLife;
    }

    /**
     * Returns the MaxDuration value.
     * 
     * @return Long
     */
    public Long getMaxDuration() {
        return maxDuration;
    }

    /**
     * Sets the MaxDuration value.
     * 
     * @param maxDuration the value to set
     */
    public void setMaxDuration(Long maxDuration) {
        this.maxDuration = maxDuration;
    }

    /**
     * Returns the RenalImpairment value.
     * 
     * @return Boolean
     */
    public Boolean getRenalImpairment() {
        return renalImpairment;
    }

    /**
     * Sets the RenalImpairment value.
     * 
     * @param renalImpairment the value to set
     */
    public void setRenalImpairment(Boolean renalImpairment) {
        this.renalImpairment = renalImpairment;
    }

    /**
     * Returns the WeightRequired value.
     * 
     * @return Boolean
     */
    public Boolean getWeightRequired() {
        return weightRequired;
    }

    /**
     * Sets the WeightRequired value.
     * 
     * @param weightRequired the value to set
     */
    public void setWeightRequired(Boolean weightRequired) {
        this.weightRequired = weightRequired;
    }

    /**
     * Returns the DoseRouteDescription value.
     * 
     * @return String
     */
    public String getDoseRouteDescription() {
        return doseRouteDescription;
    }

    /**
     * Sets the DoseRouteDescription value.
     * 
     * @param doseRouteDescription the value to set
     */
    public void setDoseRouteDescription(String doseRouteDescription) {
        this.doseRouteDescription = doseRouteDescription;
    }

    /**
     * Returns the DoseRouteId value.
     * 
     * @return String
     */
    public String getDoseRouteId() {
        return doseRouteId;
    }

    /**
     * Sets the DoseRouteId value.
     * 
     * @param doseRouteId the value to set
     */
    public void setDoseRouteId(String doseRouteId) {
        this.doseRouteId = doseRouteId;
    }

    /**
     * Returns the InternationalDoseRouteDescription value.
     * 
     * @return IString
     */
    public IString getIntlDoseRouteDescription() {
        return intlDoseRouteDescription;
    }

    /**
     * Sets the InternationalDoseRouteDescription value.
     * 
     * @param intlDoseRouteDescription the value to set
     */
    public void setIntlDoseRouteDescription(IString intlDoseRouteDescription) {
        this.intlDoseRouteDescription = intlDoseRouteDescription;
    }

    /**
     * Returns the DoseTypeDescription value.
     * 
     * @return String
     */
    public String getDoseTypeDescription() {
        return doseTypeDescription;
    }

    /**
     * Sets the DoseTypeDescription value.
     * 
     * @param doseTypeDescription the value to set
     */
    public void setDoseTypeDescription(String doseTypeDescription) {
        this.doseTypeDescription = doseTypeDescription;
    }

    /**
     * Returns the DoseTypeId value.
     * 
     * @return String
     */
    public String getDoseTypeId() {
        return doseTypeId;
    }

    /**
     * Sets the DoseTypeId value.
     * 
     * @param doseTypeId the value to set
     */
    public void setDoseTypeId(String doseTypeId) {
        this.doseTypeId = doseTypeId;
    }

    /**
     * Returns the InternationalDoseTypeDescription value.
     * 
     * @return IString
     */
    public IString getIntlDoseTypeDescription() {
        return intlDoseTypeDescription;
    }

    /**
     * Sets the InternationDoseTypeDescription value.
     * 
     * @param intlDoseTypeDescription the value to set
     */
    public void setIntlDoseTypeDescription(IString intlDoseTypeDescription) {
        this.intlDoseTypeDescription = intlDoseTypeDescription;
    }

    /**
     * Returns the CrclThreshold value.
     * 
     * @return IntegerUnitVo
     */
    public IntegerUnitVo getCrclThreshold() {
        return crclThreshold;
    }

    /**
     * Sets the CrclThreshold value.
     * 
     * @param crclThreshold the value to set
     */
    public void setCrclThreshold(IntegerUnitVo crclThreshold) {
        this.crclThreshold = crclThreshold;
    }

    /**
     * Returns the DoseFormHigh value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getDoseFormHigh() {
        return doseFormHigh;
    }

    /**
     * Sets the DoseFormHigh value.
     * 
     * @param doseFormHigh the value to set
     */
    public void setDoseFormHigh(DecimalUnitVo doseFormHigh) {
        this.doseFormHigh = doseFormHigh;
    }

    /**
     * Returns the DoseFormLow value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getDoseFormLow() {
        return doseFormLow;
    }

    /**
     * Sets the DoseFormLow value.
     * 
     * @param doseFormLow the value to set
     */
    public void setDoseFormLow(DecimalUnitVo doseFormLow) {
        this.doseFormLow = doseFormLow;
    }

    /**
     * Returns the DoseHigh value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getDoseHigh() {
        return doseHigh;
    }

    /**
     * Sets the DoseHigh value.
     * 
     * @param doseHigh the value to set
     */
    public void setDoseHigh(DecimalUnitVo doseHigh) {
        this.doseHigh = doseHigh;
    }

    /**
     * Returns the DoseLow value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getDoseLow() {
        return doseLow;
    }

    /**
     * Sets the DoseLow value.
     * 
     * @param doseLow the value to set
     */
    public void setDoseLow(DecimalUnitVo doseLow) {
        this.doseLow = doseLow;
    }

    /**
     * Returns the MaxDailyDose value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getMaxDailyDose() {
        return maxDailyDose;
    }

    /**
     * Sets the MaxDailyDose value.
     * 
     * @param maxDailyDose the value to set
     */
    public void setMaxDailyDose(DecimalUnitVo maxDailyDose) {
        this.maxDailyDose = maxDailyDose;
    }

    /**
     * Returns the MaxDailyDoseForm value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getMaxDailyDoseForm() {
        return maxDailyDoseForm;
    }

    /**
     * Sets the MaxDailyDoseForm value.
     * 
     * @param maxDailyDoseForm the value to set
     */
    public void setMaxDailyDoseForm(DecimalUnitVo maxDailyDoseForm) {
        this.maxDailyDoseForm = maxDailyDoseForm;
    }

    /**
     * Returns the MaxLifetimeDose value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getMaxLifetimeDose() {
        return maxLifetimeDose;
    }

    /**
     * Sets the MaxLifetimeDose value.
     * 
     * @param maxLifetimeDose the value to set
     */
    public void setMaxLifetimeDose(DecimalUnitVo maxLifetimeDose) {
        this.maxLifetimeDose = maxLifetimeDose;
    }

    /**
     * Returns the MaxLifetimeDoseForm value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getMaxLifetimeDoseForm() {
        return maxLifetimeDoseForm;
    }

    /**
     * Sets the MaxLifetimeDoseForm value.
     * 
     * @param maxLifetimeDoseForm the value to set
     */
    public void setMaxLifetimeDoseForm(DecimalUnitVo maxLifetimeDoseForm) {
        this.maxLifetimeDoseForm = maxLifetimeDoseForm;
    }

    /**
     * Returns the MaxSingleDose value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getMaxSingleDose() {
        return maxSingleDose;
    }

    /**
     * Sets the MaxSingleDose value.
     * 
     * @param maxSingleDose the value to set
     */
    public void setMaxSingleDose(DecimalUnitVo maxSingleDose) {
        this.maxSingleDose = maxSingleDose;
    }

    /**
     * Returns the MaxSingleDoseForm value.
     * 
     * @return DecimalUnitVo
     */
    public DecimalUnitVo getMaxSingleDoseForm() {
        return maxSingleDoseForm;
    }

    /**
     * Sets the MaxSingleDoseForm value.
     * 
     * @param maxSingleDoseForm the value to set
     */
    public void setMaxSingleDoseForm(DecimalUnitVo maxSingleDoseForm) {
        this.maxSingleDoseForm = maxSingleDoseForm;
    }

    /**
     * Returns the DurationHigh value.
     * 
     * @return Long
     */
    public Long getDurationHigh() {
        return durationHigh;
    }

    /**
     * Sets the DurationHigh value.
     * 
     * @param durationHigh the value to set
     */
    public void setDurationHigh(Long durationHigh) {
        this.durationHigh = durationHigh;
    }

    /**
     * Returns the DurationLow value.
     * 
     * @return Long
     */
    public Long getDurationLow() {
        return durationLow;
    }

    /**
     * Sets the DurationLow value.
     * 
     * @param durationLow the value to set
     */
    public void setDurationLow(Long durationLow) {
        this.durationLow = durationLow;
    }

    /**
     * Returns the FrequencyHigh value.
     * 
     * @return Double
     */
    public Double getFrequencyHigh() {
        return frequencyHigh;
    }

    /**
     * Sets the FrequencyHigh value.
     * 
     * @param frequencyHigh the value to set
     */
    public void setFrequencyHigh(Double frequencyHigh) {
        this.frequencyHigh = frequencyHigh;
    }

    /**
     * Returns the FrequencyLow value.
     * 
     * @return Double
     */
    public Double getFrequencyLow() {
        return frequencyLow;
    }

    /**
     * Sets the FrequencyLow value.
     * 
     * @param frequencyLow the value to set
     */
    public void setFrequencyLow(Double frequencyLow) {
        this.frequencyLow = frequencyLow;
    }

    /**
     * Returns the HitIndicationDescription value.
     * 
     * @return String
     */
    public String getHitIndicationDescription() {
        return hitIndicationDescription;
    }

    /**
     * Sets the HitIndicationDescription value.
     * 
     * @param hitIndicationDescription the value to set
     */
    public void setHitIndicationDescription(String hitIndicationDescription) {
        this.hitIndicationDescription = hitIndicationDescription;
    }

    /**
     * Returns the HitIndicationId value.
     * 
     * @return Integer
     */
    public Integer getHitIndicationId() {
        return hitIndicationId;
    }

    /**
     * Sets the HitIndicationId value.
     * 
     * @param hitIndicationId the value to set
     */
    public void setHitIndicationId(Integer hitIndicationId) {
        this.hitIndicationId = hitIndicationId;
    }

    /**
     * Returns the InternationalHitIndicationDescription value.
     * 
     * @return IString
     */
    public IString getIntlHitIndicationDescription() {
        return intlHitIndicationDescription;
    }

    /**
     * Sets the InternationalHitIndicationDescription value.
     * 
     * @param intlHitIndicationDescription the value to set
     */
    public void setIntlHitIndicationDescription(IString intlHitIndicationDescription) {
        this.intlHitIndicationDescription = intlHitIndicationDescription;
    }

    /**
     * Returns the IndicationDescription value.
     * 
     * @return String
     */
    public String getIndicationDescription() {
        return indicationDescription;
    }

    /**
     * Sets the IndicationDescription value.
     * 
     * @param indicationDescription the value to set
     */
    public void setIndicationDescription(String indicationDescription) {
        this.indicationDescription = indicationDescription;
    }

    /**
     * Returns the IndicationId value.
     * 
     * @return String
     */
    public String getIndicationId() {
        return indicationId;
    }

    /**
     * Sets the IndicationId value.
     * 
     * @param indicationId the value to set
     */
    public void setIndicationId(String indicationId) {
        this.indicationId = indicationId;
    }

    /**
     * Sets the IndicationIdType value.
     * 
     * @return FDBMedicationConditionType
     */
    public FDBMedicalConditionType getIndicationIdType() {
        return indicationIdType;
    }

    /**
     * Sets the IndicationIdType value.
     * 
     * @param indicationIdType the value to set
     */
    public void setIndicationIdType(FDBMedicalConditionType indicationIdType) {
        this.indicationIdType = indicationIdType;
    }

    /**
     * Returns the MaxSingleNTEDose value.
     * 
     * @return the maxSingleNTEDose
     */
    public DecimalUnitVo getMaxSingleNTEDose() {
        return maxSingleNTEDose;
    }

    /**
     * Sets the MaxSingleNTEDose value.
     * 
     * @param maxSingleNTEDose
     *            the maxSingleNTEDose to set
     */
    public void setMaxSingleNTEDose(DecimalUnitVo maxSingleNTEDose) {
        this.maxSingleNTEDose = maxSingleNTEDose;
    }

    /**
     * Returns the MaxSingleNTEDoseForm value.
     * 
     * @return the maxSingleNTEDoseForm
     */
    public DecimalUnitVo getMaxSingleNTEDoseForm() {
        return maxSingleNTEDoseForm;
    }

    /**
     * Sets the MaxSingleNTEDoseForm value.
     * 
     * @param maxSingleNTEDoseForm
     *            the maxSingleNTEDoseForm to set
     */
    public void setMaxSingleNTEDoseForm(DecimalUnitVo maxSingleNTEDoseForm) {
        this.maxSingleNTEDoseForm = maxSingleNTEDoseForm;
    }

}
