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
import java.net.URL;
import javafx.util.Duration;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

    private WelcomeView welcomeView;
    private FridgeView fridgeView;
    private JobView jobView;
    private CVView cvView;
    private OutView outView;
    private PhoneView phoneView;
    private MailView mailView;

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
        PlayerStats playerStats = GameInitializer.getPlayerStats();

        // Create a container for the specific area
        VBox gameScreen = new VBox();
        gameScreen.setPadding(new Insets(10)); // Set padding if needed

        this.welcomeView = new WelcomeView();
        this.mailView = new MailView();
        this.fridgeView = new FridgeView(this, playerStats);
        this.jobView = new JobView();
        this.cvView = new CVView();
        this.outView = new OutView();
        this.phoneView = new PhoneView();

        gameScreen.getChildren().add(welcomeView.getWelcomeScreen());

        // Create labels for player stats
        this.timeLabel = new Label();
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
            gameScreen.getChildren().clear();
            MailLogic.handleMailButton(mailView, this);
            gameScreen.getChildren().add(mailView.getMailScreen());
        });

        fridgeButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            FridgeLogic.handleFridgeButton(fridgeView); // Call the FridgeLogic to handle the fridge button
            gameScreen.getChildren().add(fridgeView.getFridgeScreen());
        });

        phoneButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            PhoneLogic.handlePhoneButton(phoneView);
            gameScreen.getChildren().add(phoneView.getPhoneScreen());

        });
        jobListingButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            JobListingLogic.handleJobButton(jobView);
            gameScreen.getChildren().add(jobView.getJobScreen());
        });
        cvButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            CVLogic.handleCVButton(cvView, playerStats);
            gameScreen.getChildren().add(cvView.getCVScreen());
        });
        outButton.setOnAction(event -> {
            gameScreen.getChildren().clear();
            OutLogic.handleOutButton(outView);
            gameScreen.getChildren().add(outView.getOutScreen());
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

        // Path to the background music file
        String musicFile = getClass().getResource("/bgmusic.wav").toString();

        // Create a Media object with the music file
        Media media = new Media(musicFile);
        // Create a MediaPlayer and set it to play the media
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        // Create a MediaView and bind it to the MediaPlayer
        // Set the volume to 50% (0.5)
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });

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
