package gov.va.med.pharmacy.peps.external.common.preencapsulation.test;

import gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.dosinginfo.DosingInfoXmlUtility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DosingInfoTestCase extends VistAWebServiceTestCase {

	public DosingInfoTestCase() {
		super();
	}
	public DosingInfoTestCase(String name) {
		super(name);
	}
	@Override
	public String getRequestURL() {
		return "dosinginfo";
	}

	@Override
	public void assertActualResponseEqual(String requestPath,
			String responsePath) throws Exception {
        String request = readInputStream(requestPath);
        System.out.println("Sending request to: " + getUrl());
        System.out.println("XML Request (" + requestPath + "):");
        System.out.println(request);
        long start = System.currentTimeMillis();
        String actual = sendRequest(request);
        long stop = System.currentTimeMillis();
        System.out.println("Actual XML Response:");
        System.out.println(DosingInfoXmlUtility.prettyPrint(actual));
        String expected = readInputStream(responsePath);
        System.out.println("Expected XML Response (" + responsePath + "):");
        System.out.println(expected);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy h:mm:ss.SSS a z");
        System.out.println("Start Time: " + dateFormat.format(new Date(start)));
        System.out.println("Stop Time: " + dateFormat.format(new Date(stop)));
        System.out.println("Execution Time: " + (stop - start) + " milliseconds");

        assertTrue("Response XML is not correct", DosingInfoXmlUtility.responseEquals(expected, actual));
	}

}
