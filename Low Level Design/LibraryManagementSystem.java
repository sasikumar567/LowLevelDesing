import java.util.*;
class Book{
    private int bookId;
    private String title;
    private String author;
    private int count;
    private Set<User> borrowers;
    Book(int id,String title, String author,int count){
        this.bookId=id; this.title=title;
        this.author=author;
        this.count=count;
        borrowers=new HashSet<>();
    }
    boolean isAvailable(){
        return count>0;
    }
    void addBook(int n){
        count+=n;
    }
    boolean addBorrower(User user){
        if(borrowers.add(user)){
            count--;
            return true;
        }
        return false;
    }
    
    void BorrowedUsers(){
        System.out.println(borrowers);
    }
    void returnBook(User user){
        borrowers.remove(user);
        count++;
    }
    
}
class User{
    private int userId;
    private String name;
    Set<Book> books;
    User(int id,String name){
        this.userId=id; this.name=name;
        books=new HashSet<>();
    }
    boolean addBook(Book book){
        return books.add(book);
    }
    void returnBook(Book book){
        books.remove(book);
    }
    void myBooks(){
        System.out.println(books);
    }
}

public class LibraryManagementSystem {
    static Map<Integer,Book>booksMap=new HashMap<>();
    static Map<Integer,User> userMap=new HashMap<>();
    static int bookId=1001,userId=1;
    static Scanner sc;
    public static void main(String[] args){
        sc=new Scanner(System.in);
        boolean loop=true;
        while(loop){
            int ch=getChoice();
            switch(ch){
                case 1: register();break;
                case 2: addBooks(); break;
                case 3: borrowBook(); break;
                case 4: returnBook(); break;
                case 5: myBooks(); break;
                case 0: loop=false; break;
                default: print("Invalid Choice");

            }
        }
    }
    static void myBooks(){
        print("User Id");
        int userNo=sc.nextInt(); sc.nextLine();
        if( !userMap.containsKey(userNo)){
            print("Invalid");
        }
         User user=userMap.get(userNo);
         user.myBooks();
    }
    static void returnBook(){
        print("Book Id: ");
        int bookNo=sc.nextInt();sc.nextLine();
        print("User Id");
        int userNo=sc.nextInt(); sc.nextLine();
        
        if(!booksMap.containsKey(bookNo) || !userMap.containsKey(userNo)){
            print("Invalid");return;
        }
         User user=userMap.get(userNo);
        Book book=booksMap.get(bookNo);
        book.returnBook(user);
        user.returnBook(book);
        
    }
    static void borrowBook(){
        print("Book Id: ");
        int bookNo=sc.nextInt();sc.nextLine();
        print("User Id");
        int userNo=sc.nextInt(); sc.nextLine();
        
        if(!booksMap.containsKey(bookNo) || !userMap.containsKey(userNo)){
            print("Invalid");
            return;
        }
        User user=userMap.get(userNo);
        Book book=booksMap.get(bookNo);
        if(book.isAvailable()){
            user.addBook(book);
            book.addBorrower(user);
        }
        else{
            print("Book is not Available");
        }
    }
    static void register(){
        print("Enter you Name: ");
        String name=sc.nextLine();
        sc.nextLine();
        userMap.put(userId,new User(userId, name));
        System.out.println(name+" user id: "+userId++);
    }
    static void addBooks(){
        print("1.new Book   2.Existing Book");
        int ch=sc.nextInt();sc.nextLine();
        if(ch==1){
            createBook();
        }
        else{
            addBookCount();
        }
    }
    static void  createBook(){
        print("Book Name: ");
        String name=sc.nextLine();
        sc.nextLine();
        print("Author name: ");
        String author=sc.nextLine();
        print("Count: ");
        int count=sc.nextInt();
        sc.nextLine();
        if(count<=0){
            print("invalid details");
            return;}
        booksMap.put(bookId,new Book(bookId, name, author, count));
        print("Book id : "+bookId++);
    }
    static void addBookCount(){
        print("Enter Book Id: ");
        int id=sc.nextInt(); sc.nextLine();
        if(!booksMap.containsKey(id)){
            print("invalid"); return;
    
    }
        Book book=booksMap.get(id);
        print("Enter count: ");
        int n=sc.nextInt();
        if(n<=0){print("invalid");return;}
        book.addBook(n);
    }
    static void print(String str){
        System.out.println(str);
    }
    static int getChoice(){
        print("Welcome to My Library");
        print("1. Register User: ");
        print("2. Add Books:");
        print("3. Borrow Book");
        print("4. return Book");
        print("5. View my Books");
        print("0. to Quit");
        print("Enter your Choice: ");
        int ch=sc.nextInt();
        sc.nextLine();
        return ch;
    }
}
