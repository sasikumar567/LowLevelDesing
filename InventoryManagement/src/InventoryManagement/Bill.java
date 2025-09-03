package InventoryManagement;
import java.time.LocalDate;
import java.util.*;
public class Bill {
	private static int no=1;
	int billNo;
	Map<Product,Integer> products;
	LocalDate date;
	Customer customer;
	float amount;
	Bill(Map<Product,Integer> products, Customer customer){
		date=LocalDate.now();
		this.products=products;
		this.customer=customer;
		amount=calculateAmount(products);
		customer.add(this);
	}
	private float calculateAmount(Map<Product, Integer> products) {
			float total=0;
			for(Product product: products.keySet()) {
				total+=(product.getPrice()*products.get(product));
			}
			return total;
	}
	void print() {
		System.out.println(date+"=>" +customer.getName());
		for(Product product: products.keySet()) {
			System.out.println(product.getProdName()+" price: "+product.getPrice()+" Quanitity: "+products.get(product));
		}
		System.out.println("Total Bill:"+amount);
	}
	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return date;
	}
}
