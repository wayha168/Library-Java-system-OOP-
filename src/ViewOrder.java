import java.util.ArrayList;
import java.util.Scanner;

public class ViewOrder implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        System.out.println("Enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        int i = database.getBook(bookName);
        if (i <= -1) {
            ArrayList<Order> orders = new ArrayList<Order>();
            for (Order order : database.getAllOrders()) {
                if (order.getBook().getName().matches(bookName)) {
                    orders.add(order);
                }
            }
            System.out.println("Book\t\tUser\t\tPrice\tQty");
            for (Order order : orders) {
                System.out.println(order.getBook().getName() + "\t\t" +
                        order.getUser().getName() + "\t\t" +
                        order.getPrice() + "\t" +
                        order.getQty());
            }
            if (orders.size() == 0) {
                System.out.println("No orders found for this book");
            }
            System.out.println("\n");
        } else {
            System.out.println("Book not found");

        }
        user.menu(database, user);
    }

}
