
import java.util.Scanner;

public class ViewOrder implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        
        System.out.println("Enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        int orderData = database.getBook(bookName);
        if (orderData > -1) {
            System.out.println("Book\t\tUser\t\tPrice\tQty");
            for (Order order : database.getAllOrders()) {
                if (order.getBook().getName().equalsIgnoreCase(bookName)) {
                    System.out.println(order.getBook().getName() + "\t\t" +
                            order.getUser().getName() + "\t\t" +
                            order.getPrice() + "\t" + order.getQty());
                }
                System.out.println();
            }
        } else {
            System.out.println("Book doesn't exit\n");

        }
        user.menu(database, user);

    }

}
