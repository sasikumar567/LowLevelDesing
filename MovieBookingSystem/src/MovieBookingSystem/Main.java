package MovieBookingSystem;

public class Main {
 private static Input input;
 public static void main(String[] args) {
	 input=new Input();
	 System.out.println("Welcome to my Application");
	 System.out.println("Enter Your Theatre Name: ");
	 String name=input.getString();
	 System.out.println("Enter No of Screens: ");
	 int noOfScreens=input.getInt();
	 Theatre theatre=new Theatre(name,noOfScreens,input);
	 theatre.setScreens();
	 theatre.printScreen();
	 System.out.println("Add Movies: ");
	 theatre.addMovies(); 
	 BookingManager bookingManager=new BookingManager(theatre, input);
	 bookingManager.run();
 }
}
