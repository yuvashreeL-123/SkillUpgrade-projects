package LibraryManagementSystem;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Patron> patrons;
    private List<Transaction> transactions;

    public Library() {
        books = new ArrayList<>();
        patrons = new ArrayList<>();
        transactions = new ArrayList<>();
        loadData();
    }

    public void addBook(Book book) {
        books.add(book);
        saveData();
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
        saveData();
    }

    public void checkOutBook(String bookId, String patronId) {
        Book book = findBook(bookId);
        Patron patron = findPatron(patronId);
        if (book != null && patron != null && !book.isCheckedOut()) {
            book.checkOut();
            patron.checkOutBook(bookId);
            LocalDate dueDate = LocalDate.now().plusWeeks(2);
            transactions.add(new Transaction(bookId, patronId, LocalDate.now(), dueDate));
            saveData();
        }
    }

    public void returnBook(String bookId, String patronId) {
        Book book = findBook(bookId);
        Patron patron = findPatron(patronId);
        if (book != null && patron != null && book.isCheckedOut()) {
            book.returnBook();
            patron.returnBook(bookId);
            for (Transaction transaction : transactions) {
                if (transaction.getBookId().equals(bookId) && transaction.getPatronId().equals(patronId) && !transaction.isReturned()) {
                    transaction.returnBook();
                    break;
                }
            }
            saveData();
        }
    }

    public List<Book> getBooks() { return books; }
    public List<Patron> getPatrons() { return patrons; }
    public List<Transaction> getTransactions() { return transactions; }

    private Book findBook(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private Patron findPatron(String patronId) {
        for (Patron patron : patrons) {
            if (patron.getId().equals(patronId)) {
                return patron;
            }
        }
        return null;
    }

    private void loadData() {
        try (ObjectInputStream bookIn = new ObjectInputStream(new FileInputStream("data/books.txt"));
             ObjectInputStream patronIn = new ObjectInputStream(new FileInputStream("data/patrons.txt"));
             ObjectInputStream transactionIn = new ObjectInputStream(new FileInputStream("data/transactions.txt"))) {
            books = (List<Book>) bookIn.readObject();
            patrons = (List<Patron>) patronIn.readObject();
            transactions = (List<Transaction>) transactionIn.readObject();
        } catch (FileNotFoundException e) {
            // Files might not exist yet, which is fine
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try (ObjectOutputStream bookOut = new ObjectOutputStream(new FileOutputStream("data/books.txt"));
             ObjectOutputStream patronOut = new ObjectOutputStream(new FileOutputStream("data/patrons.txt"));
             ObjectOutputStream transactionOut = new ObjectOutputStream(new FileOutputStream("data/transactions.txt"))) {
            bookOut.writeObject(books);
            patronOut.writeObject(patrons);
            transactionOut.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
