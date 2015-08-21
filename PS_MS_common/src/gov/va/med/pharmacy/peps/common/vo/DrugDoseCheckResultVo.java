package gov.va.med.pharmacy.peps.common.vo;


/**
 * Results from a drug dose check.
 */
public final class DrugDoseCheckResultVo extends ValueObject {
    private static final long serialVersionUID = 1L;
    private DrugCheckVo drug;
    private String singleDoseStatus = "";
    private String singleDoseStatusCode = "";
    private String singleDoseMessage = "";
    private String singleDoseMax = "";
    private String rangeDoseStatus = "";
    private String rangeDoseStatusCode = "";
    private String rangeDoseMessage = "";
    private String rangeDoseLow = "";
    private String rangeDoseHigh = "";
    private String durationStatus = "";
    private String durationStatusCode = "";
    private String durationMessage = "";
    private String frequencyStatus = "";
    private String frequencyStatusCode = "";
    private String frequencyMessage = "";
    private String dailyDoseStatus = "";
    private String dailyDoseStatusCode = "";
    private String dailyDoseMessage = "";
    private String maxDailyDoseStatus = "";
    private String maxDailyDoseStatusCode = "";
    private String maxDailyDoseMessage = "";
    private String maxLifetimeDose = "";
    private String doseLow = "";
    private String doseLowUnit = "";
    private String doseHigh = "";
    private String doseHighUnit = "";
    private String doseRouteDescription = "";
    private String doseFormLow = "";
    private String doseFormLowUnit = "";
    private String doseFormHigh = "";
    private String doseFormHighUnit = "";
    private String doseFrequencyHigh = "";
    private String doseFrequencyLow = "";


    private boolean chemoInjectable = false;
    private boolean custom;
    
    private String maxSingleNTEDose = "";
    private String maxSingleNTEDoseUnit = "";
    private String maxSingleNTEDoseForm = "";
    private String maxSingleNTEDoseFormUnit = "";

    private String maxLifetimeOrderMessage = "";
    private String maxLifetimeOrderStatus = "";
    private String maxLifetimeOrderStatusCode = "";
    
    private CalculatedDoseVo daily;
    private CalculatedDoseVo maxDaily;
    private CalculatedDoseVo rangeLow;
    private CalculatedDoseVo rangeHigh;
    private CalculatedDoseVo maxLifetime;
    private CalculatedDoseVo maxLifetimeOrder;
    private CalculatedDoseVo single;

    /**
     * Creates a new DrugDoseCheckResultVo object.
     */
    public DrugDoseCheckResultVo() {
        super();
    }

    /**
     * Returns the drug value.
     *
     * @return the drug
     */
    public DrugCheckVo getDrug() {
        return drug;
    }

    /**
     * Sets the drug value.
     *
     * @param drug the drug to set
     */
    public void setDrug(DrugCheckVo drug) {
        this.drug = drug;
    }

    /**
     * Returns the singleDoseStatus value.
     *
     * @return the singleDoseStatus
     */
    public String getSingleDoseStatus() {
        return singleDoseStatus;
    }

    /**
     * Sets the singleDoseStatus value.
     *
     * @param singleDoseStatus the singleDoseStatus to set
     */
    public void setSingleDoseStatus(String singleDoseStatus) {
        this.singleDoseStatus = singleDoseStatus;
    }

    /**
     * Returns the singleDoseStatusCode value.
     *
     * @return the singleDoseStatusCode
     */
    public String getSingleDoseStatusCode() {
        return singleDoseStatusCode;
    }

    /**
     * Sets the singleDoseStatusCode value.
     *
     * @param singleDoseStatusCode the singleDoseStatusCode to set
     */
    public void setSingleDoseStatusCode(String singleDoseStatusCode) {
        this.singleDoseStatusCode = singleDoseStatusCode;
    }

    /**
     * Returns the singleDoseMessage value.
     *
     * @return the singleDoseMessage
     */
    public String getSingleDoseMessage() {
        return singleDoseMessage;
    }

    /**
     * Sets the singleDoseMessage value.
     *
     * @param singleDoseMessage the singleDoseMessage to set
     */
    public void setSingleDoseMessage(String singleDoseMessage) {
        this.singleDoseMessage = singleDoseMessage;
    }

