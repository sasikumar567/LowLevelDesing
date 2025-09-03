package InventoryManagement;

public class Product {
	private static int no=1;
	int prodId;
	String prodName;
	double price;
	int quantity;
	Product(String name, double price, int quantity){
		prodId=no++;
		this.prodName=name;
		this.price=price;
		this.quantity=quantity;
		System.out.println(prodName+" Product Id: "+prodId);
	}
	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	void reduceProduct(int count){
		if(count>quantity) {
			System.out.println("Insufficient Stocks");
			return;
		}
		quantity-=count;
		System.out.println("Remaining "+prodName+" stocks: "+quantity);
		
	}
	 void addProduct(int quantity2) {
		quantity+=quantity2;
		
	}

}
