package com.revature.service;

import com.revature.models.CustomerAccount;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public boolean authenticate(String customerUsername, String customerPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomerAccount login(String customerUsername, String customerPassword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean checkIfUsernameExists(String customerUsername) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewAccount(CustomerAccount account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewSavings(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewChecking(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewJoint(String customerUsername1, String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomerAccount getAccount(String customerUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean depositSavings(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean depositChecking(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean depositJoint(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdrawalSavings(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdrawalChecking(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdrawalJoint(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewMoneyTransfer(String giver, String receiver, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkPendingTransfer(String customerUsername) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getMoneyTransfer(String customerUsername) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deletePendingTransfer(String customerUsername) {
		// TODO Auto-generated method stub
		return false;
	}
}
