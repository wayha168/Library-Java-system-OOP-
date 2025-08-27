import java.util.Scanner;

public class AddBook implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        // Implementation for adding a book
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();

        System.out.println("Enter book name:");
        book.setName(scanner.next());
        System.out.println("Enter author name:");
        book.setAuthor(scanner.next()); 
        System.out.println("Enter publisher name:");
        book.setPublisher(scanner.next());
        System.out.println("Enter collection address:");
        book.setAddress(scanner.next());
        System.out.println("Enter status:");
        book.setStatus(scanner.next());
        System.out.println("Enter quantity:");
        book.setQty(scanner.nextInt());
        System.out.println("Enter price:");
        book.setPrice(scanner.nextDouble());
        System.out.println("Enter borrowing copies:");
        book.setBrwcopies(scanner.nextInt());
        scanner.close();
        database.addBook(book);
        System.out.println("Book added successfully!");
    }
}
