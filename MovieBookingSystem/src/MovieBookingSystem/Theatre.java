package MovieBookingSystem;
import java.util.*;
public class Theatre {
	private Input input;
 final private String name;
 private Map<Show,List<Screen>>showMap;
 private final int noOfScreens;
 private Map<Integer,Movie>movieMap;
 private Map<Language,Set<Movie>> languageMovieMap;
 
 Theatre(String name, int noOfScreens,Input input){
	 this.name=name;
	 this.noOfScreens=noOfScreens;
	 this.input=input;
	 movieMap=new HashMap<Integer, Movie>();
	 languageMovieMap=new HashMap<Language, Set<Movie>>();
	 showMap=new HashMap<Show, List<Screen>>();
	 showMap.put(Show.morning, new ArrayList<Screen>());
	 showMap.put(Show.Afternoon, new ArrayList<Screen>());
	 showMap.put(Show.Evening, new ArrayList<Screen>());
	 showMap.put(Show.Night, new ArrayList<Screen>());
 }
 void setScreens() {
	 System.out.println("Enter Screen Seatings: ");
	 for(int i=1;i<=noOfScreens;i++) {
		 System.out.println("Screen "+i+": ");
		 System.out.println("Enter No of Rows: ");
		 int rows=input.getInt();
		 System.out.println("Enter No of Columns: ");
		 int cols=input.getInt();
		 addScreen(i,rows,cols); 
	 }
 }
 public Map<Integer, Movie> getMovieMap() {
	return movieMap;
}
 void printScreen() {
	 print(Show.morning);
	 print(Show.Afternoon);
	 print(Show.Evening);
	 print(Show.Night);
	 
 }
private void print(Show show) {
	for(Screen screen: showMap.get(show)) {
		 screen.printScreen();
	 }
	
}
private void addScreen(int id,int rows, int cols) {
	showMap.get(Show.morning).add(new Screen(id,rows,cols,Show.morning));
	showMap.get(Show.Afternoon).add(new Screen(id,rows,cols,Show.Afternoon));
	showMap.get(Show.Evening).add(new Screen(id,rows,cols,Show.Evening));
	showMap.get(Show.Night).add(new Screen(id,rows,cols,Show.Night));
	
}
public void addMovies() {
	while(true) {
		addMovie();
		System.out.println("Do you want to Add More Movies(1/0): ");
		int ch=input.getInt();
		if(ch!=1) {return;}
	}
}
public Map<Language, Set<Movie>> getLanguageMovieMap() {
	return languageMovieMap;
}
void addMovie() {
	String name=getMovieName();
	Language lang=getLanguage();
	Movie newMovie=new Movie(name, lang);
	movieMap.put(newMovie.getId(), newMovie);
	languageMovieMap.putIfAbsent(newMovie.getLanguage(), new HashSet<Movie>());
	languageMovieMap.get(newMovie.getLanguage()).add(newMovie);
	System.out.println("Add Shows: ");
	while(true) {
		
		addShow(Show.morning, newMovie);
		addShow(Show.Afternoon, newMovie);
		addShow(Show.Evening, newMovie);
		addShow(Show.Night,newMovie);
		System.out.println("Do you want to add More SHow(1/0)");
		int ch=input.getInt();
		if(ch!=1) {
			printScreen();
			return;
		}
	}
}
private void addShow(Show show, Movie newMovie) {
	System.out.println(show);
	int screenNo=getScreenNo();
	if(screenNo<0) {return;}
	Screen screen=showMap.get(show).get(screenNo);
	
	Movie currentmovie=screen.getMovie();
	if(currentmovie!=null) {
		currentmovie.removeScreen(screen);
		if(currentmovie.canRemove()) {
			movieMap.remove(currentmovie.getId());
			languageMovieMap.get(currentmovie.getLanguage()).remove(currentmovie);
		}
	}
	screen.setMovie(newMovie);
	newMovie.addScreen(screen);
	
}
private int getScreenNo() {
	for(int i=1;i<=noOfScreens;i++) {
		System.out.println("Screen "+i);
	}
	System.out.println("0 to Skip");
	System.out.println("Enter Screen No: ");
	int no=input.getInt();
	return no-1;
}
protected Language getLanguage() {
	System.out.println(" Press 1. Tamil 2. English");
	int ch=input.getInt();
	if(ch==1) {
		return Language.tamil;
	}
	if(ch==2) {
		return Language.english;
	}
	System.out.println("Invalid choice");
	return getLanguage();
}
private String getMovieName() {
	System.out.println("Enter Movie Name: ");
	String name=input.getString();
	return name;
}
public Set<Movie> getMoviesByLang() {
	Language lang=getLanguage();
	return languageMovieMap.get(lang);
	
}
 
}
