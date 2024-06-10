import java.util.ArrayList;

public class Room {
    private int money, bottle, gem, gangsterCounts, roomNum;
    private boolean hasPolice;
    private ArrayList<Gangster> gangsters = new ArrayList<>();
    private ArrayList<Police> temp = new ArrayList<>();

    // Constructor to initialize room properties
    public Room(int roomNum, int money, int bottle, int gem) {
        this.roomNum = roomNum;
        this.money = money;
        this.bottle = bottle;
        this.gem = gem;
        this.gangsterCounts = 0;
        this.hasPolice = false;
    }

    // Method to reset room rewards and clear gangsters
    public void resetRewards(int money, int bottle, int gem) {
        resetMoney(money);
        resetBottle(bottle);
        resetGem(gem);
        this.hasPolice = false;
        gangsters.clear();
    }

    // Method to reset money reward
    public void resetMoney(int money) {
        this.money = money;
    }

    // Method to reset gem reward
    public void resetGem(int gem) {
        this.gem = gem;
    }

    // Method to reset bottle reward
    public void resetBottle(int bottle) {
        this.bottle = bottle;
    }

    // Getter method for money reward
    public int getMoney() {
        return money;
    }

    // Getter method for gem reward
    public int getGem() {
        return gem;
    }

    // Getter method for bottle reward
    public int getBottle() {
        return bottle;
    }

    // Method to distribute rewards to all gangsters in the room
    public void allEarn() {
        if (!gangsters.isEmpty()) {
            for (Gangster i : gangsters) {
                i.addReward(getMoney() / gangsterCounts, getBottle(), getGem());
            }
        }
    }

    // Method to add a gangster to the room
    public boolean joinRoom(Gangster username) {
        username.chooseRoom(roomNum);
        this.gangsterCounts++;
        this.gangsters.add(username);
        return true;
    }

    // Method to add a police to the room
    public boolean joinRoom(Police username) {
        this.temp.add(username);
        username.chooseRoom(roomNum);
        hasPolice = true;
        return true;
    }

    // Method to remove a gangster from the room
    public void removeGangster(Gangster gangster) {
        this.gangsters.remove(gangster);
        this.gangsterCounts--;
        gangster.exitRoom();
    }

    // Getter method to check if the room has a police
    public boolean getPolice() {
        return hasPolice;
    }

    // Getter method to retrieve temporary police
    public ArrayList<Police> getTempPolice() {
        return this.temp;
    }

    // Getter method for the number of gangsters in the room
    public int getGangsterCounts() {
        return gangsterCounts;
    }

    // Getter method to retrieve gangsters in the room
    public ArrayList<Gangster> getGangsters() {
        return gangsters;
    }

    // Getter method for room number
    public int getRoomNum() {
        return roomNum;
    }
}
