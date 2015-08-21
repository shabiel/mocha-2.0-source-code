package gov.va.med.pharmacy.peps.common.vo;


/**
 * Messages returned by a drug check.
 */
public class DrugCheckMessageVo extends ValueObject {
    private static final long serialVersionUID = 1L;
    private DrugCheckVo drug;
    private String severity = "";
    private String type = "";
    private String drugName = "";
    private String text = "";

    /**
     * Constructor.
     */
    public DrugCheckMessageVo() {
        super();
    }

    /**
     * Returns the Drug.
     * 
     * @return DrugCheckVo
     */
    public final DrugCheckVo getDrug() {
        return drug;
    }

    /**
     * Sets the Drug.
     * 
     * @param drug DrugCheckVo
     */
    public final void setDrug(DrugCheckVo drug) {
        this.drug = drug;
    }

    /**
     * Returns the Severity.
     * 
     * @return String
     */
    public final String getSeverity() {
        return severity;
    }

    /**
     * Sets the Severity.
     * 
     * @param severity String
     */
    public final void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * Returns the Type.
     * 
     * @return String
     */
    public final String getType() {
        return type;
    }

    /**
     * Sets the Type.
     * 
     * @param type String
     */
    public final void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the Drug Name.
     * 
     * @return String
     */
    public final String getDrugName() {
        return drugName;
    }

    /**
     * Sets the Drug Name.
     * 
     * @param name String
     */
    public final void setDrugName(String name) {
        this.drugName = name;
    }

    /**
     * Returns the Text.
     * 
     * @return String
     */
    public final String getText() {
        return text;
    }

    /**
     * Sets the Text.
     * 
     * @param text String
     */
    public final void setText(String text) {
        this.text = text;
    }
}
