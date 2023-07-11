package com.succabs.devjobsim.ui;

import javafx.scene.control.TextArea;

public class PhoneView {
    private TextArea phoneScreen;

    public PhoneView() {
        phoneScreen = new TextArea();
        phoneScreen.setEditable(false);
        phoneScreen.setPrefRowCount(20);
    }

    public TextArea getPhoneScreen() {
        return phoneScreen;
    }

    public void updatePhoneScreen(String phoneContent) {
        phoneScreen.setText(phoneContent);
    }
}
