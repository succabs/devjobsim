package com.succabs.devjobsim.ui;

import javafx.scene.control.TextArea;

public class OutView {
    private TextArea outScreen;

    public OutView() {
        outScreen = new TextArea();
        outScreen.setEditable(false);
        outScreen.setPrefRowCount(20);
    }

    public TextArea getOutScreen() {
        return outScreen;
    }

    public void updateOutScreen(String outContent) {
        outScreen.setText(outContent);
    }
}
