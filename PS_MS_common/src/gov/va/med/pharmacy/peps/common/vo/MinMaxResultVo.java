package gov.va.med.pharmacy.peps.common.vo;

import firstdatabank.dif.FDBMinMaxType;

/**
 * A POJO to hold MinMax Dosing Results.
 * 
 * @author zzzzzzduffg
 * 
 */
public class MinMaxResultVo extends ValueObject {

    private static final long serialVersionUID = 1L;

    private Long ageInDaysHigh;
    private Long ageInDaysLow;
    private Boolean bsaRequired;
    private Double doseFormHigh;
    private String doseFormHighUnit;
    private Double doseFormLow;
    private String doseFormLowUnit;
    private Double doseHigh;
    private String doseHighUnit;
    private Double doseLow;
    private String doseLowUnit;
    private Double maxDailyDose;
    private Double maxDailyDoseForm;
    private String maxDailyDoseFormUnit;
    private String maxDailyDoseUnit;
    private FDBMinMaxType resultType;
    private Integer warningCode;
    private String warningMessage;
    private Boolean weightRequired;

    public Long getAgeInDaysHigh() {
        return ageInDaysHigh;
    }

    public void setAgeInDaysHigh(Long ageInDaysHigh) {
        this.ageInDaysHigh = ageInDaysHigh;
    }

    public Long getAgeInDaysLow() {
        return ageInDaysLow;
    }

    public void setAgeInDaysLow(Long ageInDaysLow) {
        this.ageInDaysLow = ageInDaysLow;
    }

    public Boolean getBsaRequired() {
        return bsaRequired;
    }

    public void setBsaRequired(Boolean bsaRequired) {
        this.bsaRequired = bsaRequired;
    }

    public Double getDoseFormHigh() {
        return doseFormHigh;
    }

    public void setDoseFormHigh(Double doseFormHigh) {
        this.doseFormHigh = doseFormHigh;
    }

    public String getDoseFormHighUnit() {
        return doseFormHighUnit;
    }

    public void setDoseFormHighUnit(String doseFormHighUnit) {
        this.doseFormHighUnit = doseFormHighUnit;
    }

    public Double getDoseFormLow() {
        return doseFormLow;
    }

    public void setDoseFormLow(Double doseFormLow) {
        this.doseFormLow = doseFormLow;
    }

    public String getDoseFormLowUnit() {
        return doseFormLowUnit;
    }

    public void setDoseFormLowUnit(String doseFormLowUnit) {
        this.doseFormLowUnit = doseFormLowUnit;
    }

    public Double getDoseHigh() {
        return doseHigh;
    }

    public void setDoseHigh(Double doseHigh) {
        this.doseHigh = doseHigh;
    }

    public String getDoseHighUnit() {
        return doseHighUnit;
    }

    public void setDoseHighUnit(String doseHighUnit) {
        this.doseHighUnit = doseHighUnit;
    }

    public Double getDoseLow() {
        return doseLow;
    }

    public void setDoseLow(Double doseLow) {
        this.doseLow = doseLow;
    }

    public String getDoseLowUnit() {
        return doseLowUnit;
    }

    public void setDoseLowUnit(String doseLowUnit) {
        this.doseLowUnit = doseLowUnit;
    }

    public Double getMaxDailyDose() {
        return maxDailyDose;
    }

    public void setMaxDailyDose(Double maxDailyDose) {
        this.maxDailyDose = maxDailyDose;
    }

    public Double getMaxDailyDoseForm() {
        return maxDailyDoseForm;
    }

    public void setMaxDailyDoseForm(Double maxDailyDoseForm) {
        this.maxDailyDoseForm = maxDailyDoseForm;
    }

    public String getMaxDailyDoseFormUnit() {
        return maxDailyDoseFormUnit;
    }

    public void setMaxDailyDoseFormUnit(String maxDailyDoseFormUnit) {
        this.maxDailyDoseFormUnit = maxDailyDoseFormUnit;
    }

    public String getMaxDailyDoseUnit() {
        return maxDailyDoseUnit;
    }

    public void setMaxDailyDoseUnit(String maxDailyDoseUnit) {
        this.maxDailyDoseUnit = maxDailyDoseUnit;
    }

    public FDBMinMaxType getResultType() {
        return resultType;
    }

    public void setResultType(FDBMinMaxType resultType) {
        this.resultType = resultType;
    }

    public Integer getWarningCode() {
        return warningCode;
    }

    public void setWarningCode(Integer warningCode) {
        this.warningCode = warningCode;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public Boolean getWeightRequired() {
        return weightRequired;
    }

    public void setWeightRequired(Boolean weightRequired) {
        this.weightRequired = weightRequired;
    }

}
