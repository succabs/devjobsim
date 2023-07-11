package com.succabs.devjobsim.ui;

import java.util.List;

import com.succabs.devjobsim.gameLogic.JobListingLogic;
import com.succabs.devjobsim.gameLogic.Job;
import com.succabs.devjobsim.gameLogic.Mail;
import com.succabs.devjobsim.gameLogic.MailLogic;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class JobView {
    private VBox jobScreen;

    public JobView() {
        jobScreen = new VBox();
    }

    public VBox getJobScreen() {
        return jobScreen;
    }

    public void updateJobScreen(List<Job> jobs) {
        jobScreen.getChildren().clear();
        for (Job job : jobs) {
            Label nameLabel = new Label("Name: " + job.getCompany());
            Button applyButton = new Button("Apply");
            applyButton.setOnAction(event -> {
                JobListingLogic.applyJob(job);
                updateJobScreen(JobListingLogic.getJobs());
                Mail mail1 = new Mail(job.getCompany(), "Your application for " + job.getPosition(),
                        "Come to an interview!");
                MailLogic.addMail(mail1);
            });

            HBox jobContainer = new HBox(nameLabel, applyButton);
            jobScreen.getChildren().add(jobContainer);
        }
    }
}
