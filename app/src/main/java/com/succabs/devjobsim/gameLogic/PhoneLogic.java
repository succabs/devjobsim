package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;

public class PhoneLogic {
    private static boolean phoneRinging;
    private static String caller;

    // function to handle what happens when the phone button is pressed
    public static void handlePhoneButton(GameUI gameUI) {
        if (phoneRinging) {
            caller = "Matt";
        } else
            caller = "No one";

        gameUI.updateGameScreen(caller.toString() + " is calling.");

    }

}
