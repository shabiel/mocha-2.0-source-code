package gov.va.med.pharmacy.peps.external.common.preencapsulation.session.bean;

import gov.va.med.pharmacy.peps.external.common.preencapsulation.session.DosingInfoService;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.transaction.annotation.Transactional;

/**
 * The DosingInfoService EJB.
 * 
 * @author zzzzzzduffg
 *
 */
@Stateless
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class DosingInfoServiceBean implements DosingInfoService {

    @Autowired
    private DosingInfoService service;

    /**
     * Retrieves the Dosing Information as an XML String.
     * 
     * NOTE: According to Checkstyle and the VA Coding Standards, this method should
     * be marked as "abstract, final or empty".  However, you will receive a 
     * ComplianceException from WebLogic if this is done.
     *   
     * @param xmlRequest the request for Dosing Information.
     * @return String the retrieved information.
     */
    @Transactional(readOnly = true)
    public String retrieveDosingInformation(String xmlRequest) {
        return service.retrieveDosingInformation(xmlRequest);
    }

    /**
     * Sets the DosingInfoService to use.
     * 
     * @param dosingInfoService the DosingInfoService to use.
     */
    public final void setDosingInfoService(DosingInfoService dosingInfoService) {
        this.service = dosingInfoService;
    }
}
