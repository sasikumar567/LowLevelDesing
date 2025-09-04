package HostpitalManagement;
import java.util.*;


public class DoctorManager {
	Input input;
	Map<Integer,Doctor> doctorMap;
	Map<String,PriorityQueue<Doctor>>specializationMap;
	DoctorManager(Input input){
		specializationMap=new HashMap<String, PriorityQueue<Doctor>>();
		this.input=input;
		doctorMap=new HashMap<Integer, Doctor>();
	}
	
	void addDoctor(){
		List<Doctor> doctors=getDoctorDetails();
		for(Doctor doctor: doctors) {
		specializationMap.putIfAbsent(doctor.getSpecialization(), new PriorityQueue<>((a,b)-> a.getRecords().size()-b.getRecords().size()));
		specializationMap.get(doctor.getSpecialization()).add(doctor);
		doctorMap.put(doctor.getDocId(), doctor);}	
	}

	private List<Doctor> getDoctorDetails() {
		List<Doctor> doctors=new ArrayList<>();
		while(true) {
		System.out.println("Enter Doctor Name: ");
		String name=input.getString();
		
		String specialization=getSpecialization();
		
		doctors.add( new Doctor(name,specialization));
		System.out.println("Do you want to add more Doctors(1/0): ");
		int opt=input.getInt();
		if(opt!=1) {break;}
		}
		return doctors;
	}

	 String getSpecialization() {
		System.out.println("1. Baby ward: ");
		System.out.println("2.Pregnant ward");
		System.out.println("3.General ward");
		System.out.println("4. Dental ward");
		System.out.println("Enter Your Ward");
		int ch=input.getInt();
		switch(ch) {
		case 1: return "baby";
		case 2: return "pregnent";
		case 3: return "general";
		case 4:return "dental";
		default: return "general";
		}
		
	}

	public void printDoctors() {
		System.out.println("Our Doctors: ");
		for(String spec: specializationMap.keySet()) {
			for(Doctor doctor: specializationMap.get(spec)) {
				System.out.println("Doctor Id: "+doctor.getDocId()+" Name: "+doctor.getName()+" ward: "+doctor.getSpecialization());
			}
		}
		
	}

	public Doctor getDoctor(String ward) {
		Doctor doctor=specializationMap.get(ward).poll();
		return doctor;
	}

	public void addApointment(Doctor doctor, Patient patient) {
		Record record=new Record(patient, doctor);
		doctor.addRecord(record);
		specializationMap.get(doctor.getSpecialization()).add(doctor);
		
		patient.addRecord(record);
		record.print();
		
	}

	public void searchDoctor() {
		System.out.println("Enter doctor id: ");
		int id=input.getInt();
		Doctor doctor=doctorMap.get(id);
		if(doctor==null) {
			System.out.println("Sorry Not found");
			return;
		}
		System.out.println(doctor);
		System.out.println("Records: ");
		List<Record> records=doctor.getRecords();
		for(Record record: records) {
			record.printPatient();
		}
		
	}	

}
