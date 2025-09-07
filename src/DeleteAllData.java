import java.util.Scanner;

public class DeleteAllData implements IOOperation {

    @Override
    public void oper(Database database, User user) {

        System.out.println("Are you sure you want to delete all data?\n" +
                "1. Continue\n" + "2. Main Menu");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        if (choice == 1) {
            database.deleteAllData();
            System.out.println("All data deleted successfully.");
            user.menu(database, user);
            s.close();
        } else {
            user.menu(database, user);
            s.close();
        }
    }

}
