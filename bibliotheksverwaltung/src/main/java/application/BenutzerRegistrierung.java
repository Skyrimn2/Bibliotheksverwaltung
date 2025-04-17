package application;


//Das macht keinen Sinn, Die Idee dahinter sollte Application Code sein. Das hier aus der Konsole gelesen wrid ist definitiv Plugin Code. Wichtige Aktionen sind asukommentiert, warum?
import java.util.Scanner;
public class BenutzerRegistrierung {

    public void registriereBenutzer() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nBenutzer registrieren:");
            System.out.print("Name: ");
            String benutzerName = scanner.nextLine();
            System.out.print("Passwort: ");
            String benutzerPasswort = scanner.nextLine();
            System.out.print("ID: ");
            int benutzerID = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Mitgliedschaft: ");
            String mitgliedschaft = scanner.nextLine();

            // BenutzerDBHandler speichereBenutzer = new speichereBenutzer(benutzerName, benutzerPasswort, benutzerID, mitgliedschaft);


        // System.out.println("Benutzer erfolgreich registriert: " + benutzerName);

        // Nur für Debug Zwecke
        System.out.println("Benutzer erfolgreich registriert: " + benutzerName + benutzerPasswort + benutzerID + mitgliedschaft);
        
    }
}
}