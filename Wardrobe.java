package GUIS;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	
	@SuppressWarnings("unused")
	public class Wardrobe {
	    // JDBC URL, username, and password of MySQL server
	    // replace with your credentials
	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/clothing_store";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "pcCervantes12345";
	    public static void main(String[] args) {
	        Connection connection = null;
	        try {
	            // Register MySQL JDBC driver
	            System.out.println("Registering MySQL JDBC driver...");
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            System.out.println("MySQL JDBC driver registered successfully.");

	            // Establish connection
	            System.out.println("Connecting to the database...");
	            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	            System.out.println("Connected to the database successfully.");

	            if (connection != null) {
	                System.out.println("Connected to the database");
	                // Perform database operations here
	            }
	        } catch (ClassNotFoundException e) {
	            System.err.println("Error loading MySQL JDBC driver: " + e.getMessage());
	        } catch (SQLException e) {
	            System.err.println("Error connecting to the database: " + e.getMessage());
	        } finally {
	            // Close the connection
	            if (connection != null) {
	                try {
	                    System.out.println("Closing the database connection...");
	                    connection.close();
	                    System.out.println("Database connection closed successfully.");
	                } catch (SQLException e) {
	                    System.err.println("Error closing the connection: " + e.getMessage());
	                }
	            }
	        }
	    }
	}

