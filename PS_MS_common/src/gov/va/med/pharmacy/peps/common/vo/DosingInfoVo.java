package gov.va.med.pharmacy.peps.common.vo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A POJO to indicate Dosing Information.
 * 
 * @author zzzzzzduffg
 *
 */
public class DosingInfoVo extends ValueObject {

    private static final long serialVersionUID = 1L;

    private BigInteger gcnSeqNo;
    private Short conceptType;
    private Short hitType;
    private String fdbdx;
    private String dispensableDrugName;
    private String dispensableDrugDescription;

    private Collection<DoseRangeResultVo> doseRangeResults = new ArrayList<DoseRangeResultVo>();
    private Collection<MinMaxResultVo> minMaxResults = new ArrayList<MinMaxResultVo>();
    private Collection<DoseRangeResultVo> neonatalDoseRangeResults = new ArrayList<DoseRangeResultVo>();

    /**
     * Constructor.
     */
    public DosingInfoVo() {
        super();
    }

    /**
     * Returns the GCN Sequence Number.
     * 
     * @return BigInteger
     */
    public final BigInteger getGcnSeqNo() {
        return gcnSeqNo;
    }

    /**
     * Sets the GCN Sequence Number.
     * 
     * @param gcnSeqNo the GCN Sequence Number
     */
    public final void setGcnSeqNo(BigInteger gcnSeqNo) {
        this.gcnSeqNo = gcnSeqNo;
    }

    /**
     * Returns the Concept Type.
     * 
     * @return Short
     */
    public final Short getConceptType() {
        return conceptType;
    }

    /**
     * Sets the Concept Type.
     * 
     * @param conceptType Concept Type
     */
    public final void setConceptType(Short conceptType) {
        this.conceptType = conceptType;
    }

    /**
     * Returns the Hit Type.
     * 
     * @return Short
     */
    public final Short getHitType() {
        return hitType;
    }

    /**
     * Sets the Hit Type.
     * 
     * @param hitType Hit Type
     */
    public final void setHitType(Short hitType) {
        this.hitType = hitType;
    }

    /**
     * Returns the FDBDX.
     * 
     * @return String
     */
    public final String getFdbdx() {
        return fdbdx;
    }

    /**
     * Sets the FDBDX.
     * 
     * @param fdbdx FDBDX
     */
    public final void setFdbdx(String fdbdx) {
        this.fdbdx = fdbdx;
    }

    /**
     * Returns a collection of DoseRangeResultVo.
     * 
     * @return Collection
     */
    public final Collection<DoseRangeResultVo> getDoseRangeResults() {
        return doseRangeResults;
    }

    /**
     * Sets a Collection of DoseRangeResultVo.
     * 
     * @param doseRangeResults Dose Range Results collection
     */
    public final void setDoseRangeResults(Collection<DoseRangeResultVo> doseRangeResults) {
        this.doseRangeResults = doseRangeResults;
    }

    /**
     * Returns a Collection of MinMaxResultVo.
     * 
     * @return Collection
     */
    public final Collection<MinMaxResultVo> getMinMaxResults() {
        return minMaxResults;
    }

    /**
     * Sets the MinMaxResultVo collection.
     * 
     * @param minMaxResults MinMax Results collection
     */
    public final void setMinMaxResults(Collection<MinMaxResultVo> minMaxResults) {
        this.minMaxResults = minMaxResults;
    }

    /**
     * Returns the Neonatal Dose Range results.
     * 
     * @return Collection
     */
    public final Collection<DoseRangeResultVo> getNeonatalDoseRangeResults() {
        return neonatalDoseRangeResults;
    }

    /**
     * Sets a collection of Neonatal Dose Range results.
     * 
     * @param neonatalDoseRangeResults Neonatal Dose Range results
     */
    public final void setNeonatalDoseRangeResults(Collection<DoseRangeResultVo> neonatalDoseRangeResults) {
        this.neonatalDoseRangeResults = neonatalDoseRangeResults;
    }

    /**
     * Returns the Dispensable Drug Name.
     * 
     * @return String
     */
    public final String getDispensableDrugName() {
        return dispensableDrugName;
    }

    /**
     * Sets the Dispensable Drug Name.
     * 
     * @param dispensableDrugName Dispensable Drug Name
     */
    public final void setDispensableDrugName(String dispensableDrugName) {
        this.dispensableDrugName = dispensableDrugName;
    }

    /**
     * Returns the Dispensable Drug Description.
     * 
     * @return String
     */
    public final String getDispensableDrugDescription() {
        return dispensableDrugDescription;
    }

    /**
     * Sets the Dispensable Drug Description.
     * 
     * @param dispensableDrugDescription Dispensable Drug Description
     */
    public final void setDispensableDrugDescription(String dispensableDrugDescription) {
        this.dispensableDrugDescription = dispensableDrugDescription;
    }
}
