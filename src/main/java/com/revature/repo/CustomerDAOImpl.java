package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.CustomerAccount;

public class CustomerDAOImpl implements CustomerDAO {

	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String username = "postgres";
	String password = "k0lj3rak";
	
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

	
	public boolean updateSavings(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE customers SET savings_balance = ? WHERE customer_username = ?";
			
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

	
	public boolean updateChecking(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE customers SET checking_balance = ? WHERE customer_username = ?";
			
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

	
	public boolean insertJoint(String customerUsername1, String customerUsername2, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "INSERT INTO joint (joint_id, joint_balance, fk_customer1, fk_customer2) VALUES (default, ?, ?, ?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, customerUsername1);
			ps.setString(3, customerUsername2);
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
	public CustomerAccount selectAccount(String customerUsername) {

		CustomerAccount account = new CustomerAccount();
		
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			
			String sql = "SELECT customers.customer_username, customers.customer_password, customers.checking_balance, customers.savings_balance, customers.isapproved, joint.joint_balance "
					+ "FROM customers "
					+ "LEFT JOIN joint ON joint.fk_customer1 = ? "
					+ "OR joint.fk_customer2 = ?"
					+ "WHERE customers.customer_username = ?";
			
			//Using a UNION statement instead of OR to save time
//			String sql = "SELECT customers.customer_username, customers.customer_password, customers.checking_balance, customers.savings_balance, customers.isapproved, joint.joint_balance "
//					+ "FROM customers "
//					+ "LEFT JOIN joint ON joint.fk_customer1 = ? "
//					+ "WHERE customers.customer_username = ?"
//					+ "UNION "
//					+ "SELECT customers.customer_username, customers.customer_password, customers.checking_balance, customers.savings_balance, customers.isapproved, joint.joint_balance "
//					+ "FROM customers "
//					+ "LEFT JOIN joint ON joint.fk_customer2 = ? "
//					+ "WHERE customers.customer_username = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, customerUsername);
			ps.setString(2, customerUsername);
			ps.setString(3, customerUsername);
			
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
	
	@Override
	public List<String> selectAllUsernames() {
		List<String> usernameList = new ArrayList<>();
		
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			
			String sql = "SELECT customer_username FROM customers";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			int i = 0;
			
			while(rs.next()) {
				
				usernameList.add(rs.getString("customer_username"));
				
				i++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usernameList;
	}

	public boolean updateAccountBalanceDepositSavings(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE customers SET savings_balance = savings_balance + ? WHERE customer_username = ?";
			
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

	public boolean updateAccountBalanceDepositChecking(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE customers SET checking_balance = checking_balance + ? WHERE customer_username = ?";
			
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

	public boolean updateAccountBalanceDepositJoint(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE joint SET joint_balance = joint_balance + ? "
					+ "WHERE joint_id = (SELECT joint_id FROM joint WHERE fk_customer1 = ? OR fk_customer2 = ?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, customerUsername);
			ps.setString(3, customerUsername);
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean updateAccountBalanceWithdrawalSavings(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE customers SET savings_balance = savings_balance - ? WHERE customer_username = ?";
			
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

	public boolean updateAccountBalanceWithdrawalChecking(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE customers SET checking_balance = checking_balance - ? WHERE customer_username = ?";
			
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

	public boolean updateAccountBalanceWithdrawalJoint(String customerUsername, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "UPDATE joint SET joint_balance = joint_balance - ? "
					+ "WHERE joint_id = (SELECT joint_id FROM joint WHERE fk_customer1 = ? OR fk_customer2 = ?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, customerUsername);
			ps.setString(3, customerUsername);
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean insertAccountTransfer(String giver, String receiver, double amount) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "INSERT INTO pending_transfers (transfer_id, fk_customer_giver, fk_customer_receiver, transfer_balance) VALUES (default, ?, ?, ?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, giver);
			ps.setString(2, receiver);
			ps.setDouble(3, amount);
			
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<String> selectAllTransferReceiverUsernames() {
		List<String> usernameList = new ArrayList<>();
		
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			
			String sql = "SELECT fk_customer_receiver FROM pending_transfers";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				usernameList.add(rs.getString("fk_customer_receiver"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usernameList;
	}


	@Override
	public double selectPendingTransferAmountByUsername(String customerUsername) {
		double amount = 0;
		
		try(Connection connection = DriverManager.getConnection(url, username, password)) {
			
			String sql = "SELECT transfer_balance FROM pending_transfers WHERE fk_customer_receiver = ? ORDER BY transfer_id DESC LIMIT 1";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, customerUsername);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				amount = rs.getDouble("transfer_balance");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return amount;
	}


	@Override
	public boolean deletePendingTransferByUsername(String customerUsername) {
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			String sql = "DELETE FROM pending_transfers WHERE transfer_id = (SELECT transfer_id FROM pending_transfers WHERE fk_customer_receiver = ? ORDER BY transfer_id DESC LIMIT 1)";
			
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
}
