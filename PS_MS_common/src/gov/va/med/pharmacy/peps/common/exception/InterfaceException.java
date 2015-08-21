package gov.va.med.pharmacy.peps.common.exception;


/**
 * Unchecked exception thrown when exceptions from third party tool encountered.
 */
public class InterfaceException extends PharmacyRuntimeException {

    // Message keys
    /**
     * The message key if an interface is not available.
     */
    public static final MessageKey INTERFACE_UNAVAILABLE = new MessageKey("INTERFACE_UNAVAILABLE");
    /**
     * The message key if an interface error was encountered.
     */
    public static final MessageKey INTERFACE_ERROR = new MessageKey("INTERFACE_ERROR");

    // Interface names used as arguments to the messages
    /**
     * A static string.
     */
    public static final String DRUG_DATA_VENDOR = "Drug Data Vendor";
    /**
     * A static string.
     */
    public static final String PRE_ENCAPSULATION = "PRE Encapsulation";
    /**
     * A static string.
     */
    public static final String MESSAGING_SERVICE = "Messaging Service";

    private static final long serialVersionUID = 1L;

    /**
     * Create a new exception with a no-argument message.
     * 
     * @param key String key to the exception message
     */
    public InterfaceException(MessageKey key) {
        super(key);
    }

    /**
     * Create a new exception with a parameterized message.
     * 
     * @param key String key to the exception message
     * @param arguments Arguments to insert into the message
     */
    public InterfaceException(MessageKey key, Object... arguments) {
        super(key, arguments);
    }

    /**
     * Create a new exception with a parameterized message.
     * 
     * @param e Exception that caused this exception
     * @param key String key to the exception message
     * @param arguments Arguments to insert into the message
     */
    public InterfaceException(Throwable e, MessageKey key, Object... arguments) {
        super(e, key, arguments);
    }

    /**
     * Create a new exception with a no-argument message.
     * 
     * @param e Exception that caused this exception
     * @param key String key to the exception message
     */
    public InterfaceException(Throwable e, MessageKey key) {
        super(e, key);
    }
}
