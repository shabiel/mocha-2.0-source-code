package gov.va.med.pharmacy.peps.common.vo;

/**
 * Class to hold the CalculateDose information from a DRCLookupResult or NeoDRCLookupResult.
 * 
 * @author zzzzzzduffg
 *
 */
public final class CalculatedDoseVo extends ValueObject {

    private static final long serialVersionUID = 1633076460383974914L;
    
    private String databaseValue;
    private String doseValue;
    private String percentError;
    private String unitOfMeasure;
    
    /**
     * Returns the databaseValue value.
     *
     * @return the databaseValue
     */
    public String getDatabaseValue() {
        return databaseValue;
    }
    /**
     * Sets the databaseValue value.
     *
     * @param databaseValue the databaseValue to set
     */
    public void setDatabaseValue(String databaseValue) {
        this.databaseValue = databaseValue;
    }
    /**
     * Returns the doseValue value.
     *
     * @return the doseValue
     */
    public String getDoseValue() {
        return doseValue;
    }
    /**
     * Sets the doseValue value.
     *
     * @param doseValue the doseValue to set
     */
    public void setDoseValue(String doseValue) {
        this.doseValue = doseValue;
    }
    /**
     * Returns the percentError value.
     *
     * @return the percentError
     */
    public String getPercentError() {
        return percentError;
    }
    /**
     * Sets the percentError value.
     *
     * @param percentError the percentError to set
     */
    public void setPercentError(String percentError) {
        this.percentError = percentError;
    }
    /**
     * Returns the unitOfMeasure value.
     *
     * @return the unitOfMeasure
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }
    /**
     * Sets the unitOfMeasure value.
     *
     * @param unitOfMeasure the unitOfMeasure to set
     */
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
    
}
