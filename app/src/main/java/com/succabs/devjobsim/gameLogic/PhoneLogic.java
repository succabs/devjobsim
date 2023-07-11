package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.PhoneView;

public class PhoneLogic {
    private static boolean phoneRinging;
    private static String caller;

    // function to handle what happens when the phone button is pressed
    public static void handlePhoneButton(PhoneView phoneView) {
        if (phoneRinging) {
            caller = "Matt";
        } else
            caller = "No one";

        phoneView.updatePhoneScreen(caller.toString() + " is calling.");

    }

}
