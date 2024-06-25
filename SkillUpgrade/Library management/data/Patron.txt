package LibraryManagementSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patron implements Serializable {
    private String id;
    private String name;
    private List<String> checkedOutBooks;

    public Patron(String id, String name) {
        this.id = id;
        this.name = name;
        this.checkedOutBooks = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getCheckedOutBooks() { return checkedOutBooks; }

    public void checkOutBook(String bookId) {
        checkedOutBooks.add(bookId);
    }

    public void returnBook(String bookId) {
        checkedOutBooks.remove(bookId);
    }

    @Override
    public String toString() {
        return id + ": " + name + " (Books: " + checkedOutBooks.size() + ")";
    }
}
