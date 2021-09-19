package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.CustomerAccount;
import com.revature.repo.CustomerDAO;
import com.revature.repo.CustomerDAOImpl;

public class CustomerServiceImpl implements CustomerService {

	private final CustomerDAO cDao = new CustomerDAOImpl();
	
	@Override
	public boolean authenticate(String customerUsername, String customerPassword) {
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount = cDao.selectAccount(customerUsername);
		if(customerAccount != null && customerAccount.getPassword() != null && customerAccount.getPassword().equals(customerPassword)) {
			return true;
		}
		
		return false;
	}

	@Override
	public CustomerAccount login(String customerUsername, String customerPassword) {
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount = cDao.selectAccount(customerUsername);
		return customerAccount;
	}
	
	@Override
	public boolean checkIfUsernameExists(String customerUsername) {
		List<String> usernameList = new ArrayList<>();
		usernameList = cDao.selectAllUsernames();
		
		for(String username : usernameList) {
			if(username.equals(customerUsername)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean createNewAccount(CustomerAccount account) {
		if(cDao.insertAccount(account)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean createNewSavings(String customerUsername, double amount) {
		if(cDao.updateSavings(customerUsername, amount)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean createNewChecking(String customerUsername, double amount) {
		if(cDao.updateChecking(customerUsername, amount)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean createNewJoint(String customerUsername1, String customerUsername2, double amount) {
		if(cDao.insertJoint(customerUsername1, customerUsername2, amount)) {
			return true;
		}
		return false;
	}

	@Override
	public CustomerAccount getAccount(String customerUsername) {
		CustomerAccount account = new CustomerAccount();
		account = cDao.selectAccount(customerUsername);
		
		return account;
	}

	@Override
	public boolean depositSavings(String customerUsername, double amount) {
		if(cDao.updateAccountBalanceDepositSavings(customerUsername, amount)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean depositChecking(String customerUsername, double amount) {
		if(cDao.updateAccountBalanceDepositChecking(customerUsername, amount)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean depositJoint(String customerUsername, double amount) {
		if(cDao.updateAccountBalanceDepositJoint(customerUsername, amount)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean withdrawalSavings(String customerUsername, double amount) {
		if(cDao.updateAccountBalanceWithdrawalSavings(customerUsername, amount)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean withdrawalChecking(String customerUsername, double amount) {
		if(cDao.updateAccountBalanceWithdrawalChecking(customerUsername, amount)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean withdrawalJoint(String customerUsername, double amount) {
		if(cDao.updateAccountBalanceWithdrawalJoint(customerUsername, amount)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean createNewMoneyTransfer(String giver, String receiver, double amount) {
		if(cDao.insertAccountTransfer(giver, receiver, amount)) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean checkPendingTransfer(String customerUsername) {
		List<String> usernameList = new ArrayList<>();
		usernameList = cDao.selectAllTransferReceiverUsernames();
		
		for(String username : usernameList) {
			if(username.equals(customerUsername)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public double getMoneyTransfer(String customerUsername) {
		double amount = 0;
		amount = cDao.selectPendingTransferAmountByUsername(customerUsername);
		
		return amount;
	}

	@Override
	public boolean deletePendingTransfer(String customerUsername) {
		if(cDao.deletePendingTransferByUsername(customerUsername)) {
			return true;
		}
		return false;
	}
}
