package application;

public interface EmailSender {
    void sendEmail(String empfaenger, String betreff, String nachricht);
}