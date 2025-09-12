package BankManagement;
import java.util.*;
public class Account {
	private static int no=1;
	private String name;
	private String password;
	private int accntNo;
	private double balance;
	List<Transaction> transactions;
	
	public Account(String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.balance = 0;
		accntNo=no++;
		transactions=new ArrayList<Transaction>();
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", accntNo=" + accntNo +"]";
	}
	public int getAccntNo() {
		return accntNo;
	}
	
	public void addCash(double amount) {
		if(amount<0) {
			System.out.println("Invalid");
			return;
		}
		balance+=amount;
		
	}
	public boolean checkPassword(String password2) {
		// TODO Auto-generated method stub
		return this.password.equals(password2);
	}
	public boolean withdraw(double amount) {
		if(amount>balance) {
			System.out.println("Insufficient balance");
			return false;
		}
		balance-=amount;
		printBalance();
		return true;
	}
	public void printBalance() {
		// TODO Auto-generated method stub
		System.out.println(" Current Balance: "+balance);
		
	}
	public String getName() {
		return name;
	}
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
		
	}
	public void printTransactions() {
		for(Transaction transaction: transactions) {
			System.out.println(transaction);
		}
	}
}
