package HostpitalManagement;

public class HospitalManagement {
	private static Input input=new Input();
	private static DoctorManager docManager;
	private static PatientManager patientManager;
	public static void main(String[] args) {
		docManager=new DoctorManager(input);
		patientManager=new PatientManager(input);
		System.out.println("Add Doctor Details Before Getting Started: ");
		docManager.addDoctor();
		docManager.printDoctors();
		
		run(patientManager,docManager);
		
	}
	private static void run(PatientManager patientManager, DoctorManager docManager) {
		while(true) {
			displayMenu();
			int ch=input.getInt();
			switch(ch) {
			case 1:docManager.addDoctor(); break;
			case 2:patientManager.visitDoctor(docManager); break;
			case 3:docManager.searchDoctor(); break;
			case 4:patientManager.searchPatient(); break;
			case 5:docManager.printDoctors(); break;
			case 6:patientManager.printPatients(); break;
			default: System.out.println("Thank you");return;
			
			
			}
		}
		
	}
	private static void displayMenu() {
		System.out.println("1. add Doctor");
		System.out.println("2. Visit Doctor");
		System.out.println("3. Search Doctor");
		System.out.println("4. Search Patient");
		System.out.println("5. Doctors List");
		System.out.println("6. Patient List");
		System.out.println("Any other Numbers to exit");
		
	}

}
