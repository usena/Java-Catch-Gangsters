import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartScreen extends JFrame {

    // Components in the StartScreen UI
    private JEditorPane playerList;
    private JTextField usernameField;
    private JButton joinButton;
    private JButton startButton;
    private JPanel StartPage;

    // ArrayList to store player usernames
    private ArrayList<String> playersList = new ArrayList<>();

    // Constructor
    public StartScreen() {
        // Set up the JFrame properties
        setContentPane(StartPage);
        setTitle("Catch Gangsters");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        // ActionListener for the joinButton
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When joinButton is clicked
                if (!usernameField.getText().isEmpty()) { // Check if usernameField is not empty
                    String username = usernameField.getText(); // Get the username entered
                    playerList.setText(playerList.getText() + username + "\n"); // Append username to playerList
                    playersList.add(username); // Add username to playersList
                    usernameField.setText(""); // Clear the usernameField
                } else {
                    // Show error message if usernameField is empty
                    JOptionPane.showMessageDialog(StartScreen.this, "Please enter a username!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ActionListener for the startButton
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the StartScreen window when startButton is clicked
                dispose();
            }
        });
    }

    // Method to get the list of player usernames
    public ArrayList<String> getPlayersList() {
        return playersList;
    }
}
