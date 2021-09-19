package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.CustomerAccount;
import com.revature.models.EmployeeAccount;
import com.revature.repo.CustomerDAO;
import com.revature.repo.CustomerDAOImpl;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDAO eDao = new EmployeeDAOImpl();
	CustomerDAO cDao = new CustomerDAOImpl();
	
	@Override
	public boolean authenticate(String employeeUsername, String employeePassword) {
		EmployeeAccount employeeAccount = new EmployeeAccount();
		employeeAccount = eDao.selectAccountByUsername(employeeUsername);
		if(employeeAccount != null && employeeAccount.getPassword() != null && employeeAccount.getPassword().equals(employeePassword)) {
			return true;
		}
		
		return false;
	}

	@Override
	public EmployeeAccount login(String employeeUsername, String employeePassword) {
		EmployeeAccount account = new EmployeeAccount();
		account = eDao.selectAccountByUsername(employeeUsername);
		
		return account;
	}

	@Override
	public boolean acceptApplication(String customerUsername) {
		if(eDao.updateApplicationAccepted(customerUsername)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean rejectApplication(String customerUsername) {
		CustomerAccount cAccount = new CustomerAccount();
		cAccount = cDao.selectAccount(customerUsername);
		if(!cAccount.isApproved()) {
			eDao.updateApplicationRejected(customerUsername);
			return true;
		} else {
			System.out.println("That account was already approved.");
		}
		
		return false;
	}

	@Override
	public List<String> getAllUnapprovedAccounts() {
		List<String> unapprovedList = new ArrayList<>();
		unapprovedList = eDao.selectAllUnapprovedAccounts();
		return unapprovedList;
	}

	@Override
	public void viewTransactionLog() {
		// TODO Auto-generated method stub
		
	}

	
	

}
