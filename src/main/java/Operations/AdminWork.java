package Operations;

import Dao.StudentDao;
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
import java.util.Iterator;
import java.util.List;

public class AdminWork extends Librarian
{
   static Connection connection;
   public void displayAllLibrarians(List<LibrarianData> allData)
   {
      Iterator<LibrarianData> iterator=allData.iterator();
     // System.out.format("%10s %10s %10s %10s","Librarian_Id,Librarian_Name,Email,Contact_Number");
      while (iterator.hasNext())
      {
         LibrarianData librarianData=iterator.next();
        // System.out.format("%10s %10s %10s %10s",librarianData.getLibrarianId(),librarianData.getLibrarianName(),librarianData.getEmail(),librarianData.getContactNumber());
         System.out.println(librarianData);
      }
   }
   public void addLibrarian()
   {
      Librarian librarian=new Librarian();
      librarian.createAccount();

   }
   public void deleteLibrarian(int id) throws SQLException, ClassNotFoundException, LibrarianNotFoundException {
      Librarian librarian=new Librarian();
      librarian.deleteLibrarianData(id);
   }
   public void viewAllStudent(List<StudentData> alldata)
   {
      Iterator<StudentData>iterator=alldata.iterator();
     // System.out.format("%10d %10s %10s %10s","Student_Id,Student_Name,Email,Contact_Number");
      while (iterator.hasNext())
      {
         StudentData studentData=iterator.next();
        // System.out.format("%10d %10s %10s %10s",studentData.getStudentId(),studentData.getStudentName(),studentData.getEmail(),studentData.getContactNumber());
         System.out.println(studentData);
      }
   }
   public void deleteStudentData(int id) throws SQLException, StudentNotFoundException, ClassNotFoundException {
      StudentUser studentUser=new StudentUser();
      studentUser.deleteStudentData(id);
   }
   public void displayAllStudent(List<StudentData> allData) {
      Iterator<StudentData> iterator = allData.iterator();
      // System.out.format("%10s %10s %10s %10s","Librarian_Id,Librarian_Name,Email,Contact_Number");
      while (iterator.hasNext()) {
         StudentData studentData = iterator.next();
         // System.out.format("%10s %10s %10s %10s",librarianData.getLibrarianId(),librarianData.getLibrarianName(),librarianData.getEmail(),librarianData.getContactNumber());
         System.out.println(studentData);
      }
   }
}
