package application;

public interface EmailSender {
    void sendEmail(String recipient, String subject, String message);
}