public class ExchangeRoom extends Room {
    // Constructor for creating an ExchangeRoom object.
    public ExchangeRoom(int roomNum, int money, int gem, int bottle) {
        // Call the constructor of the superclass (Room) to initialize the room.
        super(roomNum, money, gem, bottle);
    }

    /**
     * Exchange items between a gangster and the room.
     * @param username The gangster performing the exchange.
     * @param giveItem The item the gangster gives.
     * @param getItem The item the gangster receives.
     * @return True if the exchange is successful, false otherwise.
     */
    public boolean Exchange(Gangster username, String giveItem, String getItem) {
        // Check if the gangster is present in the room.
        if (!getGangsters().contains(username)) {
            return false; // Return false if gangster is not in the room.
        }
        // Check if the gangster has the item to give.
        if (username.getInventory().get(giveItem).equals(0)) {
            return false; // Return false if gangster does not have the item.
        }
        // Execute the exchange: remove the given item from gangster and add the received item.
        username.loseRewards(giveItem);
        username.addReward(getItem);
        return true; // Return true indicating successful exchange.
    }
}
