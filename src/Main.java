import java.util.Scanner;

public class Main {
    static Scanner scanner;
    static Database database;

    public static void main(String[] args) {

        database = new Database();
        System.out.println("====================================");
        System.out.println("Welcome to Library Mangement System!");

        int chioce;
        // do {
        System.out.println("====================================");
        System.out.println("Please choose an option:");
        System.out.println("1. login");
        System.out.println("2/ New User");
        System.out.println("3. Exit");
        System.out.println("====================================");

        scanner = new Scanner(System.in);
        chioce = scanner.nextInt();

        switch (chioce) {
            case 1:
                logIn();
                break;
            case 2:
                newUser();
                break;
        }
        // } while (chioce != 3);
        // System.out.println("Thank you for using the Library Management System!");
    }

    private static void logIn() {

        System.out.println("Enter phone Number:");
        String phoneNumber = scanner.next();
        System.out.println("Enter your Email:");
        String email = scanner.next();

        int userId = database.logIn(phoneNumber, email);

        if (userId != -1) {
            User user = database.getUser(userId);
            System.out.println("Welcome " + user.getName() + "!");
            System.out.println("====================================");
            user.menu(database, user);

        } else {
            System.out.println("Login failed. User does not exist.");
            System.out.println("Please try again.");
        }

    }
    // private static void logIn() {
    // System.out.println("Enter phone Number:");
    // String phoneNumber = scanner.next();
    // System.out.println("Enter your Email:");
    // String email = scanner.next();
    // int userId = database.logIn(phoneNumber, email);
    // if (userId != -1) {
    // User user = database.getUser(userId);
    // System.out.println("Login successful!");
    // user.menu(database, user);
    // System.out.println("====================================");
    // System.out.println("Welcome " + user.getName() + "!");
    // } else {
    // System.out.println("Login failed. User does not exist.");
    // }
    // }

    private static void newUser() {
        System.out.println("Enter name:");
        String name = scanner.next();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.next();
        System.out.println("Enter Email:");
        String email = scanner.next();
        System.out.println("1. Admin \n2. Normal User");
        int users = scanner.nextInt();
        User user;
        if (users == 1) {
            user = new Admin(name, email, phoneNumber);
        } else {
            user = new NormalUser(name, email, phoneNumber);
        }
        database.addUser(user);
        user.menu(database, user);
    }

}
