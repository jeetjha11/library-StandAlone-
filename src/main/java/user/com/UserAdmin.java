package user.com;

import connectiontodatabase.com.ConnectionToDatabase;
import exception.AdminNotFoundException;
import exception.EmailNotFound;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class UserAdmin
{
    Scanner sc=new Scanner(System.in);
    String adminName,password,email,contactNumber;
   static int adminId=0;
    public int UserValidator() throws SQLException, ClassNotFoundException, AdminNotFoundException {
        int choice;
        System.out.println("1. For existing Admin");
        System.out.println("2. For new Admin");
        choice=sc.nextInt();
        switch (choice)
        {
            case 1:
            {
                System.out.println("Please Enter Admin Name:");
                adminName=sc.next();
                System.out.println("Please give your password:");
                password=sc.next();
                Connection connection=ConnectionToDatabase.establishConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery("select adminId from adminUser where adminName='"+adminName+"'and password='"+password+"'");
                while (resultSet.next())
                {
                    adminId=resultSet.getInt(1);

                }
                if (adminId>0)
                {
                    System.out.println("Welcome to the Library:");
                }
                else
                {
                    throw new AdminNotFoundException("Admin Not found: Please enter the valid name and password:");
                }
                System.out.println("Do you want to delete your account 1. for yes 2 for no ");
                int choice3=sc.nextInt();
                switch (choice3)
                {
                    case 1:
                    {
                        System.out.println("Do you want to delete your Information from Admin? (yes/no).. (please chose this option when needed it will delete your all data) ");
                        String choice2=sc.next();
                        if ( choice2.equalsIgnoreCase("yes"))
                        {
                            deleteAdminData(adminId);
                            break;
                        }

                    }
                    case 2:
                    {
                        break;
                    }
                }
                break;

            }
            case 2:
            {
                createAccount();
                break;
            }

        }
        return adminId;
    }
    public int createAccount()
    {
        System.out.println("Please Enter Admin Name:");
        adminName=sc.next();
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
            PreparedStatement preparedStatement=connection.prepareStatement("insert into adminUser (adminName,email,contactNumber,password) values(?,?,?,?)");
            preparedStatement.setString(1,adminName);
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
            ResultSet resultSet=statement.executeQuery("select adminid from adminUser where contactNumber='"+contactNumber+"'");
            while (resultSet.next())
            {
                adminId=resultSet.getInt(1);
            }
        } catch (EmailNotFound | ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
        return adminId;
    }
    public void deleteAdminData(int Id) throws SQLException, ClassNotFoundException, AdminNotFoundException {
        Connection connection=ConnectionToDatabase.establishConnection();
        Statement statement=connection.createStatement();
        Id=adminId;
        int temp=0;
        temp=statement.executeUpdate("delete from adminUser where adminId='"+Id+"'");
       if(temp>0)
       {
           System.out.println("Admin data deleted successfully:");
       }
       else
       {
           throw new AdminNotFoundException("Admin not found:");
       }

    }
}
