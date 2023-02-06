package Dao;

import connectiontodatabase.com.ConnectionToDatabase;
import entity.BookData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BooksDao
{
    static Connection con;
       public BooksDao() throws SQLException, ClassNotFoundException {
           con= ConnectionToDatabase.establishConnection();
       }
       public List<BookData> allBooksData() throws SQLException {
           List<BookData> list=new ArrayList<>();
           Statement statement=con.createStatement();
           ResultSet resultSet=statement.executeQuery("select * from allbooks limit 10");
           while (resultSet.next())
           {
               String isbn=resultSet.getString(1);
               String title=resultSet.getString(2);
               String author=resultSet.getString(3);
               int year=resultSet.getInt(4);
               String publisher=resultSet.getString(5);
               String Image_URL_S=resultSet.getString(6);
               String Image_URL_M=resultSet.getString(7);
               String Image_URL_L=resultSet.getString(8);
               int id=resultSet.getInt(9);
               list.add(new BookData(isbn,title,author,year,publisher,Image_URL_S,Image_URL_M,Image_URL_L,id));
           }
           return list;
       }
}
