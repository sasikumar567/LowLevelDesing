package OnlineFoodOrder;

class Dish {

	private int dishId;
	private String name;
	private double price;
	public Dish(int id,String name, double price) {
		super();
		this.name = name;
		this.price = price;
		dishId=id;
	}
	public double getPrice() {
		return price;
	}
	public int getDishId() {
		return dishId;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", name=" + name + ", price=" + price + "]";
	}
}
