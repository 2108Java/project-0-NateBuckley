package com.revature.repo;

import java.util.List;

import com.revature.models.CustomerAccount;
import com.revature.models.EmployeeAccount;

public interface EmployeeDAO {

	public EmployeeAccount selectAccountByUsername(String employeeUsername);
	
	public boolean updateApplicationAccepted(String customerUsername);
	
	public boolean updateApplicationRejected(String customerUsername);
	
	public List<String> selectAllUnapprovedAccounts();
	
	public void viewTransactionLog();
}
