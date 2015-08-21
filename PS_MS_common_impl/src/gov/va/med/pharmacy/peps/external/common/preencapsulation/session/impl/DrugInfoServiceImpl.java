package gov.va.med.pharmacy.peps.external.common.preencapsulation.session.impl;


import org.apache.log4j.Logger;

import gov.va.med.pharmacy.peps.external.common.preencapsulation.capability.ProcessDrugInfoCapability;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.session.DrugInfoService;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.druginfo.DrugInfoXmlUtility;


/**
 * Retrieve the dose routes and dose types for a given list of GCN sequence numbers.
 */
public class DrugInfoServiceImpl implements DrugInfoService {
    private static final Logger LOG = Logger.getLogger(DrugInfoServiceImpl.class);
    private ProcessDrugInfoCapability processDrugInfoCapability;

    /**
     * Retrieve the dose routes and dose types for the given XML request.
     * 
     * @param request request XML from VistA
     * @return response XML
     */
    public final String retrieveDrugInformation(String request) {
        try {
            return processDrugInfoCapability.handleRequest(request);
        }
        catch (Throwable t) {
            String xmlError = DrugInfoXmlUtility.createXmlErrorMessage(t);
            LOG.error("Error message sent to VistA:");
            LOG.error(DrugInfoXmlUtility.prettyPrintException(xmlError));

            return xmlError;
        }
    }

    /**
     * Sets the ProcessDrugInfoCapability to use.
     * 
     * @param processDrugInfoCapability processDrugInfoCapability property
     */
    public final void setProcessDrugInfoCapability(ProcessDrugInfoCapability processDrugInfoCapability) {
        this.processDrugInfoCapability = processDrugInfoCapability;
    }
}