    /**
     * Returns the singleDoseMax value.
     *
     * @return the singleDoseMax
     */
    public String getSingleDoseMax() {
        return singleDoseMax;
    }

    /**
     * Sets the singleDoseMax value.
     *
     * @param singleDoseMax the singleDoseMax to set
     */
    public void setSingleDoseMax(String singleDoseMax) {
        this.singleDoseMax = singleDoseMax;
    }

    /**
     * Returns the rangeDoseStatus value.
     *
     * @return the rangeDoseStatus
     */
    public String getRangeDoseStatus() {
        return rangeDoseStatus;
    }

    /**
     * Sets the rangeDoseStatus value.
     *
     * @param rangeDoseStatus the rangeDoseStatus to set
     */
    public void setRangeDoseStatus(String rangeDoseStatus) {
        this.rangeDoseStatus = rangeDoseStatus;
    }

    /**
     * Returns the rangeDoseStatusCode value.
     *
     * @return the rangeDoseStatusCode
     */
    public String getRangeDoseStatusCode() {
        return rangeDoseStatusCode;
    }

    /**
     * Sets the rangeDoseStatusCode value.
     *
     * @param rangeDoseStatusCode the rangeDoseStatusCode to set
     */
    public void setRangeDoseStatusCode(String rangeDoseStatusCode) {
        this.rangeDoseStatusCode = rangeDoseStatusCode;
    }

    /**
     * Returns the rangeDoseMessage value.
     *
     * @return the rangeDoseMessage
     */
    public String getRangeDoseMessage() {
        return rangeDoseMessage;
    }

    /**
     * Sets the rangeDoseMessage value.
     *
     * @param rangeDoseMessage the rangeDoseMessage to set
     */
    public void setRangeDoseMessage(String rangeDoseMessage) {
        this.rangeDoseMessage = rangeDoseMessage;
    }

    /**
     * Returns the rangeDoseLow value.
     *
     * @return the rangeDoseLow
     */
    public String getRangeDoseLow() {
        return rangeDoseLow;
    }

    /**
     * Sets the rangeDoseLow value.
     *
     * @param rangeDoseLow the rangeDoseLow to set
     */
    public void setRangeDoseLow(String rangeDoseLow) {
        this.rangeDoseLow = rangeDoseLow;
    }

    /**
     * Returns the rangeDoseHigh value.
     *
     * @return the rangeDoseHigh
     */
    public String getRangeDoseHigh() {
        return rangeDoseHigh;
    }

    /**
     * Sets the rangeDoseHigh value.
     *
     * @param rangeDoseHigh the rangeDoseHigh to set
     */
    public void setRangeDoseHigh(String rangeDoseHigh) {
        this.rangeDoseHigh = rangeDoseHigh;
    }

    /**
     * Returns the durationStatus value.
     *
     * @return the durationStatus
     */
    public String getDurationStatus() {
        return durationStatus;
    }

    /**
     * Sets the durationStatus value.
     *
     * @param durationStatus the durationStatus to set
     */
    public void setDurationStatus(String durationStatus) {
        this.durationStatus = durationStatus;
    }

    /**
     * Returns the durationStatusCode value.
     *
     * @return the durationStatusCode
     */
    public String getDurationStatusCode() {
        return durationStatusCode;
    }

    /**
     * Sets the durationStatusCode value.
     *
     * @param durationStatusCode the durationStatusCode to set
     */
    public void setDurationStatusCode(String durationStatusCode) {
        this.durationStatusCode = durationStatusCode;
    }

    /**
     * Returns the durationMessage value.
     *
     * @return the durationMessage
     */
    public String getDurationMessage() {
        return durationMessage;
    }

    /**
     * Sets the durationMessage value.
     *
     * @param durationMessage the durationMessage to set
     */
    public void setDurationMessage(String durationMessage) {
        this.durationMessage = durationMessage;
    }

    /**
     * Returns the frequencyStatus value.
     *
     * @return the frequencyStatus
     */
    public String getFrequencyStatus() {
        return frequencyStatus;
    }

