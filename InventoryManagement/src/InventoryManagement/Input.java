package InventoryManagement;
import java.util.*;
public class Input {
	private static Scanner sc=new Scanner(System.in);
public static int getInt() {
	while(true) {
		try {
		int n=Integer.parseInt(sc.nextLine());
		return n;}
		catch(Exception e) {
			System.out.println("Please Enter Valid Number");
		}
	}
}
public static double getDouble() {
	while(true) {
		try {
		double n=Double.parseDouble(sc.nextLine());
		return n;}
		catch(Exception e) {
			System.out.println("Please Enter Valid Numberic Value");
		}
	}
}
public static String getString() {
	while(true) {
		try {
		String s=sc.nextLine();
		return s;}
		catch(Exception e) {
			System.out.println("Please Enter Valid Input");
		}
	}
}
}
