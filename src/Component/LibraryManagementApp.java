package Component;

import java.util.List;
import java.util.Map;

public class LibraryManagementApp {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();

        // number of books
        Book book1 = new Book("B1", "Clean Code", "Robert C. Martin", true);
        Book book2 = new Book("B2", "Effective Java", "Joshua Bloch", true);
        Book book3 = new Book("B3", "Java Concurrency", "Brian Goetz", true);

        libraryService.addBooks(book1);
        libraryService.addBooks(book2);
        libraryService.addBooks(book3);

        libraryService.availableBooks();

        User user1 = new User("U1", "Arun Thakur");
        libraryService.issueBook(book2.getBookId(), user1.getUserId());
        libraryService.issueBook(book3.getBookId(), user1.getUserId());

        List<String> issueBookByUser = libraryService.getIssuedBook(user1.getUserId());

        for(String book : issueBookByUser) {
            System.out.println(book);
        }

        libraryService.returnBook(book2.getBookId(), user1.getUserId());
        libraryService.availableBooks();
    }
}
