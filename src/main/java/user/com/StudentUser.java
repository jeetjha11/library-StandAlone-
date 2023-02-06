package user.com;

import connectiontodatabase.com.ConnectionToDatabase;
import exception.AdminNotFoundException;
import exception.EmailNotFound;
import exception.StudentNotFoundException;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentUser
{
    Scanner sc=new Scanner(System.in);
    String studentName,password,email,contactNumber;
    static int studentId=0;
    public int UserValidator() throws SQLException, ClassNotFoundException, AdminNotFoundException, StudentNotFoundException
    {
                System.out.println("Please Enter your Name:");
                studentName=sc.next();
                System.out.println("Please give your password:");
                password=sc.next();
                Connection connection= ConnectionToDatabase.establishConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select studentId from studentUser where studentName='"+studentName+"'and password='"+password+"'");
                while (resultSet.next())
                {
                    studentId=resultSet.getInt(1);

                }
                if (studentId>0)
                {
                    System.out.println("Welcome to the Library:");
                }
                else
                {
                    throw new StudentNotFoundException("Student Not found: Please enter the valid name and password:");
                }
                System.out.println("Do you want to delete your account 1. for yes 2 for no ");
                int choice3=sc.nextInt();
                switch (choice3)
                {
                    case 1:
                    {
                        System.out.println("Do you want to delete your Information from Library Student data? (yes/no).. (please chose this option when needed it will delete your all data) ");
                        String choice2=sc.next();
                        if ( choice2.equalsIgnoreCase("yes"))
                        {
                            deleteStudentData(studentId);
                            break;
                        }

                    }
                    case 2:
                    {
                        break;
                    }
                }

        return studentId;
    }
    public void createAccountStudent()
    {
        System.out.println("Please Enter your Name:");
        studentName=sc.next();
        try
        {
            System.out.println("please Enter your Email:");
            email=sc.next();
            String regex = "^(.+)@(.+)$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches())
            {
                System.out.println("Please Enter your contact Number");
                contactNumber=sc.next();
                System.out.println("Please give your password:");
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
                System.out.println("Welcome to the library");
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
    public void deleteStudentData(int Id) throws SQLException, ClassNotFoundException,StudentNotFoundException {
        Connection connection=ConnectionToDatabase.establishConnection();
        Statement statement=connection.createStatement();
        int temp=0;
        temp=statement.executeUpdate("delete from studentUser where studentId='"+Id+"'");
        if(temp>0)
        {
            System.out.println("Your data deleted successfully:");
        }
        else
        {
            throw new StudentNotFoundException("Student not found:");
        }

    }
}
