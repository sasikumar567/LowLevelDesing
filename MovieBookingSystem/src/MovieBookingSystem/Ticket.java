package MovieBookingSystem;

public class Ticket {
	private static int no=1;
	private int id;
	private Seat seat;
	private Movie movie;
	private Screen screen;
	public Ticket(Seat seat, Movie movie, Screen screen) {
		id=no++;
		this.seat = seat;
		seat.setAvailable(false);
		this.movie = movie;
		this.screen = screen;
	}
	public int getId() {
		return id;
	}
	public Seat getSeat() {
		return seat;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", seat=" + seat.getsId() + ", movie=" + movie.getId() + ", screen=" + screen.getId() +" "+
	screen.getShow()+ " "+screen.getShow().getTime()+"]";
	}
	
}
