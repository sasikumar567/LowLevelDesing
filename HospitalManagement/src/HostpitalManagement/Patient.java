package HostpitalManagement;
import java.util.*;
public class Patient {
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", name=" + name + "]";
	}
	private static int no=1;
	private int patientId;
	private String name;
	List<Record> records;
	public Patient( String name) {
		records=new ArrayList<>();
		this.patientId = no++;
		this.name = name;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Record> getRecords() {
		return records;
	}
	public void setRecords(List<Record> records) {
		this.records = records;
	}
	public void addRecord(Record record) {
		records.add(record);
		
	}
	


}
