package com.sophie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sophie.models.Employee;
import com.sophie.models.Reimbursement;
import com.sophie.services.EmployeeService;
import com.sophie.services.ReimbursementService;

public class ReimbursementServiceTest {
	private static Employee e;
	private static EmployeeService es;
	private static Reimbursement re;
	private static ReimbursementService rs;
	private static String username = "JSmith";
	private static String password;
	
	@Before
	public void ESTSetup() {
		rs = new ReimbursementService();
		es = new EmployeeService();
		Reimbursement re = new Reimbursement();
		e = EmployeeService.findByID(1).get(0);
	}
	
	@Test
	public void testRetrieveReimbursement() {
		assertNotNull(rs.findAllPendingForEmployee(e).get(0).toString());
	}
	
	@Test
	public void testNegativeReimbursement() {
		re = rs.findAllForEmployee(e).get(0);
		re.setAmount(-50);
		assertTrue(!(ReimbursementService.insert(e, re) == true));
	}
	
	
	
	
	

}
