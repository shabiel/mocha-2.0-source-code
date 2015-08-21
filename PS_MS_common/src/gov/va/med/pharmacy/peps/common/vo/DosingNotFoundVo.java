package gov.va.med.pharmacy.peps.common.vo;

import java.math.BigInteger;

/**
 * A POJO used to hold a Dosing Information not found reason.
 * 
 * @author zzzzzzduffg
 * 
 */
public class DosingNotFoundVo extends ValueObject {

    private static final long serialVersionUID = 1L;

    private BigInteger gcnSeqNo;
    private String fdbdx;
    private String notFoundReason;

    /**
     * Returns the GCN Sequence Number.
     * 
     * @return the gcnSeqNo
     */
    public final BigInteger getGcnSeqNo() {
        return gcnSeqNo;
    }

    /**
     * Sets the GCN Sequence Number.
     * 
     * @param gcnSeqNo
     *            the gcnSeqNo to set
     */
    public final void setGcnSeqNo(BigInteger gcnSeqNo) {
        this.gcnSeqNo = gcnSeqNo;
    }

    /**
     * Returns the FDBDX value.
     * 
     * @return the fdbdx
     */
    public final String getFdbdx() {
        return fdbdx;
    }

    /**
     * Sets the FDBDX value.
     * 
     * @param fdbdx
     *            the fdbdx to set
     */
    public final void setFdbdx(String fdbdx) {
        this.fdbdx = fdbdx;
    }

    /**
     * Returns the Not Found Reason.
     * 
     * @return the notFoundReason
     */
    public final String getNotFoundReason() {
        return notFoundReason;
    }

    /**
     * Sets the Not Found Reason.
     * 
     * @param notFoundReason
     *            the notFoundReason to set
     */
    public final void setNotFoundReason(String notFoundReason) {
        this.notFoundReason = notFoundReason;
    }
}
