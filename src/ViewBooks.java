import java.util.ArrayList;

public class ViewBooks implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        ArrayList<Book> books = database.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("Name\t\tAuthor\t\tPublisher\tAddress\tQty\tPrice\tBrw Copies");
        for (Book book : books) {
            System.out.println(
                    book.getName() + "\t\t" +
                    book.getAuthor() + "\t\t" +
                    book.getPublisher() + "\t\t" +
                    book.getAddress() + "\t" +
                    book.getQty() + "\t" +
                    book.getPrice() + "\t" +
                    book.getBrwcopies());
        }

        System.out.println();
        user.menu(database, user);
    }
}