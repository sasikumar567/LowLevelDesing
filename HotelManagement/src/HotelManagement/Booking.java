package HotelManagement;

import java.time.Duration;
import java.time.LocalDateTime;

public class Booking {
	private static int no=1;
	private Room room;
	private Customer customer;
	private int bookingId;
	private LocalDateTime bookingTime;
	private LocalDateTime checkoutTime;
	public Booking(Room room, Customer customer) {
		super();
		this.room = room;
		this.customer = customer;
		bookingId=no++;
		bookingTime=LocalDateTime.now();
		room.book();
		customer.addBooking(this);
		checkoutTime=null;
	}
	@Override
	public String toString() {
		return "Booking [room=" + room.getRoomId() + ", customer=" + customer.getName() + ", bookingId=" + bookingId + ", bookingTime="
				+ bookingTime + ", checkoutTime=" + checkoutTime + "]";
	}
	public int getBookingId() {
		return bookingId;
	}
	public double checkOut() {
		
		checkoutTime=LocalDateTime.now();
		Duration dif=Duration.between(bookingTime, checkoutTime);
		room.checkOut();
		
		return Math.max((dif.toHours()/24)*room.getPrice(),room.getPrice());
	}
	

}
