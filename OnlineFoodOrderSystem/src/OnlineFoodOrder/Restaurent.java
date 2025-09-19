package OnlineFoodOrder;
import java.util.*;
public class Restaurent {
	private static int no=1;
	private int id;
	private int dishId;
	private Map<Integer,Dish> menu;
	private String name;
	private Input input;
	public Restaurent(Input input, String name) {
		this.input=input;
		id=no++;
		dishId=1;
		this.name=name;
		menu=new HashMap<Integer, Dish>();
	}
	void addDish(String name,double price){
		Dish dish=new Dish(dishId++,name,price);
		menu.put(dish.getDishId(),dish);
	}
	void printMenu() {
		for(int id: menu.keySet()) {
			System.out.println(menu.get(id));
		}
	}
	public String getName() {
		return name;
	}
	public List<Order> takeOrder() {
		List<Order>orders=new ArrayList<>();
		while(true) {
			printMenu();
			System.out.println("Enter Dish Id: ");
			int id=input.getInt();
			System.out.println("Enter Count: ");
			int count=input.getInt();
			Order order=new Order(menu.get(id), this, count);
			orders.add(order);
			System.out.println("press 1 to add more dish");
			int ch=input.getInt();
			if(ch!=1) {return orders;}
		}
	}
}
