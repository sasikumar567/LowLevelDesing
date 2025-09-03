package InventoryManagement;
import java.util.*;
public class Customer {
	private static int no=1;
	int custId;
	String name;
	List<Bill> purchases;
	Customer(String name){
		this.name=name;
		custId=no++;
		purchases=new ArrayList<>();
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Bill> getPurchases() {
		return purchases;
	}
	public void setPurchases(List<Bill> purchases) {
		this.purchases = purchases;
	}
	public void add(Bill bill) {
		purchases.add(bill);
		
	}
	
}
