package MovieBookingSystem;
import java.util.*;
public class BookingManager {
 private Theatre theatre;
 private Input input;
 Map<Integer,Ticket> ticketMap;

public BookingManager(Theatre theatre,Input input) {
	super();
	this.theatre = theatre;
	this.input=input;
	ticketMap=new HashMap<Integer, Ticket>();
}
 void run(){
	 while(true) {
		 displayMenu();
		 int ch=input.getInt();
		 switch(ch) {
		 case 1: theatre.addMovie(); break;
		 case 2: bookTickets(); break;
		 case 3: searchByMovie(); break;
		 case 4: searchByLanguage(); break;
		 case 5: cancelTicket(); break;
		 case 6: System.out.println("Thank You"); return;
		 }
	 
	
	 }
 }
private void cancelTicket() {
	System.out.println("Enter your Ticket id: ");
	int id=input.getInt();
	Ticket ticket=ticketMap.get(id);
	if(ticket==null) {
		System.out.println("Ticket Not Found");
		return;
	}
	Seat seat=ticket.getSeat();
	seat.setAvailable(true);
	ticketMap.remove(id);
	System.out.println(ticket);
	System.out.println("Ticket Cancelled");
	
}
private void bookTickets() {
	System.out.println("1. Search By Movie");
	System.out.println("2. Search By Language");
	int ch=input.getInt();
	Movie movie=null;
	if(ch==1) {movie=searchByMovie();}
	else if(ch==2) {movie=searchByLanguage();}
	else {System.out.println("Invalid"); return;}
	if(movie==null) {
		System.out.println("Thank You");return;
	}
	movie.getScreens();
	System.out.println("Enter Show Id:");
	int id=input.getInt();
	Screen screen=movie.getScreen(id-1);
	bookTicket(screen,movie);
	
}
private void bookTicket(Screen screen,Movie movie) {
	
	while(true) {
		screen.printScreen();
		System.out.println("Enter Row: ");
		int r=input.getInt();
		System.out.println("Enter Column: ");
		int c=input.getInt();
		String id="R"+r+"C"+c;
		Seat seat=screen.getSeat(id);
		if(seat.isAvailable()) {
			Ticket ticket=new Ticket(seat, movie, screen);
			System.out.println(ticket);
			ticketMap.put(ticket.getId(), ticket);
		}
		else {
			System.out.println("Seat Already Booked");
		}
		System.out.println("DO you want Book More Tickets(1/0): ");
		int ch=input.getInt();
		if(ch!=1) {break;}
		
	}
	
	
}
private Movie searchByLanguage() {
	
	List<Movie> movies=new ArrayList<>(theatre.getMoviesByLang());
	if(movies.isEmpty()) {System.out.println("No Movies Available"); return null;}
	for(int i=0;i<movies.size();i++) {
		Movie movie=movies.get(i);
		System.out.println((1+i)+" "+movie.getName()+" "+movie.getLanguage());
	}
	System.out.println("Enter Movie Id: ");
	int id=input.getInt();
	if(id<1 || id> movies.size()) {System.out.println("Movie Not Available");return null;}
	Movie movie=movies.get(id-1);
	movie.getScreens();
	return movie;
	
	
}
private Movie searchByMovie() {
	Map<Integer,Movie> movieMap=theatre.getMovieMap();
	for(int i: movieMap.keySet()) {
		System.out.println(i+". "+movieMap.get(i).getName());
	}
	System.out.println("Enter Movie Id:");
	int id=input.getInt();
	Movie movie=movieMap.get(id);
	if(movie==null) {
		System.out.println("Movie Not Found");
		return searchByMovie();
	}
	System.out.println(movie.getName()+"  "+movie.getLanguage());
	movie.getScreens();
	return movie;
	
}
private void displayMenu() {
	System.out.println();
	System.out.println("1. Add Movies");
	System.out.println("2. Book Tickets");
	System.out.println("3. Search By Movie");
	System.out.println("4. Search By Language: ");
	System.out.println("5. Cancel Tickets");
	System.out.println("6. Exit");
	System.out.println("Enter Your choice: ");	
}
}
