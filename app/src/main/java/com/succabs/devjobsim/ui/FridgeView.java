package com.succabs.devjobsim.ui;

import java.util.List;

import com.succabs.devjobsim.gameLogic.Item;
import com.succabs.devjobsim.gameLogic.FridgeLogic;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FridgeView {
    private VBox fridgeScreen;

    public FridgeView() {
        fridgeScreen = new VBox();
    }

    public VBox getFridgeScreen() {
        return fridgeScreen;
    }

    public void updateFridgeScreen(List<Item> items) {
        fridgeScreen.getChildren().clear();
        for (Item item : items) {
            Label nameLabel = new Label("Name: " + item.getName());
            Label amountLabel = new Label("Amount: " + item.getAmount());
            Button consumeButton = new Button("Consume");
            consumeButton.setOnAction(event -> {
                FridgeLogic.consumeItem(item);
                updateFridgeScreen(FridgeLogic.getItems());
            });

            HBox itemContainer = new HBox(nameLabel, amountLabel, consumeButton);
            fridgeScreen.getChildren().add(itemContainer);
        }
    }
}
