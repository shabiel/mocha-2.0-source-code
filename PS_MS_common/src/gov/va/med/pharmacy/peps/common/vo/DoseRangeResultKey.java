package gov.va.med.pharmacy.peps.common.vo;

/**
 * This class holds the attributes of the DoseRangeResultVo object that uniquely
 * identifies a Dose Range object. This is to filter out FDB Dose Ranges that
 * have been customized.
 * 
 * @author zzzzzzduffg
 * 
 */
public class DoseRangeResultKey {
    private Long ageHighInDays;
    private Long ageLowInDays;
    private String doseRouteId;
    private String doseTypeId;
    private String indicationId;

    /**
     * Constructor.
     */
    public DoseRangeResultKey() {

    }

    /**
     * Constructor.
     * 
     * @param vo DoseRangeResultVo
     */
    public DoseRangeResultKey(DoseRangeResultVo vo) {
        this.ageHighInDays = vo.getAgeHighInDays();
        this.ageLowInDays = vo.getAgeLowInDays();
        this.doseRouteId = vo.getDoseRouteId();
        this.doseTypeId = vo.getDoseTypeId();
        this.indicationId = vo.getIndicationId();
    }

    /**
     * Returns the Age High In Days.
     * 
     * @return Long
     */
    public final Long getAgeHighInDays() {
        return ageHighInDays;
    }

    /**
     * Sets the Age High In Days.
     * 
     * @param ageHighInDays Age High In Days
     */
    public final void setAgeHighInDays(Long ageHighInDays) {
        this.ageHighInDays = ageHighInDays;
    }

    /**
     * Returns the Age Low In Days.
     * 
     * @return Long
     */
    public final Long getAgeLowInDays() {
        return ageLowInDays;
    }

    /**
     * Sets the Age Low In Days.
     * 
     * @param ageLowInDays Age Low In Days
     */
    public final void setAgeLowInDays(Long ageLowInDays) {
        this.ageLowInDays = ageLowInDays;
    }

    /**
     * Returns the Dose Route Identifier.
     * 
     * @return String
     */
    public final String getDoseRouteId() {
        return doseRouteId;
    }

    /**
     * Sets the Dose Route Identifier.
     * 
     * @param doseRouteId Dose Route Identifier
     */
    public final void setDoseRouteId(String doseRouteId) {
        this.doseRouteId = doseRouteId;
    }

    /**
     * Returns the Dose Type Identifier.
     * 
     * @return String
     */
    public final String getDoseTypeId() {
        return doseTypeId;
    }

    /**
     * Sets the Dose Type Identifier.
     * 
     * @param doseTypeId Dose Type Identifier.
     */
    public final void setDoseTypeId(String doseTypeId) {
        this.doseTypeId = doseTypeId;
    }

    /**
     * Returns the Indication Identifier.
     * 
     * @return String
     */
    public final String getIndicationId() {
        return indicationId;
    }

    /**
     * Sets the Indication Identifier.
     * 
     * @param indicationId Indication Identifier
     */
    public final void setIndicationId(String indicationId) {
        this.indicationId = indicationId;
    }

    /**
     * Calculates a hash code for this object.
     * 
     * @return int the calculated has code
     */
    @Override
    public final int hashCode() {
        return ageHighInDays.hashCode() + ageLowInDays.hashCode() + doseRouteId.hashCode() + doseTypeId.hashCode()
                + indicationId.hashCode();
    }

    /**
     * Checks to see if two objects are equal.
     * 
     * @param o the object to check
     * @return boolean
     */
    public final boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof DoseRangeResultKey) {
            DoseRangeResultKey o2 = (DoseRangeResultKey) o;

            if (ageHighInDays.equals(o2.getAgeHighInDays()) && ageLowInDays.equals(o2.getAgeLowInDays())
                    && doseRouteId.equals(o2.getDoseRouteId()) && doseTypeId.equals(o2.getDoseTypeId())
                    && indicationId.equals(o2.indicationId)) {
                equal = true;
            }

        }
        return equal;
    }
}
