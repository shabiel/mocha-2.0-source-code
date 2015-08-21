package gov.va.med.pharmacy.peps.common.vo;


/**
 * Data representing a dose route for a drug.
 */
public class DoseRouteVo extends ValueObject {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    /**
     * Returns the Identifier.
     * 
     * @return id property
     */
    public final String getId() {
        return id;
    }

    /**
     * Sets the Identifier.
     * 
     * @param id id property
     */
    public final void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the Name.
     * 
     * @return name property
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the Name.
     * 
     * @param name name property
     */
    public final void setName(String name) {
        this.name = name;
    }
}
