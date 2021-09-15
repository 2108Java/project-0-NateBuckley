package com.revature.repo;

import com.revature.models.CustomerAccount;

public interface CustomerDAO {
	
	public boolean authenticate(String customerUsername, String customerPassword);
	
	public boolean login(String customerUsername, String customerPassword);
	
	public boolean insertAccount(CustomerAccount account);
	
	public boolean insertSavings(String customerUsername, double amount);
	
	public boolean insertChecking(String customerUsername, double amount);
	
	public boolean insertJoin(String customerUsername1, String customerUsername, double amount);
	
	public CustomerAccount selectAccount(String customerUsername);
	
	public boolean updateAccountBalanceDepositSavings(String customerUsername, double amount);
	
	public boolean updateAccountBalanceDepositChecking(String customerUsername, double amount);
	
	public boolean updateAccountBalanceDepositJoint(String customerUsername, double amount);
	
	public boolean updateAccountBalanceWithdrawalSavings(String customerUsername, double amount);
	
	public boolean updateAccountBalanceWithdrawalChecking(String customerUsername, double amount);
	
	public boolean updateAccountBalanceWithdrawalJoint(String customerUsername, double amount);
	
	public boolean insertAccountTransfer(String giver, String receiver, double amount);
	
	public boolean checkPendingAccountTransfer(String customerUsername);

}
