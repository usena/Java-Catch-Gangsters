import java.util.HashMap;

public class Police extends Person {
    private int roomPossession; // Number of rooms possessed by the police

    // Constructor
    public Police(String username) {
        super(username);
        this.roomPossession = 0; // Initially, no rooms possessed
    }

    // Method to earn a bribe from a gangster
    public boolean earnBribe(Gangster user) {
        boolean valid = user.Bribe(); // Attempt to bribe the police
        if (valid) {
            // If bribe successful, add a bottle to the police inventory
            addReward("bottle");
        }
        return valid; // Return whether the bribe was successful
    }

    // Method to seize rewards from a gangster
    public void seize(Gangster username) {
        // Retrieve the gangster's inventory before seizure
        HashMap<String, Integer> seizeInventory = new HashMap<>(username.loseRewards());
        // Add seized rewards (bottles and gems) to the police inventory
        addReward(0, seizeInventory.get("bottle"), seizeInventory.get("gem"));
    }

    // Method for the police to choose a room and increase room possession count
    public int chooseRoom(int roomNum) {
        roomPossession++; // Increment the number of rooms possessed
        this.setInRoom(roomNum); // Set the police's current room
        return roomPossession; // Return the updated room possession count
    }

    // Getter method to retrieve the number of rooms possessed by the police
    public int getRoomPossession() {
        return roomPossession;
    }
}
