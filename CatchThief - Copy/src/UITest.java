import java.util.ArrayList;

public class UITest {
    // ArrayList to store player usernames entered in the start screen
    private static ArrayList<String> players;

    // ArrayList to store all Gangster objects representing players
    private static ArrayList<Gangster> allPlayers = new ArrayList<>();

    public static void main(String[] args) {
        // Create an instance of StartScreen
        StartScreen startScreen = new StartScreen();

        // Add a window listener to StartScreen to handle window close event
        startScreen.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // When StartScreen window is closed

                // Get the list of player usernames entered in StartScreen
                players = startScreen.getPlayersList();

                // Iterate through each player username
                for (String i : players) {
                    // Create a Gangster object for each player and add it to allPlayers list
                    Gangster player = new Gangster(i);
                    allPlayers.add(player);
                }

                // Create an instance of GameUI with allPlayers list
                GameUI gameUI = new GameUI(allPlayers);
            }
        });
    }
}
