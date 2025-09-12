package BankManagement;

import java.util.*;
public class Bank {
	private static int no=1;
	private String name,branch;
	private int bankId;
	private Input input;
	private BankManager bankManager;
	public String getName() {
		return name;
	}
	public String getBranch() {
		return branch;
	}
	public int getBankId() {
		return bankId;
	}
	public Bank(String name, String branch,Input input) {
		super();
		this.name = name;
		this.branch = branch;
		bankId=no++;
		this.input=input;
		bankManager=new BankManager(input);
		
	}
	public void run() {
		System.out.println("Welcome to "+name+" Bank");
		while(true) {
			displayMenu();
			int ch=input.getInt();
			switch(ch) {
			case 1: bankManager.createAccount(); break;
			case 2: bankManager.addCash(); break;
			case 3: bankManager.withdraw(); break;
			case 4: bankManager.ViewBalance(); break;
			case 5: bankManager.moneyTransfer(); break;
			case 6: bankManager.myTransactions(); break;
			case 7: System.out.println("Thank You"); return;
			default: System.out.println("Invalid choice");
			}
		}
		
	}
	
	private void displayMenu() {
		System.out.println("1. Create Accont ");
		System.out.println("2. Add cash");
		System.out.println("3. Withdraw");
		System.out.println("4. View Balance");
		System.out.println("5. Money Transfer");
		System.out.println("6. My Transactions");
		System.out.println("7. To Exit");
		System.out.println();
		System.out.println("Enter Your Choice");
		
	}
	
	
}