    /**
     * Sets the frequencyStatus value.
     *
     * @param frequencyStatus the frequencyStatus to set
     */
    public void setFrequencyStatus(String frequencyStatus) {
        this.frequencyStatus = frequencyStatus;
    }

    /**
     * Returns the frequencyStatusCode value.
     *
     * @return the frequencyStatusCode
     */
    public String getFrequencyStatusCode() {
        return frequencyStatusCode;
    }

    /**
     * Sets the frequencyStatusCode value.
     *
     * @param frequencyStatusCode the frequencyStatusCode to set
     */
    public void setFrequencyStatusCode(String frequencyStatusCode) {
        this.frequencyStatusCode = frequencyStatusCode;
    }

    /**
     * Returns the frequencyMessage value.
     *
     * @return the frequencyMessage
     */
    public String getFrequencyMessage() {
        return frequencyMessage;
    }

    /**
     * Sets the frequencyMessage value.
     *
     * @param frequencyMessage the frequencyMessage to set
     */
    public void setFrequencyMessage(String frequencyMessage) {
        this.frequencyMessage = frequencyMessage;
    }

    /**
     * Returns the dailyDoseStatus value.
     *
     * @return the dailyDoseStatus
     */
    public String getDailyDoseStatus() {
        return dailyDoseStatus;
    }

    /**
     * Sets the dailyDoseStatus value.
     *
     * @param dailyDoseStatus the dailyDoseStatus to set
     */
    public void setDailyDoseStatus(String dailyDoseStatus) {
        this.dailyDoseStatus = dailyDoseStatus;
    }

    /**
     * Returns the dailyDoseStatusCode value.
     *
     * @return the dailyDoseStatusCode
     */
    public String getDailyDoseStatusCode() {
        return dailyDoseStatusCode;
    }

    /**
     * Sets the dailyDoseStatusCode value.
     *
     * @param dailyDoseStatusCode the dailyDoseStatusCode to set
     */
    public void setDailyDoseStatusCode(String dailyDoseStatusCode) {
        this.dailyDoseStatusCode = dailyDoseStatusCode;
    }

    /**
     * Returns the dailyDoseMessage value.
     *
     * @return the dailyDoseMessage
     */
    public String getDailyDoseMessage() {
        return dailyDoseMessage;
    }

    /**
     * Sets the dailyDoseMessage value.
     *
     * @param dailyDoseMessage the dailyDoseMessage to set
     */
    public void setDailyDoseMessage(String dailyDoseMessage) {
        this.dailyDoseMessage = dailyDoseMessage;
    }

    /**
     * Returns the maxDailyDoseStatus value.
     *
     * @return the maxDailyDoseStatus
     */
    public String getMaxDailyDoseStatus() {
        return maxDailyDoseStatus;
    }

    /**
     * Sets the maxDailyDoseStatus value.
     *
     * @param maxDailyDoseStatus the maxDailyDoseStatus to set
     */
    public void setMaxDailyDoseStatus(String maxDailyDoseStatus) {
        this.maxDailyDoseStatus = maxDailyDoseStatus;
    }

    /**
     * Returns the maxDailyDoseStatusCode value.
     *
     * @return the maxDailyDoseStatusCode
     */
    public String getMaxDailyDoseStatusCode() {
        return maxDailyDoseStatusCode;
    }

    /**
     * Sets the maxDailyDoseStatusCode value.
     *
     * @param maxDailyDoseStatusCode the maxDailyDoseStatusCode to set
     */
    public void setMaxDailyDoseStatusCode(String maxDailyDoseStatusCode) {
        this.maxDailyDoseStatusCode = maxDailyDoseStatusCode;
    }

    /**
     * Returns the maxDailyDoseMessage value.
     *
     * @return the maxDailyDoseMessage
     */
    public String getMaxDailyDoseMessage() {
        return maxDailyDoseMessage;
    }

    /**
     * Sets the maxDailyDoseMessage value.
     *
     * @param maxDailyDoseMessage the maxDailyDoseMessage to set
     */
    public void setMaxDailyDoseMessage(String maxDailyDoseMessage) {
        this.maxDailyDoseMessage = maxDailyDoseMessage;
    }

