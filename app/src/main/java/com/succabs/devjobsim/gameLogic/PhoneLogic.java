package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;
import com.succabs.devjobsim.ui.PhoneView;

public class PhoneLogic {
    private static boolean isRinging = false;
    private static String caller;

    public static void setIsRinging(boolean value) {
        isRinging = value;
    }

    // function to handle what happens when the phone button is pressed
    public static void handlePhoneButton(PhoneView phoneView, GameUI gameUI) {

        if (isRinging) {
            caller = "Matt";
            // Play ringing sound
        } else {
            caller = "No one";
        }

        phoneView.updatePhoneScreen(caller + " is calling.");
    }

    public static void checkCalls(Time time, GameUI gameUI) {
        if (time.getSeconds() % 10 == 0) {
            isRinging = true;
            // Start the flashing animation for the phone button
            gameUI.startPhoneButtonFlashingAnimation();
            gameUI.playRingSound();
        }
    }

    public static boolean isRinging() {
        return isRinging;
    }
}
