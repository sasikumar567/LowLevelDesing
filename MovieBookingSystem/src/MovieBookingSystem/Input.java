package MovieBookingSystem;

import java.util.Scanner;
public class Input {
	private Scanner sc;
	
	Input(){
		sc=new Scanner(System.in);
	}
	int getInt(){
		while(true) {
			try {
			int n=Integer.parseInt(sc.nextLine());
			return n;
			}
			catch (Exception e) {
				System.out.println("Enter Numeric Value");
			}
		}
	}
	double getDouble(){
		while(true) {
			try {
			double n=Double.parseDouble(sc.nextLine());
			return n;
			}
			catch (Exception e) {
				System.out.println("Enter Numeric Value");
			}
		}
	}
	String getString() {
		String str=sc.nextLine();
		return str;
	}
	void close() {
		sc.close();
	}
}

