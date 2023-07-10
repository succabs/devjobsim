package com.succabs.devjobsim.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.succabs.devjobsim.gameLogic.MailLogic;
import com.succabs.devjobsim.gameLogic.PhoneLogic;
import com.succabs.devjobsim.gameLogic.JobListingLogic;
import com.succabs.devjobsim.gameLogic.CVLogic;
import com.succabs.devjobsim.gameLogic.FridgeLogic;
import com.succabs.devjobsim.gameLogic.GameInitializer;
import com.succabs.devjobsim.gameLogic.OutLogic;

import com.succabs.devjobsim.gameLogic.Time;
import com.succabs.devjobsim.player.PlayerStats;

public class GameUI extends Application {

    private TextArea gameScreen; // Declare gameScreen as a field
    private Label stressLabel;
    private Label hungerLabel;
    private Label moneyLabel;
    private Label timeLabel;
    @SuppressWarnings("unused")
    private Time time;
    private Button mailButton;
    private Button phoneButton;
    private Button jobListingButton;
    private Button cvButton;
    private Button fridgeButton;
    private Button outButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the main game window
        BorderPane gameWindow = new BorderPane();
        gameWindow.setStyle("-fx-background-color: #2c3e50;"); // Set the background color
        time = new Time(this);
        GameInitializer.initializeGame();

        // Create the game screen
        this.gameScreen = new TextArea();
        gameScreen.setEditable(false);
        gameScreen.setStyle("-fx-text-fill: black;"); // Set the text color
        gameScreen.setPrefRowCount(20); // Set the number of visible rows
        updateGameScreen("Welcome.");

        PlayerStats playerStats = GameInitializer.getPlayerStats();

        // Create labels for player stats
        this.timeLabel = new Label("0");
        this.stressLabel = new Label("Stress: " + playerStats.getStress());
        this.hungerLabel = new Label("Hunger: " + playerStats.getHunger());
        this.moneyLabel = new Label("Money: " + playerStats.getMoney());

        // Create buttons for actions
        this.mailButton = new Button("Mail");
        this.phoneButton = new Button("Phone");
        this.jobListingButton = new Button("Job Listing");
        this.cvButton = new Button("CV");
        this.fridgeButton = new Button("Fridge");
        this.outButton = new Button("Go Outside");
        // Add event handlers or actions for the buttons
        mailButton.setOnAction(event -> MailLogic.handleMailButton(this));
        phoneButton.setOnAction(event -> PhoneLogic.handlePhoneButton(this));
        fridgeButton.setOnAction(event -> FridgeLogic.handleFridgeButton(this));
        jobListingButton.setOnAction(event -> JobListingLogic.handleJobButton(this));
        cvButton.setOnAction(event -> CVLogic.handleCVButton(this, playerStats));
        outButton.setOnAction(event -> OutLogic.handleOutButton(this));

        // Create a field for player input
        TextArea playerInputField = new TextArea();
        playerInputField.setPrefRowCount(1); // Set the number of visible rows
        playerInputField.setPromptText("Enter your command");

        // Create a button to submit player input
        Button submitButton = new Button("Submit");
        // Add event handler or action for the submit button

        // Apply styles to the labels
        timeLabel.setStyle("-fx-text-fill: white;");
        stressLabel.setStyle("-fx-text-fill: white;");
        hungerLabel.setStyle("-fx-text-fill: white;");
        moneyLabel.setStyle("-fx-text-fill: white;");

        // Create a container for player stats labels
        VBox statsContainer = new VBox(10); // Set spacing between labels
        statsContainer.setPadding(new Insets(10));
        statsContainer.getChildren().addAll(timeLabel, stressLabel, hungerLabel, moneyLabel);

        // Set alignment of statsContainer
        statsContainer.setAlignment(Pos.TOP_CENTER);
        // Create a container for the buttons
        VBox buttonContainer = new VBox(10); // Set spacing between buttons
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.getChildren().addAll(mailButton, phoneButton, jobListingButton, cvButton, fridgeButton,
                outButton);
        buttonContainer.setAlignment(Pos.TOP_CENTER);

        // Apply styles to the labels
        stressLabel.setStyle("-fx-text-fill: white;");
        hungerLabel.setStyle("-fx-text-fill: white;");
        moneyLabel.setStyle("-fx-text-fill: white;");

        // Create a container for player stats labels and buttonContainer
        VBox topContainer = new VBox(10); // Set spacing between labels and buttonContainer
        topContainer.setAlignment(Pos.TOP_CENTER);
        topContainer.setPadding(new Insets(10));
        topContainer.getChildren().addAll(statsContainer, buttonContainer);

        // Create a container for the player input field and submit button
        HBox inputContainer = new HBox(10);
        inputContainer.setPadding(new Insets(10));
        inputContainer.getChildren().addAll(playerInputField, submitButton);

        // Add the game screen and containers to the game window
        gameWindow.setCenter(gameScreen);
        gameWindow.setBottom(inputContainer);
        gameWindow.setRight(topContainer);

        // Create the scene with the game window
        Scene scene = new Scene(gameWindow, 800, 600);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Developer Job Simulator");
        primaryStage.show();
    }

    public void setMailButtonText(String text) {
        mailButton.setText(text);
    }

    public void updateGameScreen(String text) {
        Platform.runLater(() -> {
            gameScreen.setText(text + "\n");
            gameScreen.positionCaret(gameScreen.getLength());
            gameScreen.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void updatePlayerStats(PlayerStats stats) {
        Platform.runLater(() -> {
            stressLabel.setText("Stress: " + stats.getStress());
            hungerLabel.setText("Hunger: " + stats.getHunger());
            moneyLabel.setText("Money: " + stats.getMoney());
        });
    }

    public void updateTimeLabel(int hours, int minutes, int seconds) {
        Platform.runLater(() -> {
            timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        });
    }
}