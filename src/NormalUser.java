public class NormalUser extends User {
    public NormalUser(String name) {
        super(name);
        // Default constructor
    }
    
    public NormalUser(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }
    
    @Override
    public void menu() {
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

    }    
}
