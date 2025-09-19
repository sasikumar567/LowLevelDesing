package OnlineFoodOrder;

public class Main {
	private static Input input;
	public static void main(String[] args) {
		input=new Input();
		System.out.println("Please Add Restaurents: ");
		Managaer manager=new Managaer(input);
		manager.addRestaurents();
		manager.addDeliveryMan();
		run(manager);
	}
	public static void run(Managaer manager) {
		while(true) {
			displayMenu();
			int ch=input.getInt();
			switch (ch) {
			case 1:{
				manager.viewMenu(); break;
			}
			case 2:{
				manager.orderFoods(); break;
			}
			case 3:{
				manager.myOrder(); break;
			}
			case 4:{
				return;
			}
			default:
				System.out.println("Invalid choice");
			}
			
		}
		
	}
	private static void displayMenu() {
		System.out.println("1. to View Menu");
		System.out.println("2. Order Foods");
		System.out.println("3. My Orders");
		System.out.println("4. Exit");
		System.out.println("Enter Your Choice");
		
	}
}
