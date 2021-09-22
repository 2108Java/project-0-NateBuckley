package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.models.CustomerAccount;

public class CustomerServiceTest {

	CustomerService cService;
	
	@Before
	public void setup() {
		cService = new CustomerServiceImpl();
	}
	
	@Test
	public void testAuthenticate() {
		assertTrue(cService.authenticate("god", "godwashere"));
		assertFalse(cService.authenticate("userdoesntexist", "thisusernamedoesntexist"));
	}
	
	@Test
	public void testLogin() {
		CustomerAccount cAccount = new CustomerAccount();
		cAccount = cService.login("god");
		
		assertEquals(cAccount.getUsername(), "god");
		assertEquals(cAccount.getPassword(), "godwashere");
		assertTrue(cAccount.isApproved());
	}
	
	@Test
	public void testCheckIfUsernameExists() {
		assertTrue(cService.checkIfUsernameExists("god"));
		assertFalse(cService.checkIfUsernameExists("thisusernamedoesnotexist"));
		
	}
	
	@Test
	public void testGetAccount() {
		CustomerAccount cAccount = new CustomerAccount();
		cAccount = cService.getAccount("god");
		
		assertEquals(cAccount.getUsername(), "god");
		assertEquals(cAccount.getPassword(), "godwashere");
		assertTrue(cAccount.isApproved());
	}
	
	@Test
	public void testDepositChecking() {
		assertTrue(cService.depositChecking("god", 0));
	}
	
	@Test
	public void testDepositSavings() {
		assertTrue(cService.depositSavings("god", 0));
	}
	
	@Test
	public void testDepositJoint() {
		assertTrue(cService.depositJoint("god", 10));
	}
	
	@Test
	public void testWithdrawalChecking() {
		assertTrue(cService.withdrawalChecking("god", 10));
	}
	
	@Test
	public void testWithdrawalSavings() {
		assertTrue(cService.withdrawalSavings("god", 10));
	}
	
	@Test void testWithdrawalJoint() {
		assertTrue(cService.withdrawalJoint("god", 10));
	}
	
}
