package gov.va.med.pharmacy.peps.external.common.preencapsulation.session.bean;


import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.transaction.annotation.Transactional;

import gov.va.med.pharmacy.peps.external.common.preencapsulation.session.OrderCheckService;


/**
 * Perform an order check requested by VistA using XML.
 */
@Stateless
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OrderCheckServiceBean implements OrderCheckService {

    @Autowired
    private OrderCheckService orderCheckService;

    /**
     * Perform and order check for the given XML request.
     * 
     * NOTE: According to Checkstyle and the VA Coding Standards, this method should
     * be marked as "abstract, final or empty".  However, you will receive a 
     * ComplianceException from WebLogic if this is done.
     *   
     * @param request request XML from VistA
     * @return response XML
     */
    @Transactional(readOnly = true)
    public String performOrderCheck(String request) {
        return orderCheckService.performOrderCheck(request);
    }

    /**
     * Sets the OrderCheckService to use.
     * 
     * @param orderCheckService orderCheckService property
     */
    public final void setOrderCheckService(OrderCheckService orderCheckService) {
        this.orderCheckService = orderCheckService;
    }
    
    
    /**
     * Checks to see if updates are currently being applied.
     * 
     * NOTE: According to Checkstyle and the VA Coding Standards, this method should
     * be marked as "abstract, final or empty".  However, you will receive a 
     * ComplianceException from WebLogic if this is done.
     *   
     * @return boolean true if updates are being applied.
     */
    public boolean updatesRunning() {
        return this.orderCheckService.updatesRunning();
    }
}
