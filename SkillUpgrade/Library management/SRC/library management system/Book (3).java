package LibraryManagementSystem;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String title;
    private String author;
    private boolean isCheckedOut;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isCheckedOut = false;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isCheckedOut() { return isCheckedOut; }

    public void checkOut() { isCheckedOut = true; }
    public void returnBook() { isCheckedOut = false; }

    @Override
    public String toString() {
        return id + ": " + title + " by " + author + (isCheckedOut ? " (Checked Out)" : "");
    }
}
