package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.revature.models.EmployeeAccount;

public class EmployeeServiceTest {

	EmployeeService eService;
	CustomerService cService;
	
	@Before
	public void setup() {
		eService = new EmployeeServiceImpl();
		cService = new CustomerServiceImpl();
	}
	
	@Test
	public void testAuthenticate() {
		assertTrue(eService.authenticate("god", "godwashere"));
		assertFalse(eService.authenticate("doesntexist", "thisusernamedoesntexist"));
	}
	
	@Test
	public void testLogin() {
		EmployeeAccount eAccount = new EmployeeAccount();
		eAccount = eService.login("god");
		
		assertEquals(eAccount.getUsername(), "god");
		assertEquals(eAccount.getPassword(), "godwashere");
		assertTrue(eAccount.getIsAdmin());
	}
	
}
