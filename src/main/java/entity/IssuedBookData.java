package entity;

import java.sql.Date;
import java.time.LocalDate;

public class IssuedBookData
{
    private int student_id;
    private String Book_Name;
    private String Author_name;
    private String currentDate;

    public IssuedBookData(int student_id, String book_Name, String author_name, String currentDate) {
        this.student_id = student_id;
        Book_Name = book_Name;
        Author_name = author_name;
        this.currentDate = currentDate;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getBook_Name() {
        return Book_Name;
    }

    public void setBook_Name(String book_Name) {
        Book_Name = book_Name;
    }

    public String getAuthor_name() {
        return Author_name;
    }

    public void setAuthor_name(String author_name) {
        Author_name = author_name;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return "IssuedBookData{" +
                "student_id=" + student_id +
                ", Book_Name='" + Book_Name + '\'' +
                ", Author_name='" + Author_name + '\'' +
                ", currentDate=" + currentDate +
                '}';
    }
}