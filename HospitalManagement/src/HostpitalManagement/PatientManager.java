package HostpitalManagement;
import java.util.*;


public class PatientManager {
	private Input input;
	Map<Integer,Patient>patientMap;
	public PatientManager(Input input) {
		patientMap=new HashMap<Integer, Patient>();
		this.input = input;
	}
	void visitDoctor(DoctorManager doctorManager){
		Patient patient=getPatient();
		String ward=doctorManager.getSpecialization();
		Doctor doctor=doctorManager.getDoctor(ward);
		doctorManager.addApointment(doctor,patient);
		
	}
	Patient addPatient(){
		System.out.println("Enter Your Name: ");
		String name=input.getString();
		Patient  patient=new Patient(name);
		patientMap.put(patient.getPatientId(), patient);
		return patient;
		
	}
	Patient getPatient() {
		System.out.print("Are you new Patient (1/0):");
		int ch=input.getInt();
		Patient patient;
		if(ch==0) {
			System.out.println("Enter Your Patient Id: ");
			int id=input.getInt();
			patient=patientMap.get(id);
			if(patient==null) {
				System.out.println("Sorry you are a new Patient");
				patient=addPatient();
			}
		}
		else {
			patient=addPatient();
		}
		return patient;
	}
	public void printPatients() {
		System.out.println("Our Patient List");
		for(int id: patientMap.keySet()) {
			System.out.println(patientMap.get(id));
		}
		
	}
	public void searchPatient() {
		System.out.println("Enter Patient id: ");
		int id=input.getInt();
		Patient patient=patientMap.get(id);
		if(patient==null) {
			System.out.println("Sorry Not found");
			return;
		}
		System.out.println(patient);
		List<Record> records=patient.getRecords();
		for(Record record: records) {
			record.print();
		}
		
	}
	

}
