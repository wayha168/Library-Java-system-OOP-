import java.util.Scanner;

public class Main {
    static Scanner scanner;
    static Database database;

    public static void main(String[] args) {

        database = new Database();
        System.out.println("====================================");
        System.out.println("Welcome to Library Mangement System!");

        int chioce;
        do {
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
                case 2:
                    newUser();                    
            }
        } while (chioce != 3);
        System.out.println("Thank you for using the Library Management System!");
        System.out.println("Goodbye!");
        scanner.close();
    }

    private static void logIn() {
        System.out.println("Enter phone Number:");
        String phoneNumber = scanner.next();
        System.out.println("Enter your Email:");
        String email = scanner.next();
        int userId = database.logIn(phoneNumber, email);
        if (userId != -1) {
            User user = database.getUser(userId);
            System.out.println("******************************************");
            System.out.println("Login successful!");
            System.out.println("Welcome " + user.getName() + "!");

        } else {
            System.out.println("Login failed. Please check your credentials.");
            return ;

        }
    }

    private static void newUser() {
        System.out.println("Enter name:");
        String name = scanner.next();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.next();
        System.out.println("Enter Email:");
        String email = scanner.next();
        System.out.println("1. Admin \n2. Normal User");
        int users = scanner.nextInt();

        if (users == 1) {
            User admin = new Admin(name, email, phoneNumber);
            database.addUser(admin);
            
        } else {
            User user = new User(name, email, phoneNumber);
            database.addUser(user);
            
        }
        System.out.println("User created successfully!");
        System.out.println("===========================");
    }

}
