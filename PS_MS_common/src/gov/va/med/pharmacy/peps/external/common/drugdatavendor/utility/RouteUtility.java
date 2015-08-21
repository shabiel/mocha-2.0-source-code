package gov.va.med.pharmacy.peps.external.common.drugdatavendor.utility;


import gov.va.med.pharmacy.peps.common.utility.PropertyUtility;


/**
 * Convert an FDB route name text value to the FDB route ID.
 */
public final class RouteUtility {

    /**
     * Convert the route name to the ID.
     * 
     * @param name name of the route
     * @return FDB ID of the route
     */
    public static String routeNameToId(String name) {
        String value = PropertyUtility.getProperty(RouteUtility.class, name);

        return StringUtility.nullToEmptyString(value);
    }

    /**
     * Cannot instantiate
     */
    private RouteUtility() {
        super();
    }
}
