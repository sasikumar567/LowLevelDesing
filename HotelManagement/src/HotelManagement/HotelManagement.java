package HotelManagement;

public class HotelManagement {
	private static Input input;
	
	public static void main(String[] args) {
		input=new Input();
		System.out.println("Welcome to HotelManagement Application");
		System.out.println("Enter Your Hotel Name: ");
		String name=input.getString();
		Hotel hotel=new Hotel(name,input);
		System.out.println("Please add Rooms: ");
		hotel.addRoom();
		hotel.printRooms();
		hotel.run();
		
	}

}
