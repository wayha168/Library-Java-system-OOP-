public abstract class User {
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected IOOperation[] operations;
    
    public User() {
        // Default constructor
    }
    
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    abstract public String toString();
    // {
    //     String text = name + "<N/>" + email + "<N/>" + phoneNumber + "<N/>";
    //     return text;
    // }   

    abstract public void menu(Database database, User user);
}
