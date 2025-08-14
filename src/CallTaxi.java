import java.util.*;

abstract class Details {

    String name;
    int age;
    long phonenumber;

    void setter(String name, int age, long phonenumber) {
        this.name = name;
        this.age = age;
        this.phonenumber = phonenumber;
    }

    void getter() {
        System.out.println("Name        : " + name);
        System.out.println("Age         : " + age);
        System.out.println("Phone Number: " + phonenumber);
        System.out.println("-----------------------------");
    }
}

class Customer extends Details {

    @Override
    void getter() {
        System.out.println("Name of Driver : " + name);
        System.out.println("Age         : " + age);
        System.out.println("Phone Number: " + phonenumber);
        System.out.println("-----------------------------");
    }
}

class Drivers extends Details {


    @Override
    void getter() {
        System.out.println("Name of Driver       : " + name);
        System.out.println("Age         : " + age);
        System.out.println("Phone Number: " + phonenumber);
        System.out.println("-----------------------------");
    }
}



public class CallTaxi {

    static ArrayList<Customer> details = new ArrayList<>();
    static ArrayList<Drivers> driver = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("\nWELCOME TO SD TRACK..\n");

        while (true) {
            try {
                System.out.println("1. Register the user");
                System.out.println("2. View user details of Customer");
                System.out.println("3. Register the Driver");
                System.out.println("4. View the details of Driver");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline
                System.out.println();

                switch (choice) {
                    case 1:
                        System.out.println("Enter the details of the user:");

                        System.out.print("Enter the name: ");
                        String name = sc.nextLine(); // read full line

                        System.out.print("Enter the age: ");
                        int age = sc.nextInt();

                        System.out.print("Enter the Phone Number: ");
                        long pn = sc.nextLong();
                        sc.nextLine(); // clear newline

                        Customer dett = new Customer();
                        dett.setter(name, age, pn);
                        details.add(dett);

                        System.out.println("User registered successfully!\n");
                        break;

                    case 3:
                        System.out.println("Enter the details of the Driver:");

                        System.out.print("Enter the name: ");
                        String Dname = sc.nextLine(); // read full line

                        System.out.print("Enter the age: ");
                        int Dage = sc.nextInt();

                        System.out.print("Enter the Phone Number: ");
                        long Dpn = sc.nextLong();
                        sc.nextLine(); // clear newline

                        Drivers driv = new Drivers();
                        driv.setter(Dname, Dage, Dpn);
                        driver.add(driv);

                        System.out.println("User registered successfully!\n");
                        break;

                    case 2:
                        if (details.isEmpty()) {
                            System.out.println("No user records found.\n");
                        } else {
                            System.out.println("Registered User Details:\n");
                            for (Details dd : details) {
                                dd.getter();
                            }
                        }
                        break;
                    case 4:
                        if (driver.isEmpty()) {
                            System.out.println("No user records found.\n");
                        } else {
                            System.out.println("Registered User Details:\n");
                            for (Drivers dd : driver) {
                                dd.getter();
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Thank you for using SD TRACK!");
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid input (e.g., number only).");
                sc.nextLine(); // clear invalid input
            }
        }
    }
}
