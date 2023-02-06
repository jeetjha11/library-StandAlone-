package Dao;

import connectiontodatabase.com.ConnectionToDatabase;
import entity.StudentData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao
{
    static Connection connection;
    public StudentDao() throws SQLException, ClassNotFoundException {
        connection= ConnectionToDatabase.establishConnection();
    }
    public List<StudentData> allStudentData()
    {
        List<StudentData>list=new ArrayList<>();
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from studentUser");  //need to create table of student
            while (resultSet.next())
            {
                int id=resultSet.getInt(1);
                String name= resultSet.getString(2);
                String email= resultSet.getString(3);
                String contact= resultSet.getString(4);
                list.add(new StudentData(id,name,email,contact));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