    /**
     * Returns the maxLifetimeDose value.
     *
     * @return the maxLifetimeDose
     */
    public String getMaxLifetimeDose() {
        return maxLifetimeDose;
    }

    /**
     * Sets the maxLifetimeDose value.
     *
     * @param maxLifetimeDose the maxLifetimeDose to set
     */
    public void setMaxLifetimeDose(String maxLifetimeDose) {
        this.maxLifetimeDose = maxLifetimeDose;
    }

    /**
     * Returns the doseLow value.
     *
     * @return the doseLow
     */
    public String getDoseLow() {
        return doseLow;
    }

    /**
     * Sets the doseLow value.
     *
     * @param doseLow the doseLow to set
     */
    public void setDoseLow(String doseLow) {
        this.doseLow = doseLow;
    }

    /**
     * Returns the doseLowUnit value.
     *
     * @return the doseLowUnit
     */
    public String getDoseLowUnit() {
        return doseLowUnit;
    }

    /**
     * Sets the doseLowUnit value.
     *
     * @param doseLowUnit the doseLowUnit to set
     */
    public void setDoseLowUnit(String doseLowUnit) {
        this.doseLowUnit = doseLowUnit;
    }

    /**
     * Returns the doseHigh value.
     *
     * @return the doseHigh
     */
    public String getDoseHigh() {
        return doseHigh;
    }

    /**
     * Sets the doseHigh value.
     *
     * @param doseHigh the doseHigh to set
     */
    public void setDoseHigh(String doseHigh) {
        this.doseHigh = doseHigh;
    }

    /**
     * Returns the doseHighUnit value.
     *
     * @return the doseHighUnit
     */
    public String getDoseHighUnit() {
        return doseHighUnit;
    }

    /**
     * Sets the doseHighUnit value.
     *
     * @param doseHighUnit the doseHighUnit to set
     */
    public void setDoseHighUnit(String doseHighUnit) {
        this.doseHighUnit = doseHighUnit;
    }

    /**
     * Returns the doseRouteDescription value.
     *
     * @return the doseRouteDescription
     */
    public String getDoseRouteDescription() {
        return doseRouteDescription;
    }

    /**
     * Sets the doseRouteDescription value.
     *
     * @param doseRouteDescription the doseRouteDescription to set
     */
    public void setDoseRouteDescription(String doseRouteDescription) {
        this.doseRouteDescription = doseRouteDescription;
    }

    /**
     * Returns the doseFormLow value.
     *
     * @return the doseFormLow
     */
    public String getDoseFormLow() {
        return doseFormLow;
    }

    /**
     * Sets the doseFormLow value.
     *
     * @param doseFormLow the doseFormLow to set
     */
    public void setDoseFormLow(String doseFormLow) {
        this.doseFormLow = doseFormLow;
    }

    /**
     * Returns the doseFormLowUnit value.
     *
     * @return the doseFormLowUnit
     */
    public String getDoseFormLowUnit() {
        return doseFormLowUnit;
    }

    /**
     * Sets the doseFormLowUnit value.
     *
     * @param doseFormLowUnit the doseFormLowUnit to set
     */
    public void setDoseFormLowUnit(String doseFormLowUnit) {
        this.doseFormLowUnit = doseFormLowUnit;
    }

    /**
     * Returns the doseFormHigh value.
     *
     * @return the doseFormHigh
     */
    public String getDoseFormHigh() {
        return doseFormHigh;
    }

    /**
     * Sets the doseFormHigh value.
     *
     * @param doseFormHigh the doseFormHigh to set
     */
    public void setDoseFormHigh(String doseFormHigh) {
        this.doseFormHigh = doseFormHigh;
    }

    /**
     * Returns the doseFormHighUnit value.
     *
     * @return the doseFormHighUnit
     */
    public String getDoseFormHighUnit() {
        return doseFormHighUnit;
    }

    /**
     * Sets the doseFormHighUnit value.
     *
     * @param doseFormHighUnit the doseFormHighUnit to set
     */
    public void setDoseFormHighUnit(String doseFormHighUnit) {
        this.doseFormHighUnit = doseFormHighUnit;
    }

