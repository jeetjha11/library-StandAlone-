package Dao;

import connectiontodatabase.com.ConnectionToDatabase;
import entity.IssuedBookData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class IssuedBookDao1
{
   static Connection connection;
   public IssuedBookDao1() throws SQLException, ClassNotFoundException {
       connection= ConnectionToDatabase.establishConnection();
   }
    public List<IssuedBookData> allIssuedBookData1() throws SQLException {
        List<IssuedBookData> list=new ArrayList<>();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from issuedBookByStudent");
        while (resultSet.next())
        {
            int id=resultSet.getInt(1);
            String bookName=resultSet.getString(2);
            String authorName=resultSet.getString(3);
            String currentDate= String.valueOf(resultSet.getDate(4));
            list.add(new IssuedBookData(id,bookName,authorName,currentDate));
        }
        return list;
    }
}
