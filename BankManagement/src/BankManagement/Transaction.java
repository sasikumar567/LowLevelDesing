package BankManagement;

import java.time.LocalDate;

public class Transaction {
	private Account from,to;
	LocalDate date;
	double amount;
	@Override
	public String toString() {
		String sender=from==null?"Deposit": from.getName();
		String receiver=to==null?"Withdraw": to.getName();
		return "Transaction [from=" + sender + ", to=" + receiver + ", date=" + date + ", amount=" + amount + "]";
	}
	public Transaction(Account from, Account to, double amount) {
		super();
		this.from = from;
		this.to = to;
		this.amount = amount;
		date=LocalDate.now();
		if(from!=null) {
		from.addTransaction(this);}
		if(to!=null) {
		to.addTransaction(this);}
	}
}
