	package TaskManagement;
	import java.time.LocalDate;
	import java.util.*;
	public class User {
		private Input input;
		private static int  no=1;
		private int id;
		private String name;
		Map<Integer,Task> taskMap;
		PriorityQueue<Task> taskQueue;
		Map<LocalDate,Task> newlyAdded;
		Map<LocalDate,Task> updated;
		Map<LocalDate,Task> deleted;
		
		User(String name, Input input){
			this.input=input;
			this.id=no++;
			this.name=name;	
			taskMap=new HashMap<Integer, Task>();
			taskQueue=new PriorityQueue<Task>((a,b)-> b.getPriority()-a.getPriority());
			deleted=new HashMap<LocalDate, Task>();
			newlyAdded=new HashMap<LocalDate, Task>();
			updated=new HashMap<LocalDate, Task>();
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public void run() {
			System.out.println("welcome "+name);
			while(true) {
			displayMenu();
			int ch=input.getInt();
			switch(ch) {
			case 1: addTask(); break;
			case 2: updateTask(); break;
			case 3: deleteTask(); break;
			case 4: viewTask(); break;
			case 5: todayProgress(); break;
			case 6: giveTask(); break;
			case 7: System.out.println("logged out successfully"); return;
			default: System.out.println("Invalid");
			}
			}
			
		}
		private void giveTask() {
			if(taskQueue.isEmpty()) {
				System.out.println(" No Tasks Available");
				return;
			}
			Task task=taskQueue.poll();
			if(task.getStatus().equals("removed")|| task.getStatus().equals("completed")){
				giveTask();
				return;
			}
			task.update("pending");
			updated.put(LocalDate.now(), task);
			System.out.println(task);
			
		}
		private void todayProgress() {
			System.out.println("Newly Added Tasks");
			for(Task task: newlyAdded.values()) {
				System.out.println(task);
			}
			System.out.println();
			System.out.println("Updated Tasks");
			for(Task task: updated.values()) {
				System.out.println(task);
			}
			System.out.println();
			System.out.println("Deleted Tasks");
			for(Task task: deleted.values()) {
				System.out.println(task);
			}
		}
		private void viewTask() {
			for(Task task: taskMap.values()) {
				System.out.println(task);
			}
			
		}
		private void deleteTask() {
			Task task=getTask();
			if(task==null) {
				System.out.println("Task Not found");
				return;
			}
			
			taskMap.remove(task.getTaskId());
			task.update("removed");
			deleted.put(LocalDate.now(), task);
		}
		private Task getTask() {
			System.out.println("Enter Task id: ");
			int id=input.getInt();
			Task task=taskMap.get(id);
			return task;
		}
		private void updateTask() {
			Task task=getTask();
			if(task==null) {
				System.out.println("Task Not found");
				return;
			}
			System.out.println("Current Status: "+task.getStatus());
			System.out.println("1. to update pending");
			System.out.println("2. to update completed");
			int ch=input.getInt();
			if(ch==1) {
				task.update("pending");
			}
			if(ch==2) {
				task.update("completed");
			}
			updated.put(LocalDate.now(), task);
			
		}
		private void addTask() {
			System.out.println("Enter Task Name:");
			String name=input.getString();
			System.out.println("Enter Description: ");
			String desc=input.getString();
			int priority=getPriority();
			Task task=new Task(name,desc,priority);
			taskQueue.add(task);
			taskMap.put(task.getTaskId(), task);
			newlyAdded.put(LocalDate.now(), task);
		}
		private int getPriority() {
			System.out.println("Enter Priority");
			System.out.println("1. High	  2.Mid    3.Low");
			int ch=input.getInt();
			if(ch>=1 && ch<=3) {
				return ch;
			}
			return getPriority();
		}
		private void displayMenu() {
			System.out.println();
			System.out.println("1. To Add Task");
			System.out.println("2. Update Task");
			System.out.println("3. Delete Task");
			System.out.println("4. view Tasks");
			System.out.println("5. Today's progress");
			System.out.println("6. GetTask");
			System.out.println("7. Exit");	
		}
	}
