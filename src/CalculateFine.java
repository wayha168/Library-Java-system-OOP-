import java.util.Scanner;

public class CalculateFine implements IOOperation {

    @Override
    public void oper(Database database, User user) {

        System.out.println("Enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();

        for (Borrowing borrowing : database.getAllBorrowings()) {
            if (borrowing.getBook().getName().matches(bookName) &&
                    borrowing.getUser().getName().matches(user.getName())) {
                int daysLeft = borrowing.getDaysLeft();
                if (daysLeft < 0) {
                    // int fine = Math.abs(daysLeft) * 5; // Assuming a fine of 5 units per day
                    System.out.println("You are late! " + " You have to pay "
                            + Math.abs(daysLeft * 50) + " as fine.");
                } else {
                    System.out.println("No fine. You have " + daysLeft + " days left to return the book.");
                }
                user.menu(database, user);
                return;
            }
        }
    }
}
