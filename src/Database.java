import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Database {

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> username = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> booknames = new ArrayList<String>();

    private File userfile = new File("//data/Users");
    private File bookfile = new File("//data/Books");
    // private File userfile = new
    // File(Main.class.getClassLoader().getResource("//data/Users").getFile());
    // private File bookfile = new
    // File(Main.class.getClassLoader().getResource("//data/Books").getFile());

    public Database() {
        if (!userfile.exists()) {
            userfile.mkdirs();
        }
        if (!bookfile.exists()) {
            bookfile.mkdirs();
        }
        getUsers();
    }

    public void addUser(User user) {
        users.add(user);
        username.add(user.getName());
        saveUsers();
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

    private void getUsers() {
        String text1 = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userfile));
            String line;
            while ((line = reader.readLine()) != null) {
                text1 += line + "\n";
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading user file: " + e.toString());
        }

        if (text1.matches("") || !text1.isEmpty()) {
            String[] usersData = text1.split("<NewUser/>\n");
            for (String  userData : usersData) {
                String[] fields = userData.split("<N/>");
                if (fields.length >= 4) {
                    String name = fields[0];
                    String email = fields[1];
                    String phoneNumber = fields[2];
                    String userType = fields[3];

                    User user;
                    if (userType.matches("Admin")) {
                        user = new Admin(name, email, phoneNumber);
                        
                    } else {
                        user = new NormalUser(name, email, phoneNumber);
                    }
                    addUser(user);
                }
            }
        }
    }

    private void saveUsers() {
        String text1 = "";
        for (User user : users) {
            text1 += text1 + user.toString() + "<NewUser/>\n";
        }
        try {
            PrintWriter writer = new PrintWriter(userfile);
            writer.print(text1);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error writing user file: " + e.toString());
        }
    }
}