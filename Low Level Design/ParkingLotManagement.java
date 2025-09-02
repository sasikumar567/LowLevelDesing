import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

enum VehicleType {
    CAR, BIKE, TRUCK;
}

// Base Vehicle class
abstract class Vehicle {
    String vehNo;
    VehicleType type;
    LocalDateTime entry;
    LocalDateTime exit;

    public Vehicle(String no, VehicleType type) {
        this.vehNo = no;
        this.type = type;
        this.entry = LocalDateTime.now();
    }
    abstract int getCharge();
}

class Car extends Vehicle {
    int charge = 50;
    Car(String no) {
        super(no, VehicleType.CAR);
    }
    int getCharge(){
        return charge;
    }
}

class Bike extends Vehicle {
    int charge = 25;
    Bike(String no) {
        super(no, VehicleType.BIKE);
    }
    int getCharge(){
        return charge;
    }
}

class Truck extends Vehicle {
    int charge = 100;
    Truck(String no) {
        super(no, VehicleType.TRUCK);
    }
    int getCharge(){
        return charge;
    }
}

// Floor with slots
class Floor {
    int no;
    Vehicle[] carSlots;
    Vehicle[] bikeSlots;
    Vehicle[] truckSlots;

    Floor(int no, int carCount, int bikeCount, int truckCount) {
        this.no = no;
        carSlots = new Vehicle[carCount];
        bikeSlots = new Vehicle[bikeCount];
        truckSlots = new Vehicle[truckCount];
    }

    int park(Vehicle vehicle) {
        if (vehicle.type == VehicleType.CAR) {
            for (int i = 0; i < carSlots.length; i++) {
                if (carSlots[i] == null) {
                    carSlots[i] = vehicle;
                    return i;
                }
            }
        } else if (vehicle.type == VehicleType.BIKE) {
            for (int i = 0; i < bikeSlots.length; i++) {
                if (bikeSlots[i] == null) {
                    bikeSlots[i] = vehicle;
                    return i;
                }
            }
        } else if (vehicle.type == VehicleType.TRUCK) {
            for (int i = 0; i < truckSlots.length; i++) {
                if (truckSlots[i] == null) {
                    truckSlots[i] = vehicle;
                    return i;
                }
            }
        }
        return -1;
    }

    void remove(Vehicle vehicle) {
        if (vehicle.type == VehicleType.CAR) {
            for (int i = 0; i < carSlots.length; i++) {
                if (carSlots[i] != null && carSlots[i].vehNo.equals(vehicle.vehNo)) {
                    carSlots[i] = null;
                    return;
                }
            }
        } else if (vehicle.type == VehicleType.BIKE) {
            for (int i = 0; i < bikeSlots.length; i++) {
                if (bikeSlots[i] != null && bikeSlots[i].vehNo.equals(vehicle.vehNo)) {
                    bikeSlots[i] = null;
                    return;
                }
            }
        } else {
            for (int i = 0; i < truckSlots.length; i++) {
                if (truckSlots[i] != null && truckSlots[i].vehNo.equals(vehicle.vehNo)) {
                    truckSlots[i] = null;
                    return;
                }
            }
        }
    }
}

// Parking lot
class ParkingLot {
    private String name;
    private int noOfFloors;
    List<Floor> floors;

    ParkingLot(String name, int floorCount) {
        this.name = name;
        this.noOfFloors = floorCount;
        this.floors = new ArrayList<>();
    }

    void addFloor(int no, int car, int bike, int truck) {
        floors.add(new Floor(no, car, bike, truck));
    }

    public String getName() {
        return name;
    }

    Ticket park(Vehicle vehicle) {
        for (Floor floor : floors) {
            int slot = floor.park(vehicle);
            if (slot != -1) {
                return new Ticket(vehicle, floor, slot);
            }
        }
        System.out.println("No slots available for " + vehicle.type);
        return null;
    }

    void removeVehicle(Ticket ticket) {
        ticket.floor.remove(ticket.vehicle);
        ticket.vehicle.exit = LocalDateTime.now();
        Duration duration= Duration.between(ticket.vehicle.entry,ticket.vehicle.exit);
        long minutes=duration.toMinutes();
        long hrs=(minutes+59)/60;
        System.out.println("Vehicle " + ticket.vehicle.vehNo + " removed from Floor " + ticket.floor.no);
        System.out.println("Your parking Charge: "+hrs*ticket.vehicle.getCharge());
    }

    void availableSlots() {
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.no + ": "
                    + "Cars=" + Arrays.stream(floor.carSlots).filter(Objects::isNull).count()
                    + ", Bikes=" + Arrays.stream(floor.bikeSlots).filter(Objects::isNull).count()
                    + ", Trucks=" + Arrays.stream(floor.truckSlots).filter(Objects::isNull).count());
        }
    }
}

// Ticket class
class Ticket {
    static int ticketCounter = 1;
    int no;
    Vehicle vehicle;
    Floor floor;
    int slotNo;

    Ticket(Vehicle vehicle, Floor floor, int slotNo) {
        no = ticketCounter++;
        this.vehicle = vehicle;
        this.floor = floor;
        this.slotNo = slotNo;
        System.out.println("Ticket " + no + " issued for Vehicle " + vehicle.vehNo +
                " at Floor " + floor.no + ", Slot " + slotNo);
    }
}

// Main driver
public class ParkingLotManagement {
    static Scanner sc;

    static void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        print("Enter Your Parking Lot Name: ");
        String name = sc.nextLine();
        print("No of Floors: ");
        int floorCount = sc.nextInt(); sc.nextLine();

        ParkingLot lot = new ParkingLot(name, floorCount);

        print("Please Complete the Parking Slot Details");
        for (int i = 0; i < floorCount; i++) {
            print("Enter Floor " + (i + 1) + " Details: ");
            print("No Car Slots: ");
            int car = sc.nextInt();
            print("No Bike Slots: ");
            int bike = sc.nextInt();
            print("No Truck Slots: ");
            int truck = sc.nextInt();
            lot.addFloor(i + 1, car, bike, truck);
        }
        print("Thanks for Filling Details\n");
        print("Welcome to " + lot.getName());

        boolean loop = true;
        Map<Integer, Ticket> tickets = new HashMap<>();

        while (loop) {
            int ch = getChoice();
            switch (ch) {
                case 1: {
                    Ticket ticket = parkVehicle(lot);
                    if (ticket != null) tickets.put(ticket.no, ticket);
                    break;
                }
                case 2: {
                    print("Enter Ticket No to remove: ");
                    int tno = sc.nextInt();
                    if (tickets.containsKey(tno)) {
                        lot.removeVehicle(tickets.get(tno));
                        tickets.remove(tno);
                    } else print("Invalid Ticket No");
                    break;
                }
                case 3: lot.availableSlots(); break;
                case 4: loop = false; break;
                default: print("Invalid Choice");
            }
        }
    }

    private static Ticket parkVehicle(ParkingLot lot) {
        print("1.Bike   2.Car   3.Truck");
        int ch = sc.nextInt(); sc.nextLine();
        print("Enter Vehicle No: ");
        String no = sc.nextLine();

        Vehicle vehicle;
        switch (ch) {
            case 1: vehicle = new Bike(no); break;
            case 2: vehicle = new Car(no); break;
            case 3: vehicle = new Truck(no); break;
            default: print("Invalid"); return null;
        }
        return lot.park(vehicle);
    }

    private static int getChoice() {
        print("\n1. Park Vehicle");
        print("2. Remove Vehicle");
        print("3. Available Slots");
        print("4. Exit");
        int ch = sc.nextInt(); sc.nextLine();
        return ch;
    }
}