    /**
     * Returns the doseFrequencyHigh value.
     *
     * @return the doseFrequencyHigh
     */
    public String getDoseFrequencyHigh() {
        return doseFrequencyHigh;
    }

    /**
     * Sets the doseFrequencyHigh value.
     *
     * @param doseFrequencyHigh the doseFrequencyHigh to set
     */
    public void setDoseFrequencyHigh(String doseFrequencyHigh) {
        this.doseFrequencyHigh = doseFrequencyHigh;
    }

    /**
     * Returns the doseFrequencyLow value.
     *
     * @return the doseFrequencyLow
     */
    public String getDoseFrequencyLow() {
        return doseFrequencyLow;
    }

    /**
     * Sets the doseFrequencyLow value.
     *
     * @param doseFrequencyLow the doseFrequencyLow to set
     */
    public void setDoseFrequencyLow(String doseFrequencyLow) {
        this.doseFrequencyLow = doseFrequencyLow;
    }

    /**
     * Returns the chemoInjectable value.
     *
     * @return the chemoInjectable
     */
    public boolean isChemoInjectable() {
        return chemoInjectable;
    }

    /**
     * Sets the chemoInjectable value.
     *
     * @param chemoInjectable the chemoInjectable to set
     */
    public void setChemoInjectable(boolean chemoInjectable) {
        this.chemoInjectable = chemoInjectable;
    }

    /**
     * Returns the custom value.
     *
     * @return the custom
     */
    public boolean isCustom() {
        return custom;
    }

    /**
     * Sets the custom value.
     *
     * @param custom the custom to set
     */
    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    /**
     * Returns the maxSingleNTEDose value.
     *
     * @return the maxSingleNTEDose
     */
    public String getMaxSingleNTEDose() {
        return maxSingleNTEDose;
    }

    /**
     * Sets the maxSingleNTEDose value.
     *
     * @param maxSingleNTEDose the maxSingleNTEDose to set
     */
    public void setMaxSingleNTEDose(String maxSingleNTEDose) {
        this.maxSingleNTEDose = maxSingleNTEDose;
    }

    /**
     * Returns the maxSingleNTEDoseUnit value.
     *
     * @return the maxSingleNTEDoseUnit
     */
    public String getMaxSingleNTEDoseUnit() {
        return maxSingleNTEDoseUnit;
    }

    /**
     * Sets the maxSingleNTEDoseUnit value.
     *
     * @param maxSingleNTEDoseUnit the maxSingleNTEDoseUnit to set
     */
    public void setMaxSingleNTEDoseUnit(String maxSingleNTEDoseUnit) {
        this.maxSingleNTEDoseUnit = maxSingleNTEDoseUnit;
    }

    /**
     * Returns the maxSingleNTEDoseForm value.
     *
     * @return the maxSingleNTEDoseForm
     */
    public String getMaxSingleNTEDoseForm() {
        return maxSingleNTEDoseForm;
    }

    /**
     * Sets the maxSingleNTEDoseForm value.
     *
     * @param maxSingleNTEDoseForm the maxSingleNTEDoseForm to set
     */
    public void setMaxSingleNTEDoseForm(String maxSingleNTEDoseForm) {
        this.maxSingleNTEDoseForm = maxSingleNTEDoseForm;
    }

    /**
     * Returns the maxSingleNTEDoseFormUnit value.
     *
     * @return the maxSingleNTEDoseFormUnit
     */
    public String getMaxSingleNTEDoseFormUnit() {
        return maxSingleNTEDoseFormUnit;
    }

    /**
     * Sets the maxSingleNTEDoseFormUnit value.
     *
     * @param maxSingleNTEDoseFormUnit the maxSingleNTEDoseFormUnit to set
     */
    public void setMaxSingleNTEDoseFormUnit(String maxSingleNTEDoseFormUnit) {
        this.maxSingleNTEDoseFormUnit = maxSingleNTEDoseFormUnit;
    }

    /**
     * Returns the maxLifetimeOrderMessage value.
     *
     * @return the maxLifetimeOrderMessage
     */
    public String getMaxLifetimeOrderMessage() {
        return maxLifetimeOrderMessage;
    }

