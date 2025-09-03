import java.util.Scanner;

public class Search implements IOOperation {
            
    @Override
    public void oper(Database database, User user) {
        
        System.out.println("Enter Book name: ");
        Scanner s = new Scanner(System.in);
        String name = s.next();
        int i = database.getBook(name);
        if (i > -1) {
            System.out.println(database.getBook(i).toString()); 
        } else {
            System.out.println("Book not found");
        }
        user.menu(database, user);
        s.close();
    }
}
