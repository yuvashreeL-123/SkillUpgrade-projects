package LibraryManagementSystem;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Patron");
            System.out.println("3. Check Out Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Books");
            System.out.println("6. List Patrons");
            System.out.println("7. List Transactions");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;
                case 2:
                    System.out.print("Enter patron ID: ");
                    String patronId = scanner.nextLine();
                    System.out.print("Enter patron name: ");
                    String name = scanner.nextLine();
                    library.addPatron(new Patron(patronId, name));
                    break;
                case 3:
                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter patron ID: ");
                    patronId = scanner.nextLine();
                    library.checkOutBook(bookId, patronId);
                    break;
                case 4:
                    System.out.print("Enter book ID: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter patron ID: ");
                    patronId = scanner.nextLine();
                    library.returnBook(bookId, patronId);
                    break;
                case 5:
                    for (Book book : library.getBooks()) {
                        System.out.println(book);
                    }
                    break;
                case 6:
                    for (Patron patron : library.getPatrons()) {
                        System.out.println(patron);
                    }
                    break;
                case 7:
                    for (Transaction transaction : library.getTransactions()) {
                        System.out.println(transaction);
                    }
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
