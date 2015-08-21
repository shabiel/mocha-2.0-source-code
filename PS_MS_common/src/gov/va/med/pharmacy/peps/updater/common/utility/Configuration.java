package gov.va.med.pharmacy.peps.updater.common.utility;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Site configuration.
 */
public final class Configuration {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Configuration.class);

    // Configuration file location system property
    private static final String CONFIG_LOCATION_PROPERTY = "peps.datup.configuration";

    /**
     * The scheduled time.
     */
    public static final String SCHEDULED_TIME = "scheduled.time";

    // FTP
    /**
     * The FTP host name.
     */
    public static final String FTP_HOSTNAME = "ftp.hostname";
    /**
     * The FTP port.
     */
    public static final String FTP_PORT = "ftp.port";
    /**
     * The FTP user name.
     */
    public static final String FTP_USERNAME = "ftp.username";
    /**
     * The FTP password.
     */
    public static final String FTP_PASSWORD = "ftp.password";
    /**
     * The FTP working directory.
     */
    public static final String FTP_WORKING_DIRECTORY = "ftp.directory.working";
    /**
     * The FTP pending directory.
     */
    public static final String FTP_PENDING_DIRECTORY = "ftp.directory.pending";

    // FDB
    /**
     * The retention period.
     */
    public static final String FDB_RETENTON = "fdb.retention";
    /**
     * The test count.
     */
    public static final String FDB_TEST_COUNT = "fdb.verification.test.count";
    /**
     * The batch commit size.
     */
    public static final String FDB_BATCH_COMMIT_SIZE = "fdb.batch.commit.size";

    // Email
    /**
     * The email host name.
     */
    public static final String EMAIL_HOSTNAME = "email.hostname";
    /**
     * The email sender.
     */
    public static final String EMAIL_SENDER = "email.sender";
    /**
     * The email user name.
     */
    public static final String EMAIL_USERNAME = "email.username";
    /**
     * The email password.
     */
    public static final String EMAIL_PASSWORD = "email.password";
    /**
     * The email success list.
     */
    public static final String EMAIL_LIST_SUCCESS = "email.list.success";
    /**
     * The email failure list.
     */
    public static final String EMAIL_LIST_FAILURE = "email.list.failure";
    /**
     * The email update available list.
     */
    public static final String EMAIL_LIST_UPDATE_AVAILABLE = "email.list.update.available";
    /**
     * The email success template.
     */
    public static final String EMAIL_TEMPLATE_SUCCESS = "email.template.success";
    /**
     * The email failure template.
     */
    public static final String EMAIL_TEMPLATE_FAILURE = "email.template.failure";
    /**
     * The email update available template.
     */
    public static final String EMAIL_TEMPLATE_UPDATE_AVAILABLE = "email.template.update.available";
    
    // Locality
    /**
     * The RDC name.
     */
    public static final String RDC_NAME = "locality.rdc.name";
    /**
     * The RDC site number.
     */
    public static final String SITE_NUMBER = "locality.site.number";

        
    private String filename;
    private PropertiesConfiguration config;
    private boolean isLoaded;

    /**
     * Default constructor.
     */
    private Configuration() {
        this.config = new PropertiesConfiguration();
    }

    /**
     * Load the current system configuration file. The file is automatically reloaded if changed.
     * 
     * @return configuration
     */
    public synchronized org.apache.commons.configuration.Configuration load() {
        if (!isLoaded) {
            try {
                config.load(System.getProperty(CONFIG_LOCATION_PROPERTY, filename));
                config.setReloadingStrategy(new FileChangedReloadingStrategy());

                isLoaded = true;
            }
            catch (ConfigurationException e) {
                LOG.error(System.getProperty(CONFIG_LOCATION_PROPERTY, filename));
                LOG.error("Unable to locate the DATUP configuration file in the (1) current directory or (2) classpath.",
                    e);
        }
        }
        return config;
    }

    /**
     * Set filename.
     * 
     * @param filename the name of the file.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Pretty representation.
     * 
     * @return keys/values
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("file", System.getProperty(CONFIG_LOCATION_PROPERTY, filename)).append("loaded", isLoaded);

        for (@SuppressWarnings("unchecked")
        Iterator<String> i = config.getKeys(); i.hasNext();) {
            String key = i.next();

            if (SITE_NUMBER.equals(key)) {
                builder.append(key, Arrays.toString(config.getStringArray(key)));
            }
            else {
                builder.append(key, config.getString(key));
            }
        }

        return builder.toString();
    }

}
