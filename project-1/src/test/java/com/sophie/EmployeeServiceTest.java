package com.sophie;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sophie.models.Employee;
import com.sophie.services.EmployeeService;

public class EmployeeServiceTest {
	private static Employee e;
	private static EmployeeService es;
	private static String username;
	private static String password;
	
	@Before
	public void ESTSetup() {
		username = "JSmith";
		es = new EmployeeService();
		e = EmployeeService.findByUsername(username).get(0);
	}
	
	@Test
	public void testFindByUsernameHappy() throws Throwable{
		assertEquals(username,EmployeeService.findByUsername(username).get(0).getUsername());
	}
	
	@Test
	public void testFindByUsernameSad() throws Throwable{
		assertNull(EmployeeService.findByUsername("Nemo"));
	}
	
	

}
