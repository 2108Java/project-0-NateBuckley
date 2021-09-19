package com.revature.presentation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.CustomerAccount;
import com.revature.models.EmployeeAccount;
import com.revature.service.CustomerService;
import com.revature.service.CustomerServiceImpl;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;

public class Presentation {
	
	private final CustomerService customerService = new CustomerServiceImpl();
	private final EmployeeService employeeService = new EmployeeServiceImpl();
	private final NumberFormat formatter = NumberFormat.getCurrencyInstance();
	private final Scanner sc = new Scanner(System.in);
	
	private void prettyDisplayOfUnapprovedCustomers(List<String> accounts) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i) != null) {
				System.out.println("Username: " + accounts.get(i));
			}
		}
	}
	
	private void displayCustomerAccount(CustomerAccount customerAccount) {
		System.out.println("Username :" + customerAccount.getUsername());
		System.out.println("Checking balance :" + formatter.format(customerAccount.getCheckingBalance()));
		System.out.println("Savings balance :" + formatter.format(customerAccount.getSavingsBalance()));
		System.out.println("Joint account balance :" + formatter.format(customerAccount.getJointBalance()));
		System.out.println(" ");
	}
	
	public void displayStartMenu() {
		boolean isRunning = true;
		
		while(isRunning) {
			System.out.println("What do you want to do? (Input the number for your decision)");
			System.out.println("1) Log in as an employee");
			System.out.println("2) Log in as a customer");
			System.out.println("3) Sign up for a new customer account");
			System.out.println("4) Exit");
			System.out.println(" ");
			
			String result = sc.nextLine();
			String username;
			String password;
			
			switch(result) {
				case "1":
					System.out.println("What is your username?");
					username = sc.nextLine();
					
					System.out.println("What is your password?");
					password = sc.nextLine();
					
					if(employeeService.authenticate(username, password)) {
						EmployeeAccount employeeAccount = new EmployeeAccount();
						employeeAccount = employeeService.login(username, password);
						if(employeeAccount.getIsAdmin()) {
							CustomerAccount adminCustomerAccount = new CustomerAccount();
							adminCustomerAccount = customerService.login(username, password);
							displayAdminMainMenu(adminCustomerAccount);
							isRunning = false;
						} else {
							displayEmployeeMainMenu();
							isRunning = false;
						}
						
					} else {
						System.out.println("That isn't a valid username or password.");
					}
					break;
				case "2":
					System.out.println("What is your username?");
					username = sc.nextLine();
					
					System.out.println("What is your password?");
					password = sc.nextLine();
					
					if(customerService.authenticate(username, password)) {
						CustomerAccount customerAccount = new CustomerAccount();
						customerAccount = customerService.login(username, password);
						if(customerAccount.isApproved()) {
							displayCustomerMainMenu(customerAccount);
							isRunning = false;
						} else {
							System.out.println("Sorry, but that account has not been approved yet.");
						}
						
					} else {
						System.out.println("That isn't a valid username or password.");
					}
					break;
				case "3":
					System.out.println("Username?");
					username = sc.nextLine();
					
					System.out.println("Password?");
					password = sc.nextLine();
					System.out.println(" ");
					
					if(!customerService.checkIfUsernameExists(username)) {
						CustomerAccount customerAccount = new CustomerAccount(username, password);
						if(customerService.createNewAccount(customerAccount)) {
							System.out.println("Your account has been created!");
							isRunning = false;
						} else {
							System.out.println("Something went wrong!");
						}
					} else {
						System.out.println("Sorry, but that username already exists.");
					}
					
					break;
				case "4":
					isRunning = false;
					break;
				default:
					System.out.println("That is not a valid input!");
					break;
			}
			
		}
	}
	
	private CustomerAccount depositChecking(CustomerAccount customerAccount, double amount) {
		if(customerService.depositChecking(customerAccount.getUsername(), amount)) {
			System.out.println("Money successfully added to your checking account!");
			double newAmount = customerAccount.getCheckingBalance() + amount;
			customerAccount.setCheckingBalance(newAmount);
		} else {
			System.out.println("Something went wrong!");
		}
		
		return customerAccount;
	}
	
	private CustomerAccount depositSavings(CustomerAccount customerAccount, double amount) {
		if(customerService.depositSavings(customerAccount.getUsername(), amount)) {
			System.out.println("Money successfully added to your savings account!");
			double newAmount = customerAccount.getSavingsBalance() + amount;
			customerAccount.setSavingsBalance(newAmount);
		} else {
			System.out.println("Something went wrong!");
		}
		
		return customerAccount;
	}
	
	private CustomerAccount depositJoint(CustomerAccount customerAccount, double amount) {
		if(customerService.depositJoint(customerAccount.getUsername(), amount)) {
			System.out.println("Money successfully added to your joint account!");
			double newAmount = customerAccount.getJointBalance() + amount;
			customerAccount.setJointBalance(newAmount);
		} else {
			System.out.println("Something went wrong!");
		}
		
		return customerAccount;	
	}
	
	private void displayCustomerMainMenu(CustomerAccount customerAccount) {
		boolean isRunning = true;
		while(isRunning) {
			
			this.displayCustomerAccount(customerAccount);
			
			System.out.println(" ");
			System.out.println("What do you want to do? (Input the number for your decision)");
			System.out.println("1) Create new checking account");
			System.out.println("2) Create new savings account");
			System.out.println("3) Create a new joint account");
			System.out.println("4) Make a deposit into your checking account");
			System.out.println("5) Make a deposit into your savings account");
			System.out.println("6) Make a deposit into your joint account");
			System.out.println("7) Make a withdrawal from your checking account");
			System.out.println("8) Make a withdrawal from your savings account");
			System.out.println("9 Make a withdrawal from your joint acount");
			System.out.println("10) Make a transfer");
			System.out.println("11) Accept a transfer");
			System.out.println("12) Exit");
			System.out.println(" ");
			
			String result = sc.nextLine();
			double amount = 0;
			switch(result) {
				case "1":
					if(customerAccount.getCheckingBalance() == 0) {
						System.out.println("Input the starting balance:");
						try {
							amount = Double.parseDouble(sc.nextLine());
							if(amount > 0) {
								if(customerService.createNewChecking(customerAccount.getUsername(), amount)) {
									System.out.println("Checking account sucessfully created!");
									double newAmount = customerAccount.getCheckingBalance() + amount;
									customerAccount.setCheckingBalance(newAmount);
								} else {
									System.out.println("Something went wrong!");
								}
							} else {
								System.out.println("You must input a number greater than zero.");
							}
							
						} catch(NumberFormatException e) {
							System.out.println("Invalid number.");
						}
						
						
					} else {
						System.out.println("You already have a checking account.");
					}
					
					break;
				case "2":
					if(customerAccount.getSavingsBalance() == 0) {
						System.out.println("Input the starting balance:");
						try {
							amount = Double.parseDouble(sc.nextLine());
							if(amount > 0) {
								
							} else {
								System.out.println("You must input a number greater than zero.");
							}
								if(customerService.createNewSavings(customerAccount.getUsername(), amount)) {
									System.out.println("Checking account sucessfully created!");
									double newAmount = customerAccount.getSavingsBalance() + amount;
									customerAccount.setSavingsBalance(newAmount);
								} else {
									System.out.println("Something went wrong!");
								}
						} catch(NumberFormatException e) {
							System.out.println("Invalid number.");
						}
						
						
					} else {
						System.out.println("You already have a savings account.");
					}
					break;
				case "3":
					if(customerAccount.getJointBalance() == 0) {
						System.out.println("What is the username of the other person you wish to create a joint account with?");
						result = sc.nextLine();
						
						if(customerService.checkIfUsernameExists(result)) {
							CustomerAccount secondAccount = new CustomerAccount();
							secondAccount = customerService.getAccount(result);
							
							if(secondAccount.isApproved()) {
								if(secondAccount.getJointBalance() == 0) {
									System.out.println("How much do you want to add as a starting amount?");
									try {
										amount = Double.parseDouble(sc.nextLine());
										if(amount > 0) {
											if(customerService.createNewJoint(customerAccount.getUsername(), secondAccount.getUsername(), amount)) {
												System.out.println("Joint account sucessfully created!");
											} else {
												System.out.println("Something went wrong!");
											}
										} else {
											System.out.println("You must input a number greater than zero.");
										}
									} catch(NumberFormatException e) {
										System.out.println("Invalid number.");
									}
								} else {
									System.out.println("Sorry, but " + secondAccount.getUsername() + " already has a joint account.");
								}
							} else {
								System.out.println("Sorry, but the other account isn't approved yet.");
							}
						} else {
							System.out.println("That username doesn't exist.");
						}
					} else {
						System.out.println("You already have a joint account.");
					}
					break;
				case "4":
					if(customerAccount.getCheckingBalance() != 0) {
						System.out.println("How much do you want to add to your checking account?");
						try {
							amount = Double.parseDouble(sc.nextLine());
							if(amount > 0) {
								customerAccount = this.depositChecking(customerAccount, amount);
							} else {
								System.out.println("You must input a number greater than zero.");
							}
							
						} catch(NumberFormatException e) {
							System.out.println("Invalid number.");
						}
					} else {
						System.out.println("You don't have a checking account.");
					}
					
					break;
				case "5":
					if(customerAccount.getSavingsBalance() != 0) {
						System.out.println("How much do you want to add to your savings account?");
						try {
							amount = Double.parseDouble(sc.nextLine());
							if(amount > 0) {
								customerAccount = this.depositSavings(customerAccount, amount);
							} else {
								System.out.println("You must input a number greater than zero.");
							}
						} catch(NumberFormatException e) {
							System.out.println("Invalid number.");
						}
					} else {
						System.out.println("You don't have a savings account.");
					}
					
					break;
				case "6":
					if(customerAccount.getJointBalance() != 0) {
						System.out.println("How much do you want to add to your joint account?");
						try {
							amount = Double.parseDouble(sc.nextLine());
							if(amount > 0) {
								this.depositJoint(customerAccount, amount);
								double newAmount = customerAccount.getJointBalance() + amount;
								customerAccount.setJointBalance(newAmount);
							} else {
								System.out.println("You must input a number greater than zero.");
							}
						} catch(NumberFormatException e) {
							System.out.println("Invalid number.");
						}
					} else {
						System.out.println("You don't have a joint account.");
					}
					
					break;
				case "7":
					if(customerAccount.getCheckingBalance() != 0) {
						System.out.println("How much do you want to withdraw from your checking account?");
						try {
							amount = Double.parseDouble(sc.nextLine());
							if(amount > 0) {
								if(customerService.withdrawalChecking(customerAccount.getUsername(), amount)) {
									System.out.println("Money successfully removed from your checking account!");
									double newAmount = customerAccount.getCheckingBalance() - amount;
									customerAccount.setCheckingBalance(newAmount);
								} else {
									System.out.println("Something went wrong!");
								}
							} else {
								System.out.println("You must input a number greater than zero.");
							}
								
						} catch(NumberFormatException e) {
							System.out.println("Invalid number.");
						}
					} else {
						System.out.println("You don't have a checking account.");
					}
					break;
				case "8":
					if(customerAccount.getSavingsBalance() != 0) {
						System.out.println("How much do you want to withdraw from your savings account?");
						try {
							amount = Double.parseDouble(sc.nextLine());
							if(amount > 0) {
								if(customerService.withdrawalSavings(customerAccount.getUsername(), amount)) {
									System.out.println("Money successfully removed from your savings account!");
									double newAmount = customerAccount.getSavingsBalance() - amount;
									customerAccount.setSavingsBalance(newAmount);
								} else {
									System.out.println("Something went wrong!");
								}
							} else {
								System.out.println("You must input a number greater than zero.");
							}
								
						} catch(NumberFormatException e) {
							System.out.println("Invalid number.");
						}
					} else {
						System.out.println("You don't have a savings account.");
					}
					break;
				case "9":
					if(customerAccount.getJointBalance() != 0) {
						System.out.println("How much do you want to withdraw your joint account?");
						try {
							amount = Double.parseDouble(sc.nextLine());
							if(amount > 0) {
								if(customerService.withdrawalJoint(customerAccount.getUsername(), amount)) {
									System.out.println("Money successfully removed from your joint account!");
									double newAmount = customerAccount.getJointBalance() - amount;
									customerAccount.setJointBalance(newAmount);
								} else {
									System.out.println("Something went wrong!");
								}
							} else {
								System.out.println("You must input a number greater than zero.");
							}
								
						} catch(NumberFormatException e) {
							System.out.println("Invalid number.");
						}
					} else {
						System.out.println("You don't have a joint account.");
					}
					break;
				case "10":
					System.out.println("What is the username of the person to whom you wish to transfer money?");
					result = sc.nextLine();
					
					if(customerService.checkIfUsernameExists(result)) {
						CustomerAccount secondAccount = new CustomerAccount();
						secondAccount = customerService.getAccount(result);
						
						if(secondAccount.isApproved()) {
							System.out.println("How much would you like to transfer?");
							try {
								amount = Double.parseDouble(sc.nextLine());
								if(amount > 0) {
									if(customerService.createNewMoneyTransfer(customerAccount.getUsername(), secondAccount.getUsername(), amount)) {
										System.out.println("Money successfully transferred!");
									} else {
										System.out.println("Something went wrong!");
									}
								} else {
									System.out.println("You must input a number greater than zero.");
								}
							} catch(NumberFormatException e) {
								System.out.println("Invalid number.");
							}
						} else {
							System.out.println("Sorry, but the other account isn't approved yet.");
						}
					} else {
						System.out.println("That username doesn't exist.");
					}
					break;
				case "11":
					if(customerService.checkPendingTransfer(customerAccount.getUsername())) {
						amount = customerService.getMoneyTransfer(customerAccount.getUsername());
						System.out.println("Would you like to deposit the money into your checking, savings, or joint account? (input either checking, savings, or joint)");
						result = sc.nextLine();
						
						switch(result) {
							case "checking":
								this.depositChecking(customerAccount, amount);
								break;
							case "savings":
								this.depositSavings(customerAccount, amount);
								break;
							case "joint":
								this.depositJoint(customerAccount, amount);
								break;
							default:
								System.out.println("That's an invalid input.");
								break;
						}
						
						customerService.deletePendingTransfer(customerAccount.getUsername());
						
					} else {
						System.out.println("You don't have any impending transfers.");
					}
					break;
				case "12":
					System.out.println("Thank you for banking with us!");
					isRunning = false;
					break;
				default:
					System.out.println("That isn't a valid input.");
					break;
			}
		}
		
	}
	
	private void displayEmployeeMainMenu() {
		boolean isRunning = true;
		while (isRunning) {
			System.out.println(" ");
			System.out.println("What do you want to do? (Input the number for your decision)");
			System.out.println("1) Pull up a customer's account details");
			System.out.println("2) See a list of all unapproved customer applications.");
			System.out.println("3) Accept an unapproved customer's application.");
			System.out.println("4) Reject an unapproved customer's application.");
			System.out.println("5) View transaction log");
			System.out.println("6) Exit");
			System.out.println(" ");
			
			String result = sc.nextLine();
			switch(result) {
				case "1":
					System.out.println("What is the username of the customer you want to view?");
					result = sc.nextLine();
					
					if(customerService.checkIfUsernameExists(result)) {
						CustomerAccount customerAccount = new CustomerAccount();
						customerAccount = customerService.getAccount(result);
						this.displayCustomerAccount(customerAccount);
					} else {
						System.out.println("That username doesn't exist.");
					}
					break;
				case "2":
					List<String> listOfUsernames = new ArrayList<>();
					listOfUsernames = employeeService.getAllUnapprovedAccounts();
					this.prettyDisplayOfUnapprovedCustomers(listOfUsernames);
					break;
				case "3":
					System.out.println("What is the username of the customer whose application you want to approve?");
					result = sc.nextLine();
					
					if(customerService.checkIfUsernameExists(result)) {
						if(employeeService.acceptApplication(result)) {
							System.out.println("Application successfully accepted!");
						} else {
							System.out.println("Something went wrong!");
						}
					} else {
						System.out.println("That username doesn't exist.");
					}
					break;
				case "4":
					System.out.println("What is the username of the customer whose application you want to reject?");
					result = sc.nextLine();
					
					if(customerService.checkIfUsernameExists(result)) {
						if(employeeService.rejectApplication(result)) {
							System.out.println("Application successfully rejected!");
						} else {
							System.out.println("Something went wrong!");
						}
					} else {
						System.out.println("That username doesn't exist.");
					}
					break;
				case "5":
					break;
				case "6":
					isRunning = false;
					break;
				default:
					break;
			}
		}
	}
	
	private void displayAdminMainMenu(CustomerAccount adminCustomerAccount) {
		
		boolean isRunning = true;
		
		while(isRunning) {
			System.out.println(" ");
			System.out.println("Welcome, my lord.");
			System.out.println("What do you want to do? (Input the number for your decision)");
			System.out.println("1) Access the employee menu");
			System.out.println("2) Access the customer menu");
			System.out.println("3) Exit");
			System.out.println(" ");
			
			String result = sc.nextLine();
			switch(result) {
				case "1":
					displayEmployeeMainMenu();
					break;
				case "2":
					adminCustomerAccount = customerService.getAccount(adminCustomerAccount.getUsername());
					displayCustomerMainMenu(adminCustomerAccount);
					break;
				case "3":
					System.out.println("Thank you for checking in, my lord.");
					isRunning = false;
					break;
				default:
					System.out.println("Sorry, but that isn't an acceptable input");
					break;
			}
		}
		
		
		
	}
}
