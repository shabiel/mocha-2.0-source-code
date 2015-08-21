package gov.va.med.pharmacy.peps.common.vo;


import java.util.ArrayList;
import java.util.Collection;


/**
 * Results from drug check containing Collection of DrugDoseCheckResultVo, DrugDrugCheckResultVo,
 * or DrugTherapyCheckResultVo instances.
 * 
 * @param <T> Type of drug check (drug-drug, dosing or therapy)
 */
public class DrugCheckResultsVo<T> extends ValueObject {
    private static final long serialVersionUID = 1L;
    private Collection<DrugCheckMessageVo> messages = new ArrayList<DrugCheckMessageVo>();
    private Collection<T> checks = new ArrayList<T>();

    /**
     * Empty constructor.
     */
    public DrugCheckResultsVo() {
        super();
    }

    /**
     * Returns the Messages.
     * 
     * @return messages property
     */
    public final Collection<DrugCheckMessageVo> getMessages() {
        return messages;
    }

    /**
     * Sets the Messages.
     * 
     * @param messages messages property
     */
    public final void setMessages(Collection<DrugCheckMessageVo> messages) {
        this.messages = messages;
    }

    /**
     * Collection of DrugDoseCheckResultVo, DrugDrugCheckResultVo, or DrugTherapyCheckResultVo
     * instances, depending on check performed.
     * 
     * @return checks property
     */
    public final Collection<T> getChecks() {
        return checks;
    }

    /**
     * Collection of DrugDoseCheckResultVo, DrugDrugCheckResultVo, or DrugTherapyCheckResultVo
     * instances, depending on check performed.
     * 
     * @param checks therapyCheckResults property
     */
    public final void setChecks(Collection<T> checks) {
        this.checks = checks;
    }
}
