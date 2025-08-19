import java.util.ArrayList;

public class Database {

    ArrayList<User> users = new ArrayList<User>();
    ArrayList<String> username = new ArrayList<String>();
    
    public void addUser(User user) {
        users.add(user);
        username.add(user.getName());
    }
    public int logIn(String phoneNumber, String email) {
        int found = -1;
        for (User user : users) {
            if (user.getPhoneNumber().matches(phoneNumber) && user.getEmail().matches(email)) {
                System.out.println("Login successful for: " + user.getName());
                found = users.indexOf(user);
                break;
            }
        }
        System.out.println("Login failed. User not found.");
        return found;
    }
    public User getUser(int userId) {
        return users.get(userId);
    }
}