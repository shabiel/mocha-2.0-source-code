package gov.va.med.pharmacy.peps.external.common.preencapsulation.session.impl;


import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.jndi.JndiTemplate;

import gov.va.med.pharmacy.peps.common.exception.FDBUpdateInProgressException;
import gov.va.med.pharmacy.peps.common.exception.MessageKey;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.capability.ProcessOrderChecksCapability;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.session.OrderCheckService;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.drugcheck.DrugCheckXmlUtility;


/**
 * Perform an order check requested by VistA using XML.
 */
public class OrderCheckServiceImpl implements OrderCheckService {
    private static final Logger LOG = Logger.getLogger(OrderCheckServiceImpl.class);
    private static int RUNCOUNTER;
    private Long lastSynchonizationCheck = System.currentTimeMillis();
    private boolean updateInProgress;
    private static final int MILLISECONDS = 1000;
    private static final int NUMBER_OF_SECONDS_TO_SLEEP = 15;

    private ProcessOrderChecksCapability processOrderChecksCapability;
    private JndiTemplate jndiTemplate;

    /**
     * Atomic technique to check if an order check is active.
     * 
     * @return true if active, false otherwise
     */
    public static boolean isRunning() {
        return (RUNCOUNTER > 0);
    }

    /**
     * Perform and order check for the given XML request.
     * 
     * @param request request XML from VistA
     * @return response XML
     */
    public final String performOrderCheck(String request) {
        try {
            // check DIF update status every 15 seconds
            if ((System.currentTimeMillis() - lastSynchonizationCheck) >= (MILLISECONDS * NUMBER_OF_SECONDS_TO_SLEEP)) {
                try {
                    updateInProgress = Boolean.TRUE.equals(jndiTemplate.lookup("DATUP_UPDATE_IN_PROGRESS"));
                }
                catch (NamingException e) {
                    LOG.debug("DATUP does not appear to be installed. Update synchronization check will not be performed.",
                        e);
                }

                lastSynchonizationCheck = System.currentTimeMillis();
            }

            // stop if DIF update is in progress
            if (updateInProgress) {
                throw new FDBUpdateInProgressException(new MessageKey("UPDATE_IN_PROGRESS"));
            }

            try {
                RUNCOUNTER++; // obtain order check lock

                return processOrderChecksCapability.handleRequest(request);
            }
            finally {
                RUNCOUNTER--; // re;ease order check lock
            }
        }
        catch (Throwable t) {
            String xmlError = DrugCheckXmlUtility.createXmlErrorMessage(t);
            LOG.error("Error message sent to VistA:");
            LOG.error(DrugCheckXmlUtility.prettyPrintException(xmlError));

            return xmlError;
        }
    }

    /**
     * Sets the ProcessOrderChecksCapability to use.
     * 
     * @param processOrderChecksCapability processOrderChecksCapability property
     */
    public final void setProcessOrderChecksCapability(ProcessOrderChecksCapability processOrderChecksCapability) {
        this.processOrderChecksCapability = processOrderChecksCapability;
    }

    /**
     * Returns a boolean if any updates are currently running.
     * 
     * @return boolean
     * 
     * @see gov.va.med.pharmacy.peps.external.common.preencapsulation.session.OrderCheckService#updatesRunning()
     */
    public final boolean updatesRunning() {
        return RUNCOUNTER == 0;
    }

    /**
     * Sets the JndiTemplate to use.
     * 
     * @param jndiTemplate jndiTemplate property
     */
    public final void setJndiTemplate(JndiTemplate jndiTemplate) {
        this.jndiTemplate = jndiTemplate;
    }

    /**
     * Retrieves the JndiTemplate being used.
     * 
     * @return jndiTemplate property
     */
    public final JndiTemplate getJndiTemplate() {
        return jndiTemplate;
    }
}
