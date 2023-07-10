package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.List;

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

        gameUI.updateGameScreen(mailText.toString());

        unreadMails = 0; // set unread mail count to 0
        updateMailButton(gameUI); // update the mail button
    }

    public static void checkMail(Time time, GameUI gameUI) {

        if (time.getSeconds() % 30 == 0) {
            // Create a new mail and add it to the list
            Mail newMail = new Mail("Matt", "New Mail", "This is a new mail.");
            addMail(newMail);
            unreadMails++;
            updateMailButton(gameUI);
        }

        if ((time.getHours() == 0 && time.getMinutes() == 0 && time.getSeconds() == 5)) {
            // Create a new mail and add it to the list
            Mail newMail = new Mail("Matt", "New Mail", "This is a new mail.");
            addMail(newMail);
            unreadMails++;
            updateMailButton(gameUI);
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

class Mail {
    private String sender;
    private String subject;
    private String content;

    public Mail(String sender, String subject, String content) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }
}
