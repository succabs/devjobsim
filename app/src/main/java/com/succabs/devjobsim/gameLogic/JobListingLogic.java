package com.succabs.devjobsim.gameLogic;

import java.util.ArrayList;
import java.util.List;

import com.succabs.devjobsim.ui.JobView;

public class JobListingLogic {
    private static List<Job> jobs = new ArrayList<>();

    // Add a mail to the list
    public static void addJob(Job job) {
        jobs.add(job);
    }

    // Get the list of mails
    public static List<Job> getJobs() {
        return jobs;
    }

    // function to handle what happens when the mail button is pressed
    public static void handleJobButton(JobView jobView) {
        jobView.updateJobScreen(getJobs());
    }

    public static void applyJob(Job job) {
        System.out.println("haettu");
    }
}