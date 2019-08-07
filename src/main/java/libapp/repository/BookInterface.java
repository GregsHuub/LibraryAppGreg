package libapp.repository;

import libapp.model.BookDetails;
import libapp.model.Order;

import java.util.List;

public interface BookInterface {

    BookDetails addBook(BookDetails newBook);

    List<BookDetails> getListOfBooks();

    BookDetails getBookById(String bookId);

    void removeBook(String bookId);

    List<Order> lentBookByID(String bookId, String nameOfLenter);

    List findBook(String word);

    Object getBookDetailsById(String bookId);

    List findBooksByWords(String titleOrAuthor, String yearOrTitleOrAuthor);


}
