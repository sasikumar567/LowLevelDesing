import java.time.LocalDate;

import java.util.*;

public class ExpenseTracker {
    private static Scanner sc;
    private static void print(String str){
        System.out.println(str);
    }
    public static void main(String[] args){
        sc=new Scanner(System.in);
        print("Welcom to Expense Tracker");
        print("Please Fill the Details");
       
        boolean loop=true;
        List<Category> categories=new ArrayList<>();
        print("Mention the Category Names(q to stop): ");
        while(loop){
            String str=sc.nextLine();
            if(str.equals("q")){loop=false; break;}
            categories.add(new Category(str));
        }
        print("Set Budget Limit for Each Category: ");
        for(Category category: categories){
            print(category.categoryName);
                category.setBudgetLimit(sc.nextDouble());
        }
        print("Thank you for Your Responces");
        print("");
        loop=true;
        while(loop){
            int ch=getChoice();
            switch(ch){
                case 1: addExpense(categories);break;
                case 2: viewExpense(categories); break;
                case 3: generateReport(categories); break;
                case 4: resetExpense(categories); break;
                case 5: loop=false; break;
                default: print("Invalid choice");
            }
        } 
    }
    static void resetExpense(List<Category> categories){
        for(Category category: categories){
            category.resetExpense();
        }
        print("Now you current expense is 0 in all categories");
    }
    static void generateReport(List<Category> categories){
        print(" Daily Spendings");
        double overallSpending=0;
        for(Map.Entry<LocalDate,List<Expense>> entry: Expense.dailySpending.entrySet()){
            double total=0;
            print(entry.getKey()+"");
            for(Expense expense: entry.getValue()){
                print(expense.toString());
                total+=expense.amount;
            }
            print("Total: "+ total);
            overallSpending+=total;
        }
        print("Overall Spent: "+overallSpending);
    }
    static void viewExpense(List<Category> categories){
        print("1. Overall Expense:");
        print("2. Last No of Days");
        print("3. Amount Range");
        int ch=sc.nextInt(); sc.nextLine();
        switch (ch) {
            case 1:
                overallExpense(categories);
                break;
            case 2:
                beforeDays(categories);
                break;
            case 3:
                viewByAmountRange(categories);
                break;
        
            default: print("Invalid");
                break;
        }
    }
    static void viewByAmountRange(List<Category> categories){
        print("starting Range");
        double starting=sc.nextDouble();sc.nextLine();
        print("Ending Range");
        double ending=sc.nextDouble();sc.nextLine();
        if(starting>ending || starting<0 || ending<0){
            print("Invalid");
            return;
        }
        for(Category category: categories){
            print("");
            print(category.categoryName+" : ");
            for(Expense expense: category.getExpenses()){
                if(expense.amount>=starting && expense.amount<=ending){
                print(expense.date+" "+expense.amount+" "+expense.description);}
            }   
        }

    }
    static void overallExpense(List<Category> categories){
        for(Category category: categories){
            print("");
            print(category.categoryName+" : ");
            for(Expense expense: category.getExpenses()){
                print(expense.date+" "+expense.amount+" "+expense.description);
            }   
        }
    }
    static void beforeDays(List<Category> categories){
        print("How many days Before: ");
        int days=sc.nextInt();sc.nextLine();
        if(days<=0){print("Invalid"); return;}
        LocalDate lastDate=LocalDate.now().minusDays(days);
        for(Category category: categories){
            print("");
            print(category.categoryName+" : ");
            for(Expense expense: category.getExpenses()){
                if(expense.date.isAfter(lastDate)){
                print(expense.date+" "+expense.amount+" "+expense.description);}
            }   
        }
    }
    static void addExpense(List<Category> categories){
        for(int i=0;i<categories.size();i++){
            print("Press"+(i)+" for "+categories.get(i).categoryName);
        }
        int ch=sc.nextInt();sc.nextLine();
        Category category=categories.get(ch);
        print("Enter Amount: ");
        double amount=sc.nextDouble();sc.nextLine();
        if(amount<=0){
            print("invalid amount");
            return;
        }
        String description =sc.nextLine();
        Expense expense=new Expense(amount, description,category.categoryName);
        print(category.addExpense(expense));
        Expense.dailySpending.putIfAbsent(expense.date, new ArrayList<>());
        Expense.dailySpending.get(expense.date).add(expense);
    }
    static int getChoice(){
        print("1. Add Expence");
        print("2.View Expense");
        print("3. Generate Reports");
        print("4. Reset Expense");
        print("5. Exit");
        int ch=sc.nextInt();
        return ch;
    }
}
class Category{
    List<Expense> expenses;
    String categoryName;
    double budgetLimit;
    double currentExpense;
    Category(String name){
        categoryName=name;
        expenses=new ArrayList<>();
    }
    void resetExpense(){
        currentExpense=0;
    }
    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }
    public String addExpense(Expense expense){
        expenses.add(expense);
        currentExpense+=expense.amount;
        double rem=budgetLimit-currentExpense;
        if(rem>0){
            return "Warning: Your Budget Limit in "+categoryName+": "+rem;
        }
        return " You Exceeded your Budge Limit in "+categoryName;
    }
    public List<Expense> getExpenses() {
        return expenses;
    }
}
class Expense{
    static Map<LocalDate, List<Expense>> dailySpending=new HashMap();
    double amount;
    LocalDate date;
    String description;
    String category;
    Expense(double amount, String description,String category){
        this.amount=amount;
        date=LocalDate.now();
        this.description=description;
        this.category=category;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return category+" "+amount+" "+description;
    }
}

