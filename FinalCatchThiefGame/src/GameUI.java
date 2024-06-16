import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GameUI extends JFrame implements Randomize{

    private JButton bribeButton;
    private JButton snitchButton;
    private JButton silentButton;
    private JButton room1Button;
    private JButton room5Button;
    private JButton room2Button;
    private JButton room4Button;
    private JButton room3Button;
    private JButton mostPoints;
    private JButton leastPoints;
    private JButton secondMostPoints;
    private JEditorPane inventoryKeys;
    private JEditorPane inventoryValues;
    private JEditorPane missions;
    private JEditorPane currentPoliceField;
    private JEditorPane leaderboard;
    private JPanel mainGame;
    private JEditorPane currentPlayerField;
    private JEditorPane roomField;
    private JEditorPane monitor;
    private int policeIndex;
    private boolean policeTurn;
    private Police curPolice;
    private Gangster curGangster;
    private int gangsterIndex;
    private Room room1;
    private Room room2;
    private Room room3;
    private Room room4;
    private ExchangeRoom room5;
    private ArrayList<Gangster> playerOrder;
    private ArrayList<String> chosenCards = new ArrayList<>();
    private ArrayList<Gangster> temp=new ArrayList<>();
    private Room currentRoom;
    private ArrayList<Gangster> original = new ArrayList<>();
    private MissionHall missionHall;
    private Gangster temporaryPolice;
    private ArrayList<Gangster> policeOrder=new ArrayList<>();
    private int roomIndex;
    private boolean done;
    private int round;
    private ArrayList<Room> roomOrder=new ArrayList<>();
    public GameUI (ArrayList<Gangster> allPlayers,int round){
        setTitle("Catch Gangsters");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,800);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(mainGame);
        original=allPlayers;
        missionHall = new MissionHall();
        this.done=false;
        round=round;

        room1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (curGangster != null) {
                    room1.joinRoom(curGangster);
                }
                if (policeTurn) {
                    room1.joinRoom(curPolice);
                }
                roomButtonDisabled();
                advanceNextPlayer();
            }
        });

        room2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curGangster!=null) room2.joinRoom(curGangster);
                if(policeTurn){
                    room2.joinRoom(curPolice);
                }
                roomButtonDisabled();
                advanceNextPlayer();
            }
        });
        room3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curGangster!=null) room3.joinRoom(curGangster);
                if(policeTurn){
                    room3.joinRoom(curPolice);
                }
                roomButtonDisabled();
                advanceNextPlayer();
            }
        });
        room4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curGangster!=null) room4.joinRoom(curGangster);
                if(policeTurn){
                    room4.joinRoom(curPolice);
                }
                roomButtonDisabled();
                advanceNextPlayer();
            }
        });
        room5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curGangster!=null) room5.joinRoom(curGangster);
                if(policeTurn){
                    room5.joinRoom(curPolice);
                }
                roomButtonDisabled();
                advanceNextPlayer();
            }
        });

        bribeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenCards.add("Bribe");
                temp.add(curGangster);
                cardButtonDisabled();
                advanceNextPlayer2();
            }
        });
        silentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenCards.add("Silent");
                temp.add(curGangster);
                cardButtonDisabled();
                advanceNextPlayer2();
            }
        });
        snitchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenCards.add("Snitch");
                temp.add(curGangster);
                cardButtonDisabled();
                advanceNextPlayer2();
            }
        });

        leastPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                missionHall.completeMission(curGangster,1);
                Leaderboard();
                playerInventory();
            }
        });
        secondMostPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                missionHall.completeMission(curGangster,2);
                Leaderboard();
                playerInventory();
            }
        });
        mostPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                missionHall.completeMission(curGangster,3);
                Leaderboard();
                playerInventory();
            }
        });

        room1 = new Room(1,2,2,2);
        room2 = new Room(2,2,0,2);
        room3 = new Room(3,2,2,0);
        room4 = new Room(4,2,1,0);
        room5 = new ExchangeRoom(5,2,0,0);

        RoomBoard();//done
        Leaderboard();//done
        MissionBoard();//done
        cardButtonDisabled();

        startGame(allPlayers);
    }
    public void startGame(ArrayList<Gangster> allPlayers){
        policeOrder = randomize(allPlayers);
        policeIndex=0;
        temporaryPolice=policeOrder.get(0);
        startRound(temporaryPolice,allPlayers);
    }
    public void RoomBoard(){
        roomField.setText("");
        roomField.setText("Rooms"+"\n"+"\n"
                +"Room 1: Money="+room1.getMoney()+", "+"Bottle="+room1.getBottle()+", "+"Gem="+room1.getGem()+"\n"
                +"Room 2: Money="+room2.getMoney()+", "+"Bottle="+room2.getBottle()+", "+"Gem="+room2.getGem()+"\n"
                +"Room 3: Money="+room3.getMoney()+", "+"Bottle="+room3.getBottle()+", "+"Gem="+room3.getGem()+"\n"
                +"Room 4: Money="+room4.getMoney()+", "+"Bottle="+room4.getBottle()+", "+"Gem="+room4.getGem()+"\n"
                +"Room 5: Money="+room5.getMoney()+", "+"Bottle="+room5.getBottle()+", "+"Gem="+room5.getGem());
    }
    public void playerInventory(){
        inventoryValues.setText("");
        inventoryKeys.setText("Money: "+"\n"+"Bottle: "+"\n"+"Gem: ");
        inventoryValues.setText(curGangster.getMoney()+"\n"+curGangster.getBottle()+"\n"+curGangster.getGem());
    }
    public void policeInventory(){
        inventoryValues.setText("");
        inventoryKeys.setText("Money: "+"\n"+"Bottle: "+"\n"+"Gem: ");
        inventoryValues.setText(curPolice.getMoney()+"\n"+curPolice.getBottle()+"\n"+curPolice.getGem());
    }
    public void MissionBoard(){
        missions.setText("");
        missions.setText("Mission Board"+"\n"+"\n"+"10 Points: "+"Money="+missionHall.getMission3Money()+", "+"Bottle="+missionHall.getMission3Bottle()+", "+"Gem="+missionHall.getMission3Gem()+"\n"
        +" 7 Points: "+"Money="+missionHall.getMission2Money()+", "+"Bottle="+missionHall.getMission2Bottle()+", "+"Gem="+missionHall.getMission2Gem()+"\n"
        +"3 Points: "+"Money="+missionHall.getMission1Money()+", "+"Bottle="+missionHall.getMission1Bottle()+", "+"Gem="+missionHall.getMission1Gem());
    }
    public void Leaderboard(){
        leaderboard.setText("");
        leaderboard.setText("Leaderboard"+"\n"+"\n");
        List<Gangster> gangsterList = new ArrayList<>(original);
        Collections.sort(gangsterList,new GangsterComparator());
        for(Gangster gangster:gangsterList){
            if(gangster!=null){
                leaderboard.setText(leaderboard.getText()+gangster.getUsername()+": "+gangster.getPoints()+"\n");
            }
        }
    }
    class GangsterComparator implements Comparator<Gangster>{

        @Override
        public int compare(Gangster g1, Gangster g2) {
            if (g1.getPoints()<g2.getPoints())
                return 1;
            else if (g1.getPoints()>g2.getPoints())
                return -1;
                    return 0;
        }
    }
    public void startRound(Gangster temporaryPolice, ArrayList<Gangster> allPlayers){
        curPolice=new Police(temporaryPolice.getUsername());
        curPolice.setInventory(temporaryPolice.getInventory());
        playerOrder = new ArrayList<>(allPlayers);
        playerOrder.remove(temporaryPolice);
        try{
            curGangster=playerOrder.get(0);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        gangsterIndex=0;
        currentPoliceField.setText("Police: "+curPolice.getUsername());
        loopChooseRoom();
    }
    public void policeGoesIn(){
        currentPlayerField.setText("");
        policeInventory();
        if(curPolice.getRoomPossession()<2){
            policeTurn=true;
            roomButtonEnabled();
        }else {
            roomButtonDisabled();
            checkRoom();
        }
    }
    //might need review
    public void checkRoom(){
        ArrayList<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);
        roomList.add(room5);

        for(Room i:roomList){
            if(i.getPolice()){
                if(i.getGangsterCounts()==1){
                    curPolice.seize(i.getGangsters().getFirst());
                    temporaryPolice.setInventory(curPolice.getInventory());
                    policeInventory();
                }
                else if(i.getGangsterCounts()>1){
                    roomOrder.add(i);
                }
            }
            else{
                i.allEarn();
            }
        }

        if(!roomOrder.isEmpty()){
            currentRoom=roomOrder.get(0);
            playerOrder=currentRoom.getGangsters();
            roomIndex=0;
            gangsterIndex=0;
            try{
                curGangster=playerOrder.get(0);
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
            loopChooseCard();
        }else{
            endGame();
        }
    }
    public void loopThroughRoom(){
        monitor.setText("Room: "+currentRoom.getRoomNum());
        playerInventory();
        loopChooseCard();
    }
    public void loopChooseRoom(){
        currentPlayerField.setText("Player: "+curGangster.getUsername());
        playerInventory();
        roomButtonEnabled();
    }
    public void loopChooseCard(){
        currentPlayerField.setText("Player: "+curGangster.getUsername());
        playerInventory();
        cardButtonEnabled();
        if (curGangster.getBottle()==0){
            bribeButton.setVisible(false);
        }
    }
    public void advanceNextRound(){
        if(policeIndex<original.size()-1){
            temporaryPolice=policeOrder.get(++policeIndex);
            startRound(temporaryPolice,original);
        }
    }
    public void advanceNextRoom(){
        if(roomIndex<roomOrder.size()-1){
            currentRoom=roomOrder.get(++roomIndex);
            playerOrder=currentRoom.getGangsters();
            curGangster=playerOrder.get(0);
            gangsterIndex=0;
            loopThroughRoom();
        }
        else{
            for(Gangster i:original){
                System.out.println(i.getUsername()+": "+i.getInventory());
            }
            endGame();
        }
    }
    //need edits
    public void advanceNextPlayer2(){
        if(gangsterIndex<playerOrder.size()-1){
            curGangster=playerOrder.get(++gangsterIndex);
            playerInventory();
            loopChooseCard();
        }
        else{
            curGangster=null;
            implementCards(currentRoom,curPolice,playerOrder,chosenCards);
            chosenCards.clear();
            currentPlayerField.setText("");
            temporaryPolice.setInventory(curPolice.getInventory());
            policeInventory();
            for(Gangster i:original){
                System.out.println(i.getUsername()+": "+i.getInventory());
            }
            advanceNextRoom();
        }
    }
    //needs review
    public void advanceNextPlayer(){
        if(gangsterIndex<playerOrder.size()-1){
            curGangster=playerOrder.get(++gangsterIndex);
            playerInventory();
            loopChooseRoom();
        }else{
            curGangster=null;
            policeGoesIn();
        }
    }
    //need edits
    public void implementCards(Room room, Police police, ArrayList<Gangster> gangsters, ArrayList<String> chosenCards){
        Cards strategy = new Cards(room,police,gangsters,chosenCards);
    }
    public void roomButtonDisabled(){
        room1Button.setVisible(false);
        room2Button.setVisible(false);
        room3Button.setVisible(false);
        room4Button.setVisible(false);
        room5Button.setVisible(false);
    }
    public void roomButtonEnabled(){
        room1Button.setVisible(true);
        room2Button.setVisible(true);
        room3Button.setVisible(true);
        room4Button.setVisible(true);
        room5Button.setVisible(true);
    }
    public void cardButtonDisabled(){
        bribeButton.setVisible(false);
        silentButton.setVisible(false);
        snitchButton.setVisible(false);
    }
    public void cardButtonEnabled(){
        bribeButton.setVisible(true);
        silentButton.setVisible(true);
        snitchButton.setVisible(true);
    }
    @Override
    public ArrayList randomize(ArrayList<Gangster> gangsters) {
        ArrayList<Gangster> temporary = new ArrayList<>(gangsters);
        ArrayList<Gangster> randomizedOrder = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<gangsters.size();i++){
            Gangster randomElement = temporary.get(random.nextInt(temporary.size()));
            randomizedOrder.add(randomElement);
            temporary.remove(randomElement);
        }
        return randomizedOrder;
    }

    @Override
    public void randomize() {
        ;
    }
    public void clearState(){
        room1.resetState();
        room2.resetState();
        room3.resetState();
        room4.resetState();
        room5.resetState();
        policeIndex=0;
        roomIndex=0;
        policeTurn=false;
        currentRoom=null;
        roomOrder.clear();
    }
    public void endGame(){
        System.out.println("Round Done");
        for(Gangster i:original){
            System.out.println(i.getUsername()+": "+i.getInventory());
        }
        done=true;
        round++;
        clearState();
        if(round<original.size()){
            startGame(original);
        }
    }
}
