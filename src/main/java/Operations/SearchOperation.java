package Operations;

import Dao.BooksDao;
import entity.BookData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchOperation
{

    public List<BookData> searchByName(List<BookData> bookDataList, String name)
    {
        List<BookData> list=new ArrayList<>();
        for (BookData bookData:bookDataList)
        {
            if (bookData.getBookTitle().equalsIgnoreCase(name))
            {
                list.add(bookData);
            }
        }
        return list;
    }
    public List<BookData> searchByPublisher(List<BookData> bookDataList, String Publisher)
    {
        List<BookData> list=new ArrayList<>();
        for (BookData bookData:bookDataList)
        {
            if (bookData.getPublisher().equalsIgnoreCase(Publisher))
            {
                list.add(bookData);
            }
        }
        return list;
    }
    public List<BookData> searchByAuthorName(List<BookData> bookDataList, String name)
    {
        List<BookData> list=new ArrayList<>();
        for (BookData bookData:bookDataList)
        {
            if (bookData.getBookAuthor().equalsIgnoreCase(name))
            {
                list.add(bookData);
            }
        }
        return list;
    }
//    public List<BookData> searchByISBNNumber(List<BookData> bookDataList, int number)
//    {
//        List<BookData> list=new ArrayList<>();
//        for (BookData bookData:bookDataList)
//        {
//            if (bookData.getISBN()==(number))
//            {
//                list.add(bookData);
//            }
//        }
//        return list;
//    }

    public void displayAllBooks(List<BookData> allBook)
    {
        System.out.format("%-15s %-40s %-20s %-10s %-35s %-55s\n","ISBN_Number","Book_Title","Book_Author","year_Of_Publication","Publisher","Image_URL_S");
        Iterator<BookData>iterator=allBook.iterator();
        while (iterator.hasNext())
        {
            BookData bookData=iterator.next();
            //  System.out.println(bookData);
            System.out.format("%-15s %-40s %-20s %-10s %-35s %-55s\n",bookData.getISBN(), bookData.getBookTitle(),
                    bookData.getBookAuthor(),bookData.getYearOfPublication(),bookData.getPublisher(), bookData.getImage_URL_S());
        }

    }
}
