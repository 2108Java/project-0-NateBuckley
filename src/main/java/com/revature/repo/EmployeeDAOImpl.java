package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.CustomerAccount;

public class EmployeeDAOImpl implements EmployeeDAO {

	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String username = "postgres";
	String password = "k0lj3rak";
	
	public boolean authenticate(String employeeUsername, String employeePassword) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean login(String employeeUsername, String employeePassword) {
		// TODO Auto-generated method stub
		return false;
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

	public CustomerAccount selectCustomerAccount(String customerUsername) {
		
		CustomerAccount account = new CustomerAccount();
		
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			
			String sql = "SELECT customer_username, checking_balance, savings_balance, joint_balance, isApproved "
					+ "FROM customers "
					+ "LEFT JOIN checking_balance ON customer.customer_username = checking.checking_id "
					+ "LEFT JOIN savings_balance ON customer.customer_username = savings.savings_id "
					+ "LEFT JOIN joint_balance ON customer.customer_username = joint.joint_id "
					+ "WHERE customer_username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, customerUsername);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				account = new CustomerAccount(
						rs.getString("customer_username"),
						rs.getDouble("checking_balance"),
						rs.getDouble("savings_balance"),
						rs.getDouble("joint_balance"),
						rs.getBoolean("isApproved")
						);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
	}

	public void viewTransactionLog() {
		// TODO Auto-generated method stub

	}

	
	public List<CustomerAccount> selectAllUnapprovedAccounts() {

		List<CustomerAccount> unapprovedAccountList = new ArrayList<>();
		
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT customer_username FROM customers WHERE isApproved = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setBoolean(1, false);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			
			while(rs.next()) {
				unapprovedAccountList.set(i, new CustomerAccount(rs.getString("customer_username")));
				
				i++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unapprovedAccountList;
	}

}
