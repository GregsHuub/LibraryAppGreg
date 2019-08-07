package libapp.model;

import java.util.UUID;

public class BookDetails {

    private String title;
    private int year;
    private String author;
    private String uniqueId;
    private boolean forLent;

    public BookDetails() {
    }

    public BookDetails(String title, int year, String author) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.forLent = true;
        this.uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }


    public boolean isForLent() {
        return forLent;
    }

    public void setLent(boolean lent) {
        this.forLent = lent;
    }

    @Override
    public String toString() {
        return " \nTitle: " + title + "\n" +
                "Year: " + year + "\nAuthor: " + author +
                "\n" + "For lent: " + forLent +
                "\nBook id: " + uniqueId +
                "\n-----------------------";
    }
}
