import java.util.Scanner;

public class BorrowBook implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book name to borrow: ");
        String bookName = scanner.next();

        int i = database.getBook(bookName);
        if (i > -1) {
            Book book = database.getBook(i);
            if (book.getBrwcopies() > 1) {
                Borrowing borrowing = new Borrowing(book, user);
                book.setBrwcopies(book.getBrwcopies() - 1);
                database.borrowBook(borrowing, book, i);
                System.out.println("You have borrowed the book:\n" + borrowing);
                System.out.println("You must return the book before 14days from now.\n" +
                        "expire date: " + borrowing.getFinish() + "\nEnjoy!");
            } else {
                System.out.println("The book is no available copies to borrow.");
            }
        } else {
            System.out.println("Book not found");
        }
        user.menu(database, user);
    }

}
