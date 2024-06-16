import java.util.ArrayList;

public class Cards {
    // Constructor to handle the effects of chosen cards on the room, police, and gangsters.
    public Cards(Room room, Police police, ArrayList<Gangster> target, ArrayList<String> chosenCard) {
        boolean valid = false; // Flag to track if the chosen cards combination is valid.

        // Iterate through the chosen cards.
        for (int i = 0; i < chosenCard.size(); i++) {
            switch (chosenCard.get(i)) {
                case "Bribe": // If the chosen card is "Bribe".
                    // Police earns bribe and removes gangster from the room.
                    police.earnBribe(target.get(i));
                    break;
                case "Snitch": // If the chosen card is "Snitch".
                    try {
                        // Check if the previous card was "Silent", making this combination valid.
                        if (chosenCard.get(i - 1).equals("Silent")) {
                            valid = true;
                        }
                    } catch (IndexOutOfBoundsException e) { // Catch exception for the first card.
                        if ("Snitch".equals(null)) { // Check if the first card is "Snitch".
                            continue; // Continue to the next iteration.
                        }
                    }
                    break;
                case "Silent": // If the chosen card is "Silent".
                    try {
                        // Check if the previous card was "Snitch", making this combination valid.
                        if (chosenCard.get(i - 1).equals("Snitch")) {
                            valid = true;
                        }
                    } catch (IndexOutOfBoundsException e) { // Catch exception for the first card.
                        if ("Silent".equals(null)) { // Check if the first card is "Silent".
                            continue; // Continue to the next iteration.
                        }
                    }
                    break;
            }
        }

        // If the combination is valid, execute the valid actions.
        if (valid) {
            for (int i = 0; i < chosenCard.size(); i++) {
                if (chosenCard.get(i).equals("Silent")) { // If the chosen card is "Silent".
                    // Police seize the gangster and remove them from the room.
                    police.seize(target.get(i));
                    room.removeGangster(target.get(i));
                }
            }
            for(int i = 0; i < chosenCard.size(); i++){
                if(chosenCard.get(i).equals("Bribe")) room.removeGangster(target.get(i));
            }
            // All gangsters earn rewards in the room.
            room.allEarn();
        } else if (!valid&&!room.getGangsters().isEmpty()) { // If the combination is not valid.
            for (int i = 0; i < chosenCard.size(); i++) {
                if (chosenCard.get(i).equals("Snitch")) { // If the chosen card is "Snitch".
                    // Police seize the gangster and remove them from the room.
                    police.seize(target.get(i));
                    room.removeGangster(target.get(i));
                }
            }
            for(int i = 0; i < chosenCard.size(); i++){
                if(chosenCard.get(i).equals("Bribe")) room.removeGangster(target.get(i));
            }
            // All gangsters earn rewards in the room.
            if(!room.getGangsters().isEmpty()){
                room.allEarn();
            }
        }
    }
}
