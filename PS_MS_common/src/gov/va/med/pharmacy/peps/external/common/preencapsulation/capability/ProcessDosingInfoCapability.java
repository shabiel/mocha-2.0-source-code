
package gov.va.med.pharmacy.peps.external.common.preencapsulation.capability;

/**
 * Interface to define the processing of Dosing Information.
 * 
 * @author zzzzzzduffg
 *
 */
public interface ProcessDosingInfoCapability {

    /**
     * Handle the XML request.
     * 
     * @param request request XML from VistA
     * @return response XML
     */
    String handleRequest(String request);
}
