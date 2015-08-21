package gov.va.med.pharmacy.peps.external.common.preencapsulation.servlet;


import javax.ejb.EJB;

import gov.va.med.pharmacy.peps.common.servlet.AbstractPepsServlet;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.session.OrderCheckService;


/**
 * Handle the order check request from VistA.
 */
public class OrderCheckServlet extends AbstractPepsServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private OrderCheckService orderCheckService;

    /**
     * Handle the XML Request from VistA.
     * 
     * @param xmlRequest String request from VistA
     * @return String response for VistA
     */
    protected final String getResponse(String xmlRequest) {
        return orderCheckService.performOrderCheck(xmlRequest);
    }
}
