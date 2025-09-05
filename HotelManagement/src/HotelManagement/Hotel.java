package HotelManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
	Input input;
	private static int  no=0;
	private int hotelNo;
	private String name;
	Map<String,List<Room>> roomMap;
	Map<Integer,Customer> customerMap;
	Map<LocalDate, List<Booking>> dailyBookings;
	Map<LocalDate,List<Booking>> dailyCheckouts;
	Map<Integer,Booking> bookingMap;
	Hotel(String name,Input input){
		bookingMap=new HashMap<Integer, Booking>();
		this.input=input;
		hotelNo=no++;
		this.name=name;
		roomMap=new HashMap<String, List<Room>>();
		roomMap.put("Single", new ArrayList<>());
		roomMap.put("Double", new ArrayList<>());
		roomMap.put("Delux", new ArrayList<>());
		customerMap=new HashMap<Integer, Customer>();
		dailyBookings=new HashMap<LocalDate, List<Booking>>();
		dailyCheckouts=new HashMap<LocalDate, List<Booking>>();
		
	}
	void addRoom() {
		while(true) {
		String roomType=getRoomType();
		System.out.println("Enter Price perDay: ");
		double price=input.getDouble();
		roomMap.get(roomType).add(new Room(roomType,price));
		System.out.println("Do you want to add more ROoms(1/0): ");
		int ch=input.getInt();
		if(ch==1) {
			continue;
		}
		return;
		}
		
	}
	private String getRoomType() {
		System.out.println("1. Single /2. Double/ 3.Delux");
		while(true) {
		System.out.println("Enter Room type: ");
		int ch=input.getInt();
		if(ch==1) {
			return "Single";
		}
		if(ch==2) {return "Double";}
		if(ch==3) {return "Delux";}
		System.out.println("Invalid choice");
		}
		
	}
	public void printRooms() {
		System.out.println();
		for(String type: roomMap.keySet()) {
			for(Room room: roomMap.get(type)) {
				System.out.println(room);
			}
		}
		
	}
	public void run() {
		System.out.println("Welcome to "+name);
		while(true) {
		displayMenu();
		int ch=input.getInt();
		switch(ch) {
		case 1:addRoom(); break;
		case 2:bookRoom(); break;
		case 3:checkOutRoom(); break;
		case 4:checkAvailableRooms(); break;
		case 5:customerBookings(); break;
		case 6: DailyReports(); break;
		case 7: System.out.println("Thank You"); input.close(); return;
		default: System.out.println("Invalid choice");
		}
		}	
	}
	private void DailyReports() {
		List<Booking> bookings=dailyBookings.get(LocalDate.now());
		List<Booking> checkouts=dailyCheckouts.get(LocalDate.now());
		if(bookings==null || bookings.isEmpty()) {
			System.out.println("No Booking Today");
		}
		else {
			System.out.println("Today's Bookings: ");
			printBookings(bookings);
		}
		if(checkouts==null || checkouts.isEmpty()) {
			System.out.println("No Checkouts Today");
		}
		else {
			System.out.println("Today's CheckOuts: ");
			printBookings(bookings);
		}
		
		
	}
	private void printBookings(List<Booking> bookings) {
		for(Booking booking: bookings) {
			System.out.println(booking);
		}
		
	}
	private void customerBookings() {
		Customer customer=getCustomer();
		if(customer==null) {
			System.out.println("Customer not found");return;
		}
		System.out.println(customer);
		customer.printBookings();
		
	}
	private void checkAvailableRooms() {
		System.out.println();
		for(String type: roomMap.keySet()) {
			for(Room room: roomMap.get(type)) {
				if(!room.isBooked()) {
				System.out.println(room);}
			}
		}
		
	}
	private void checkOutRoom() {
		System.out.println("Enter Your Booking Id: ");
		int id=input.getInt();
		Booking booking=bookingMap.get(id);
		if(booking==null) {
			System.out.println("Sorry Booking does not exist");
			return;
		}
		
		dailyCheckouts.putIfAbsent(LocalDate.now(), new ArrayList<Booking>());
		dailyCheckouts.get(LocalDate.now()).add(booking);
		System.out.println("Your Bill: "+booking.checkOut());
	}
	private void bookRoom() {
		System.out.println("Are you a New Cusotme(1/0):");
		int ch=input.getInt();
		Customer customer;
		if(ch==1) {
			customer=registerCustomer();
		}
		else {
		customer=getCustomer();
		if(customer==null) {
			System.out.println("You are a new Cusotmer");
			customer=registerCustomer();
		}}
		String roomType=getRoomType();
		Room room=getRoom(roomType);
		if(room==null ) {
			System.out.println("Sorry No rooms Available in"+ roomType);
			return;
		}
		Booking booking=new Booking(room, customer);
		bookingMap.put(booking.getBookingId(), booking);
		dailyBookings.putIfAbsent(LocalDate.now(), new ArrayList<>());
		dailyBookings.get(LocalDate.now()).add(booking);
		System.out.println(booking);
		
		
	}
	private Room getRoom(String roomType) {
		List<Room> rooms=roomMap.get(roomType);
		for(Room room: rooms) {
			if(!room.isBooked()) {
				return room;
			}
		}
		return null;
	}
	private Customer registerCustomer() {
		System.out.println("Enter your name: ");
		String name=input.getString();
		Customer customer=new Customer(name);
		System.out.println("You Customer id: "+customer.getCustId());
		customerMap.put(customer.getCustId(), customer);
		return customer;
	}
	private Customer getCustomer() {
		System.out.println("Enter your cusotmer id: ");
		int id=input.getInt();
		return customerMap.get(id);
	}
	private void displayMenu() {
		System.out.println("1. to add Rooms");
		System.out.println("2. Book Rooms");
		System.out.println("3. checkOut Rooms");
		System.out.println("4. Check Available Rooms");
		System.out.println("5. Customer Bookings");
		System.out.println("6. DailyReport");
		System.out.println("7. Exit");
		System.out.print("Enter your choice: ");
		
	}
	

}
