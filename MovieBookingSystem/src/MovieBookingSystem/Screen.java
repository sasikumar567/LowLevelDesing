package MovieBookingSystem;
import java.util.*;
public class Screen {
	private int noOfRows,noOfCols;
	private Map<String,Seat> seatsMap;
	Show show;
	private int id;
	Movie movie;
	public Screen(int id,int rows,int cols ,Show show) {
		this.id=id;
		this.show=show;
		noOfCols=cols; noOfRows=rows;
		seatsMap=new HashMap<String, Seat>();
		for(int i=1;i<noOfRows/2;i++) {
			for(int j=1;j<=noOfCols;j++) {
				Seat seat= new Seat(i,j, SeatType.Regular);
				seatsMap.put(seat.getsId(), seat);
			}
		}
		for(int i=noOfRows/2;i<=noOfRows;i++) {
			for(int j=1;j<=noOfCols;j++) {
				Seat seat= new Seat(i,j, SeatType.Premium);
				seatsMap.put(seat.getsId(), seat);
			}
		}
	movie=null;
		
	}
	public Show getShow() {
		return show;
	}
	public int getId() {
		return id;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Movie getMovie() {
		return movie;
	}
	void printScreen(){
		System.out.println("Screen "+id);
		if(movie!=null) {
		System.out.println(movie.getName());}
		System.out.println(show+" "+show.getTime());
		for(int i=1;i<=noOfRows; i++) {
			System.out.print("Row "+i+"->");
			for(int j=1;j<=noOfCols;j++) {
				Seat seat=seatsMap.get("R"+i+"C"+j);
				String c=(seat.isAvailable()?String.valueOf(j):"X");
				System.out.print(c+" ");
			}
			System.out.println();
		}
	}
	
	public Seat getSeat(String id2) {
		// TODO Auto-generated method stub
		return seatsMap.get(id2);
	}

}
