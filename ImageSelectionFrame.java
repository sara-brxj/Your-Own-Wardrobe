package GUIS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.*;

public class ImageSelectionFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private JLabel imageLabel;
    private File selectedFile; // Declaring selectedFile outside the action listener

    public ImageSelectionFrame(Connection connection) {
        this.setConnection(connection);
        setTitle("Image Selection");
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel browseLabel = new JLabel("Select Image:");
        formPanel.add(browseLabel);

        JButton browseButton = new JButton("Browse");
        formPanel.add(browseButton);

        JLabel weatherLabel = new JLabel("Weather Type:");
        formPanel.add(weatherLabel);

        JComboBox<String> weatherComboBox = new JComboBox<>(new String[]{"Hot", "Warm", "Okay-ish", "Cold", "Freezing"});
        formPanel.add(weatherComboBox);

        JLabel preferenceLabel = new JLabel("Preference Rate (1-5):");
        formPanel.add(preferenceLabel);

        JTextField preferenceField = new JTextField();
        formPanel.add(preferenceField);

        imageLabel = new JLabel();
        formPanel.add(imageLabel);

        add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();

        JFileChooser fileChooser = new JFileChooser(); // Moved fileChooser declaration here

        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(ImageSelectionFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile(); // Assigning the selected file to selectedFile
                    ImageIcon icon = new ImageIcon(selectedFile.getPath());
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    ImageIcon newIcon = new ImageIcon(newImg);
                    imageLabel.setIcon(newIcon);
                }
            }
        });

        JButton uploadButton = new JButton("Upload");
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Upload button clicked");
                String weather = (String) weatherComboBox.getSelectedItem();
                int preference = Integer.parseInt(preferenceField.getText());
                if (selectedFile != null) { // Checking if a file is selected
                    try {
                        // Read the selected image file
                        FileInputStream fis = new FileInputStream(selectedFile);
                        byte[] imageData = new byte[(int) selectedFile.length()];
                        fis.read(imageData);
                        fis.close();

                        // Insert the image into the database
                        String query = "INSERT INTO clothes (image, weather_type, preference_rate) VALUES (?, ?, ?)";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setBytes(1, imageData);
                        statement.setString(2, weather);
                        statement.setInt(3, preference);
                        statement.executeUpdate();

                        JOptionPane.showMessageDialog(ImageSelectionFrame.this, "Image uploaded successfully!");
                    } catch (IOException | SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ImageSelectionFrame.this, "Failed to upload image.");
                    }
                } else {
                    JOptionPane.showMessageDialog(ImageSelectionFrame.this, "Please select an image.");
                }
            }
        });
        buttonPanel.add(uploadButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}