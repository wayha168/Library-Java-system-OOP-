public class NormalUser extends User {
    public NormalUser(String name) {
        super(name);
        // Default constructor
    }
    
    public NormalUser(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }
    
    // Additional methods specific to NormalUser can be added here
    
}
