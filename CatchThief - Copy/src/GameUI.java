import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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
    public GameUI (ArrayList<Gangster> allPlayers){
        setTitle("Catch Gangsters");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,800);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(mainGame);
        original=allPlayers;
        missionHall = new MissionHall();

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

        startRound(original);
        Leaderboard();
        MissionBoard();
    }
    public void playerInventory(){
        inventoryKeys.setText("Money: "+"\n"+"Bottle: "+"\n"+"Gem: ");
        inventoryValues.setText(curGangster.getMoney()+"\n"+curGangster.getBottle()+"\n"+curGangster.getGem());
    }
    public void policeInventory(){
        inventoryKeys.setText("Money: "+"\n"+"Bottle: "+"\n"+"Gem: ");
        inventoryValues.setText(curPolice.getMoney()+"\n"+curPolice.getBottle()+"\n"+curPolice.getGem());
    }
    public void MissionBoard(){
        missions.setText("Mission Board"+"\n"+"10 Points: money(2), bottle(3), gem(2)"+"\n"+"7 Points: money(2), bottle(3), gem(1)"+"\n"+"3 Points: money(3), bottle(0), gem(2)");
    }
    public void Leaderboard(){
        leaderboard.setText("");
        for(Gangster i:original){
            leaderboard.setText(leaderboard.getText()+"\n"+i.getUsername()+": "+i.getPoints());
        }
    }
    public void startRound(ArrayList<Gangster> allPlayers){
        cardButtonDisabled();
        ArrayList<Gangster> policeOrder = randomize(allPlayers);
        temporaryPolice=policeOrder.get(0);
        curPolice=new Police(temporaryPolice.getUsername());
        playerOrder = new ArrayList<>(allPlayers);
        playerOrder.remove(policeOrder.get(0));
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
                    currentRoom=i;
                    playerOrder=i.getGangsters();
                    gangsterIndex=0;
                    try{
                        curGangster=playerOrder.get(0);
                    }catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                    loopChooseCard();
                }
            }
        }
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
    }
    public void advanceNextPlayer2(){
        if(gangsterIndex<playerOrder.size()-1){
            try{
                curGangster=playerOrder.get(++gangsterIndex);
                loopChooseCard();
            } catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }else{
            implementCards(currentRoom,curPolice,playerOrder,chosenCards);
            policeInventory();
            temporaryPolice.setInventory(curPolice.getInventory());
            for(Gangster i:original){
                System.out.println(i.getUsername()+": "+i.getInventory());
            }
        }
    }
    public void advanceNextPlayer(){
        if(gangsterIndex<playerOrder.size()-1){
            curGangster=playerOrder.get(++gangsterIndex);
            loopChooseRoom();
        }else{
            curGangster=null;
            policeGoesIn();
        }
    }
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
}
