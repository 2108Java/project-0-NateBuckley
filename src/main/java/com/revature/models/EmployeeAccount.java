package com.revature.models;

public class EmployeeAccount implements Account {

	private String username;
	private String password;
	private boolean isAdmin;
	
	public EmployeeAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = false;
	}

	public EmployeeAccount(String username, String password, boolean isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
}
