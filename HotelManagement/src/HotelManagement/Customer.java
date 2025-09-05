package HotelManagement;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private static int no=1;
	private int custId;
	private String name;
	private List<Booking> bookings;
	Customer(String name){
		this.name=name;
		custId=no++;
	    bookings=new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	
	public int getCustId() {
		return custId;
	}
	public void addBooking(Booking booking) {
		bookings.add(booking);
		
	}
	void printBookings() {
		for(Booking booking: bookings) {
			System.out.println(bookings);
		}
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + "]";
	}

}
