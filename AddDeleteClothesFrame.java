package GUIS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddDeleteClothesFrame extends JFrame {
    private Connection connection;

    public AddDeleteClothesFrame(Connection connection) {
        this.connection = connection;
        setTitle("Edit Wardrobe");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Clothes");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ImageSelectionFrame(connection);
            }
        });
        buttonPanel.add(addButton);

        JButton deleteButton = new JButton("Delete Clothes");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeleteClothesFrame(connection);
            }
        });
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/clothing_store", "root", "Incorrect$21");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
        }
        new AddDeleteClothesFrame(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}