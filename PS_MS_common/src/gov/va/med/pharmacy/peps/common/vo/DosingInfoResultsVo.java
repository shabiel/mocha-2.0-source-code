package gov.va.med.pharmacy.peps.common.vo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A POJO to hold Dosing Information Results.
 * 
 * @author zzzzzzduffg
 *
 */
public class DosingInfoResultsVo extends ValueObject {

    private static final long serialVersionUID = 1L;

    private Collection<DosingInfoVo> dosingInfo = new ArrayList<DosingInfoVo>();
    private Collection<DosingNotFoundVo> dosingNotFound = new ArrayList<DosingNotFoundVo>();

    /**
     * Returns a collection of DosingInfoVo objects.
     * 
     * @return Collection
     */
    public final Collection<DosingInfoVo> getDosingInfo() {
        return dosingInfo;
    }

    /**
     * Sets the Dosing Information.
     * 
     * @param dosingInfo the Dosing Information
     */
    public final void setDosingInfo(Collection<DosingInfoVo> dosingInfo) {
        this.dosingInfo = dosingInfo;
    }

    /**
     * Returns a Collection of DosingInfoVo objects indicating Dosing not found.
     * 
     * @return Collection
     */
    public final Collection<DosingNotFoundVo> getDosingNotFound() {
        return dosingNotFound;
    }

    /**
     * Sets the Dose Not Found information.
     * 
     * @param dosingNotFound Dosing Information that was not found.
     */
    public final void setDosingNotFound(Collection<DosingNotFoundVo> dosingNotFound) {
        this.dosingNotFound = dosingNotFound;
    }
}
