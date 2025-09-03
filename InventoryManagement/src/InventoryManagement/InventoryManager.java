package InventoryManagement;
import java.time.LocalDate;
import java.util.*;
public class InventoryManager {
	private static Map<LocalDate,List<Bill>> dailySales=new HashMap<LocalDate, List<Bill>>();
	public static void main(String[] args) {
		Map<Integer,Product>productMap=initialize();
		Map<Integer,Customer> customerMap=new HashMap<>();

		displayProducts(productMap);
		run(productMap,customerMap);
	}
	private static void run(Map<Integer, Product> productMap, Map<Integer, Customer> customerMap) {
		while(true) {
			displayMenu();
			int ch=Input.getInt();
			switch(ch) {
			case 1: addProduct(productMap);
			break;
			case 2: purchase(productMap,customerMap); break;
			case 3: dailySalesReport(); break;
			case 4: searchProduct(productMap); break;
			case 5: System.out.print("Thank you for your visit");return;
			default: System.out.println("Invlaid choice");
			}
		}
		
	}
	private static void searchProduct(Map<Integer, Product> productMap) {
		Product product=getProduct(productMap);
		if(product==null) {
			System.out.println("Product not exist");
			return;
		}
		System.out.println(product.toString());
		
	}
	private static void dailySalesReport() {
		for(LocalDate date: dailySales.keySet()) {
			System.out.println();
			System.out.println(" 	"+date);
			List<Bill> bills=dailySales.get(date);
			for(Bill bill: bills) {
				bill.print();
			}
		}
		
	}
	private static void purchase(Map<Integer, Product> productMap, Map<Integer, Customer> customerMap) {
		Map<Product,Integer>purchase=new HashMap<>();
		while(true) {
			displayProducts(productMap);
			Product prod=getProduct(productMap);
			if(prod==null) {
				System.out.println("Product Not Exist");
				continue;
			}
			System.out.println("Enter Count");
			int count=Input.getInt();
			if(count>prod.getQuantity()) {
				System.out.println("Sorry  Product Unavailable for your requirements");
				System.out.println(prod.getProdName()+" is not added in the cart");
				continue;
			}
			prod.reduceProduct(count);
			purchase.put(prod, purchase.getOrDefault(prod,0)+count);
			System.out.println("press 1 to Add more Products else enter any number: ");
			int ch=Input.getInt();
			if(ch!=1) {break;}
		}	
		Bill bill=generateBill(purchase,customerMap);
		dailySales.putIfAbsent(bill.getDate(), new ArrayList<>());
		dailySales.get(bill.getDate()).add(bill);
		bill.print();
	}
	private static Bill generateBill(Map<Product, Integer> purchase, Map<Integer, Customer> customerMap) {
		Customer customer=getCustomer(customerMap);
		Bill bill=new Bill(purchase,customer);
		
		return bill;
	}
	private static Customer getCustomer(Map<Integer, Customer> customerMap) {
		Customer customer;
		System.out.println("Are you New Customer(1/0):");
		int ch=Input.getInt();
		if(ch==1) {
			customer=getNewCusotmer();
			customerMap.put(customer.getCustId(), customer);
		}
		else {
			System.out.println("Enter Your Customer id: ");
			int id=Input.getInt();
			customer=customerMap.get(id);
			if(customer==null) {
				System.out.println("Sorry You are a new Cusotmer to Us");
				customer=getNewCusotmer();
				customerMap.put(customer.getCustId(), customer);
			}
			else {
				System.out.println("Welcom Back "+customer.getName());
			}
		}
		return customer;
	}
	private static Customer getNewCusotmer() {
		System.out.println("Enter Your Name:" );
		String name=Input.getString();
		Customer customer=new Customer(name);
		System.out.println("Welcome "+name+" Your Customer id: "+ customer.getCustId());
		
		return customer;
	}
	private static void addProduct(Map<Integer, Product> productMap) {
		System.out.println("1.TO add new Product 2.To add ExistingProduct");
		int ch=Input.getInt();
		if(ch==1) {
			Product product=getNewProduct();
			productMap.put(product.getProdId(), product);
		}
		else if(ch==2) {
			addProductQuantity(productMap);
		}
		else {
			System.out.println("Invalid CHoice");return;
		}
		System.out.println("Product Added Successfully");
		
	}
	private static void addProductQuantity(Map<Integer, Product> productMap) {
		
		Product prod=getProduct(productMap);
		if(prod==null) {
			System.out.println("Product not Exist Please Add the Product in Menu");
			prod=getNewProduct();
			productMap.put(prod.getProdId(), prod);
			return;
		}
		System.out.println("Enter Quanitity: ");
		int quantity=Input.getInt();
		prod.addProduct(quantity);
	}
	private static Product getProduct(Map<Integer, Product> productMap) {
		System.out.println("Enter Product Id:");
		int id=Input.getInt();
		return productMap.get(id);
	}
	private static void displayMenu() {
		System.out.println("1.Add Product");
		System.out.println("2.Purchase Product");
		System.out.println("3.Daily Sales Report");
		System.out.println("4. Search Product");
		System.out.println("5. Exit");
		System.out.println();
		System.out.println("Enter Your CHoice: ");
		
	}
	private static Map<Integer,Product> initialize() {
		Map<Integer,Product> map=new HashMap<Integer, Product>();
		System.out.println("Welcome to Inventory Manager");
		System.out.println("Please add some Products to get started=>");
		
		while(true) {
			Product product=getNewProduct();
			map.put(product.getProdId(), product);
			System.out.println("Do you want to add more products(1/0):");
			int ch=Input.getInt();
			if(ch!=1) {return map;}
		}
	}
	private static Product getNewProduct() {
		System.out.println();
		System.out.println("Enter Product Name:");
		String name=Input.getString();
		System.out.println("Enter Price: ");
		Double price=Input.getDouble();
		System.out.println("Enter Quanitity");
		int quantity=Input.getInt();
		return new Product(name,price,quantity);
	}
	static private void displayProducts(Map<Integer,Product>productMap) {
		for(int id: productMap.keySet()) {
			System.out.println(productMap.get(id));
		}
		
	}
	

}
