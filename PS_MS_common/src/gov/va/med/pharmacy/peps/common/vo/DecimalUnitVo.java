package gov.va.med.pharmacy.peps.common.vo;

/**
 * POJO to hold a Decimal Value and it's associated Dose Unit.
 * 
 * @author zzzzzzduffg
 *
 */
public class DecimalUnitVo extends ValueObject {

    private static final long serialVersionUID = 1L;

    private Double value;
    private String unit;

    /**
     * Constructor.
     */
    public DecimalUnitVo() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param value the Decimal value
     * @param unit the dose unit type
     */
    public DecimalUnitVo(Double value, String unit) {
        super();
        this.value = value;
        this.unit = unit;
    }

    /**
     * Returns the value.
     * 
     * @return Double
     */
    public final Double getValue() {
        return value;
    }

    /**
     * Sets the value.
     * 
     * @param value the value
     */
    public final void setValue(Double value) {
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
     * @param unit the Dose Unit Type
     */
    public final void setUnit(String unit) {
        this.unit = unit;
    }
}
