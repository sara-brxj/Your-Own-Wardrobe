package GUIS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class HelloLynseyFrame extends JFrame {
    private Connection connection;

    // Define the JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/clothing_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pcCervantes12345";

    public HelloLynseyFrame() {
        // Establish database connection
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
        }

        // Set JFrame title
        setTitle("Hello, Lynsey");

        // Set JFrame size
        setSize(400, 200);

        // Set JFrame layout
        setLayout(new BorderLayout());

        // Create label with "Hello, Lynsey"
        JLabel label = new JLabel("Hello, Lynsey", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        // Add label to JFrame
        add(label, BorderLayout.CENTER);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();

        // Create "Create Outfit" button
        JButton createOutfitButton = new JButton("Create Outfit");
        createOutfitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the TimeSelectionFrame when "Create Outfit" button is clicked
                new TimeSelectionFrame(connection);
            }
        });
        buttonPanel.add(createOutfitButton);

        // Create "Edit Wardrobe" button
        JButton editWardrobeButton = new JButton("Edit Wardrobe");
        editWardrobeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the AddDeleteClothesFrame when "Edit Wardrobe" button is clicked
                new AddDeleteClothesFrame(connection);
            }
        });
        buttonPanel.add(editWardrobeButton);

        // Add button panel to JFrame
        add(buttonPanel, BorderLayout.SOUTH);

        // Set JFrame to be visible
        setVisible(true);

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Create an instance of HelloLynseyFrame
        new HelloLynseyFrame();
    }
} 

