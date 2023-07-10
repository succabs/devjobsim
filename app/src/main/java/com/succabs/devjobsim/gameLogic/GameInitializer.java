package com.succabs.devjobsim.gameLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.succabs.devjobsim.player.PlayerStats;

public class GameInitializer {
    private static PlayerStats playerStats;

    // Define lists for randomized values
    private static final List<String> firstNames = Arrays.asList("John", "Derek", "Emily", "Sarah", "Michael",
            "Jessica", "David", "Olivia", "Daniel", "Sophia", "Andrew", "Emma", "Matthew", "Isabella", "William", "Mia",
            "James", "Ava", "Joseph", "Charlotte");
    private static final List<String> lastNames = Arrays.asList("Doe", "Assman", "Kirkby", "Palin", "Smith", "Johnson",
            "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris",
            "Clark", "Lewis", "Young");
    private static final List<String> addresses = Arrays.asList("Street 1", "Street 2", "Street 3", "Avenue 1",
            "Avenue 2", "Avenue 3", "Road 1", "Road 2", "Road 3", "Lane 1", "Lane 2", "Lane 3", "Boulevard 1",
            "Boulevard 2", "Boulevard 3", "Place 1", "Place 2", "Place 3", "Court 1", "Court 2");
    private static final List<String> skills = Arrays.asList("Java", "Python", "C++", "JavaScript", "HTML+CSS", "SQL",
            "Ruby", "PHP", "Swift");
    private static final int MIN_SKILLS = 2;
    private static final int MAX_SKILLS = 4;
    private static final List<String> universityNames = Arrays.asList(
            "City University",
            "Global Institute of Technology",
            "Eastern State University",
            "Western University",
            "Northern College",
            "Southern Technical Institute",
            "Pacific Coast University");

    public static void initializeGame() {
        MailLogic.loadMailsFromCSV();
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
        playerStats.setName(getRandomElement(firstNames) + " " + getRandomElement(lastNames));
        playerStats.setAddress(getRandomElement(addresses));
        playerStats.setPhone("555-555-555");
        playerStats.setMail(getRandomElement(firstNames) + "." + getRandomElement(lastNames) + "@devmail.com");
        playerStats.setStress(50);
        playerStats.setHunger(10);
        playerStats.setMoney(100);
        initializePlayerSkills();
    }

    private static <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private static void initializePlayerSkills() {
        List<String> shuffledSkills = new ArrayList<>(skills);
        Collections.shuffle(shuffledSkills);

        int numSkills = getRandomNumber(MIN_SKILLS, MAX_SKILLS);
        List<String> selectedSkills = shuffledSkills.subList(0, numSkills);

        Random random = new Random();
        for (String skill : selectedSkills) {
            String level = random.nextInt(10) < 3 ? "Intermediate" : "Beginner";
            playerStats.addSkill(skill, level);
        }
    }

    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static PlayerStats getPlayerStats() {
        return playerStats; // Provide a static method to access playerStats
    }

    private static void initializeMails() {
        // Create and add mails to the mailbox
        // Mail mail1 = new Mail("HR Sarah", "Job opening", "We have a job opening for
        // you! Apply now!");
        // Mail mail2 = new Mail("LoveFinder", "Find Your True Love",
        // "With just a little money, we will find your true love!");
        // Add mails to the mailbox using MailLogic.addMail(mail) method
        // MailLogic.addMail(mail1);
        // MailLogic.addMail(mail2);
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
        educationEntry.addField("School", getRandomElement(universityNames));
        educationEntry.addField("Year", "2018-2023");
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
