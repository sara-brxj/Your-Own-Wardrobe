package GUIS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/clothing_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pcCervantes12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void insertData(Connection connection, String imagePath, String weatherType, int preferenceLevel) throws SQLException {
        String sql = "INSERT INTO your_table (image_path, weather_type, preference_level) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, imagePath);
            statement.setString(2, weatherType);
            statement.setInt(3, preferenceLevel);
            statement.executeUpdate();
        }
    }

	public static String getJdbcUrl() {
		return JDBC_URL;
	}
}