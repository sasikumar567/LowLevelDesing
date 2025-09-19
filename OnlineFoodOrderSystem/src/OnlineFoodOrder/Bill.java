package OnlineFoodOrder;
import java.util.*;
public class Bill {
	private static int no=1;
	private int id;
	private DeliveryMan deliveryMan;
	private List<Order> orders;
	private Customer customer;
	private double total;
	public Bill(DeliveryMan deliveryMan, List<Order> orders, Customer customer) {
		super();
		id=no++;
		this.deliveryMan = deliveryMan;
		this.orders = orders;
		this.customer = customer;
		
		total=0;
		printBill();
		customer.addBill(this);
		deliveryMan.addBill(this);
	}
	void printBill(){
		System.out.println("Bill: "+id);
		System.out.println(customer);
		System.out.println("Your Orders: ");
		for(Order order: orders) {
			System.out.println(order);
			total+=order.getTotal();
		}
		System.out.println(deliveryMan);
	}
}
