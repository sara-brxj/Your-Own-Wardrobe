package GUIS;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class OutfitSuggestionFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Connection connection;

    public OutfitSuggestionFrame(Connection connection, String selectedWeather) {
        this.setConnection(connection);

        setTitle("Outfit Suggestions for " + selectedWeather);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel to display outfit suggestions
        JPanel outfitPanel = new JPanel(new GridLayout(0, 3, 10, 10));

        try {
            // Query the database to retrieve outfit suggestions based on selected weather
            PreparedStatement statement = connection.prepareStatement("SELECT image FROM clothes WHERE weather_type = ?");
            statement.setString(1, selectedWeather);
            ResultSet resultSet = statement.executeQuery();

            // Display outfit images
            while (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("image");
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image scaledImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(scaledIcon);
                outfitPanel.add(imageLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve outfit suggestions.");
        }

        JScrollPane scrollPane = new JScrollPane(outfitPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}