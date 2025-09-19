package OnlineFoodOrder;
import java.util.*;
public class Managaer {
	private List<Restaurent> restaurents;
	private Input input;
	private PriorityQueue<DeliveryMan> deliveryPartners;
	private Map<Integer,Customer> customerMap;
	public Managaer(Input input) {
		customerMap=new HashMap<Integer, Customer>();
		this.input=input;
		restaurents=new ArrayList<Restaurent>();
		deliveryPartners=new PriorityQueue<DeliveryMan>((a,b)->a.orderSize()-b.orderSize());
	}
	public void addRestaurents() {
		while(true) {
			System.out.println("Enter Restaurent Name: ");
			String name=input.getString();
			Restaurent restaurent=new Restaurent(input,name);
			System.out.println("Add Menu:");
			addDish(restaurent);
			System.out.println("Do you want to add More Restaurents(1/0): ");
			
			restaurents.add(restaurent);
			int ch=input.getInt();
			if(ch!=1) {return;}
		}
		
	}
	private void addDish(Restaurent restaurent) {
		while(true) {
			System.out.println("Enter Dish Name: ");
			String name=input.getString();
			System.out.println("Enter price: ");
			Double price=input.getDouble();
			restaurent.addDish(name, price);
			System.out.println("Do you want to add More Dish(1/0): ");
			int ch=input.getInt();
			if(ch!=1) {return;}
		}
	}
	
	void viewMenu() {
		printRestaurents();
		int id=input.getInt();
		restaurents.get(id-1).printMenu();
		
	}
	private void printRestaurents() {
		for(int i=0;i<restaurents.size();i++) {
			System.out.println((i+1)+" ."+restaurents.get(i).getName());
		}	
	}
	public void orderFoods() {
		List<Order> orders=new ArrayList<Order>();
		while(true) {
			printRestaurents();
			System.out.println("Enter Restaurent id: ");
			int restId=input.getInt();
			Restaurent restaurent=restaurents.get(restId-1);
			orders.addAll(restaurent.takeOrder());
			System.out.println("press 1 to continue");
			int ch=input.getInt();
			if(ch!=1) {break;}
		}
		DeliveryMan deliveryMan=deliveryPartners.poll();
		Customer customer=getCustomer();
		Bill bill=new Bill(deliveryMan, orders, customer);
		deliveryPartners.add(deliveryMan);
		
	}
	private Customer getCustomer() {
		System.out.println("Are You new Customer(1/0): ");
		int ch=input.getInt();
		if(ch==1) {
			return createCustomer();
		}
		else {
			System.out.println("Enter Cusotmer id: ");
			int id=input.getInt();
			Customer customer= customerMap.get(id);
			if(customer==null) {
				System.out.println("You are new Customer");
				return createCustomer();
			}
			return customer;
		}
	}
	private Customer createCustomer() {
		System.out.println("Enter Your Name: ");
		String name=input.getString();
		Customer customer=new Customer(name);
		System.out.println(customer);
		customerMap.put(customer.getId(), customer);
		return customer;
	}
	public void addDeliveryMan() {
		while(true) {
			System.out.println("Add Delivery Partners:");
			System.out.println("Enter Name: ");
			String name=input.getString();
			System.out.println("Enter Phone Number: ");
			String pnNo=input.getString();
			DeliveryMan deliveryMan=new DeliveryMan(name, pnNo);
			System.out.println(deliveryMan);
			deliveryPartners.add(deliveryMan);
			System.out.println("press 1 to add More: ");
			int ch=input.getInt();
			if(ch!=1) {return;}
		}
		
		
		
	}
	public void myOrder() {
		System.out.println("Enter customer id: ");
		int id=input.getInt();
		Customer customer=customerMap.get(id);
		if(customer==null) {
			System.out.println("Not Found");
			return;
		}
		customer.printOrders();
		
	}
	
	
}
