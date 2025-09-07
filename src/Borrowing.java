import java.time.LocalDate;
import java.time.Period;

public class Borrowing {

    LocalDate start;
    LocalDate finish;
    int daysLeft;
    Book book;
    User user;      

    public Borrowing(Book book, User user) {
       start = LocalDate.now();
       finish = start.plusDays(14);
       Period period = Period.between(start, finish);
       daysLeft = period.getDays();
       this.book = book;
       this.user = user;    
    }

    public Borrowing(LocalDate start, LocalDate finish, int daysLeft, Book book, User user) {
        this.start = start;
        this.finish = finish;
        this.daysLeft = Period.between(start, finish).getDays();
        this.book = book;
        this.user = user;
    }
}
