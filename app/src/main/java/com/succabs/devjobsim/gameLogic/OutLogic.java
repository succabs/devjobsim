package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;

public class OutLogic {
    private static String places;

    // function to handle what happens when the phone button is pressed
    public static void handleOutButton(GameUI gameUI) {
        places = "Nowhere to go.";

        gameUI.updateGameScreen(places.toString());

    }

}
