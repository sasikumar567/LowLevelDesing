package MovieBookingSystem;
import java.io.ObjectInputStream.GetField;
import java.util.*;
public class Movie {
	private static int no=1;
	private int id;
	private String  name;
	Language language;
	List<Screen> screens;
	public Movie(String name, Language language) {
		super();
		this.name = name;
		this.language = language;
		screens=new ArrayList<Screen>();
		id=no++;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	void addScreen(Screen screen){
	 screens.add(screen);
	}
	void removeScreen(Screen screen) {
		screens.remove(screen);
	}
	public Language getLanguage() {
		return language;
	}
	boolean canRemove() {
		return screens.isEmpty();
	}
	 Screen getScreen(int id){
		return screens.get(id);
	}
	
	public void getScreens() {
		for(int i=0;i<screens.size();i++) {
			Screen screen=screens.get(i);
			System.out.println("Show Id: "+(i+1)+"  Screen"+screen.getId()+"  "+screen.getShow()+" "+screen.getShow().getTime());
		}
	}
}
