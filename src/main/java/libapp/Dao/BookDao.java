package libapp.Dao;

import libapp.model.BookDetails;
import libapp.model.Order;
import libapp.repository.BookInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDao implements BookInterface {

    private List<BookDetails> listOfBooks = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

    // ADD BOOK TO THE LIBRARY
    public BookDetails addBook(BookDetails newBook) {
        listOfBooks.add(newBook);
        return newBook;
    }

    //GET LIST OF BOOKS WITH NOTICE HOW MANY BOOKS IS LEFT AND HOW MANY IS LENT
    //customer shouldn't see the name of another customer who is borrow the book
    public List<BookDetails> getListOfBooks() {
        int valueForLent = 0;
        int valueAlreadyLend = 0;
        System.out.println("List of Books:\n");
        for (BookDetails listOfBook : listOfBooks) {
            if (listOfBook.isForLent()) {
                valueForLent++;
            } else {
                valueAlreadyLend++;
            }
        }
        System.out.println(String.format("Number of books for lent: %s", valueForLent));
        System.out.println(String.format("Number of books already lend: %s", valueAlreadyLend));
        listOfBooks.forEach(System.out::println);
        return listOfBooks;
    }

    // GET BOOK BY ID
    public BookDetails getBookById(String bookId) throws NullPointerException {
        List<BookDetails> tempList = new ArrayList<>();
        for (BookDetails bookDetails : listOfBooks) {
            if (bookDetails.getUniqueId().equals(bookId)) {
                tempList.add(bookDetails);
            }
        }
        return tempList.get(0);
    }

    // REMOVE BOOK BY ID
    public void removeBook(String bookId) throws NullPointerException {
        try {
            BookDetails bookById = getBookById(bookId);
            if (bookById.isForLent()) {
                listOfBooks.remove(bookById);
            }
        } catch (NullPointerException npe) {
            System.err.println("Book with this ID doesn't exist or is already lent");
        }
    }

    // LENT THE BOOK BY ID
    public List<Order> lentBookByID(String bookId, String nameOfLend) {
        BookDetails bookById = getBookById(bookId);
        if (!bookById.isForLent()) {
            System.out.println("Book is already lend, or wrong ID");
        } else {
            bookById.setLent(false);
            Order order = new Order(nameOfLend, bookById);
            orderList.add(order);
//            System.out.println(order);
        }
        return orderList;
    }

    //FIND BOOK BY WORD
    public List findBook(String word) {
        List<BookDetails> temp = new ArrayList<>();
        boolean isNumeric = word.chars().allMatch(Character::isDigit);
        if (isNumeric) {
            temp = listOfBooks.stream()
                    .filter(s -> s.getYear() == Integer.parseInt(word))
                    .collect(Collectors.toList());
        } else {
            temp = listOfBooks.stream().filter(s -> s.getTitle().equals(word)
                    || s.getAuthor().equals(word))
                    .collect(Collectors.toList());
        }
        return temp;
    }

    //FIND BOOKS BY WORDS COMBINATION
    public List findBooksByWords(String titleOrAuthor, String yearOrTitleOrAuthor) {
        List<BookDetails> temp = new ArrayList<>();
        boolean isNumeric = yearOrTitleOrAuthor.chars().allMatch(Character::isDigit);
        if (isNumeric) {
            temp = listOfBooks.stream().filter(s -> (s.getTitle().equals(titleOrAuthor) ||
                    s.getAuthor().equals(titleOrAuthor)) &&
                    s.getYear() == Integer.parseInt(yearOrTitleOrAuthor))
                    .collect(Collectors.toList());
        } else {
            temp = listOfBooks.stream()
                    .filter(s -> s.getAuthor().equals(titleOrAuthor) || s.getTitle().equals(titleOrAuthor)
                            && s.getAuthor().equals(yearOrTitleOrAuthor) || s.getTitle().equals(yearOrTitleOrAuthor))
                    .collect(Collectors.toList());
        }
        return temp;
    }

    // GET BOOK BY ID WITH USER
    public Object getBookDetailsById(String bookId) {
        List<Object> listOfObjects = new ArrayList<>();
        BookDetails bookById = getBookById(bookId);
        if (orderList != null) {
            listOfObjects = orderList.stream().
                    filter(order -> order.getBookDetails().getUniqueId().equals(bookById.getUniqueId()))
                    .collect(Collectors.toList());
        }
        if (orderList.isEmpty()) {
            listOfObjects.add(bookById);
        }
        return listOfObjects.get(0);
    }
}
