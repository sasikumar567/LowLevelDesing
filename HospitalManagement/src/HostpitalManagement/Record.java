package HostpitalManagement;

import java.time.LocalDateTime;

public class Record {
	LocalDateTime dateTime;
	Patient patient;
	Doctor doctor;
	public Record(Patient patient, Doctor doctor) {
		dateTime=LocalDateTime.now();
		this.patient = patient;
		this.doctor = doctor;
	}
	public void print() {
		System.out.println(dateTime);
		System.out.println(patient);
		System.out.println(doctor);
		
	}
	public void printPatient() {
		
		System.out.println(dateTime+"  "+patient);
		
	}
	public void printDoctor() {
		System.out.println(dateTime+"  "+doctor);
	}
	

}
