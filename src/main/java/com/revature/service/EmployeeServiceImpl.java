package com.revature.service;

import java.util.List;

import com.revature.models.CustomerAccount;
import com.revature.models.EmployeeAccount;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public boolean authenticate(String employeeUsername, String employeePassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmployeeAccount login(String employeeUsername, String employeePassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean acceptApplication(String customerUsername) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectApplication(String customerUsername) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomerAccount getCustomerAccount(String customerUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllUnapprovedAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewTransactionLog() {
		// TODO Auto-generated method stub
		
	}

	
	

}
