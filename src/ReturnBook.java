import java.util.Scanner;

public class ReturnBook implements IOOperation {

    @Override
    public void oper(Database database, User user) {

        System.out.println("Enter book name: ");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.next();
        if (!database.getAllBorrowings().isEmpty()) {
            for (Borrowing borrowing : database.getAllBorrowings()) {
                if (borrowing.getBook().getName().matches(bookName) &&
                        borrowing.getUser().getName().matches(user.getName())) {

                    Book book = borrowing.getBook();
                    int daysLeft = borrowing.getDaysLeft();
                    int i = database.getAllBooks().indexOf(book);
                    if (daysLeft < 0) {
                        System.out.println("You are late! " + " You have to pay "
                                + Math.abs(daysLeft * 50) + " as fine.");
                    } else {
                        System.out.println("No fine. You have " + daysLeft + " days left to return the book.");
                    }
                    book.setBrwcopies(book.getBrwcopies() + 1);
                    database.returnBook(borrowing, book, i);
                    System.out.println("You have returned the book:\n" + borrowing);

                } else {
                    System.out.println("No record found for this book under your name.");

                }
                user.menu(database, user);
            }
        }
    }

}
