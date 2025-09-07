package TaskManagement;

import java.util.HashMap;
import java.util.Map;

public class Main {
	private static	Input input;
	private static Map<Integer,User> userMap;
	 public static void main(String[] args) {
		 userMap=new HashMap<Integer, User>();
		 input=new Input();
		 run(input);
	 }

	private static void run(Input input) {
		while(true) {
		 displayMenu();
		 int ch=input.getInt();
		 switch(ch) {
		 case 1: logIn(); break;
		 case 2: signUp(); break;
		 case 3: System.out.println("Thank you"); return;
		 default: System.out.println("Invalid Choice");
		 }
		}
		
	}

	private static void logIn() {
		System.out.println("Enter Your User Id: ");
		int id=input.getInt();
		System.out.println("Enter Your Name: ");
		String name=input.getString();
		User user=userMap.get(id);
		if(user==null || !user.getName().equals(name)) {
			System.out.println("Invalid credentials");
			System.out.println("1. to create account 0. to retry");
			int ch=input.getInt();
			if(ch==1) {
				signUp();
				return;
			}
			else {
				logIn();
				return;
			}
		}
		user.run();
		
		
	}

	private static void signUp() {
		System.out.println("Enter your Name: ");
		String name=input.getString();
		User user=new User(name,input);
		System.out.println("Your User id: "+user.getId());
		userMap.put(user.getId(), user);
		user.run();
		
	}

	private static void displayMenu() {
		System.out.println("1. to Login");
		System.out.println("2. to SignUp");
		System.out.println("3. to Exit");
	}
}
