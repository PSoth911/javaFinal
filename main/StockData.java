package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StockData {
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://127.0.0.1:3306/stock";
    private static final String username = "root";
    private static final String password = "soth123%";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connection established successfully.");
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed successfully.");
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static int executeUpdate(String query) {
        try {
            Statement statement = getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);
            return rowsAffected;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    // public static void main(String[] args) {
    //     // Test the database connection
    //     Connection conn = StockData.getConnection();

    //     ResultSet rs = StockData.executeQuery("select * from admin_account;");
    //     try {
    //         while (rs.next()) {
    //             System.out.println( "Username: " + rs.getString("Admin_Name")+", Email: " + rs.getString("Admin_Email"));
    //             // for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
    //             //     System.out.print(rs.getString(i) + " ");
    //             // }
    //             // System.out.println();
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     // Close the connection when done
    //     StockData.closeConnection();
    // }
}