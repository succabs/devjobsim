package com.succabs.devjobsim.ui;

import javafx.scene.control.TextArea;

public class CVView {
    private TextArea cvScreen;

    public CVView() {
        cvScreen = new TextArea();
        cvScreen.setEditable(false);
        cvScreen.setPrefRowCount(20);
    }

    public TextArea getCVScreen() {
        return cvScreen;
    }

    public void updateCVScreen(String cvContent) {
        cvScreen.setText(cvContent);
    }
}
