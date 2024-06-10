import java.util.HashMap;
import java.util.Stack;

public class Gangster extends Person {
    // Constructor
    public Gangster(String username) {
        // Call the constructor of the superclass (Person)
        super(username);
    }

    /**
     * Lose rewards (money, bottle, gem) from the inventory.
     * @param money Amount of money to lose.
     * @param bottle Amount of bottle to lose.
     * @param gem Amount of gem to lose.
     * @return The previous inventory before the loss.
     */
    public HashMap loseRewards(int money, int bottle, int gem) {
        // Store the previous inventory before the loss
        HashMap<String, Integer> previousInventory = new HashMap<>(getInventory());

        // Stack to keep track of validity of each loss operation
        Stack<Boolean> valid = new Stack<>();
        valid.push(loseRewards("money", money));
        valid.push(loseRewards("gem", gem));
        valid.push(loseRewards("bottle", bottle));

        // Rollback the inventory changes if any of the losses fail
        while (!valid.isEmpty()) {
            boolean confirm = valid.pop();
            if (!confirm) {
                setInventory(null); // Rollback inventory to previous state
            }
        }

        // If the inventory becomes empty after loss, restore the previous inventory
        if (getInventory().isEmpty()) {
            setInventory(previousInventory);
        }
        return inventory; // Return the updated inventory
    }

    /**
     * Lose all rewards from the inventory.
     * @return The previous inventory before the loss.
     */
    public HashMap loseRewards() {
        // Store the previous inventory before the loss
        HashMap<String, Integer> previousInventory = new HashMap<>(getInventory());
        // Lose all rewards
        loseRewards("bottle", (Integer) getInventory().get("bottle"));
        loseRewards("gem", (Integer) getInventory().get("gem"));
        return previousInventory; // Return the previous inventory
    }

    /**
     * Perform a bribe action.
     * @return True if the bribe is successful, false otherwise.
     */
    public boolean Bribe() {
        return loseRewards("bottle"); // Attempt to lose one bottle for the bribe
    }

    /**
     * Lose a specific item from the inventory.
     * @param item Item to lose.
     * @return True if the loss is successful, false otherwise.
     */
    public boolean loseRewards(String item) {
        int available = (int) getInventory().get(item);
        boolean valid = true;
        // Check if the item is available to lose
        if (available == 0) {
            valid = false; // Cannot lose if the item is not available
        } else {
            // Reduce the quantity of the item in the inventory
            inventory.put(item, available - 1);
        }
        return valid; // Return the validity of the operation
    }

    /**
     * Lose a specific number of items from the inventory.
     * @param item Item to lose.
     * @param num Number of items to lose.
     * @return True if the loss is successful, false otherwise.
     */
    public boolean loseRewards(String item, int num) {
        int available = (int) getInventory().get(item);
        boolean valid = true;
        // Check if there are enough items available to lose
        if (num > available) {
            this.inventory.put(item, 0); // Set item count to 0 if not enough items available
        } else {
            this.inventory.put(item, available - num); // Reduce the quantity of the item
        }
        return valid; // Return the validity of the operation
    }
}
