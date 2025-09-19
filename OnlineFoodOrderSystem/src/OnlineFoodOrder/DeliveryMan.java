package OnlineFoodOrder;
import java.util.*;
public class DeliveryMan {
	private static int no=1;
	private int id;
	private String name;
	private String phNo;
	List<Bill> orders;
	DeliveryMan(String name,String phno){
		this.name=name;
		this.phNo=phno;
		id=no++;
		orders=new ArrayList<Bill>();
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhNo() {
		return phNo;
	}
	public int orderSize() {
		return orders.size();
	}
	@Override
	public String toString() {
		return "DeliveryMan [id=" + id + ", name=" + name + ", phNo=" + phNo + "]";
	}
	void addBill(Bill bill){
		orders.add(bill);
	}
}
