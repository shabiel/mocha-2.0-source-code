package gov.va.med.pharmacy.peps.common.exception;


/**
 * Unchecked exception thrown when exceptions from the shared common code.
 */
public class CommonException extends PharmacyRuntimeException {
    /**
     * The message key if the property file is not available.
     */
    public static final MessageKey PROPERTY_FILE_UNAVAILABLE = new MessageKey("PROPERTY_FILE_UNAVAILABLE");
    /**
     * The message key if a resource is not available.
     */
    public static final MessageKey RESOURCE_UNAVAILABLE = new MessageKey("RESOURCE_UNAVAILABLE");
    /**
     * The message key if a configuration file is not available.
     */
    public static final MessageKey CONFIGURATION_FILE_UNAVAILABLE = new MessageKey("CONFIGURATION_FILE_UNAVAILABLE");

    private static final long serialVersionUID = 1L;

    /**
     * Create a new exception with a no-argument message.
     * 
     * @param key String key to the exception message
     */
    public CommonException(MessageKey key) {
        super(key);
    }

    /**
     * Create a new exception with a parameterized message.
     * 
     * @param key String key to the exception message
     * @param arguments Arguments to insert into the message
     */
    public CommonException(MessageKey key, Object... arguments) {
        super(key, arguments);
    }

    /**
     * Create a new exception with a parameterized message.
     * 
     * @param e Exception that caused this exception
     * @param key String key to the exception message
     * @param arguments Arguments to insert into the message
     */
    public CommonException(Throwable e, MessageKey key, Object... arguments) {
        super(e, key, arguments);
    }

    /**
     * Create a new exception with a no-argument message.
     * 
     * @param e Exception that caused this exception
     * @param key String key to the exception message
     */
    public CommonException(Throwable e, MessageKey key) {
        super(key, e);
    }
}
