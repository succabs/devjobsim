package com.succabs.devjobsim.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

import com.succabs.devjobsim.gameLogic.MailLogic;
import com.succabs.devjobsim.gameLogic.PhoneLogic;
import com.succabs.devjobsim.gameLogic.JobListingLogic;

import java.net.URL;

import com.succabs.devjobsim.gameLogic.CVLogic;
import com.succabs.devjobsim.gameLogic.FridgeLogic;
import com.succabs.devjobsim.gameLogic.GameInitializer;
import com.succabs.devjobsim.gameLogic.OutLogic;

import com.succabs.devjobsim.gameLogic.Time;
import com.succabs.devjobsim.player.PlayerStats;

public class GameUI extends Application {

    private ScrollPane welcomeScreen;
    private TextArea mailScreen;
    private TextArea phoneScreen;
    private TextArea cvScreen;
    private TextArea jobScreen;
    private TextArea outScreen;
    private FridgeView fridgeView;
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
        gameWindow.setStyle("-fx-background-color: #C0C0C0;"); // Set the background color
        time = new Time(this);
        GameInitializer.initializeGame();

        // Create a container for the specific area
        VBox gameScreen = new VBox();
        gameScreen.setPadding(new Insets(10)); // Set padding if needed

        // Create the welcome screen
        this.welcomeScreen = new ScrollPane();
        welcomeScreen.setFitToWidth(true);
        welcomeScreen.setFitToHeight(true);
        welcomeScreen.setMouseTransparent(true); // Make it not selectable
        welcomeScreen.setFocusTraversable(false); // Disable focus traversal
        // welcomeScreen.setPrefRowCount(20); // Set the number of visible rows
        VBox.setVgrow(welcomeScreen, Priority.ALWAYS); // Make the welcomeScreen grow to fill the available space
        // Create the styled logo text
        String logoText = "Developer Job Hunt Simulator\n";
        Label styledLogoText = new Label(logoText);
        styledLogoText.getStyleClass().add("retro-logo");

        // Create the plain welcome text
        String welcomeText = "\nWelcome to Developer Job Hunt Simulator.\nTry to get a job!\n";

        // Create a FlowPane to hold the styled logo text and welcome text
        FlowPane textContainer = new FlowPane();
        textContainer.getChildren().addAll(styledLogoText, new Text(welcomeText));

        // Set the FlowPane as the content of the TextArea
        welcomeScreen.setContent(textContainer);

        // Create the mail screen
        this.mailScreen = new TextArea();
        mailScreen.setEditable(false);
        // mailScreen.setMouseTransparent(true); // Make it not selectable
        mailScreen.setFocusTraversable(false); // Disable focus traversal
        mailScreen.setPrefRowCount(20); // Set the number of visible rows

        // Create the mail screen
        this.phoneScreen = new TextArea();
        phoneScreen.setEditable(false);
        phoneScreen.setMouseTransparent(true); // Make it not selectable
        phoneScreen.setFocusTraversable(false); // Disable focus traversal
        phoneScreen.setPrefRowCount(20); // Set the number of visible rows

        // Create the mail screen
        this.jobScreen = new TextArea();
        jobScreen.setEditable(false);
        jobScreen.setMouseTransparent(true); // Make it not selectable
        jobScreen.setFocusTraversable(false); // Disable focus traversal
        jobScreen.setPrefRowCount(20); // Set the number of visible rows

        // Create the mail screen
        this.cvScreen = new TextArea();
        cvScreen.setEditable(false);
        cvScreen.setMouseTransparent(true); // Make it not selectable
        cvScreen.setFocusTraversable(false); // Disable focus traversal
        cvScreen.setPrefRowCount(20); // Set the number of visible rows

        this.outScreen = new TextArea();
        outScreen.setEditable(false);
        outScreen.setMouseTransparent(true); // Make it not selectable
        outScreen.setFocusTraversable(false); // Disable focus traversal
        outScreen.setPrefRowCount(20); // Set the number of visible rows

        this.fridgeView = new FridgeView();

        gameScreen.getChildren().add(welcomeScreen);

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
            gameScreen.getChildren().add(mailScreen);
        });

        fridgeButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            FridgeLogic.handleFridgeButton(fridgeView); // Call the FridgeLogic to handle the fridge button
            gameScreen.getChildren().add(fridgeView.getFridgeScreen());
        });

        phoneButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            PhoneLogic.handlePhoneButton(this);
            gameScreen.getChildren().add(phoneScreen);

        });
        jobListingButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            JobListingLogic.handleJobButton(this);
            gameScreen.getChildren().add(jobScreen);
        });
        cvButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            CVLogic.handleCVButton(this, playerStats);
            gameScreen.getChildren().add(cvScreen);
        });
        outButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            OutLogic.handleOutButton(this);
            gameScreen.getChildren().add(outScreen);
        });

        // Create a field for player input
        TextArea playerInputField = new TextArea();
        playerInputField.setPrefRowCount(1); // Set the number of visible rows
        playerInputField.setMouseTransparent(true); // Make it not selectable
        playerInputField.setFocusTraversable(false); // Disable focus traversal
        playerInputField.setPromptText("Enter your command");

        // Create a button to submit player input
        Button submitButton = new Button("Submit");
        // Add event handler or action for the submit button
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

        URL cssUrl = getClass().getResource("/retro.css");
        String cssExternalForm = cssUrl.toExternalForm();
        scene.getStylesheets().add(cssExternalForm);
        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Developer Job Hunt Simulator");
        primaryStage.show();
    }

    public void setMailButtonText(String text) {
        mailButton.setText(text);
    }

    public void updateMailScreen(String text) {
        Platform.runLater(() -> {
            mailScreen.setText(text + "\n");
            mailScreen.positionCaret(mailScreen.getLength());
            mailScreen.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void updatePhoneScreen(String text) {
        Platform.runLater(() -> {
            phoneScreen.setText(text + "\n");
            phoneScreen.positionCaret(phoneScreen.getLength());
            phoneScreen.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void updateJobScreen(String text) {
        Platform.runLater(() -> {
            jobScreen.setText(text + "\n");
            jobScreen.positionCaret(jobScreen.getLength());
            jobScreen.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void updateCVScreen(String text) {
        Platform.runLater(() -> {
            cvScreen.setText(text + "\n");
            cvScreen.positionCaret(cvScreen.getLength());
            cvScreen.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void updateOutScreen(String text) {
        Platform.runLater(() -> {
            outScreen.setText(text + "\n");
            outScreen.positionCaret(outScreen.getLength());
            outScreen.setScrollTop(Double.MAX_VALUE);
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
