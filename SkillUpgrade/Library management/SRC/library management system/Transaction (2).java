package LibraryManagementSystem;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private String bookId;
    private String patronId;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private boolean isReturned;

    public Transaction(String bookId, String patronId, LocalDate checkoutDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.patronId = patronId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.isReturned = false;
    }

    public String getBookId() { return bookId; }
    public String getPatronId() { return patronId; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isReturned() { return isReturned; }

    public void returnBook() { isReturned = true; }

    @Override
    public String toString() {
        return "Transaction [bookId=" + bookId + ", patronId=" + patronId + ", checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + ", isReturned=" + isReturned + "]";
    }
}
