package com.succabs.devjobsim.gameLogic;

import java.util.ArrayList;
import java.util.List;

import com.succabs.devjobsim.ui.GameUI;

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

    public static void handleFridgeButton(GameUI gameUI) {
        StringBuilder fridgeContent = new StringBuilder();
        fridgeContent.append("You have the following items in your fridge:\n");

        for (Item item : items) {
            fridgeContent.append("Name: ").append(item.getName()).append("\n");
            fridgeContent.append("Amount: ").append(item.getAmount()).append("\n");
            fridgeContent.append("----------------------\n");
        }
        gameUI.updateFridgeScreen(fridgeContent.toString());
    }
}