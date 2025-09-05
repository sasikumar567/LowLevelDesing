package HotelManagement;




public class Room {
 private static int no=0;
 private int roomId;
 private String roomType;
 private double price;
 private boolean isBooked;
 
public Room( String roomType, double price) {
	
	this.roomId = no++;
	this.roomType = roomType;
	this.price = price;
	isBooked=false;
}
public int getRoomId() {
	return roomId;
}
public void setRoomId(int roomId) {
	this.roomId = roomId;
}
public String getRoomType() {
	return roomType;
}
public void setRoomType(String roomType) {
	this.roomType = roomType;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public boolean isBooked() {
	return isBooked;
}


@Override
public String toString() {
	return "Room [roomId=" + roomId + ", roomType=" + roomType + ", price=" + price + "]";
}
public void book() {
	isBooked=true;
	
}
public void checkOut() {
	isBooked=false;
}

 
}
