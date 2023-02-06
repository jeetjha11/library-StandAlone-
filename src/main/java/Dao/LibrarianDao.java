package Dao;

import connectiontodatabase.com.ConnectionToDatabase;
import entity.LibrarianData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDao
{
    public static Connection connection;
    List<LibrarianData>list=new ArrayList<>();
    public LibrarianDao() throws SQLException, ClassNotFoundException {
         connection= ConnectionToDatabase.establishConnection();
    }
    public List<LibrarianData> allLibrarianData()
    {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from librarianUser");
            while (resultSet.next())
            {
                int librarianId=resultSet.getInt(1);
                String librarianName=resultSet.getString(2);
                String email=resultSet.getString(3);
                String contactNumber=resultSet.getString(4);
                list.add(new LibrarianData(librarianId,librarianName,email,contactNumber));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
