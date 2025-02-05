package application;

import java.util.ArrayList;
import java.util.List;

public class MockEmailSender implements EmailSender {
    private List<String> gesendeteEmails = new ArrayList<>();
    @Override
    public void sendEmail(String empfaenger, String betreff, String nachricht) {
        String email = "An: " + empfaenger + ", Betreff: " + betreff + ", Nachricht: " + nachricht;
        System.out.println("Email gesendet: " + email);
        gesendeteEmails.add(email);
    }
    public boolean wurdeEmailGesendet(String empfaenger, String betreff, String nachricht) {
        String gesendeteEmail = "An: " + empfaenger + ", Betreff: " + betreff + ", Nachricht: " + nachricht;
        return gesendeteEmails.contains(gesendeteEmail);
    }
}