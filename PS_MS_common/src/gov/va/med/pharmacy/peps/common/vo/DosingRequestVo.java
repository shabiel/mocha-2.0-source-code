package gov.va.med.pharmacy.peps.common.vo;

/**
 * A POJO to hold a Dosing Request.
 * 
 * @author zzzzzzduffg
 *
 */
public class DosingRequestVo extends ValueObject {

    private static final long serialVersionUID = 1L;

    private Integer gcnSeqNo;
    private Short conceptType;
    private Short hitType;
    private String fdbdx;

    /**
     * Returns the GCN Sequence Number.
     * 
     * @return Integer
     */
    public final Integer getGcnSeqNo() {
        return gcnSeqNo;
    }

    /**
     * Sets the GCN Sequence Number.
     * 
     * @param gcnSeqNo GCN Sequence Number
     */
    public final void setGcnSeqNo(Integer gcnSeqNo) {
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
     * Sets the FDBDX.
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
}
