import java.util.Scanner;

public class PlaceOrder implements IOOperation {

    @Override
    public void oper(Database database, User user) {

        Order order = new Order();
        System.out.println("Enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        int i = database.getBook(bookName);
        if (i <= -1) {
            System.out.println("Book not found");
            return;
        } else {
            Book book = database.getBook(i);
            order.setBook(book);
            order.setUser(user);
            System.out.println("Enter quantity: ");
            int qty = scanner.nextInt();
            order.setQty(qty);
            order.setPrice(book.getPrice() * qty);
            int bookIndex = database.getBook(book.getName());
            book.setQty(book.getQty() - 1);
            database.addOrder(order, book, bookIndex);
            System.out.println("Order placed successfully!\n");
        }
        user.menu(database, user);
    }
}
