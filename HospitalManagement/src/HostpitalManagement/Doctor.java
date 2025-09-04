package HostpitalManagement;
import java.util.*;
public class Doctor {
	private  String name;
	private static int no=1;
	private String specialization;

	private int docId;
	List<Record> records;
	public Doctor(String name, String specialization) {
		docId=no++;
		this.name = name;
		this.specialization = specialization;
	
		records=new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public List<Record> getRecords() {
		return records;
	}
	public void addRecord(Record record) {
		records.add(record);
	}
	@Override
	public String toString() {
		return "Doctor [name=" + name + ", specialization=" + specialization + ", docId=" + docId + "]";
	}
	
	
	

}
