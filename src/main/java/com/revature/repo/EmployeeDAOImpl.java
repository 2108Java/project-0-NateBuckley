package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.CustomerAccount;
import com.revature.models.EmployeeAccount;

public class EmployeeDAOImpl implements EmployeeDAO {

	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String username = "postgres";
	String password = "k0lj3rak";
	
	@Override
	public EmployeeAccount selectAccountByUsername(String employeeUsername) {
		
		EmployeeAccount account = new EmployeeAccount();
		
try(Connection connection = DriverManager.getConnection(url, username, password)) {
			
			String sql = "SELECT * FROM employees WHERE employee_username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, employeeUsername);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				account = new EmployeeAccount(
						rs.getString("employee_username"),
						rs.getString("employee_password"),
						rs.getBoolean("isAdmin")
						);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
	}

	public boolean updateApplicationAccepted(String customerUsername) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE customers SET isApproved = ? WHERE customer_username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setString(2, customerUsername);
			
			ps.execute();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean updateApplicationRejected(String customerUsername) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "DELETE FROM customers WHERE customer_username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, customerUsername);
			
			ps.execute();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public void viewTransactionLog() {
		// TODO Auto-generated method stub

	}

	
	public List<String> selectAllUnapprovedAccounts() {

		List<String> unapprovedAccountList = new ArrayList<>();
		
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT customer_username FROM customers WHERE isApproved = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setBoolean(1, false);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			
			while(rs.next()) {
				unapprovedAccountList.add(i, rs.getString("customer_username"));
				
				i++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unapprovedAccountList;
	}

}
