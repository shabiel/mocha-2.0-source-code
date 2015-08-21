package gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.test;

import gov.va.med.pharmacy.peps.common.vo.DosingInfoResultsVo;
import gov.va.med.pharmacy.peps.common.vo.DosingInfoVo;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.DosingInfoCapability;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DosingInfoCapabilityTest extends TestCase {
	
	@Resource
	private DosingInfoCapability capability;

    /**
     * Setup the capability
     * 
     * @throws Exception id error
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        System.out.println("--------------------------" + getName() + "--------------------------");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/mocha/test/InterfaceContext.xml");
        this.capability = (DosingInfoCapability) context.getBean("dosingInfoCapability");
    }

    /**
     * Test retrieving dose routes and types for one GCN sequence number
     */
    public void testSingleGcn() {
        Collection<DosingInfoVo> gcnSeqNos = new ArrayList<DosingInfoVo>();
        DosingInfoVo one = new DosingInfoVo();
        one.setGcnSeqNo(new BigInteger("22211"));	// 3874, 19141, 22211
        one.setConceptType((short) 2);
        //one.setHitType((short) 1);
        one.setFdbdx("999");		// 999
        gcnSeqNos.add(one);

        DosingInfoResultsVo results = capability.processDosingInfoRequest(gcnSeqNos);
        System.out.println(results);

        assertEquals("Incorrect number of dosing info returned", 0, results.getDosingInfo().size());
        assertEquals("All drugs should be found", 1, results.getDosingNotFound().size());

        
//        for (DosingInfoVo dosingInfoVo : results.getDosingInfo()) {
//            assertEquals("Incorrect number of Dose Ranges", 1, dosingInfoVo.getDoseRangeResult().size());
//        }
    }
    
}
