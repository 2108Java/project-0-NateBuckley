package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.CustomerAccount;

public class CustomerDAOImpl implements CustomerDAO {

	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String username = "postgres";
	String password = "k0lj3rak";
	
	public boolean authenticate(String customerUsername, String customerPassword) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean login(String customerUsername, String customerPassword) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean insertAccount(CustomerAccount account) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "INSERT INTO customers (customer_username, customer_password, isApproved) VALUES (?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setBoolean(3, false);
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
	public boolean insertSavings(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "INSERT INTO savings (savings_id, savings_balance, foreign_customer_username_key) VALUES (default,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, customerUsername);
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
	public boolean insertChecking(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "INSERT INTO checking (checking_id, checking_balance, foreign_customer_username_key) VALUES (default,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, customerUsername);
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
	public boolean insertJoin(String customerUsername1, String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public CustomerAccount selectAccount(String customerUsername) {

		CustomerAccount account = new CustomerAccount();
		
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			
			String sql = "SELECT customer_username, customer_password, checking_balance, savings_balance, joint_balance, isApproved "
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
						rs.getString("customer_password"),
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

	
	public boolean insertAccountTransfer(String giver, String receiver, double amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean checkPendingAccountTransfer(String customerUsername) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "SELECT customer_username FROM customers EXISTS (SELECT foreign_customer_receiver_key FROM pending_transfers WHERE customer_username = ?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, customerUsername);
			
			if(ps.execute()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean updateAccountBalanceDepositSavings(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAccountBalanceDepositChecking(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAccountBalanceDepositJoint(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAccountBalanceWithdrawalSavings(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAccountBalanceWithdrawalChecking(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAccountBalanceWithdrawalJoint(String customerUsername, double amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
