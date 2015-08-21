package gov.va.med.pharmacy.peps.external.common.preencapsulation.servlet.test.integration;

import gov.va.med.pharmacy.peps.external.common.preencapsulation.test.DosingInfoTestCase;

public class DosingInfoServletTest extends DosingInfoTestCase {

    public void testSingleGcn() throws Exception {
        assertActualResponseEqual("xml/test/messages/dosingInfoSingle.xml", "xml/test/messages/dosingInfoSingleResponse.xml");
    }

    public void testNotFoundGcn() throws Exception {
        assertActualResponseEqual("xml/test/messages/dosingInfoNotFound.xml", "xml/test/messages/dosingInfoNotFoundResponse.xml");
    }
    public void testFoundAndNotFoundGcn() throws Exception {
        assertActualResponseEqual("xml/test/messages/dosingInfoFoundAndNotFound.xml", "xml/test/messages/dosingInfoFoundAndNotFoundResponse.xml");
    }
}
