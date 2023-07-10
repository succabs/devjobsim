package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;

import javafx.application.Platform;

public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    private GameUI gameUI;

    public Time(GameUI gameUI) {
        this.gameUI = gameUI;
        hours = 0;
        minutes = 0;
        seconds = 0;

        // Start a separate thread to advance the time
        Thread timeThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                advanceTime();
            }
        });
        timeThread.setDaemon(true);
        timeThread.start();
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    private void advanceTime() {
        seconds++;
        MailLogic.checkMail(this, gameUI);
        setTime(this, gameUI);

        if (seconds >= 60) {
            seconds = 0;
            minutes++;
            if (minutes >= 60) {
                minutes = 0;
                hours++;
                if (hours >= 24) {
                    hours = 0;
                }
            }
        }
    }

    private void setTime(Time time, GameUI gameUI) {
        Platform.runLater(() -> gameUI.updateTimeLabel(getHours(), getMinutes(), getSeconds())); // Update the time
                                                                                                 // label in the UI
    }
}
