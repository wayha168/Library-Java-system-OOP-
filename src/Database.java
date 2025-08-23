import java.io.File;
import java.util.ArrayList;

public class Database {

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> username = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> booknames = new ArrayList<String>();

    private File userfile = new File("//data/Users");
    private File bookfile = new File("//data/Books");

    public Database() {
    if (!userfile.exists()) {
            userfile.mkdirs();
        }
        if (!bookfile.exists()) {
            bookfile.mkdirs();
        
    }
    }

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
        if (found != -1) {
        System.out.println("Login failed. User not found.");
        }
        return found;
    }
    
    public User getUser(int userId) {
        return users.get(userId);
    }

    public void addBook(Book book) {
        books.add(book);
        booknames.add(book.getName());
    }

}