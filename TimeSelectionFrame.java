package GUIS;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Enumeration;
import javax.swing.AbstractButton;

@SuppressWarnings("serial")
public class TimeSelectionFrame extends JFrame {
    private Connection connection;

    public TimeSelectionFrame(Connection connection) {
        this.setConnection(connection);

        // Set JFrame title
        setTitle("Weather Selection");

        // Set JFrame size
        setSize(400, 200);

        // Set JFrame layout
        setLayout(new BorderLayout());

        // Create label with "What is the weather outside?"
        JLabel label = new JLabel("What is the weather outside?", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add label to JFrame
        add(label, BorderLayout.NORTH);

        // Create panel for weather selection
        JPanel weatherPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Create weather options
        String[] weatherOptions = {"Hot", "Warm", "Okay-ish", "Cold", "Freezing"};

        // Create weather radio buttons
        ButtonGroup weatherGroup = new ButtonGroup();
        for (String weather : weatherOptions) {
            JRadioButton weatherButton = new JRadioButton(weather);
            weatherGroup.add(weatherButton);
            weatherPanel.add(weatherButton);
        }

        // Add weather selection panel to JFrame
        add(weatherPanel, BorderLayout.CENTER);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();

        // Create "Confirm" button
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform action when "Confirm" button is clicked
                String selectedWeather = getSelectedWeather(weatherGroup);
                if (selectedWeather != null) {
                    // Redirect to OutfitSuggestionFrame
                    dispose(); // Close the current frame
                    new OutfitSuggestionFrame(getConnection(), selectedWeather); // Open OutfitSuggestionFrame with selected weather
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a weather option.");
                }
            }
        });
        buttonPanel.add(confirmButton);

        // Create "Cancel" button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform action when "Cancel" button is clicked
                dispose(); // Close the frame
            }
        });
        buttonPanel.add(cancelButton);

        // Add button panel to JFrame
        add(buttonPanel, BorderLayout.SOUTH);

        // Set JFrame to be visible
        setVisible(true);

        // Set default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Method to get the selected weather option
    private String getSelectedWeather(ButtonGroup weatherGroup) {
        for (Enumeration<AbstractButton> buttons = weatherGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}