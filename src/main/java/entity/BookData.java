package entity;

public class BookData
{
    private String ISBN;
    private String bookTitle;
    private String BookAuthor;
    private int yearOfPublication;
    private String publisher;
    private String Image_URL_S;
    private  String Image_URL_M;
    private String Image_URL_L;
    private int bookId;

    public BookData(String ISBN, String bookTitle, String bookAuthor, int yearOfPublication, String publisher, String image_URL_S, String image_URL_M, String image_URL_L,int bookId) {
        this.ISBN = ISBN;
        this.bookTitle = bookTitle;
        BookAuthor = bookAuthor;
        this.yearOfPublication = yearOfPublication;
        this.publisher = publisher;
        Image_URL_S = image_URL_S;
        Image_URL_M = image_URL_M;
        Image_URL_L = image_URL_L;
        this.bookId=bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        BookAuthor = bookAuthor;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImage_URL_S() {
        return Image_URL_S;
    }

    public void setImage_URL_S(String image_URL_S) {
        Image_URL_S = image_URL_S;
    }

    public String getImage_URL_M() {
        return Image_URL_M;
    }

    public void setImage_URL_M(String image_URL_M) {
        Image_URL_M = image_URL_M;
    }

    public String getImage_URL_L() {
        return Image_URL_L;
    }

    public void setImage_URL_L(String image_URL_L) {
        Image_URL_L = image_URL_L;
    }

    @Override
    public String toString() {
        return "BookData{" +
                "ISBN='" + ISBN + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", BookAuthor='" + BookAuthor + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", publisher='" + publisher + '\'' +
                ", Image_URL_S='" + Image_URL_S + '\'' +
                ", Image_URL_M='" + Image_URL_M + '\'' +
                ", Image_URL_L='" + Image_URL_L + '\'' +
                '}';
    }
}
