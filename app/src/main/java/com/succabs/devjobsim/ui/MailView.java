package com.succabs.devjobsim.ui;

import javafx.scene.control.TextArea;

public class MailView {
    private TextArea mailScreen;

    public MailView() {
        mailScreen = new TextArea();
        mailScreen.setEditable(false);
        mailScreen.setPrefRowCount(20);
    }

    public TextArea getMailScreen() {
        return mailScreen;
    }

    public void updateMailScreen(String mailContent) {
        mailScreen.setText(mailContent);
    }
}
