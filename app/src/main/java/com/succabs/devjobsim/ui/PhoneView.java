package com.succabs.devjobsim.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.succabs.devjobsim.gameLogic.PhoneLogic;

public class PhoneView {
    private VBox phoneScreen;
    private GameUI gameUI; // Reference to the GameUI instance

    public PhoneView(GameUI gameUI) {
        phoneScreen = new VBox();
        this.gameUI = gameUI;
    }

    public VBox getPhoneScreen() {
        return phoneScreen;
    }

    public void updatePhoneScreen(String phoneContent) {
        phoneScreen.getChildren().clear();
        Label nameLabel = new Label(phoneContent + " is calling");
        HBox phoneContainer = new HBox(nameLabel);
        phoneScreen.getChildren().add(phoneContainer);
        if (PhoneLogic.isRinging()) {
            Button answerButton = new Button("Apply");
            Button declineButton = new Button("Decline");
            HBox buttonContainer = new HBox(answerButton, declineButton);
            phoneScreen.getChildren().add(buttonContainer);
            answerButton.setOnAction(event -> {
                PhoneLogic.setIsRinging(false);
                gameUI.stopPhoneButtonFlashingAnimation();
                PhoneLogic.handlePhoneButton(this, gameUI);
            });
            declineButton.setOnAction(event -> {
                PhoneLogic.setIsRinging(false);
                gameUI.stopPhoneButtonFlashingAnimation();
                PhoneLogic.handlePhoneButton(this, gameUI);
            });
        }

    }
}
