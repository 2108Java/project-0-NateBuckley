package com.revature.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.CustomerAccount;
import com.revature.models.EmployeeAccount;
import com.revature.repo.CustomerDAO;
import com.revature.repo.CustomerDAOImpl;
import com.revature.repo.EmployeeDAO;
import com.revature.repo.EmployeeDAOImpl;

public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeDAO eDao = new EmployeeDAOImpl();
	private final CustomerDAO cDao = new CustomerDAOImpl();
	private static final Logger loggy = Logger.getLogger(EmployeeServiceImpl.class);
	
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
	public EmployeeAccount login(String employeeUsername) {
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
		try{

				FileInputStream fstream = new FileInputStream("src/main/resources/log4j-bank-application.log");

				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

				String strLine;

			/* read log line by line */

			while ((strLine = br.readLine()) != null) {

			/* parse strLine to obtain what you want */

				System.out.println (strLine);

			}

			fstream.close();

			} catch (Exception e) {

				System.err.println("Error: " + e.getMessage());

			}
	}

	
	

}
