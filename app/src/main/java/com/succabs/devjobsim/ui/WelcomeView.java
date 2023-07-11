package com.succabs.devjobsim.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class WelcomeView {
    private ScrollPane welcomeScreen;

    public WelcomeView() {
        welcomeScreen = new ScrollPane();
        welcomeScreen.setFitToWidth(true);
        welcomeScreen.setFitToHeight(true);
        welcomeScreen.setMouseTransparent(true);
        welcomeScreen.setFocusTraversable(false);

        String logoText = "Developer Job Hunt Simulator\n";
        Label styledLogoText = new Label(logoText);
        styledLogoText.getStyleClass().add("retro-logo");

        String welcomeText = "\nYour goal is to get a job.\nYou can apply for a job through the job listing, mail or phone.\nAce the interview and try to get as good salary as you can.\nKeep your stress and hunger levels low by eating or doing other tasks than job hunt.\nGood luck!";

        FlowPane textContainer = new FlowPane();
        textContainer.getChildren().addAll(styledLogoText, new Text(welcomeText));

        welcomeScreen.setContent(textContainer);
    }

    public ScrollPane getWelcomeScreen() {
        return welcomeScreen;
    }
}
