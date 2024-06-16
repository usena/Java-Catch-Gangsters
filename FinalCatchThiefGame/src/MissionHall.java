import java.util.ArrayList;

public class MissionHall {
    // ArrayList to store missions, each represented by an int array: {money, bottle, gem, points}
    private ArrayList<int[]> missions = new ArrayList<>();
    private ArrayList<Integer> displayMission = new ArrayList<>();

    // Constructor to initialize missions
    public MissionHall() {
        // Define mission requirements and points for each mission
        int[] mission1 = {2, 3, 2, 10}; // money, bottle, gem, points
        int[] mission2 = {2, 3, 1, 7};
        int[] mission3 = {1, 1, 1, 3};

        // Add missions to the ArrayList
        missions.add(mission3);
        missions.add(mission2);
        missions.add(mission1);

        displayMission.add(missions.get(0)[0]);
        displayMission.add(missions.get(0)[1]);
        displayMission.add(missions.get(0)[2]);
        displayMission.add(missions.get(1)[0]);
        displayMission.add(missions.get(1)[1]);
        displayMission.add(missions.get(1)[2]);
        displayMission.add(missions.get(2)[0]);
        displayMission.add(missions.get(2)[1]);
        displayMission.add(missions.get(2)[2]);
    }
    public int getMission1Money() {
        return displayMission.get(0);
    }
    public int getMission1Bottle() {
        return displayMission.get(1);
    }
    public int getMission1Gem() {
        return displayMission.get(2);
    }
    public int getMission2Money() {
        return displayMission.get(3);
    }
    public int getMission2Bottle() {
        return displayMission.get(4);
    }
    public int getMission2Gem() {
        return displayMission.get(5);
    }
    public int getMission3Money() {
        return displayMission.get(6);
    }
    public int getMission3Bottle() {
        return displayMission.get(7);
    }
    public int getMission3Gem() {
        return displayMission.get(8);
    }

    /**
     * Method to complete a mission for a gangster.
     * @param gangster The gangster attempting to complete the mission.
     * @param missionNum The mission number to be completed.
     */
    public void completeMission(Gangster gangster, int missionNum) {
        // Get the mission requirements and points for the specified mission number
        int[] mission = missions.get(missionNum - 1);

        // Check if the gangster meets the mission requirements
        if (gangster.getMoney() >= mission[0] && gangster.getBottle() >= mission[1] && gangster.getGem() >= mission[2]) {
            // If requirements are met, add points to the gangster and deduct rewards
            gangster.addPoints(mission[3]);
            gangster.loseRewards(mission[0], mission[1], mission[2]);
        } else {
            // If requirements are not met, display a message
            System.out.println("Gangster " + gangster.getUsername() + " does not meet the mission requirements.");
        }
    }
}
