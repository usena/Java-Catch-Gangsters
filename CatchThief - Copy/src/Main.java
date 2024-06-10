import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Room room1 = new Room(1,2,2,2);
        Room room2 = new Room(2,2, 2, 0);
        Room room3 = new Room(3,2, 0, 2);
        Room room4 = new Room(4,2, 0, 1);
        ExchangeRoom room5 = new ExchangeRoom(5,2,0,0);

        Gangster KwangSoo = new Gangster("KwangSoo");
        Police JaeSuk = new Police("JaeSuk");
        Gangster JongKook = new Gangster("JongKook");
        Gangster Jihyo = new Gangster("Jihyo");
        Gangster Haha = new Gangster("Haha");
        Gangster SeokJin = new Gangster("SeokJin");

        room1.joinRoom(KwangSoo);
        room2.joinRoom(SeokJin);
        room4.joinRoom(JongKook);
        room4.joinRoom(Jihyo);
        room1.joinRoom(Haha);
        System.out.println("Room 2 Counts: "+room2.getGangsterCounts());
        System.out.println("Room 1 Gangsters: ");
        for(int i=0;i<room1.getGangsterCounts();i++){
            System.out.println(room1.getGangsters().get(i).getUsername());
        }

        System.out.println("Police JaeSuk goes into room 2");
        if(room2.getGangsterCounts()!=0){
            if(room2.getGangsterCounts()==1){
                JaeSuk.seize(room2.getGangsters().get(0));
            }
        }

        ArrayList<Gangster> players = new ArrayList<>();
        players.add(KwangSoo);
        players.add(Haha);
        ArrayList<String> chosenCards = new ArrayList<>();
        chosenCards.add("Bribe");
        chosenCards.add("Bribe");
        System.out.println("KwangSoo: "+KwangSoo.getInventory());
        System.out.println("Haha: "+Haha.getInventory());
        System.out.println("JaeSuk: "+JaeSuk.getInventory());
        room1.joinRoom(JaeSuk);
        System.out.println(room1.getPolice());
        room2.joinRoom(JaeSuk);
        System.out.println(room2.getPolice());
        Cards strategy = new Cards(room1,JaeSuk,players,chosenCards);
        System.out.println("KwangSoo: "+KwangSoo.getInventory());
        System.out.println("Haha: "+Haha.getInventory());
        System.out.println("JaeSuk: "+JaeSuk.getInventory());

    }
}