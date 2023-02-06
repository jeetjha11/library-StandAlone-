package Operations;

import connectiontodatabase.com.ConnectionToDatabase;
import entity.IssuedBookData;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentWork {
    static Scanner sc = new Scanner(System.in);
    int bookId = 0;
    public static Connection connection;


    public StudentWork() throws SQLException, ClassNotFoundException {
        connection = ConnectionToDatabase.establishConnection();
    }

    public void issueBook(String bookname, String authorName) throws SQLException {
        System.out.println("Please Enter your student ID:");
        int id = sc.nextInt();
        LocalDate currentDate = LocalDate.now();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select bookId from allbooks where BookTitle='" + bookname + "'");
        while (resultSet.next()) {
            bookId = resultSet.getInt(1);
        }
        StudentWork.addingBook(id, bookId);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into issuedBookByStudent values(?,?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, bookname);
        preparedStatement.setString(3, authorName);
        preparedStatement.setString(4, String.valueOf(currentDate));
        int count = 0;
        count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Thank You.");
        } else {
            System.out.println("Something wants Wrong, please try again.");
        }
    }



    public static void addingBook(int id,int bookId) throws SQLException {
        PreparedStatement preparedStatement= connection.prepareStatement("insert into issuedBook values(?,?)");
        preparedStatement.setInt(1,id);
        preparedStatement.setInt(2,bookId);
        int count=0;
        count=preparedStatement.executeUpdate();
        if (count>0)
        {
            System.out.println("Your Book has been issued ThankYou........ ");
        }
        else {//need to add the data into the list....
            System.out.println("Sorry something went wrong :");
        }
    }
    public  void displayIssuedBookByStudent(List<IssuedBookData> allList) throws SQLException
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
    public void deleteBook(int id,String Title) throws SQLException {
        Statement statement=connection.createStatement();
        int count=0;
        count=statement.executeUpdate("delete  from issuedBookByStudent where Book_Name='"+Title+"'");
        if (count>0)
        {
            System.out.println("Thank you for the submission of the book. ");
        }
        else {
            System.out.println("Something Wrong. Please try again.");
        }

    }
}
