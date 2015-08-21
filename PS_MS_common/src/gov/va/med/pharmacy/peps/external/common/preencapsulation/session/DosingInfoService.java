package gov.va.med.pharmacy.peps.external.common.preencapsulation.session;

/**
 * Interface describing the behavior for the Dosing Information service.
 * 
 * @author zzzzzzduffg
 *
 */
public interface DosingInfoService {

    /**
     * The method to process the dosing info request.
     * 
     * @param xmlRequest the dosing info request.
     * @return String
     */
    String retrieveDosingInformation(String xmlRequest);
}
