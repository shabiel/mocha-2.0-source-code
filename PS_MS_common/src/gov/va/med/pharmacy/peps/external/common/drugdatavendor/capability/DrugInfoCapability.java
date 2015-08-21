package gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability;


import java.util.Collection;

import gov.va.med.pharmacy.peps.common.vo.DrugInfoResultsVo;
import gov.va.med.pharmacy.peps.common.vo.DrugInfoVo;


/**
 * Lookup the dose routes and types for the given drugs.
 */
public interface DrugInfoCapability {

    /**
     * Lookup the dose routes and types for the given drugs.
     * 
     * @param drugs Collection of DrugInfoVo
     * @return DrugInfoResultsVo containing drugs not found and drugs with does routes and types
     */
    DrugInfoResultsVo processDrugInfoRequest(Collection<DrugInfoVo> drugs);
}
