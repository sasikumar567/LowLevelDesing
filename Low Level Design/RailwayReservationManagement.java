import java.util.*;
public class RailwayReservationManagement {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args){
        RailwayManager railwayManager=new RailwayManager();
        fillRailwayDetails(railwayManager);
        run(railwayManager);
    }
    private static void run(RailwayManager railwayManager){
        while(true){
            try{
                displayMenu();
                int ch=sc.nextInt();sc.nextLine();
                switch(ch){
                    case 1: bookTicket( railwayManager);break;
                    case 2: cancelTicket(railwayManager); break;
                    case 3: viewAvailability(railwayManager); break;
                    case 4: print("Thank You"); return;
                    default: print("Invalid");
                }
            }
            catch(Exception e){
                print("Please enter a valid Option");
                sc.nextLine(); // clear invalid token
            }
        }
    }
    private static void viewAvailability(RailwayManager railwayManager){
        print("Enter From: ");
        String from=sc.nextLine();
        List<Train> trains=railwayManager.getTrainsFrom(from);
        if(trains==null || trains.isEmpty()){
            print("Sorry no trains Available from here");
            return;
        }
        displayTrainsAvailability(trains);
    }
    private static void displayTrainsAvailability(List<Train> trains){
        if(trains==null || trains.isEmpty()){
            print("No trains to display");
            return;
        }
        for(Train train: trains){
            train.displayAvailability();
        }
    }
    private static void cancelTicket(RailwayManager railwayManager){
        print("Enter Ticker Number: ");
        int ticketNo= sc.nextInt();sc.nextLine();
        Ticket ticket=railwayManager.getTicket(ticketNo);
        if(ticket==null){
            print("Invalid");
            return;
        }
        cancelTicket(ticket);
    }
    private static void cancelTicket(Ticket ticket){
        Train train=ticket.getTrain();
        if(ticket.getCoach().equals("sleeper")){
            train.cancelSleeperTicket(ticket.getSeatNo());
        }
        else{
            train.cancelSeaterTicket(ticket.getSeatNo());
        }
    }
    private static void bookTicket(RailwayManager railwayManager){
        print("Enter From: ");
        String from=sc.nextLine();
        List<Train> trains=railwayManager.getTrainsFrom(from);
        if(trains==null || trains.isEmpty()){
            print("Sorry no trains Available from here");
            return;
        }
        displayTrainsFrom(trains);
        int ch=getChoice()-1;
        if(ch<0 || ch>=trains.size()){
            print("Sorry Invalid Train Number"); return;
        }
        Train train=trains.get(ch);
        train.displayAvailability();
        print("Number of Sleeper Tickets:");
        int sleepSeats=sc.nextInt();
        print("Number of Normal Seater Tickets: ");
        int seaterSeat=sc.nextInt();
        sc.nextLine(); // consume newline before reading names
        if(sleepSeats>0){
            manageSleeperBooking(train,sleepSeats,railwayManager);
        }
        if(seaterSeat>0){
            manageSeaterBooking(train,seaterSeat,railwayManager);
        }
    }
    private static void manageSeaterBooking(Train train, int seats,RailwayManager railwayManager){
        print("Please Enter the details of Seater Passengers");
        List<Passenger> seaterPassengers=getPassengers(seats);
        List<Ticket> seaterTickets=train.bookSeaterTicket(seaterPassengers);
        print("Your Seater Tickets:");
        printTickets(seaterTickets,railwayManager);
    }
    private static void manageSleeperBooking(Train train, int seats,RailwayManager railwayManager){
        print("Please Enter the details of Sleeper Passengers");
        List<Passenger> sleeperPassengers=getPassengers(seats);
        List<Ticket> sleeperTickets=train.bookSleeperTicket(sleeperPassengers);
        print("Your Sleeper Tickets:");
        printTickets(sleeperTickets,railwayManager);
    }
    private static void printTickets(List<Ticket> tickets,RailwayManager railwayManager) {
        for(Ticket ticket:tickets){
            railwayManager.addTicket(ticket);
            ticket.printTicket();
        }
    }
    private static List<Passenger> getPassengers(int n){
        if(n<=0){return null;}
        List<Passenger> passengers=new ArrayList<>();
        for(int i=0;i<n;i++){
            print("Enter Passenger "+(i+1)+" details: ");
            print("Enter Name: ");
            String name = sc.nextLine();
            print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());
            passengers.add(new Passenger(name, age));
        }
        return passengers;
    }
    private static int getChoice(){
        while(true){
            try{
                int ch=sc.nextInt();
                sc.nextLine();
                return ch;
            }
            catch(Exception e){
                print("Please Enter a Valid Train Number");
                sc.nextLine(); // clear invalid token
            }
        }
    }
    private static  void displayTrainsFrom(List<Train> trains){
        for(int i=0;i<trains.size();i++){
            Train train=trains.get(i);
            print((i+1)+". "+train.toString());
        }
        print("Enter Train Number: ");
    }
    private static void displayMenu(){
        System.out.println("1. Book Ticket");
        System.out.println("2. Cancel ticket");
        System.out.println("3.View Availability");
        System.out.println("4. Exit");
    }
    private static  void fillRailwayDetails(RailwayManager railwayManager){
        print("Please add Trains and its Details");
        while(true){
            print("Do you want to add Train:(1/0) ");
            try{
                int ch=sc.nextInt(); sc.nextLine();
                if(ch==0){
                    print("Thank You");
                    return;
                }
                else if(ch==1){
                    addTrain(railwayManager);
                }
                else{print("invalid");}
            }
            catch(Exception e){
                print("Please enter Number(1/0)");
                sc.nextLine(); // clear invalid token
            }
        }
    }
    static void addTrain(RailwayManager railwayManager){
        Train train=getTrain();
        railwayManager.addTrain(train);
    }
    static Train getTrain(){
        while(true){
            try{
                print("Train name");
                String name=sc.nextLine();
                print("No of SleepSeats:");
                int noOfSleeper=sc.nextInt(); sc.nextLine();
                print("No of SeaterSeats:");
                int noOfSeater=sc.nextInt(); sc.nextLine();
                print("SleeperSeat Price:");
                int sleeperPrice=sc.nextInt(); sc.nextLine();
                print("SeaterSeat Price:");
                int seaterPrice=sc.nextInt(); sc.nextLine();
                print("Enter Date and Time dd/mm/yyy hr:min");
                String dateTime=sc.nextLine();
                print("From: "); String from=sc.nextLine();
                print("To: "); String to=sc.nextLine();
                return new Train(name, noOfSleeper, noOfSeater, sleeperPrice, seaterPrice, dateTime, dateTime, from, to);
            }
            catch(Exception e){
                print("Invalid");
                sc.nextLine(); // clear invalid token
            }
        }
    }
    private static void print(String str){
        System.out.println(str);
    }
}
class Passenger{
    String name;
    int age;
    Passenger(String name,int age){
        this.name=name;
        this.age=age;
    }
}
class RailwayManager{
    List<Train> trains;
    Map<String,List<Train>> trainsFrom;
    Map<Integer,Ticket> ticketMap;
    RailwayManager(){
        trains=new ArrayList<>();
        trainsFrom=new HashMap<>();
        ticketMap=new HashMap<>();
    }
    void addTicket(Ticket ticket){
        ticketMap.put(ticket.ticketNo,ticket);
    }
    Ticket getTicket(int ticketNo){
        return ticketMap.get(ticketNo);
    }
    List<Train> getTrainsFrom(String from){
        return trainsFrom.get(from);
    }
    void addTrain(Train train){
        trains.add(train);
        trainsFrom.putIfAbsent(train.from, new ArrayList<>());
        trainsFrom.get(train.from).add(train);
    }
}
class Train{
    private static int no=1;
    String trainName;
    String trainNo;
    int noOfSleeper;
    int noOfSeater;
    int bookedSleeper;
    int bookedSeater;
    int sleeperPrice;
    int seaterPrice;
    String DateAndTime,from,to;
    Set<Integer> canceledSleeperTrickets;
    Set<Integer> canceledSeaterTrickets;
    Train(String name,int noOfSleeper,int noOfSeater,int sleeperPrice,int seaterPrice,String date,String time,String from,String to){
        this.trainName=name;
        this.trainNo="Train"+ no++;
        this.noOfSleeper=noOfSleeper;
        this.noOfSeater=noOfSeater;
        this.seaterPrice=seaterPrice;
        this.sleeperPrice=sleeperPrice;
        this.DateAndTime=date;
        this.to=to;
        this.from=from;
        bookedSeater=0;
        bookedSleeper=0;
        canceledSleeperTrickets=new HashSet<>();
        canceledSeaterTrickets=new HashSet<>();
    }
    void cancelSeaterTicket(int seatNo){
        canceledSeaterTrickets.add(seatNo);
    }
    void cancelSleeperTicket(int seatNo){
        canceledSleeperTrickets.add(seatNo);
    }
    void displayAvailability(){
        System.out.println("Available Seater Seats "+((noOfSeater-bookedSeater)+canceledSeaterTrickets.size()));
        System.out.println("Available Sleeper Seats "+((noOfSleeper-bookedSleeper)+canceledSleeperTrickets.size()));
    }
    @Override
    public String toString() {
        return trainName+" From: "+from+" To "+to+" on "+DateAndTime;
    }
    List<Ticket> bookSleeperTicket(List<Passenger> passengers){
        List<Ticket> tickets=new ArrayList<>();
        if(passengers==null) return tickets;
        for(Passenger passenger: passengers){
            if(bookedSleeper<noOfSleeper || !canceledSleeperTrickets.isEmpty()){
                int seatNo;
                if(!canceledSleeperTrickets.isEmpty()){
                    seatNo=Collections.min(canceledSleeperTrickets);
                    canceledSleeperTrickets.remove(seatNo);
                }
                else{
                    seatNo=++bookedSleeper;
                }
                Ticket ticket=new Ticket(this, passenger,seatNo,"sleeper");
                tickets.add(ticket);
            }
            else{
                System.out.println("Ticket is not Available for "+passenger.name);
            }
        }
        return tickets;
    }
    List<Ticket> bookSeaterTicket(List<Passenger> passengers){
        List<Ticket> tickets=new ArrayList<>();
        if(passengers==null) return tickets;
        for(Passenger passenger: passengers){
            if(bookedSeater<noOfSeater || !canceledSeaterTrickets.isEmpty()){
                int seatNo;
                if(!canceledSeaterTrickets.isEmpty()){
                    seatNo=Collections.min(canceledSeaterTrickets);
                    canceledSeaterTrickets.remove(seatNo);
                }
                else{
                    seatNo=++bookedSeater;
                }
                Ticket ticket=new Ticket(this, passenger,seatNo,"seater");
                tickets.add(ticket);
            }
            else{
                System.out.println("Ticket is not Available for "+passenger.name);
            }
        }
        return tickets;
    }
}
class Ticket{
    private static int no=1;
    int ticketNo;
    Train train; Passenger passenger;
    int seatNo;
    String coach;
    Ticket(Train train,Passenger passenger,int seatNo,String coach){
        ticketNo=no++;
        this.train=train;
        this.passenger=passenger;
        this.seatNo=seatNo;
        this.coach=coach;
    }
    public Train getTrain() {
        return train;
    }
    public Passenger getPassenger() {
        return passenger;
    }
    public int getSeatNo() {
        return seatNo;
    }
    public int getTicketNo() {
        return ticketNo;
    }
    public String getCoach() {
        return coach;
    }
    void printTicket(){
        System.out.println("TicketNo: "+ticketNo);
        System.out.println(passenger.name+" "+passenger.age);
        System.out.println(train.DateAndTime+" "+train.from+" to "+train.to);
        System.out.println(train.trainNo+" "+train.trainName);
        System.out.println(coach+" "+seatNo);
    }
}
