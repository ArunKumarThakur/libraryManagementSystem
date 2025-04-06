package Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class LibraryService {
    List<Book> bookList = new ArrayList<>();

    // Book id , Book
    Map<String, Book> bookMap = new HashMap<>();

    Map<String, List<String>> issuedBooksByUser = new HashMap<>();


    public void addBooks(Book book) {
        bookList.add(book);
        bookMap.put(book.getBookId(), book);
    }

    public Book searchBook(String bookId) {
        return bookMap.get(bookId);
    }

    public void availableBooks() {
        for(Book book : bookList) {
            if(book.isAvailable())
                System.out.println(book);
        }
    }

    public boolean issueBook(String bookId, String userId) {
        Book book = bookMap.get(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);

            issuedBooksByUser.putIfAbsent(userId, new ArrayList<>());
            issuedBooksByUser.get(userId).add(bookId);

            System.out.println("✅ Book '" + book.getTitle() + "' issued to user: " + userId);
            return true;
        } else {
            System.out.println("❌ Book not available or doesn't exist.");
            return false;
        }
    }


    public boolean returnBook(String bookId, String userId) {
        Book book = bookMap.get(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);

            List<String> userBooks = issuedBooksByUser.get(userId);
            if (userBooks != null) {
                userBooks.remove(bookId);
            }

            System.out.println("🔁 Book '" + book.getTitle() + "' returned by user: " + userId);
            return true;
        } else {
            System.out.println("❌ Book was not issued or doesn't exist.");
            return false;
        }
    }

    public List<String> getIssuedBook(String user) {
        return issuedBooksByUser.get(user);
    }

}
