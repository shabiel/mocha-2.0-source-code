package gov.va.med.pharmacy.peps.common.vo;


import java.util.ArrayList;
import java.util.List;


/**
 * Consumer monograph data.
 */
public class ConsumerMonographVo extends ValueObject {
    /**
     * The Monograph Title.
     */
    public static final String MONOGRAPH_TYPE = "FDB-CE";
    /**
     * The Disclaimer section code.
     */
    public static final String DISCLAIMER_SECTION = "Z";
    /**
     * The Title section code.
     */
    public static final String TITLE_SECTION = "T";
    /**
     * The Medical Warning section code.
     */
    public static final String MEDICAL_WARNING_SECTION = "L";
    /**
     * The How Occurs section code.
     */
    public static final String HOW_OCCURS_SECTION = "A";
    /**
     * The What Might Happen section code.
     */
    public static final String WHAT_MIGHT_HAPPEN_SECTION = "E";
    /**
     * The What To Do section code.
     */
    public static final String WHAT_TO_DO_SECTION = "M";
    /**
     * The Reference section code.
     */
    public static final String REFERENCE_SECTION = "R";

    private static final long serialVersionUID = 1L;

    private boolean fdbMonographSourceType = true;
    private String disclaimer = "";
    private String monographTitle = "";
    private String medicalWarning = "";
    private String howOccurs = "";
    private String whatMightHappen = "";
    private String whatToDo = "";
    private List<String> references = new ArrayList<String>();

    /**
     * Returns the Disclaimer.
     * 
     * @return disclaimer property
     */
    public final String getDisclaimer() {
        return disclaimer;
    }

    /**
     * Sets the Disclaimer.
     * 
     * @param disclaimer disclaimer property
     */
    public final void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    /**
     * Returns the Monograph Title.
     * 
     * @return monographTitle property
     */
    public final String getMonographTitle() {
        return monographTitle;
    }

    /**
     * Sets the Monograph Title.
     * 
     * @param monographTitle monographTitle property
     */
    public final void setMonographTitle(String monographTitle) {
        this.monographTitle = monographTitle;
    }

    /**
     * Returns Medical Warning.
     * 
     * @return medicalWarning property
     */
    public final String getMedicalWarning() {
        return medicalWarning;
    }

    /**
     * Sets Medical Warning.
     * 
     * @param medicalWarning medicalWarning property
     */
    public final void setMedicalWarning(String medicalWarning) {
        this.medicalWarning = medicalWarning;
    }

    /**
     * Returns How Occurs.
     * 
     * @return howOccurs property
     */
    public final String getHowOccurs() {
        return howOccurs;
    }

    /**
     * Sets How Occurs.
     * 
     * @param howOccurs howOccurs property
     */
    public final void setHowOccurs(String howOccurs) {
        this.howOccurs = howOccurs;
    }

    /**
     * Returns What Might Happen.
     * 
     * @return whatMightHappen property
     */
    public final String getWhatMightHappen() {
        return whatMightHappen;
    }

    /**
     * Sets What Might Happen.
     * 
     * @param whatMightHappen whatMightHappen property
     */
    public final void setWhatMightHappen(String whatMightHappen) {
        this.whatMightHappen = whatMightHappen;
    }

    /**
     * Returns What To Do.
     * 
     * @return whatToDo property
     */
    public final String getWhatToDo() {
        return whatToDo;
    }

    /**
     * Sets What To Do.
     * 
     * @param whatToDo whatToDo property
     */
    public final void setWhatToDo(String whatToDo) {
        this.whatToDo = whatToDo;
    }

    /**
     * Returns the references.
     * 
     * @return references property
     */
    public final List<String> getReferences() {
        return references;
    }

    /**
     * Sets the references.
     * 
     * @param references references property
     */
    public final void setReferences(List<String> references) {
        this.references = references;
    }

    /**
     * Returns the FDB Monograph Source Type.
     * 
     * @return fdbMonographSourceType property
     */
    public final boolean isFdbMonographSourceType() {
        return fdbMonographSourceType;
    }

    /**
     * Sets the FDB Monograph Source Type.
     * 
     * @param fdbMonographSourceType fdbMonographSourceType property
     */
    public final void setFdbMonographSourceType(boolean fdbMonographSourceType) {
        this.fdbMonographSourceType = fdbMonographSourceType;
    }
}
