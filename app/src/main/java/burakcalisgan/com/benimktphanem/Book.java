package burakcalisgan.com.benimktphanem;

public class Book {
    String bookName;
    String author;
    String pageNumber;

    public Book(String bookName, String author, String pageNumber) {
        this.bookName = bookName;
        this.author = author;
        this.pageNumber = pageNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }
}
