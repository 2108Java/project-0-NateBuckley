package com.revature.models;

public class CustomerAccount implements Account {

	private String username;
	private String password;
	private double checkingBalance;
	private double savingsBalance;
	private double jointBalance;
	private boolean isApproved;
	
	public CustomerAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.isApproved = false;
	}

	public CustomerAccount(String username, String password, double checkingBalance, double savingsBalance,
			double jointBalance, boolean isApproved) {
		super();
		this.username = username;
		this.password = password;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
		this.jointBalance = jointBalance;
		this.isApproved = isApproved;
	}
	
	public CustomerAccount(String username, double checkingBalance, double savingsBalance, double jointBalance, boolean isApproved) {
		super();
		this.username = username;
		this.checkingBalance = checkingBalance;
		this.savingsBalance = savingsBalance;
		this.jointBalance = jointBalance;
		this.isApproved = isApproved;
	}
	
	public CustomerAccount(String username) {
		this.username = username;
	}
	
	public CustomerAccount() {}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(double checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public double getJointBalance() {
		return jointBalance;
	}

	public void setJointBalance(double jointBalance) {
		this.jointBalance = jointBalance;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
