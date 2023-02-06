package App;

import Dao.*;
import Operations.AdminWork;
import Operations.LibrarianWork;
import Operations.SearchOperation;
import Operations.StudentWork;
import connectiontodatabase.com.ConnectionToDatabase;
import entity.BookData;
import entity.IssuedBookData;
import entity.LibrarianData;
import entity.StudentData;
import exception.AdminNotFoundException;
import exception.LibrarianNotFoundException;
import exception.StudentNotFoundException;
import user.com.Librarian;
import user.com.StudentUser;
import user.com.UserAdmin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LibraryImpl
{
    public static void main(String[] args) throws SQLException, AdminNotFoundException, ClassNotFoundException, LibrarianNotFoundException, StudentNotFoundException
    {

        Scanner sc=new Scanner(System.in);
        Librarian librarian=new Librarian();
        UserAdmin userAdmin=new UserAdmin();
        StudentUser studentUser=new StudentUser();
        int choice,tempChoice,choice2,choice3,tempChoice1;
        System.out.println("Please choose a option:\n" +
                "1.If you are a Admin press 1:\n" +
                "2.If you are a Librarian press 2:\n" +
                "3.If you are a Student press 3:");
        choice=sc.nextInt();
        switch (choice)
        {
            case 1:
            {
                userAdmin.UserValidator();
                break;
            }

            case 2:
            {
                librarian.UserValidator();
                break;
            }
            case 3:
            {
                studentUser.UserValidator();
                break;
            }
        }
        tempChoice=choice;
        loop:while (true)
        {
            switch (tempChoice)
            {
                case 1: {
                    do {
                        LibrarianData librarianData = new LibrarianData();
                        LibrarianDao librarianDao = new LibrarianDao();
                        AdminWork adminWork = new AdminWork();
                        List<LibrarianData> allLibrarianData = librarianDao.allLibrarianData();
                        StudentDao studentDao=new StudentDao();
                        List<StudentData>allStudentData=studentDao.allStudentData();
                        System.out.println("1.Press 1 for view all librarians:\n" +
                                "2.Press 2 for add Librarians:\n" +
                                "3.Press 3 for delete Librarian:\n" +
                                "4.Press 4 for view all Student who is using Library:\n" +
                                "5.For delete student data\n" +
                                "6. Log Out:");
                        choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1: {
                                adminWork.displayAllLibrarians(allLibrarianData);
                                break;
                            }
                            case 2: {
                                adminWork.addLibrarian();
                                break;
                            }
                            case 3:
                            {
                                adminWork.displayAllLibrarians(allLibrarianData);
                                System.out.println("Please enter the Librarian id which you want to delete:");
                                int tempId=sc.nextInt();
                                adminWork.deleteLibrarian(tempId);
                            }
                            case 4:
                            {
                                adminWork.viewAllStudent(allStudentData);
                                break;
                            }

                            case 5:
                            {
                              adminWork.displayAllStudent(allStudentData);
                                System.out.println("Please enter the Student id which you want to delete:");
                                int tempId=sc.nextInt();
                                adminWork.deleteStudentData(tempId);
                            }
                        }
                    }
                    while (choice2!=6);
                    break;
                }
                case 2:
                {
                    do
                    {
                        BooksDao booksDao=new BooksDao();
                        List<BookData> bookDataList=booksDao.allBooksData();
                        LibrarianWork librarianWork=new LibrarianWork();
                        LibrarianData librarianData = new LibrarianData();
                        LibrarianDao librarianDao = new LibrarianDao();
                        AdminWork adminWork = new AdminWork();
                        List<LibrarianData> allLibrarianData = librarianDao.allLibrarianData();
                        StudentDao studentDao=new StudentDao();
                        List<StudentData>allStudentData=studentDao.allStudentData();
                        System.out.println("Please Enter the choice:\n");
                        System.out.println("1.For view all student\n" +
                                "2.For Add a student as a member of the library\n" +
                                "3.Delete the student from library\n" +
                                "4.For view all books present in the library\n" +
                                "5.Issued book name with student name\n" +
                                "6.Log Out");
                        choice2=sc.nextInt();
                        switch (choice2)
                        {
                            case 1:
                            {
                                adminWork.viewAllStudent(allStudentData);
                                break;
                            }
                            case 2:
                            {
                                librarianWork.createAccountStudent();
                                break;
                            }
                            case 3:
                            {
                                adminWork.displayAllStudent(allStudentData);
                                System.out.println("Please enter the Student id which you want to delete:");
                                int tempId=sc.nextInt();
                                adminWork.deleteStudentData(tempId);
                                break ;
                            }
                            case 4:
                            {
                                librarianWork.displayAllBooks(bookDataList);
                                break ;
                            }
                            case 5:
                            {
                                System.out.println("Press 1 for see all all books issued by student\n" +
                                        "Press 2 fro see by student Id");
                                int choice4=sc.nextInt();
                                if (choice4==1)
                                {

                                    StudentWork studentWork=new StudentWork();
                                    IssuedBookDao1 issuedBookDao1=new IssuedBookDao1();
                                    List<IssuedBookData> allIssuedBookList=issuedBookDao1.allIssuedBookData1();
                                    studentWork.displayIssuedBookByStudent(allIssuedBookList);
                                    break ;
                                }
                                else
                                {
                                    StudentWork studentWork=new StudentWork();
                                    System.out.println("Please Enter your Student id.");
                                    int id=sc.nextInt();
                                    IssuedBookDao issuedBookDao=new IssuedBookDao();
                                    List<IssuedBookData> allIssuedBookList=issuedBookDao.allIssuedBookData1(id);
                                    studentWork.displayIssuedBookByStudent(allIssuedBookList);
                                    break ;
                                }
                            }
                            case 6:
                            {
                                System.exit(230);
                            }
                        }
                    }
                    while (choice2!=6);
                    break;
                }
                case 3:
                    do
                    {

                        StudentWork studentWork=new StudentWork();
                        BooksDao booksDao=new BooksDao();
                        List<BookData> bookDataList=booksDao.allBooksData();
                        LibrarianWork librarianWork=new LibrarianWork();
                        LibrarianData librarianData = new LibrarianData();
                        LibrarianDao librarianDao = new LibrarianDao();
                        AdminWork adminWork = new AdminWork();
                        List<LibrarianData> allLibrarianData = librarianDao.allLibrarianData();
                        StudentDao studentDao=new StudentDao();
                        List<StudentData>allStudentData=studentDao.allStudentData();
                        System.out.println("1.For view all books\n" +
                                "2.For search the books\n" +
                                "3.For issue your Book\n"+
                                "4.For view your issued books\n" +
                                "5.For return the book\n" +
                                "6.Log Out");
                        choice3=sc.nextInt();
                        switch (choice3)
                        {
                            case 1:
                            {
                                librarianWork.displayAllBooks(bookDataList);
                                break ;
                            }
                            case 2:
                            {
                                do
                                {
                                    bookDataList=booksDao.allBooksData();
                                    SearchOperation searchOperation=new SearchOperation();

                                System.out.println("1.Search book by book name:\n" +
                                        "2.Search book by Publisher:\n" +
                                        "3.Search book by book Author name:\n" +
                                        "4.For next operation"+
                                        "5.Log Out:");
                                tempChoice1=sc.nextInt();
                                switch (tempChoice1)
                                {
                                    case 1:
                                    {
                                        sc.nextLine();
                                        System.out.println("Please Enter the book name:");
                                        String name="";
                                        name=sc.nextLine();
                                        List<BookData>searchedByArtistName=searchOperation.searchByName(bookDataList,name);
                                        searchOperation.displayAllBooks(searchedByArtistName);
                                        break;
                                    }
                                    case 2:
                                    {
                                        sc.nextLine();
                                        System.out.println("Please Enter the publisher name:");
                                        String publisher="";
                                        publisher=sc.nextLine();
                                        List<BookData>searchedByGenreName=searchOperation.searchByPublisher(bookDataList,publisher);
                                        searchOperation.displayAllBooks(searchedByGenreName);
                                        break ;
                                    }
                                    case 3:
                                    {
                                        sc.nextLine();
                                        System.out.println("Please Enter the Author Name");
                                        String name="";
                                        name=sc.nextLine();
                                        List<BookData>searchedByAuthorName=searchOperation.searchByAuthorName(bookDataList,name);
                                        searchOperation.displayAllBooks(searchedByAuthorName);
                                    }
                                    case 4:
                                    {
                                        break;
                                    }
                                    case 5:
                                    {
                                        System.exit(333);
                                    }

                                }

                                }
                                while (tempChoice1!=5);
                                break ;
                            }
                            case 3:
                            {
                                librarianWork.displayAllBooks(bookDataList);
                                sc.nextLine();
                                System.out.println("Please enter the Book Title(book name):");
                                String bookTitle="";
                                bookTitle=sc.nextLine();
                                String authorName="";
                                sc.nextLine();
                                System.out.println("Please Enter the author name of the book");
                                authorName=sc.nextLine();
                                studentWork.issueBook(bookTitle,authorName);
                                break ;
                            }
                            case 4:
                            {
                                System.out.println("Please Enter your Student id.");
                                int id=sc.nextInt();
                                IssuedBookDao issuedBookDao=new IssuedBookDao();
                                List<IssuedBookData> allIssuedBookList=issuedBookDao.allIssuedBookData1(id);
                                studentWork.displayIssuedBookByStudent(allIssuedBookList);
                                break ;
                            }
                            case 5:
                            {
                                System.out.println("Please Enter the student ID");
                                int id=sc.nextInt();
                                sc.nextLine();
                                System.out.println("Please Enter Book Title");
                                String Title="";
                                Title=sc.nextLine();
                                studentWork.deleteBook(id,Title);
                                break;
                            }
                            case 6:
                            {
                                System.exit(555);
                            }
                        }
                    }
                    while (choice3!=6);
                    break ;
            }
        }
    }
}
