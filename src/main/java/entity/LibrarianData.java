package entity;

public class LibrarianData
{
    private int librarianId;
    private String librarianName;
    private String email;
    private String contactNumber;


    public LibrarianData(int librarianId, String librarianName, String email, String contactNumber) {
        this.librarianId = librarianId;
        this.librarianName = librarianName;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public LibrarianData() {

    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public String getLibrarianName() {
        return librarianName;
    }

    public void setLibrarianName(String librarianName) {
        this.librarianName = librarianName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    @Override
    public String toString() {
        return "LibrarianData{" +
                "librarianId=" + librarianId +
                ", librarianName='" + librarianName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
