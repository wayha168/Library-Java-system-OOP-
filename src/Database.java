import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> username = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> booknames = new ArrayList<String>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();

    private File dataDir = new File("Data");
    private File userfile = new File(dataDir, "Users");
    private File booksfile = new File(dataDir, "Books");
    private File ordersfile = new File(dataDir, "Orders");
    private File borrowsfile = new File(dataDir, "Borrows");

    public Database() {
        // Create "data" directory if it doesn't exist
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        if (!userfile.exists()) {
            try {
                userfile.createNewFile();
            } catch (Exception e) {
            }
        }
        if (!booksfile.exists()) {
            try {
                booksfile.createNewFile();
            } catch (Exception e) {
            }
        }
        if (!ordersfile.exists()) {
            try {
                ordersfile.createNewFile();
            } catch (Exception e) {
            }
        }
        if (!borrowsfile.exists()) {
            try {
                borrowsfile.createNewFile();
            } catch (Exception e) {
            }
        }
        getUsers();
        getBooks();
        getOrders();
        getBorrowings();
    }

    // public Database() {
    // if (!userfile.exists()) {
    // userfile.mkdirs();
    // }
    // if (!bookfile.exists()) {
    // bookfile.mkdirs();
    // }
    // getUsers();
    // }

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
        if (found == -1) {
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
        saveBooks();
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

        if (!text1.isEmpty() || !text1.matches("")) {
            String[] usersData = text1.split("<NewUser/>\n");
            for (String userData : usersData) {
                String[] fields = userData.split("<N/>");
                if (fields.length >= 4) {
                    String name = fields[0];
                    String email = fields[1];
                    String phoneNumber = fields[2];
                    String userType = fields[3];

                    User user;
                    if (userType.equalsIgnoreCase("Admin")) {
                        user = new Admin(name, email, phoneNumber);
                        // username.add(user.getName());
                    } else {
                        user = new NormalUser(name, email, phoneNumber);
                        // username.add(user.getName());
                    }
                    users.add(user);
                    username.add(user.getName());

                }
            }
        }
    }

    private void saveUsers() {
        String text1 = "";
        for (User user : users) {
            String userType = (user instanceof Admin) ? "Admin" : "NormalUser";
            text1 += user.getName() + "<N/>"
                    + user.getEmail() + "<N/>"
                    + user.getPhoneNumber() + "<N/>"
                    + userType + "<NewUser/>\n";
        }
        try {
            PrintWriter writer = new PrintWriter(userfile);
            writer.print(text1);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error writing user file: " + e.toString());
        }
    }

    private void saveBooks() {
        String text1 = "";
        for (Book book : books) {
            text1 += book.getName() + "<N/>"
                    + book.getAuthor() + "<N/>"
                    + book.getPublisher() + "<N/>"
                    + book.getAddress() + "<N/>"
                    + book.getStatus() + "<N/>"
                    + book.getQty() + "<N/>"
                    + book.getPrice() + "<N/>"
                    + book.getBrwcopies() + "<NewBook/>\n";
        }
        try {
            PrintWriter writer = new PrintWriter(booksfile);
            writer.print(text1);
            writer.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void getBooks() {
        String text1 = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(booksfile));
            String line;
            while ((line = reader.readLine()) != null) {
                text1 = text1 + line + "\n";
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.isEmpty()) {
            String[] bookData = text1.split("<NewBook/>\n");
            for (String bookDetails : bookData) {
                Book book = parseBook(bookDetails);
                if (book != null) { // <-- fix: only add valid books
                    books.add(book);
                    booknames.add(book.getName());
                } else {
                    System.err.println("Error parsing book data: " + bookDetails);
                }
            }
        }
    }

    public Book parseBook(String data) {
        String[] fields = data.split("<N/>");
        if (fields.length < 8) {
            System.err.println("Error: Book data format invalid: " + data);
            return null;
        }

        Book book = new Book();
        book.setName(fields[0]);
        book.setAuthor(fields[1]);
        book.setPublisher(fields[2]);
        book.setAddress(fields[3]);
        book.setStatus(fields[4]);
        book.setQty(Integer.parseInt(fields[5]));
        book.setPrice(Double.parseDouble(fields[6]));
        book.setBrwcopies(Integer.parseInt(fields[7]));
        return book;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public int getBook(String bookName) {
        int found = -1;
        for (Book book : books) {
            if (book.getName().matches(bookName)) {
                found = books.indexOf(book);
            }
        }
        return found;
    }

    public Book getBook(int i) {
        return books.get(i);
    }

    public void deleteBook(int bookId) {
        if (bookId >= 0 && bookId < books.size()) {
            books.remove(bookId);
            booknames.remove(bookId);
            saveBooks();
        } else {
            System.out.println("Invalid book ID.");
        }
    }

    public void deleteAllData() {
        if (userfile.exists()) {
            try {
                userfile.delete();
            } catch (Exception e) {
            }
        }
        if (booksfile.exists()) {
            try {
                booksfile.delete();
            } catch (Exception e) {
            }
        }
        if (ordersfile.exists()) {
            try {
                ordersfile.delete();
            } catch (Exception e) {
            }
        }
        if (borrowsfile.exists()) {
            try {
                borrowsfile.delete();
            } catch (Exception e) {
            }
        }
    }

    public void addOrder(Order order, Book book, int bookIndex) {
        orders.add(order);
        books.set(bookIndex, book);
        saveOrders();
        saveBooks();
    }

    private void saveOrders() {
        String text1 = "";
        for (Order order : orders) {
            text1 += order.getBook().getName() + "<N/>"
                    + order.getUser().getName() + "<N/>"
                    + order.getPrice() + "<N/>"
                    + order.getQty() + "<NewOrder/>\n";
        }
        try (PrintWriter writer = new PrintWriter(ordersfile)) {
            writer.print(text1);
        } catch (Exception e) {
            System.err.println("Error writing orders file: " + e);
        }
    }

    private void getOrders() {
        String text1 = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ordersfile));
            String line;
            while ((line = reader.readLine()) != null) {
                text1 += line + "\n";
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.isEmpty() || !text1.matches("")) {
            String[] data = text1.split("<NewOrder/>\n");
            for (String orderData : data) {
                Order order = parseOrder(orderData);
                if (order != null) {
                    orders.add(order);
                } else {
                    System.err.println("Error parsing order data: " + orderData);
                }
            }
        }
    }

    private User getUserByName(String name) {
        User u = new NormalUser("");
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                u = user;
                break;
            }
        }
        return u;
    }

    private Order parseOrder(String data) {
        String[] fields = data.split("<N/>");
        if (fields.length < 4) {
            System.err.println("Error: Order data format invalid: " + data);
            return null;
        }
        Order order = new Order(books.get(getBook(fields[0])),
                getUserByName(fields[1]), Double.parseDouble(fields[2]),
                Integer.parseInt(fields[3]));
        return order;
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    private void saveBorrowings() {
        String text1 = "";
        for (Borrowing borrowing : borrowings) {
            text1 = text1 + borrowing.toString2() + "<NewBorrowing/>\n";
        }
        try {
            PrintWriter writer = new PrintWriter(borrowsfile);
            writer.print(text1);
            writer.close();
        } catch (Exception e) {
            System.err.println("Error writing borrowings file: " + e);
        }
    }

    private void getBorrowings() {
        String text1 = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(borrowsfile));
            String line;
            while ((line = reader.readLine()) != null) {
                text1 += line + "\n";
            }
            reader.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.isEmpty() || !text1.matches("")) {
            String[] data = text1.split("<NewBorrowing/>\n");
            for (String borrowingData : data) {
                Borrowing borrowing = parseBorrowing(borrowingData);
                if (borrowing != null) {
                    borrowings.add(borrowing);
                } else {
                    System.err.println("Error parsing borrowing data: " + borrowingData);
                }
            }
        }
    }

    private Borrowing parseBorrowing(String data) {
        String[] fields = data.split("<N/>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(fields[0], formatter);
        LocalDate finish = LocalDate.parse(fields[1], formatter);
        Book book = getBook(getBook(fields[3]));
        User user = getUserByName(fields[4]);
        if (fields.length < 5) {
            System.err.println("Error: Borrowing data format invalid: " + data);
            return null;
        }

        Borrowing borrowing = new Borrowing(start, finish, 0, book, user);
        return borrowing;
    }

    void borrowBook(Borrowing borrowing, Book book, int bookIndex) {
        borrowings.add(borrowing);
        books.set(bookIndex, book);
        saveBorrowings();
        saveBooks();
    }

    public ArrayList<Borrowing> getAllBorrowings() {
        return borrowings;
    }
    
}