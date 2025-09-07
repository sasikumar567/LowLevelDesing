package MovieBookingSystem;

public enum Show {
	morning("10.00 A.M"),
	Afternoon("2.00 P.M"),
	Evening("6.00 P.M"),
	Night("9.00 P.M");
	private String time;
	Show(String time){
		this.time=time;
	}
	public String getTime() {
		return time;
	}
	
}
