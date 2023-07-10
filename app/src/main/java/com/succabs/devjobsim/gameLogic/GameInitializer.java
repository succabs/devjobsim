package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.gameLogic.CVEntry;
import com.succabs.devjobsim.gameLogic.CVLogic;
import com.succabs.devjobsim.gameLogic.FridgeLogic;
import com.succabs.devjobsim.gameLogic.Mail;
import com.succabs.devjobsim.gameLogic.MailLogic;
import com.succabs.devjobsim.gameLogic.OutLogic;
import com.succabs.devjobsim.player.PlayerStats;

public class GameInitializer {
    private static PlayerStats playerStats;

    public static void initializeGame() {
        initializePlayerStats();
        initializeMails();
        initializeCVEntries();
        initializeFridgeItems();
        initializeAvailablePlaces();
    }

    private static void initializePlayerStats() {
        // Initialize the player stats and set the values
        playerStats = new PlayerStats();
        playerStats.setName("Derek Developer");
        playerStats.setStress(50);
        playerStats.setHunger(10);
        playerStats.setMoney(100);
    }

    public static PlayerStats getPlayerStats() {
        return playerStats; // Provide a static method to access playerStats
    }

    private static void initializeMails() {
        // Create and add mails to the mailbox
        Mail mail1 = new Mail("HR Sarah", "Job opening", "We have a job opening for you! Apply now!");
        Mail mail2 = new Mail("LoveFinder", "Find Your True Love",
                "With just a little money, we will find your true love!");
        // Add mails to the mailbox using MailLogic.addMail(mail) method
        MailLogic.addMail(mail1);
        MailLogic.addMail(mail2);
    }

    private static void initializeCVEntries() {
        // Create and add CV entries
        CVEntry entry1 = new CVEntry("Type 1");
        entry1.addField("Field 1", "Value 1");
        entry1.addField("Field 2", "Value 2");

        CVEntry entry2 = new CVEntry("Type 2");
        entry2.addField("Field 1", "Value 1");
        entry2.addField("Field 3", "Value 3");

        // Add CV entries using CVLogic.addEntry(entry) method
        CVLogic.addEntry(entry1);
        CVLogic.addEntry(entry2);
    }

    private static void initializeFridgeItems() {
        // Add items to the fridge using FridgeLogic.addItem(item) method
        // You can create Item objects and add them to the fridge
    }

    private static void initializeAvailablePlaces() {
        // Set available places to go using OutLogic.setAvailablePlaces() method
        // You can create Place objects and set them as available places to go
    }
}
