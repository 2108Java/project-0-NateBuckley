package com.revature.repo;

import java.util.List;

import com.revature.models.CustomerAccount;

public interface EmployeeDAO {

	public boolean authenticate(String employeeUsername, String employeePassword);
	
	public boolean login(String employeeUsername, String employeePassword);
	
	public boolean updateApplicationAccepted(String employeeUsername);
	
	public boolean updateApplicationRejected(String employeeUsername);
	
	public CustomerAccount selectCustomerAccount(String employeeUsername);
	
	public List<CustomerAccount> selectAllUnapprovedAccounts();
	
	public void viewTransactionLog();
}
