package librarymangementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LibraryDB {
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb";
    private static final String USER = "root";
    private static final String PASSWORD = "rak@2007"; 

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
            return con;
        } catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
    public static void addBook(Book book) {

    String sql = "INSERT INTO books(book_id,title,author,publisher,available) VALUES(?,?,?,?,?)";

    try {
        Connection con = getConnection();

        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, book.getBookId());
        pst.setString(2, book.getTitle());
        pst.setString(3, book.getAuthor());
        pst.setString(4, book.getPublisher());
        pst.setBoolean(5, book.isAvailable());

        pst.executeUpdate();

        System.out.println("Book Added Successfully!");

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public static ResultSet getAllBooks() {

    try {

        Connection con = getConnection();

        Statement st = con.createStatement();

        return st.executeQuery("SELECT * FROM books");

    } catch (Exception e) {

        e.printStackTrace();

        return null;
    }
}

}
