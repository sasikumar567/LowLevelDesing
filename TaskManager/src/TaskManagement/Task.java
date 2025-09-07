package TaskManagement;

public class Task {
 private static int no=1;
 private int taskId;
 private String name;
 private String description;
 private int priority;
 private String status;
public Task(String name, String description, int priority) {
	taskId=no++;
	
	this.name = name;
	this.description = description;
	this.priority = priority;
	this.status = "Not Started";
}
public int getPriority() {
	return priority;
}
public int getTaskId() {
	return taskId;
}
public String getStatus() {
	return status;
}
public void update(String string) {
	status=string;
	
}
@Override
public String toString() {
	return "Task [taskId=" + taskId + ", name=" + name + ", description=" + description + ", status=" + status + "]";
}
}
