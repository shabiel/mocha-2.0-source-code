package gov.va.med.pharmacy.peps.common.vo;

/**
 * A POJO to hold an Integer Value and an associated Dose Unit Type.
 * 
 * @author zzzzzzduffg
 *
 */
public class IntegerUnitVo extends ValueObject {

    private static final long serialVersionUID = 1L;

    private Integer value;
    private String unit;

    /**
     * Constructor.
     */
    public IntegerUnitVo() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param value Integer value
     * @param unit Dose Unit Type
     */
    public IntegerUnitVo(Integer value, String unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    /**
     * Returns the Value.
     * 
     * @return Integer
     */
    public final Integer getValue() {
        return value;
    }

    /**
     * Sets the Value.
     * 
     * @param value Integer
     */
    public final void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Returns the Dose Unit Type.
     * 
     * @return String
     */
    public final String getUnit() {
        return unit;
    }

    /**
     * Sets the Dose Unit Type.
     * 
     * @param unit Dose Unit Type.
     */
    public final void setUnit(String unit) {
        this.unit = unit;
    }

}
