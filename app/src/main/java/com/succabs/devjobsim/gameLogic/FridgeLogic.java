package com.succabs.devjobsim.gameLogic;

import java.util.ArrayList;
import java.util.List;
import com.succabs.devjobsim.ui.FridgeView;

public class FridgeLogic {
    private static List<Item> items = new ArrayList<>();

    // Add an item to the list
    public static void addItem(Item item) {
        items.add(item);
    }

    // Get the list of items
    public static List<Item> getItems() {
        return items;
    }

    public static void handleFridgeButton(FridgeView fridgeView) {
        fridgeView.updateFridgeScreen(getItems());
    }

    public static void consumeItem(Item item) {

        // Perform the consume action
        if (item.getAmount() > 0) {
            int amount = item.getAmount() - 1;
            item.setAmount(amount);

            // Remove the item from the list if the amount reaches 0
            if (amount == 0) {
                items.remove(item);
            }
        }
    }

}