package libapp.model;

public class Order {

    private String nameOfLender;
    private BookDetails bookDetails;

    public Order(String nameOfLender, BookDetails bookDetails) {
        this.nameOfLender = nameOfLender;
        this.bookDetails = bookDetails;
    }

    public Order() {
    }

    public String getNameOfLender() {
        return nameOfLender;
    }

    public void setNameOfLender(String nameOfLender) {
        this.nameOfLender = nameOfLender;
    }

    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public String toString() {
        return "Name of Lend: " + nameOfLender + bookDetails;
    }
}
