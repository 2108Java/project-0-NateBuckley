package com.revature.service;

import java.util.List;

import com.revature.models.CustomerAccount;
import com.revature.models.EmployeeAccount;

public interface EmployeeService {
	
public boolean authenticate(String employeeUsername, String employeePassword);
	
	public EmployeeAccount login(String employeeUsername, String employeePassword);
	
	public boolean acceptApplication(String customerUsername);
	
	public boolean rejectApplication(String customerUsername);
	
	public List<String> getAllUnapprovedAccounts();
	
	public void viewTransactionLog();
	
}
