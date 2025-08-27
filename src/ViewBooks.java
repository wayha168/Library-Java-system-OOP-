import java.util.ArrayList;

public class ViewBooks implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        ArrayList<Book> books = database.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("Name\tAuthor\tPublisher\tAddress\tStatus\tQty\tPrice\tBrw Copies");
        for (Book book : books) {
            System.out.println(
                    book.getName() + "\t" +
                    book.getAuthor() + "\t" +
                    book.getPublisher() + "\t" +
                    book.getAddress() + "\t" +
                    book.getStatus() + "\t" +
                    book.getQty() + "\t" +
                    book.getPrice() + "\t" +
                    book.getBrwcopies());
        }
    }

}