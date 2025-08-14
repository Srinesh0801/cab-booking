import java.util.*;

// ===== Person (Base Class) =====
abstract class Person {
    String name;
    String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

// ===== User Class =====
class User extends Person {
    int userId;
    static int userCounter = 1;
    List<Booking> bookings = new ArrayList<>();

    public User(String name, String phone) {
        super(name, phone);
        this.userId = userCounter++;
    }
}

// ===== Driver Class =====
class Driver extends Person {
    int driverId;
    static int driverCounter = 1;
    boolean isAvailable = true;

    public Driver(String name, String phone) {
        super(name, phone);
        this.driverId = driverCounter++;
    }
}

// ===== Cab Class =====
class Cab {
    int cabId;
    static int cabCounter = 1;
    String cabType;
    String location;
    boolean isAvailable = true;
    Driver driver;

    public Cab(String cabType, String location, Driver driver) {
        this.cabId = cabCounter++;
        this.cabType = cabType;
        this.location = location;
        this.driver = driver;
    }
}

// ===== Booking Class =====
class Booking {
    static int bookingCounter = 1;
    int bookingId;
    User user;
    Cab cab;
    String pickupLocation;
    String dropLocation;
    String status;

    public Booking(User user, Cab cab, String pickupLocation, String dropLocation) {
        this.bookingId = bookingCounter++;
        this.user = user;
        this.cab = cab;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.status = "Booked";
    }
}

// ===== CabSystem Class =====
class CabSystem {
    List<User> users = new ArrayList<>();
    List<Driver> drivers = new ArrayList<>();
    List<Cab> cabs = new ArrayList<>();

    public void registerUser(String name, String phone) {
        User user = new User(name, phone);
        users.add(user);
        System.out.println("User registered with ID: " + user.userId);
    }

    public void addCab(String cabType, String location, String driverName, String driverPhone) {
        Driver driver = new Driver(driverName, driverPhone);
        drivers.add(driver);
        Cab cab = new Cab(cabType, location, driver);
        cabs.add(cab);
        System.out.println("Cab added with ID: " + cab.cabId);
    }

    public void viewAvailableCabs() {
        boolean found = false;
        for (Cab cab : cabs) {
            if (cab.isAvailable) {
                System.out.println("Cab ID: " + cab.cabId + ", Type: " + cab.cabType + ", Location: " + cab.location);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cabs available at the moment.");
        }
    }

    public User findUserById(int id) {
        for (User u : users) {
            if (u.userId == id) return u;
        }
        return null;
    }

    public void bookCab(int userId, String pickup, String drop) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Available Cabs in your area:");
        viewAvailableCabs();

        for (Cab cab : cabs) {
            if (cab.isAvailable && cab.location.equalsIgnoreCase(pickup)) {
                Booking booking = new Booking(user, cab, pickup, drop);
                cab.isAvailable = false;
                cab.driver.isAvailable = false;
                user.bookings.add(booking);
                System.out.println("Cab booked! Booking ID: " + booking.bookingId);
                return;
            }
        }
        System.out.println("No cabs available in your location.");
    }

    public void cancelBooking(int userId, int bookingId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        for (Booking b : user.bookings) {
            if (b.bookingId == bookingId && b.status.equals("Booked")) {
                b.status = "Cancelled";
                b.cab.isAvailable = true;
                b.cab.driver.isAvailable = true;
                System.out.println("Booking cancelled.");
                return;
            }
        }
        System.out.println("Active booking not found.");
    }

    public void viewUserBookings(int userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (user.bookings.isEmpty()) {
            System.out.println("No bookings found for user.");
        } else {
            for (Booking b : user.bookings) {
                System.out.println("Booking ID: " + b.bookingId + ", Cab Type: " + b.cab.cabType + ", Status: " + b.status);
            }
        }
    }
}

// ===== Main Class =====
public class CabReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CabSystem system = new CabSystem();

        while (true) {
            try {
                System.out.println("\n==== CabEase - Cab Booking System ====");
                System.out.println("1. Register User");
                System.out.println("2. Add Cab");
                System.out.println("3. View Available Cabs");
                System.out.println("4. Book a Cab");
                System.out.println("5. Cancel Booking");
                System.out.println("6. View My Bookings");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String uname = sc.nextLine();
                        System.out.print("Enter phone: ");
                        String uphone = sc.nextLine();
                        system.registerUser(uname, uphone);
                        break;
                    case 2:
                        System.out.print("Enter cab type: ");
                        String type = sc.nextLine();
                        System.out.print("Enter location: ");
                        String loc = sc.nextLine();
                        System.out.print("Enter driver name: ");
                        String dname = sc.nextLine();
                        System.out.print("Enter driver phone: ");
                        String dphone = sc.nextLine();
                        system.addCab(type, loc, dname, dphone);
                        break;
                    case 3:
                        system.viewAvailableCabs();
                        break;
                    case 4:
                        System.out.print("Enter user ID: ");
                        int uid = sc.nextInt(); sc.nextLine();
                        System.out.print("Enter pickup location: ");
                        String pick = sc.nextLine();
                        System.out.print("Enter drop location: ");
                        String drop = sc.nextLine();
                        system.bookCab(uid, pick, drop);
                        break;
                    case 5:
                        System.out.print("Enter user ID: ");
                        int userId = sc.nextInt();
                        System.out.print("Enter booking ID: ");
                        int bid = sc.nextInt();
                        system.cancelBooking(userId, bid);
                        break;
                    case 6:
                        System.out.print("Enter user ID: ");
                        int viewId = sc.nextInt();
                        system.viewUserBookings(viewId);
                        break;
                    case 7:
                        System.out.println("Thank you for using CabEase!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // clear the buffer
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                sc.nextLine();
            }
        }
    }
}