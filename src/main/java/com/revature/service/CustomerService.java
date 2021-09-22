package com.revature.service;

import com.revature.models.CustomerAccount;

public interface CustomerService {

	public boolean authenticate(String customerUsername, String customerPassword);
	
	public CustomerAccount login(String customerUsername);
	
	public boolean checkIfUsernameExists(String customerUsername);
	
	public boolean createNewAccount(CustomerAccount account);
	
	public boolean createNewSavings(String customerUsername, double amount);
	
	public boolean createNewChecking(String customerUsername, double amount);
	
	public boolean createNewJoint(String customerUsername1, String customerUsername, double amount);
	
	public CustomerAccount getAccount(String customerUsername);
	
	public boolean depositSavings(String customerUsername, double amount);
	
	public boolean depositChecking(String customerUsername, double amount);
	
	public boolean depositJoint(String customerUsername, double amount);
	
	public boolean withdrawalSavings(String customerUsername, double amount);
	
	public boolean withdrawalChecking(String customerUsername, double amount);
	
	public boolean withdrawalJoint(String customerUsername, double amount);
	
	public boolean createNewMoneyTransfer(String giver, String receiver, double amount);
	
	public double getMoneyTransfer(String customerUsername);
	
	public boolean checkPendingTransfer(String customerUsername);
	
	public boolean deletePendingTransfer(String customerUsername);
	
}
