package Dao;

import connectiontodatabase.com.ConnectionToDatabase;
import entity.IssuedBookData;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssuedBookDao
{
     static  Connection connection;
    public IssuedBookDao() throws SQLException, ClassNotFoundException {
        connection= ConnectionToDatabase.establishConnection();
    }
    public List<IssuedBookData> allIssuedBookData1(int s_id) throws SQLException {
        List<IssuedBookData> list=new ArrayList<>();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from issuedBookByStudent where student_id='"+s_id+"'");
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
