package BankManagement;

import java.util.HashMap;
import java.util.Map;

public class BankManager {
	private Map<Integer,Account> accountMap;
	private Input input;
	public BankManager(Input input) {
		this.input=input;
		accountMap=new HashMap<Integer, Account>();
	}
	public void myTransactions() {
		Account account=getAccount();
		if(account==null) {System.out.println("Account Not Found"); return;}
		if(!verifyLogin(account)) {
			System.out.println("Invalid Credintials");
			return;
		}
		account.printTransactions();
		
	}
	public void moneyTransfer() {
		Account sender=getAccount();
		if(sender==null) {System.out.println("Account Not Found"); return;}
		if(!verifyLogin(sender)) {
			System.out.println("Invalid Credintials");
			return;
		}
		System.out.print("Enter Receiver ");
		Account receiver=getAccount();
		if(receiver==null) {System.out.println("Account Not Found"); return;}
		double amount=getAmount();
		if(sender.withdraw(amount)) {
			receiver.addCash(amount);
			Transaction transaction=new Transaction(sender, receiver, amount);
			System.out.println(transaction);
			System.out.println("Transaction Successfull");
			return;
		}
		System.out.println("Transaction failed");
		
	}
	public void ViewBalance() {
		Account account=getAccount();
		if(account==null) {System.out.println("Account Not Found"); return;}
		if(!verifyLogin(account)) {
			System.out.println("Invalid Credintials");
			return;
		}
		account.printBalance();
		
	}
	public void withdraw() {
		Account account=getAccount();
		if(account==null) {
			System.out.println("Account not Found");
		}
		if(!verifyLogin(account)) {
			System.out.println("Invalid Credintials");
			return;
		}
		double amount=getAmount();
		if(account.withdraw(amount)) {
			Transaction transaction=new Transaction(account, null, amount);
			System.out.println(transaction);
		}
		
	}
	private double getAmount() {
		System.out.println("Enter Amount: ");
		double amount=input.getDouble();
		return amount;
	}
	private boolean verifyLogin(Account account) {
		System.out.println("Enter PassWord");
		String password=input.getString();
		 return account.checkPassword(password);
			
	}
	public void addCash() {
		Account account=getAccount();
		if(account==null) {
			System.out.println("Account not Found"); return;
		}
		double amount=getAmount();
		account.addCash(amount);
		Transaction transaction=new Transaction(null, account, amount);
		System.out.println(transaction);
			
	}
	private Account getAccount() {
		System.out.println("Account Number: ");
		int accntNo=input.getInt();
		return accountMap.get(accntNo);
		
	}
	public void createAccount() {
		System.out.println("Enter Name: ");
		String name=input.getString();
		System.out.println("Enter Password: ");
		String password=input.getString();
		Account account= new Account(name, password);
		System.out.println(account);
		accountMap.put(account.getAccntNo(), account);
		
	}
}
