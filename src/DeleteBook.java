import java.util.Scanner;

public class DeleteBook implements IOOperation {
    
    @Override
    public void oper (Database database, User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the book to delete:");
        String bookName = scanner.next();

        int bookId = database.getBook(bookName);
        if (bookId > -1) {
            database.deleteBook(bookId);
            System.out.println(bookName + "' deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }

        user.menu(database, user);
    }
    
}
