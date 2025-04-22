import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Call the ConnectToDB() method to establish a connection
        Connection conn = Connect.ConnectToDB();

        if (conn != null) {
            System.out.println("Connection to the database was successful!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
