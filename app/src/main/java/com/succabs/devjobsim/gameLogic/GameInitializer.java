package com.succabs.devjobsim.gameLogic;

import java.util.Map;

import com.succabs.devjobsim.player.PlayerStats;

public class GameInitializer {
    private static PlayerStats playerStats;

    public static void initializeGame() {
        initializePlayerStats();
        initializeMails();
        initializeCVEntries();
        initializeFridgeItems();
        initializeAvailablePlaces();
        initializeJobListings();
    }

    private static void initializePlayerStats() {
        // Initialize the player stats and set the values
        playerStats = new PlayerStats();
        playerStats.setName("Derek Developer");
        playerStats.setAddress("Hardware Street 1337");
        playerStats.setPhone("555-555-555");
        playerStats.setMail("derek.developer@mail.com");
        playerStats.setStress(50);
        playerStats.setHunger(10);
        playerStats.setMoney(100);

        playerStats.addSkill("Java", "Intermediate");
        playerStats.addSkill("Python", "Beginner");
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
        CVEntry informationEntry = new CVEntry("Information");
        informationEntry.addField("Name", playerStats.getName());
        informationEntry.addField("Address", playerStats.getAddress());
        informationEntry.addField("Phone", playerStats.getPhone());
        informationEntry.addField("Email", playerStats.getMail());

        CVEntry skillsEntry = new CVEntry("Skills");
        // Get the skills data from PlayerStats

        // Get the skills data from PlayerStats
        Map<String, String> skills = playerStats.getSkills();
        for (Map.Entry<String, String> entry : skills.entrySet()) {
            String skill = entry.getKey();
            String level = entry.getValue();
            skillsEntry.addField(skill, level);
        }

        CVEntry educationEntry = new CVEntry("Education");
        educationEntry.addField("School", "University of Example");
        educationEntry.addField("Year", "2010-2014");
        educationEntry.addField("GPA", "3.5");
        educationEntry.addField("Major", "Computer Science");

        CVEntry jobHistoryEntry = new CVEntry("Job History");
        jobHistoryEntry.addField("Job", "Software Engineer");
        jobHistoryEntry.addField("Role", "Developing web applications");
        jobHistoryEntry.addField("Year", "2015-2020");

        CVLogic.addEntry(informationEntry);
        CVLogic.addEntry(skillsEntry);
        CVLogic.addEntry(educationEntry);
        CVLogic.addEntry(jobHistoryEntry);
    }

    private static void initializeFridgeItems() {
        // Create and add items to the fridge
        Item item1 = new Item("Beer", 6);
        Item item2 = new Item("Sandwich", 2);
        Item item3 = new Item("Pizza", 1);

        // Add items to the fridge using FridgeLogic.addItem(item) method
        FridgeLogic.addItem(item1);
        FridgeLogic.addItem(item2);
        FridgeLogic.addItem(item3);
    }

    private static void initializeAvailablePlaces() {
        // Set available places to go using OutLogic.setAvailablePlaces() method
        // You can create Place objects and set them as available places to go
        Place place1 = new Place("Shop");
        Place place2 = new Place("Interview");
        Place place3 = new Place("Park");
        Place place4 = new Place("Friend");
        OutLogic.setAvailablePlaces(place1, place2, place3, place4);
    }

    private static void initializeJobListings() {
        // Create and add job listings
        Job job1 = new Job("Company 1", "Position 1", "Job Content 1", 5000);
        Job job2 = new Job("Company 2", "Position 2", "Job Content 2", 6000);
        // Add job listings using JobListingLogic.addJob(job) method
        JobListingLogic.addJob(job1);
        JobListingLogic.addJob(job2);
    }
}
