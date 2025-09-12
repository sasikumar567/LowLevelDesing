package BankManagement;

public class Main {
	private static Input input;
	public static void main(String[] args) {
		input=new Input();
		System.out.println("Enter Your Bank Name: ");
		String name=input.getString();
		System.out.println("Enter Branch Name: ");
		String branch=input.getString();
		Bank bank=new Bank(name,branch,input);
		bank.run();
		input.close();
	}
}
