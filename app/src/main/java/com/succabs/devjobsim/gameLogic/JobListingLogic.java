package com.succabs.devjobsim.gameLogic;

import java.util.ArrayList;
import java.util.List;

import com.succabs.devjobsim.ui.GameUI;

public class JobListingLogic {
    private static List<Job> jobs = new ArrayList<>();

    // Add a mail to the list
    public static void addJob(Job job) {
        jobs.add(job);
    }

    // Get the list of mails
    public static List<Job> getjobs() {
        return jobs;
    }

    // function to handle what happens when the mail button is pressed
    public static void handleJobButton(GameUI gameUI) {
        StringBuilder jobListing = new StringBuilder();
        jobListing.append("Jobs available \n");

        for (Job job : jobs) {
            jobListing.append("Company: ").append(job.getCompany()).append("\n");
            jobListing.append("Position: ").append(job.getPosition()).append("\n");
            jobListing.append("Information: ").append(job.getContent()).append("\n");
            jobListing.append("Salary: ").append(job.getSalary()).append("\n");
        }

        gameUI.updateGameScreen(jobListing.toString());
    }
}

class Job {
    private String company;
    private String position;
    private String content;
    private int salary;

    public Job(String company, String position, String content, int salary) {
        this.company = company;
        this.position = position;
        this.content = content;
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public String getContent() {
        return content;
    }

    public int getSalary() {
        return salary;
    }
}
