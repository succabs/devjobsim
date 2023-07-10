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

    private TextArea gameScreen2;
    private VBox fridgeScreen;
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

        // Create a container for the specific area
        VBox gameScreen = new VBox();
        gameScreen.setStyle("-fx-background-color: white;"); // Set background color or other styles
        gameScreen.setPadding(new Insets(10)); // Set padding if needed

        // Create the game screen
        this.gameScreen2 = new TextArea();
        gameScreen2.setEditable(false);
        gameScreen2.setStyle("-fx-text-fill: black;"); // Set the text color
        gameScreen2.setPrefRowCount(20); // Set the number of visible rows
        updateGameScreen("Welcome.");

        this.fridgeScreen = new VBox();
        fridgeScreen.setStyle("-fx-background-color: white;"); // Set background color or other styles
        fridgeScreen.setPadding(new Insets(10)); // Set padding if needed

        gameScreen.getChildren().add(gameScreen2);

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
        mailButton.setOnAction(event -> {
            // Clear the current content
            gameScreen.getChildren().clear();
            MailLogic.handleMailButton(this);
            // Add the new content to the area container
            gameScreen.getChildren().add(gameScreen2);
        });

        fridgeButton.setOnAction(event -> {
            // Clear the current content
            gameScreen.getChildren().clear();
            FridgeLogic.handleFridgeButton(this);
            // Add the new content to the area container
            gameScreen.getChildren().add(fridgeScreen);
        });

        phoneButton.setOnAction(event -> PhoneLogic.handlePhoneButton(this));
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
            gameScreen2.setText(text + "\n");
            gameScreen2.positionCaret(gameScreen2.getLength());
            gameScreen2.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void updateMailScreen(String text) {
        Platform.runLater(() -> {
            gameScreen2.setText(text + "\n");
            gameScreen2.positionCaret(gameScreen2.getLength());
            gameScreen2.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void updateFridgeScreen(String text) {
        Platform.runLater(() -> {
            Label fridgeLabel = new Label(text); // Create a label with the desired text
            fridgeScreen.getChildren().add(fridgeLabel); // Add the label to the fridgeScreen container

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
