package Operations;

import connectiontodatabase.com.ConnectionToDatabase;
import entity.BookData;
import entity.IssuedBookData;
import exception.EmailNotFound;

import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibrarianWork
{
    Scanner sc=new Scanner(System.in);
    String studentName,email,contactNumber,password;
    int studentId;
    static Connection con;
    public LibrarianWork() throws SQLException, ClassNotFoundException {
         con= ConnectionToDatabase.establishConnection();
    }
    public void createAccountStudent()
    {
        System.out.println("Please Enter student Name:");
        studentName=sc.next();
        try
        {
            System.out.println("please Enter student Email:");
            email=sc.next();
            String regex = "^(.+)@(.+)$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches())
            {
                System.out.println("Please Enter Student contact Number");
                contactNumber=sc.next();
                System.out.println("Please give  password:");
                password=sc.next();

            }
            else throw new EmailNotFound("Please Enter the valid Email:");
            Connection connection= ConnectionToDatabase.establishConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("insert into studentUser (studentName,email,contactNumber,password) values(?,?,?,?)");
            preparedStatement.setString(1,studentName);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,contactNumber);
            preparedStatement.setString(4,password);
            int count=0;
            count=preparedStatement.executeUpdate();
            if (count>0)
            {
                System.out.println("Student Added Successfully");
            }
            else
            {
                System.out.println("Something  wrong please try again:");
            }
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select studentId from studentUser where contactNumber='"+contactNumber+"'");
            while (resultSet.next())
            {
                studentId=resultSet.getInt(1);
            }
        } catch (EmailNotFound | ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
    }
    public void displayAllBooks(List<BookData> allBook)
    {
        System.out.format("%-15s %-40s %-20s %-10s %-35s %-55s\n","ISBN_Number","Book_Title","Book_Author","year_Of_Publication","Publisher","Image_URL_S");
        Iterator<BookData>iterator=allBook.iterator();
        while (iterator.hasNext())
        {
            BookData bookData=iterator.next();
          //  System.out.println(bookData);
            System.out.format("%-15s %-40s %-20s %-10s %-35s %-55s\n",bookData.getISBN(), bookData.getBookTitle(),
                  bookData.getBookAuthor(),bookData.getYearOfPublication(),bookData.getPublisher(), bookData.getImage_URL_S());
        }

    }
    public  void displayIssuedBookByStudentById(List<IssuedBookData> allList) throws SQLException
    {
        System.out.format("%-15s %-40s %-20s %-10s\n","Student_id","Book_Title","Book_Author","Date of Issue");
        Iterator<IssuedBookData> iterator=allList.iterator();
        while (iterator.hasNext())
        {

            IssuedBookData issuedBookData=iterator.next();
            System.out.format("%-15d %-40s %-20s %-10s\n",issuedBookData.getStudent_id(),issuedBookData.getBook_Name(),
                    issuedBookData.getAuthor_name(),issuedBookData.getCurrentDate());
        }
    }
}
