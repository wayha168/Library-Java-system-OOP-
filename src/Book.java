
public class Book {
    private String name;
    private String author;
    private String publisher;
    private String address;
    private String status;
    private int qty;
    private double price;
    private int brwcopies;

    public Book() {
    };

    public Book(String name, String author, String publisher,
            String address, String status, int qty,
            double price, int brwcopies) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.address = address;
        this.status = status;
        this.qty = qty;
        this.price = price;
        this.brwcopies = brwcopies;
    }

    public String toString() {
        String text = " Book {" +
                " name = '" + name + '\'' +
                ", Author = '" + author + '\'' +
                ", Publisher = '" + publisher + '\'' +
                ", Address = '" + address + '\'' +
                ", Status = '" + status + '\'' +
                ", Qty = " + String.valueOf(qty) +
                ", Price = " + String.valueOf(price) +
                ", Borrowing Copies = " + String.valueOf(brwcopies) +
                '}';
        return text;
    }

    // public ArrayList<String> toList(){
    // ArrayList<String> list = new ArrayList<>();
    // list.add(name);
    // list.add(author);
    // list.add(publisher);
    // list.add(address);
    // list.add(status);
    // list.add(String.valueOf(qty));
    // list.add(String.valueOf(price));
    // list.add(String.valueOf(brwcopies));
    // return list;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBrwcopies() {
        return brwcopies;
    }

    public void setBrwcopies(int brwcopies) {
        this.brwcopies = brwcopies;
    }

    public String toString2() {
    String text = name + "<N/>" + author + 
                "<N/>" + publisher + 
                "<N/>" + address +
                "<N/>" + status +
                "<N/>" + String.valueOf(qty) +
                "<N/>" + String.valueOf(price) +
                "<N/>" + String.valueOf(brwcopies);
        return text;
    }

    public Book parseBook(String data) {
        String[] fields = data.split("<N/>");
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

}
