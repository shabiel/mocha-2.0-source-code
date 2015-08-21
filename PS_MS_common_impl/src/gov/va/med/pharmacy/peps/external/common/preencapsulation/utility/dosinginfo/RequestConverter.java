package gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.dosinginfo;

import gov.va.med.pharmacy.peps.common.vo.DosingInfoVo;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.request.DosingInfo;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.request.DosingInfoRequest;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Converts an XML request into a VO.
 */
public final class RequestConverter {

    /**
     * Converts a DosingInfoRequest to a collection of DosingInfoVo objects.
     * 
     * @param request the DosingInfoRequest object.
     * @return a Collection of DosingInfoVo objects.
     */
    public static Collection<DosingInfoVo> toDosingInfoVo(DosingInfoRequest request) {
        Collection<DosingInfoVo> dosingRequest = new ArrayList<DosingInfoVo>(request.getDosingInfo().size());

        for (DosingInfo dosingInfo : request.getDosingInfo()) {
            DosingInfoVo dosingInfoVo = new DosingInfoVo();
            dosingInfoVo.setGcnSeqNo(dosingInfo.getGcnSeqNo());
            dosingInfoVo.setFdbdx(dosingInfo.getFdbdx());

            dosingRequest.add(dosingInfoVo);
        }

        return dosingRequest;
    }

    /**
     * Cannot instantiate
     */
    private RequestConverter() {
        super();
    }
}
