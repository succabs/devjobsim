package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MailLogic {
    private static List<Mail> mails = new ArrayList<>();
    private static int unreadMails = 0;

    // Add a mail to the list
    public static void addMail(Mail mail) {
        mails.add(mail);
    }

    // Get the list of mails
    public static List<Mail> getMails() {
        return mails;
    }

    // function to handle what happens when the mail button is pressed
    public static void handleMailButton(GameUI gameUI) {
        StringBuilder mailText = new StringBuilder();
        if (unreadMails > 0) {
            mailText.append("You have ").append(unreadMails).append(" unread mails.\n");
        }

        for (Mail mail : mails) {
            mailText.append("Sender: ").append(mail.getSender()).append("\n");
            mailText.append("Subject: ").append(mail.getSubject()).append("\n");
            mailText.append("Content: ").append(mail.getContent()).append("\n");
            mailText.append("----------------------\n");
        }

        gameUI.updateMailScreen(mailText.toString());

        unreadMails = 0; // set unread mail count to 0
        updateMailButton(gameUI); // update the mail button
    }

    private static final List<Mail> possibleMails = new ArrayList<>();

    public static void loadMailsFromCSV() {
        try {
            InputStream inputStream = MailLogic.class.getResourceAsStream("/mails.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header line
                }
                String[] mailData = line.split(",", 3);
                if (mailData.length == 3) {
                    String sender = mailData[0].trim();
                    String subject = mailData[1].trim();
                    String content = mailData[2].trim();
                    Mail mail = new Mail(sender, subject, content);
                    possibleMails.add(mail);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file read error
        }
    }

    public static void checkMail(Time time, GameUI gameUI) {
        if (time.getSeconds() % 5 == 0) {
            // Check if there are any possible mails
            if (!possibleMails.isEmpty()) {
                // Select a random mail
                Random random = new Random();
                Mail randomMail = possibleMails.get(random.nextInt(possibleMails.size()));

                // Add the random mail to the list
                addMail(randomMail);
                unreadMails++;
                updateMailButton(gameUI);
            }
        }
    }

    public static void updateMailButton(GameUI gameUI) {
        Platform.runLater(() -> {
            // Update mail button text based on unread mails count
            String buttonText = "Mail";
            if (unreadMails > 0) {
                buttonText += " (" + unreadMails + ")";
            }
            gameUI.setMailButtonText(buttonText); // Call the setMailButtonText method in GameUI
        });
    }
}