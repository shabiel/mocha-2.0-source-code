package gov.va.med.pharmacy.peps.external.common.preencapsulation.session.impl;

import gov.va.med.pharmacy.peps.external.common.preencapsulation.capability.ProcessDosingInfoCapability;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.session.DosingInfoService;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.dosinginfo.DosingInfoXmlUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Retrieves the Dose Range information for the given GCN Sequence number(s).
 * 
 * @author zzzzzzduffg
 * 
 */
public class DosingInfoServiceImpl implements DosingInfoService, InitializingBean {

    private static final Log LOGGER = LogFactory.getLog(DosingInfoServiceImpl.class);

    private ProcessDosingInfoCapability capability;

    @Override
    public final String retrieveDosingInformation(String xmlRequest) {
        try {
            return capability.handleRequest(xmlRequest);
        } catch (Throwable t) {
            String xmlError = DosingInfoXmlUtility.createXmlErrorMessage(t);
            LOGGER.error("Error message returned to caller:");
            LOGGER.error(DosingInfoXmlUtility.prettyPrintException(xmlError));

            return xmlError;
        }
    }

    /**
     * Sets the ProcessDosingInfoCapability to use.
     * 
     * @param processDosingInfoCapability the ProcessDosingInfoCapability to use.
     */
    public final void setProcessDosingInfoCapability(ProcessDosingInfoCapability processDosingInfoCapability) {
        this.capability = processDosingInfoCapability;
    }

    /**
     * Called by Spring after the bean is initialized so it can checks it's configuration.
     * 
     * @throws Exception if an object has not been injected.
     */
    public final void afterPropertiesSet() throws Exception {
        if (capability == null) {
            throw new Exception("ProcessDosingInfoCapability has not been injected in to DosingInfoServiceImpl.");
        }
    }
}
