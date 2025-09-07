public class Order {
    private Book book;
    private User user;
    private double price;
    private int qty;

    public Order() {};

    public Order(Book book, User user, double price, int qty) {
        this.book = book;
        this.user = user;
        this.price = price;
        this.qty = qty;
    };

    public String toString() {
        String text = " Order {" +
                " Book = '" + book.getName() + '\'' +
                ", User = '" + user.getName() + '\'' +
                ", Price = " + String.valueOf(price) +
                ", Qty = " + String.valueOf(qty) +
                '}';
        return text;
    }

    public String toString2() {
        return book.getName() + "<N/>"
                + user.getName() + "<N/>"
                + String.valueOf(price) + "<N/>"
                + String.valueOf(qty);

    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