    /**
     * Sets the maxLifetimeOrderMessage value.
     *
     * @param maxLifetimeOrderMessage the maxLifetimeOrderMessage to set
     */
    public void setMaxLifetimeOrderMessage(String maxLifetimeOrderMessage) {
        this.maxLifetimeOrderMessage = maxLifetimeOrderMessage;
    }

    /**
     * Returns the maxLifetimeOrderStatus value.
     *
     * @return the maxLifetimeOrderStatus
     */
    public String getMaxLifetimeOrderStatus() {
        return maxLifetimeOrderStatus;
    }

    /**
     * Sets the maxLifetimeOrderStatus value.
     *
     * @param maxLifetimeOrderStatus the maxLifetimeOrderStatus to set
     */
    public void setMaxLifetimeOrderStatus(String maxLifetimeOrderStatus) {
        this.maxLifetimeOrderStatus = maxLifetimeOrderStatus;
    }

    /**
     * Returns the maxLifetimeOrderStatusCode value.
     *
     * @return the maxLifetimeOrderStatusCode
     */
    public String getMaxLifetimeOrderStatusCode() {
        return maxLifetimeOrderStatusCode;
    }

    /**
     * Sets the maxLifetimeOrderStatusCode value.
     *
     * @param maxLifetimeOrderStatusCode the maxLifetimeOrderStatusCode to set
     */
    public void setMaxLifetimeOrderStatusCode(String maxLifetimeOrderStatusCode) {
        this.maxLifetimeOrderStatusCode = maxLifetimeOrderStatusCode;
    }

    /**
     * Returns the daily value.
     *
     * @return the daily
     */
    public CalculatedDoseVo getDaily() {
        return daily;
    }

    /**
     * Sets the daily value.
     *
     * @param daily the daily to set
     */
    public void setDaily(CalculatedDoseVo daily) {
        this.daily = daily;
    }

    /**
     * Returns the maxDaily value.
     *
     * @return the maxDaily
     */
    public CalculatedDoseVo getMaxDaily() {
        return maxDaily;
    }

    /**
     * Sets the maxDaily value.
     *
     * @param maxDaily the maxDaily to set
     */
    public void setMaxDaily(CalculatedDoseVo maxDaily) {
        this.maxDaily = maxDaily;
    }

    /**
     * Returns the rangeLow value.
     *
     * @return the rangeLow
     */
    public CalculatedDoseVo getRangeLow() {
        return rangeLow;
    }

    /**
     * Sets the rangeLow value.
     *
     * @param rangeLow the rangeLow to set
     */
    public void setRangeLow(CalculatedDoseVo rangeLow) {
        this.rangeLow = rangeLow;
    }

    /**
     * Returns the rangeHigh value.
     *
     * @return the rangeHigh
     */
    public CalculatedDoseVo getRangeHigh() {
        return rangeHigh;
    }

    /**
     * Sets the rangeHigh value.
     *
     * @param rangeHigh the rangeHigh to set
     */
    public void setRangeHigh(CalculatedDoseVo rangeHigh) {
        this.rangeHigh = rangeHigh;
    }

    /**
     * Returns the maxLifetime value.
     *
     * @return the maxLifetime
     */
    public CalculatedDoseVo getMaxLifetime() {
        return maxLifetime;
    }

    /**
     * Sets the maxLifetime value.
     *
     * @param maxLifetime the maxLifetime to set
     */
    public void setMaxLifetime(CalculatedDoseVo maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    /**
     * Returns the maxLifetimeOrder value.
     *
     * @return the maxLifetimeOrder
     */
    public CalculatedDoseVo getMaxLifetimeOrder() {
        return maxLifetimeOrder;
    }

    /**
     * Sets the maxLifetimeOrder value.
     *
     * @param maxLifetimeOrder the maxLifetimeOrder to set
     */
    public void setMaxLifetimeOrder(CalculatedDoseVo maxLifetimeOrder) {
        this.maxLifetimeOrder = maxLifetimeOrder;
    }

    /**
     * Returns the single value.
     *
     * @return the single
     */
    public CalculatedDoseVo getSingle() {
        return single;
    }

    /**
     * Sets the single value.
     *
     * @param single the single to set
     */
    public void setSingle(CalculatedDoseVo single) {
        this.single = single;
    }

}
