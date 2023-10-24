import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/warehouseassetmanagement";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String loginUsername = scan.nextLine();
        System.out.print("Enter your password: ");
        String loginPassword = scan.nextLine();

        if (authenticateUser(loginUsername, loginPassword)) {
            // User is successfully logged in
            // You can add your application logic here
            // For now, we'll just display a welcome message
            System.out.println("Welcome, " + loginUsername + "!");
        } else {
            System.out.println("Invalid username or password.");
        }

        scan.close();
    }

    private static boolean authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If a matching user is found, authentication is successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle any database errors
        }
    }
}
