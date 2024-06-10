import java.util.HashMap;

public class Person {
    // Private member variables to store the username, room number, and points of the person.
    private String username;
    private int inRoom, points;

    // Protected HashMap to store the inventory items (money, bottle, gem) and their quantities.
    protected HashMap<String, Integer> inventory = new HashMap<>();

    // Constructor to initialize a Person object with a username and default inventory values.
    public Person(String username) {
        this.username = username;
        // Initialize the inventory with default values: 1 money, 1 bottle, and 1 gem.
        this.inventory.put("money", 1);
        this.inventory.put("bottle", 1);
        this.inventory.put("gem", 1);
        // Set default values for inRoom and points.
        this.inRoom = 0;
        this.points = 0;
    }

    // Method to add rewards (money, bottle, gem) to the inventory.
    public void addReward(int money, int bottle, int gem) {
        addMoney(money);
        addGem(gem);
        addBottle(bottle);
    }

    // Method to add a single item to the inventory by specifying the item name.
    public void addReward(String item) {
        this.inventory.put(item, inventory.get(item) + 1);
    }

    // Method to add money to the inventory.
    public void addMoney(int money) {
        this.inventory.put("money", inventory.get("money") + money);
    }

    // Method to add gems to the inventory.
    public void addGem(int gem) {
        this.inventory.put("gem", inventory.get("gem") + gem);
    }

    // Method to add bottles to the inventory.
    public void addBottle(int bottle) {
        this.inventory.put("bottle", inventory.get("bottle") + bottle);
    }

    // Method to add points to the person.
    public void addPoints(int points) {
        this.points += points;
    }

    // Method to get the current points of the person.
    public int getPoints() {
        return points;
    }

    // Method to get the current inventory of the person.
    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    // Method to set the inventory of the person.
    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    // Method to get the username of the person.
    public String getUsername() {
        return username;
    }

    // Method to get the current amount of money in the inventory.
    public int getMoney() {
        return inventory.get("money");
    }

    // Method to get the current amount of gems in the inventory.
    public int getGem() {
        return inventory.get("gem");
    }

    // Method to get the current amount of bottles in the inventory.
    public int getBottle() {
        return inventory.get("bottle");
    }

    // Method to get the current room number the person is in.
    public int getInRoom() {
        return inRoom;
    }

    // Method to set the room number the person is in.
    public void setInRoom(int num) {
        this.inRoom = num;
    }

    // Method to choose a room and set the room number.
    public int chooseRoom(int roomNum) {
        this.inRoom = roomNum;
        return inRoom;
    }

    // Method to exit the room and reset the room number to 0.
    public void exitRoom() {
        this.inRoom = 0;
    }
}
