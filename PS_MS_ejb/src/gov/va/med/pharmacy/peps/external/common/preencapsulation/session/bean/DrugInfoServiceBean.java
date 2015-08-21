package gov.va.med.pharmacy.peps.external.common.preencapsulation.session.bean;


import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.transaction.annotation.Transactional;

import gov.va.med.pharmacy.peps.external.common.preencapsulation.session.DrugInfoService;


/**
 * Retrieve the dose routes and dose types for a given list of GCN sequence numbers.
 */
@Stateless
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class DrugInfoServiceBean implements DrugInfoService {

    @Autowired
    private DrugInfoService drugInfoService;

    /**
     * Retrieve the dose routes and dose types for the given XML request.
     * 
     * NOTE: According to Checkstyle and the VA Coding Standards, this method should
     * be marked as "abstract, final or empty".  However, you will receive a 
     * ComplianceException from WebLogic if this is done.
     *   
     * @param request request XML from VistA
     * @return response XML
     */
    @Transactional(readOnly = true)
    public String retrieveDrugInformation(String request) {
        return drugInfoService.retrieveDrugInformation(request);
    }

    /**
     * Sets the DrugInfoService to use.
     * 
     * @param drugInfoService drugInfoService property
     */
    public final void setDrugInfoService(DrugInfoService drugInfoService) {
        this.drugInfoService = drugInfoService;
    }
}
