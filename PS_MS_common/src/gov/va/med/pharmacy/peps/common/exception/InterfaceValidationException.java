package gov.va.med.pharmacy.peps.common.exception;


/**
 * Exception thrown when value/data objects used by interfaces are invalid.
 */
public class InterfaceValidationException extends InterfaceException {
    /**
     * The message key for a validation error.
     */
    public static final MessageKey VALIDATION_ERROR = new MessageKey("VALIDATION_ERROR");
    /**
     * The message key for drugs to screen required.
     */
    public static final MessageKey DRUGS_TO_SCREEN_REQUIRED = new MessageKey("DRUGS_TO_SCREEN_REQUIRED");
    /**
     * The message key for order checks required.
     */
    public static final MessageKey ORDER_CHECK_REQUERED = new MessageKey("ORDER_CHECK_REQUERED");
    /**
     * The message key for prospective drugs required.
     */
    public static final MessageKey PROSPECTIVE_DRUGS_REQUIRED = new MessageKey("PROSPECTIVE_DRUGS_REQUIRED");
    /**
     * The message key for XML request required.
     */
    public static final MessageKey XML_REQUEST_REQUIRED = new MessageKey("XML_REQUEST_REQUIRED");
    /** 
     * The message key for FDB Verification.
     */
    public static final MessageKey FDB_VERIFICATION = new MessageKey("FDB_VERIFICATION");

    private static final long serialVersionUID = 1L;

    /**
     * Create a new exception with a no-argument message.
     * 
     * @param key String key to the exception message
     */
    public InterfaceValidationException(MessageKey key) {
        super(key);
    }

    /**
     * Create a new exception with a parameterized message.
     * 
     * @param key String key to the exception message
     * @param arguments Arguments to insert into the message
     */
    public InterfaceValidationException(MessageKey key, Object... arguments) {
        super(key, arguments);
    }

    /**
     * Create a new exception with a parameterized message.
     * 
     * @param e Exception that caused this exception
     * @param key String key to the exception message
     * @param arguments Arguments to insert into the message
     */
    public InterfaceValidationException(Throwable e, MessageKey key, Object... arguments) {
        super(e, key, arguments);
    }

    /**
     * Create a new exception with a no-argument message.
     * 
     * @param e Exception that caused this exception
     * @param key String key to the exception message
     */
    public InterfaceValidationException(Throwable e, MessageKey key) {
        super(e, key);
    }
}
