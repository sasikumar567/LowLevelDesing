import java.util.*;
class Result{
    float marks,average;
    char Grade;
    Result(List<Float> marks){
        float sum=0;
        for(float mark: marks){
            sum+=mark;
        }
        this.marks=sum;
        average=sum/=marks.size();
        Grade=findGrade(average);
    }
    char findGrade(float average){
        if(average>=90){return 'A';}
        if(average>=75){return 'B';}
        if(average>=60){return 'c';}
        if(average>=50){return 'd';}
        return 'f';
    }
}
class Student{
    String name;
    int regno;
    
     Set<Subject> subjects;
     HashMap<Subject,Float> marks;
     Result result;
    Student(int no,String name){
        this.regno=no;
        this.name=name; 
        subjects=new HashSet<>();
        marks=new HashMap<>();
    }
    public int getRegno() {
        return regno;
    }
    boolean addSubject(Subject sub){
        return subjects.add(sub);
    }
    void setMarks(Subject sub,float mark){
        marks.put(sub,mark);
    }
    public Result getResult() {
        if(result==null){
            result=new Result((List<Float>) marks.values());
        }
        return result;
    }

    

}
class Subject{
    int code;
    String name;
    Set<Student> students;
    Subject(int code,String name){
        this.code=code;
        this.name=name;
        students=new HashSet<>();
    }
    boolean addStudent(Student st){
       return students.add(st);
    }
     Set<Student> getStudents() {
        return students;
    } 
}
class GradeCalculator{
    private static Scanner sc=new Scanner(System.in);
    private static Map<Integer,Subject> subjects=new HashMap<>();
    private static Map<Integer,Student> students=new HashMap<>();
    private static int code=1001,no=01;
    public static void main(String[] args){
        boolean loop=true;
        while(loop){
            int ch=choice();
            switch(ch){
                case 1:createSubject();
                break;
                case 2: createStudent();
                break;
                case 3: enrollment();
                break;
                case 4: calculateResults();
                break;
                case 5:publishResults();
                break;
                case 6: loop=false;
                break;
                default: System.out.println("invalid choice");
            }
        }
        
    }
    static void publishResults(){
        
        for (Student st : students.values()) {
        Result r = st.getResult();
        System.out.println("Student: " + st.name + " | RegNo: " + st.getRegno());
        System.out.println("Total Marks: " + r.marks);
        System.out.println("Average: " + r.average);
        System.out.println("Grade: " + r.Grade);
        System.out.println("------------------------");
    }
    }
    static void calculateResults(){
        List<Subject> subjectsList=(List<Subject>)subjects.values();
        for(Subject sub: subjectsList){
            setResults(sub);
        }
    }
    static void setResults(Subject sub){
        Set<Student> stu=sub.getStudents();
        for(Student student: stu){
            System.out.println(student.getRegno()+"Mark: ");
            float mark=sc.nextFloat();
            student.setMarks(sub, mark);
        }
    }
    static void enrollment(){
        while(true){
            System.out.println("Enter Subject Code: ");
            int sub=sc.nextInt();
            System.out.println("Your RegisterNumber: ");
            int reg=sc.nextInt();
            if(!subjects.containsKey(sub) || !students.containsKey(reg)){
              System.out.println("Invalid details");
              continue;
            }
            else{
                Subject subject=subjects.get(sub);
                Student student=students.get(reg);
                if(!subject.addStudent(student) || !student.addSubject(subject)){
                    System.out.println("already enrolled");
                }
                else{
                    System.out.println("Successfully enrolled");
                }
                break;

            }
        }
    }
    static void createSubject(){
        System.out.println("Name:");
        String name=sc.nextLine(); 
        subjects.put(code,new Subject(code, name));
        System.out.println(name+" code: "+code);
        code++;
    }
    static void createStudent(){
        System.out.println("Enter Name");
        String name=sc.nextLine();
        students.put(no,new Student(no, name));
        System.out.println("your Register Number: "+no++);  
    }
     static int choice(){
        System.out.println("Welcome to Grade Calculator:");
        System.out.println("1. create Subject");
        System.out.println("2. create student");
        System.out.println("3. enrollment");
        System.out.println("4. calculate results");
        System.out.println("5. publish results");
        
        System.out.println("6. end");
        return sc.nextInt();
    }
}