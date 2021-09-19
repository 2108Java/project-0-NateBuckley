package com.revature.repo;

import java.util.List;

import com.revature.models.CustomerAccount;

public interface CustomerDAO {
	
	public boolean insertAccount(CustomerAccount account);
	
	public boolean updateSavings(String customerUsername, double amount);
	
	public boolean updateChecking(String customerUsername, double amount);
	
	public boolean insertJoint(String customerUsername1, String customerUsername, double amount);
	
	public CustomerAccount selectAccount(String customerUsername);
	
	public List<String> selectAllUsernames();
	
	public boolean updateAccountBalanceDepositSavings(String customerUsername, double amount);
	
	public boolean updateAccountBalanceDepositChecking(String customerUsername, double amount);
	
	public boolean updateAccountBalanceDepositJoint(String customerUsername, double amount);
	
	public boolean updateAccountBalanceWithdrawalSavings(String customerUsername, double amount);
	
	public boolean updateAccountBalanceWithdrawalChecking(String customerUsername, double amount);
	
	public boolean updateAccountBalanceWithdrawalJoint(String customerUsername, double amount);
	
	public boolean insertAccountTransfer(String giver, String receiver, double amount);
	
	public List<String> selectAllTransferReceiverUsernames();
	
	public double selectPendingTransferAmountByUsername(String customerUsername);
	
	public boolean deletePendingTransferByUsername(String customerUsername);

}
