import java.util.Scanner;

public class Exit implements IOOperation {
    Scanner scanner;
    Database database;
    User user;

    @Override
    public void oper(Database database, User user) {
        this.database = database;
        this.user = user;
        scanner = new Scanner(System.in);
        
        System.out.println("\nAre you sure you want to exit?");
        System.out.println("1. Yes");
        System.out.println("2. Main menu");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("0. Exit\n1. Login\n2. New User");
            int chioce = scanner.nextInt();
            switch (chioce) {
                case 0:
                    System.out.println("Thank you for using the Library Management System!");
                    System.exit(0);
                    break;
                case 1:
                    logIn();;
                    break;
                case 2:
                    newUser();
                    break;
            }
        } else {
            user.menu(database, user);
        }
    }

    private void logIn() {
        System.out.println("Enter phone Number:");
        String phoneNumber = scanner.next();
        System.out.println("Enter your Email:");
        String email = scanner.next();

        int userId = database.logIn(phoneNumber, email);

        if (userId != -1) {
            User user = database.getUser(userId);
            System.out.println("Welcome " + user.getName() + "!");
            // Check user type
            if (user instanceof Admin) {
                System.out.println("You are logged in as Admin.");
                ((Admin) user).menu(database, user);
            } else if (user instanceof NormalUser) {
                System.out.println("You are logged in as Normal User.");
                ((NormalUser) user).menu(database, user);
            }
            System.out.println("====================================");
            user.menu(database, user);

        } else {
            System.out.println("Login failed. User does not exist.");
            System.out.println("Please try again.");
        }
    }

    private void newUser() {
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
