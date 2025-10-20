package GUIS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteClothesFrame extends JFrame {
    private Connection connection;

    public DeleteClothesFrame(Connection connection) {
        this.connection = connection;
        setTitle("Delete Clothes");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel clothesPanel = new JPanel(new GridLayout(0, 3, 10, 10));

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, image FROM clothes");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                byte[] imageData = resultSet.getBytes("image");
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JButton imageButton = new JButton(scaledIcon);
                imageButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM clothes WHERE id = ?");
                                deleteStatement.setInt(1, id);
                                int rowsAffected = deleteStatement.executeUpdate();
                                if (rowsAffected > 0) {
                                    JOptionPane.showMessageDialog(null, "Clothing item deleted successfully.");
                                    dispose();
                                    new DeleteClothesFrame(connection);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Failed to delete clothing item.");
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error deleting clothing item.");
                            }
                        }
                    }
                });
                clothesPanel.add(imageButton);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve clothes from the database.");
        }

        JScrollPane scrollPane = new JScrollPane(clothesPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

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
