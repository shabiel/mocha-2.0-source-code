package gov.va.med.pharmacy.peps.external.common.preencapsulation.capability;


//import gov.va.med.pharmacy.peps.external.common.preencapsulation.capability.impl.RandomOrderCheckCapabilityImpl.DrugCheckType;


/**
 * Interface for random order checks.
 */
public interface RandomOrderCheckCapability {

    /**
     * Generates a random order check.
     * 
     * @param drugCheckType the DrugCheckType
     * @return String
     */
    String getRandomOrderCheck(DrugCheckType drugCheckType);
}
