package OnlineFoodOrder;
import java.util.*;
public class Customer {
	private  static int no=1;
	private int id;
	private String name;
	List<Bill> orders;
	public Customer(String name) {
		super();
		this.name = name;
		id=no++;
		orders=new ArrayList<Bill>();
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
	void addBill(Bill bill){
		orders.add(bill);
	}
	public void printOrders() {
		for(Bill bill: orders) {
			bill.printBill();
		}
		
	}
	
}
