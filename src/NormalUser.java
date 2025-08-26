import java.util.Scanner;

public class NormalUser extends User {
    public NormalUser(String name) {
        super(name);
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    public NormalUser(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    @Override
    public void menu(Database database, User user) {
        System.out.println("***********************************");
        System.out.println("Welcome to Menu!");
        System.out.println("1. View Books");
        System.out.println("2. Search Book");
        System.out.println("3. Place Order");
        System.out.println("4. Borrow Book");
        System.out.println("5. Calculate Fine");
        System.out.println("6. Return Book");
        System.out.println("7. Exit");
        System.out.println("***********************************");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        this.operations[choice - 1].oper(database, user);
        scanner.close();
    }

    public String toString() {
        return name + "<N/>" + email + "<N/>" + phoneNumber + "<N/>" + "NormalUser" ;
    }
    
    // public void menu() {
    // Scanner scanner = new Scanner(System.in);
    // int choice;
    // do {
    // System.out.println("***********************************");
    // System.out.println("Welcome to Menu!");
    // System.out.println("1. View Books");
    // System.out.println("2. Search Book");
    // System.out.println("3. Place Order");
    // System.out.println("4. Borrow Book");
    // System.out.println("5. Calculate Fine");
    // System.out.println("6. Return Book");
    // System.out.println("7. Exit");
    // System.out.println("***********************************");

    // choice = scanner.nextInt();
    // if (choice >= 1 && choice <= operations.length) {
    // operations[choice - 1].oper();
    // } else {
    // System.out.println("Invalid choice. Please try again.");
    // }
    // } while (choice != 7);
    // scanner.close();
    // }

}
