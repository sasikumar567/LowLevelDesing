package MovieBookingSystem;

public class Seat {
	private SeatType type;
	private String sId;
	boolean isAvailable;
	Seat(int r,int c, SeatType type){
	this.type=type;
	sId="R"+r+"C"+c;
	isAvailable=true;
	}
	public String getsId() {
		return sId;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	

}
