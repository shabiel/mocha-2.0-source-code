package gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability;

import gov.va.med.pharmacy.peps.common.vo.DosingInfoResultsVo;
import gov.va.med.pharmacy.peps.common.vo.DosingInfoVo;

import java.util.Collection;

/**
 * Interface defining the behavior to retrieve Dosing Information.
 * 
 * @author zzzzzzduffg
 *
 */
public interface DosingInfoCapability {

    /**
     * The method to retrieve dosing information.
     * 
     * @param dosingInfoRequests a collection of DosingInfoVo objects.
     * @return DosingInfoResultsVo
     */
    DosingInfoResultsVo processDosingInfoRequest(Collection<DosingInfoVo> dosingInfoRequests);
}
