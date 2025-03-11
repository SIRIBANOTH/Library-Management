import java.util.ArrayList;
import java.util.Scanner;

// Book Class
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You have borrowed: " + title);
        } else {
            System.out.println("Sorry, this book is already borrowed.");
        }
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println("You have returned: " + title);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

// Library Class
class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

// User Class
class User {
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Library library, String title) {
        Book book = library.findBook(title);
        if (book != null && book.isAvailable()) {
            book.borrowBook();
            borrowedBooks.add(book);
        } else {
            System.out.println("Book not available or not found.");
        }
    }

    public void returnBook(Library library, String title) {
        for (Book book : borrowedBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                borrowedBooks.remove(book);
                return;
            }
        }
        System.out.println("You haven't borrowed this book.");
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books.");
        } else {
            System.out.println("Borrowed books:");
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }
}

// Main Class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);

        // Adding some default books
        library.addBook(new Book("Java Programming", "James Gosling"));
        library.addBook(new Book("Data Structures", "Mark Weiss"));
        library.addBook(new Book("Machine Learning", "Tom Mitchell"));

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Display Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Show Borrowed Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    user.borrowBook(library, borrowTitle);
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine();
                    user.returnBook(library, returnTitle);
                    break;
                case 4:
                    user.showBorrowedBooks();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
