package user.com;

import connectiontodatabase.com.ConnectionToDatabase;
import exception.AdminNotFoundException;
import exception.EmailNotFound;
import exception.LibrarianNotFoundException;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Librarian
{
    Scanner sc=new Scanner(System.in);
    String librarianName,password,email,contactNumber;
    static int librarianId=0;
    public int UserValidator() throws SQLException, ClassNotFoundException, LibrarianNotFoundException, AdminNotFoundException
    {

                System.out.println("Please Enter librarian Name:");
                librarianName=sc.next();
                System.out.println("Please give your password:");
                password=sc.next();
                Connection connection= ConnectionToDatabase.establishConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select librarianId from librarianUser where librarianName='"+librarianName+"'and password='"+password+"'");
                while (resultSet.next())
                {
                    librarianId=resultSet.getInt(1);

                }
                if (librarianId>0)
                {
                    System.out.println("Welcome to the Library:");
                }
                else
                {
                    throw new LibrarianNotFoundException("Librarian Not found: Please enter the valid name and password:");
                }
                System.out.println("Do you want to delete your account 1. for yes 2 for no ");
                int choice3=sc.nextInt();
                switch (choice3)
                {
                    case 1:
                    {
                        System.out.println("Do you want to delete your Information from Library? (yes/no).. (please chose this option when needed it will delete your all data) ");
                        String choice2=sc.next();
                        if ( choice2.equalsIgnoreCase("yes"))
                        {
                            deleteLibrarianData(librarianId);
                            break;
                        }

                    }
                    case 2:
                    {
                        break;
                    }
                }
        return librarianId;
    }
    public void createAccount()
    {
        System.out.println("Please Enter Librarian Name:");
        librarianName=sc.next();
        try
        {
            System.out.println("please Enter Librarian Email:");
            email=sc.next();
            String regex = "^(.+)@(.+)$";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches())
            {
                System.out.println("Please Enter Librarian contact Number");
                contactNumber=sc.next();
                System.out.println("Please give Librarian password:");
                password=sc.next();

            }
            else throw new EmailNotFound("Please Enter the valid Email:");
            Connection connection= ConnectionToDatabase.establishConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("insert into librarianUser (librarianName,email,contactNumber,password) values(?,?,?,?)");
            preparedStatement.setString(1,librarianName);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,contactNumber);
            preparedStatement.setString(4,password);
            int count=0;
            count=preparedStatement.executeUpdate();
            if (count>0)
            {
                System.out.println("Librarian Added Successfully");
            }
            else
            {
                System.out.println("Something  wrong please try again:");
            }
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select librarianId from librarianUser where contactNumber='"+contactNumber+"'");
            while (resultSet.next())
            {
                librarianId=resultSet.getInt(1);
            }
        } catch (EmailNotFound | ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }

    }
    public void deleteLibrarianData(int Id) throws SQLException, ClassNotFoundException,  LibrarianNotFoundException {
        Connection connection=ConnectionToDatabase.establishConnection();
        Statement statement=connection.createStatement();
        int temp=0;
        temp=statement.executeUpdate("delete from librarianUser where librarianId='"+Id+"'");
        if(temp>0)
        {
            System.out.println("Librarian data deleted successfully:");
        }
        else
        {
            throw new LibrarianNotFoundException("Librarian not found:");
        }

    }
}
