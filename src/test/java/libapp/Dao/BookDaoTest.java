package libapp.Dao;

import libapp.model.BookDetails;
import libapp.model.Order;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao operations = new BookDao();
    private BookDetails book = new BookDetails("Inferno", 2016, "Dan Brown");
    private BookDetails book2 = new BookDetails("Kod da vinci", 2014, "Dan Brown");
    private BookDetails book3 = new BookDetails("Origin", 2018, "Dan Brown");
    private BookDetails book4 = new BookDetails("New Book", 2018, "Dan Brown");


    @Test
    public void shouldAddBookToTheList() {
        System.out.println("ADD BOOK TEST");

        //ADDED BOOKS TO THE LIST BY METHOD
        operations.addBook(book);
        operations.addBook(book2);
        operations.addBook(book3);

        assertSame(book, operations.getListOfBooks().get(0));
    }

    @Test
    public void shouldShowListOfBooks() {
        System.out.println("LIST OF BOOKS TEST");
        List<BookDetails> listOfBooks = new ArrayList<>();

        //ADDED BOOKS TO THE LIST BY METHOD - ACTUAL LIST
        operations.addBook(book);
        operations.addBook(book2);
        operations.addBook(book3);

        //EXPECT LIST
        listOfBooks.add(book);
        listOfBooks.add(book2);
        listOfBooks.add(book3);

        assertEquals(listOfBooks, operations.getListOfBooks());
    }

    @Test
    public void shouldRemoveBook() {
        System.out.println("REMOVE BOOK TEST");

        operations.addBook(book);
        operations.addBook(book2);

        //THIS LINE GOING TO PRINT LIST OF BOOKS
        List<BookDetails> listOfBooks = operations.getListOfBooks();

        //GET BOOK BY ID TO REMOVE
        BookDetails bookFromTheList = listOfBooks.get(0);
        operations.removeBook(bookFromTheList.getUniqueId());

        assertTrue("List should have less books", listOfBooks.size() < 2);

        //LIST OF BOOKS AFTER REMOVE
        operations.getListOfBooks();

    }

    @Test
    public void shouldChangeBookStatusAsLend() {
        System.out.println("LENT BOOK TEST");
        List<BookDetails> testList = new ArrayList<>();

        operations.addBook(book);

        //EXPECT OBJECT
        testList.add(book);

        List<BookDetails> listOfBooks = operations.getListOfBooks();
        //ACTUAL OBJECT
        BookDetails bookFromTheList = listOfBooks.get(0);

        //SET BOOK AS LENT AND ADD NAME OF LENTER
        System.out.println(operations.lentBookByID(bookFromTheList.getUniqueId(), "Grzegorz"));

        //PRINT LIST OF BOOKS WITH CHANGE WITHOUT NAME
        operations.getListOfBooks();

        assertNotEquals("boolean should not be the same", testList.get(0), bookFromTheList.isForLent());
    }

    @Test
    public void shouldFindBooksBySingleWord() {
        System.out.println("FIND BOOKS BY WORD TEST");

        operations.addBook(book);
        operations.addBook(book2);
        operations.addBook(book3);

        List test1 = operations.findBook("Inferno");
        System.out.println(test1);
        List test2 = operations.findBook("Dan Brown");
        List test3 = operations.findBook("2016");

        assertTrue("Should be only 1 - title Inferno", test1.size() == 1);
        assertTrue("Should be 3 - author Dan Brown", test2.size() == 3);
        assertTrue("Should be 1 - year 2016", test3.size() == 1);

    }

    @Test
    public void shouldFindBooksByMixedWords() {
        System.out.println("FIND BOOKS BY MIX WORDS");

        operations.addBook(book);
        operations.addBook(book2);
        operations.addBook(book3);
        operations.addBook(book4);

        List test1 = operations.findBooksByWords("Dan Brown", "2014");
        List test2 = operations.findBooksByWords("Inferno", "Dan Brown");
        List test3 = operations.findBooksByWords("Dan Brown", "2018");
        System.out.println(test3);

        assertTrue("Should be only 1 With this author and year", test1.size() == 1);
        assertTrue("Should be only 1 With this title and author", test2.size() == 1);
        assertTrue("Should be 2 With this author and year", test3.size() == 2);
    }

    @Test
    public void shouldShowBookDetailsById() {
        System.out.println("GET BOOK DETAILS BY ID TEST");
        operations.addBook(book);
        operations.addBook(book2);
        operations.addBook(book3);
        //MAKE BOOK AS LEND FOR TEST IN LIST
        List<Order> orderList = operations.lentBookByID(book.getUniqueId(), "Greg");
        List<BookDetails> listOfBooks = operations.getListOfBooks();
        BookDetails bookForID = listOfBooks.get(0);

        assertEquals("Object should be the same", orderList.get(0), operations.getBookDetailsById(bookForID.getUniqueId()));
    }
}