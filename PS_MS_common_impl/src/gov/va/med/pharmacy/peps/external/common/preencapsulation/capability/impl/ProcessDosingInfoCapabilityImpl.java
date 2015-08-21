package gov.va.med.pharmacy.peps.external.common.preencapsulation.capability.impl;

import gov.va.med.pharmacy.peps.common.exception.InterfaceValidationException;
import gov.va.med.pharmacy.peps.common.vo.DosingInfoResultsVo;
import gov.va.med.pharmacy.peps.common.vo.DosingInfoVo;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.DosingInfoCapability;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.capability.ProcessDosingInfoCapability;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.dosinginfo.DosingInfoXmlUtility;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.druginfo.DrugInfoXmlUtility;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Process Dosing Information Capability implementation.
 * 
 * @author zzzzzzduffg
 *
 */
public class ProcessDosingInfoCapabilityImpl implements InitializingBean, ProcessDosingInfoCapability {

    private static final Log LOGGER = LogFactory.getLog(ProcessDosingInfoCapabilityImpl.class);

    private DosingInfoCapability dosingCapability;

    /**
     * The method to process the request.
     * 
     * @param xmlRequest the dosing info request.
     * @return String
     */
    @Override
    public final String handleRequest(String xmlRequest) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Request received from VistA:");
            LOGGER.debug(xmlRequest);
        }

        if (xmlRequest == null) {
            throw new InterfaceValidationException(InterfaceValidationException.XML_REQUEST_REQUIRED);
        }

        Collection<DosingInfoVo> drugs = DosingInfoXmlUtility.toDosingInfoVo(xmlRequest);

        DosingInfoResultsVo results = dosingCapability.processDosingInfoRequest(drugs);

        String xmlResponse = DosingInfoXmlUtility.toDosingInfoResponse(results);

        // Prevent the XmlUtility from formatting the XML response with indents
        // and the like if DEBUG is not turned on.
        // If this is not done, the expense of formatting will always be done.
        // JAXB documentation recommends against
        // formatting in production code.
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Response sent back to VistA:");
            LOGGER.debug(DrugInfoXmlUtility.prettyPrintResponse(xmlResponse));
        }

        return xmlResponse;
    }

    /**
     * Sets the Dosing Info Capability.
     * 
     * @param dosingInfoCapability the DosingInfoCapability
     */
    public final void setDosingInfoCapability(DosingInfoCapability dosingInfoCapability) {
        this.dosingCapability = dosingInfoCapability;
    }

    /**
     * Allows the object to check to see if the Spring configuration was successful.
     * 
     * @throws Exception if an item was not injected.
     */
    public final void afterPropertiesSet() throws Exception {
        if (dosingCapability == null) {
            throw new Exception("DosingInfoCapability has not been injected in to ProcessDosingInfoCapabilityImpl.");
        }
    }
}
