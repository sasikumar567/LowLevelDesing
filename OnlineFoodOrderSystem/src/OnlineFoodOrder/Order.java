package OnlineFoodOrder;
import java.util.*;
public class Order {
	private Restaurent restaurent;
	private Dish dish;
	private int count;
	private double total;
	public Order(Dish dish,Restaurent restaurent,int count) {
		this.dish=dish;
		this.count=count;
		this.restaurent=restaurent;
		total=count*dish.getPrice();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [restaurent=" + restaurent.getName() + ", dish=" + dish.getName() + ", count=" + count + ", total=" + total + "]";
	}
	public Restaurent getRestaurent() {
		return restaurent;
	}
	public Dish getDish() {
		return dish;
	}
	public int getCount() {
		return count;
	};
	public double getTotal() {
		return total;
	}
}
