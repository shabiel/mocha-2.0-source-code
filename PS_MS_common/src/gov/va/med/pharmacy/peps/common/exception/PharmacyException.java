package gov.va.med.pharmacy.peps.common.exception;


import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


/**
 * Pharmacy checked exception base class. Intended for use where cause of exception is recoverable.
 */
public class PharmacyException extends Exception {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(PharmacyException.class);

    private MessageKey key;
    private Object[] arguments;

    /**
     * Create a new exception with a no-argument message.
     * 
     * @param key String key to the exception message
     */
    protected PharmacyException(MessageKey key) {
        this.key = key;
        this.arguments = new Object[] {};

        log();
    }

    /**
     * Create a new exception with a parameterized message.
     * 
     * @param key String key to the exception message
     * @param arguments Arguments to insert into the message
     */
    protected PharmacyException(MessageKey key, Object... arguments) {
        this.key = key;
        this.arguments = arguments;

        log();
    }

    /**
     * Create a new exception with a no-argument message.
     * 
     * @param cause Exception that caused this exception
     * @param key String key to the exception message
     */
    protected PharmacyException(Throwable cause, MessageKey key) {
        super(cause);

        this.key = key;
        this.arguments = new Object[] {};

        log();
    }

    /**
     * Create a new exception with a parameterized message.
     * 
     * @param cause Exception that caused this exception
     * @param key String key to the exception message
     * @param arguments Arguments to insert into the message
     */
    protected PharmacyException(Throwable cause, MessageKey key, Object... arguments) {
        super(cause);

        this.key = key;
        this.arguments = arguments;

        log();
    }

    /**
     * Return the message localized for the default locale. The localized message is derived from the ExceptionMessage
     * provided in the constructor.
     * 
     * @return String localized message
     * 
     * @see java.lang.Throwable#getLocalizedMessage()
     */
    public final String getLocalizedMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("properties/" + getClass().getName(), Locale.getDefault(), Thread
            .currentThread().getContextClassLoader());

        String message;

        try {
            message = bundle.getString(key.getKey());

            if (arguments.length > 0) {
                message = MessageFormat.format(message, arguments);
            }
        }
        catch (MissingResourceException e) {
            message = e.getLocalizedMessage();
        }

        return message;
    }

    /**
     * Return the message localized for the default locale. The localized message is derived from the ExceptionMessage
     * provided in the constructor.
     * 
     * @return String localized message
     * 
     * @see java.lang.Throwable#getMessage()
     */
    public final String getMessage() {
        return getLocalizedMessage();
    }

    /**
     * Log the message via Log4j.
     */
    protected final void log() {
        LOG.error("", this);
    }
}
