package gov.va.med.pharmacy.peps.external.common.preencapsulation.servlet;

import javax.ejb.EJB;

import gov.va.med.pharmacy.peps.common.servlet.AbstractPepsServlet;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.session.DosingInfoService;

/**
 * Servlet used to retrieve DosingInfo.
 * 
 * @author zzzzzzduffg
 * 
 */
public class DosingInfoServlet extends AbstractPepsServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private DosingInfoService service;

    /**
     * Retrieves the DosingInfo information in XML format.
     * 
     * @param xmlRequest
     *            the XML request string.
     * @return String the XML response.
     */
    @Override
    protected final String getResponse(String xmlRequest) {
        return service.retrieveDosingInformation(xmlRequest);
    }

}
