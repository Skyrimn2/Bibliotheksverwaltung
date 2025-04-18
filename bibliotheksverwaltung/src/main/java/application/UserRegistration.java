package application;

//Das macht keinen Sinn, Die Idee dahinter sollte Application Code sein. Das hier aus der Konsole gelesen wrid ist definitiv Plugin Code. Wichtige Aktionen sind asukommentiert, warum?
//Kurz: Die Klasse mischt Was-Code (der sit hier richtig) mit Wie-Code (der Wie-Code hier gehört in die Plugin Schicht)
import java.util.Scanner;
public class UserRegistration {

    public void registerUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nBenutzer registrieren:");
            System.out.print("Name: ");
            String username = scanner.nextLine();
            System.out.print("Passwort: ");
            String password = scanner.nextLine();
            System.out.print("ID: ");
            int userID = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Mitgliedschaft: ");
            String membership = scanner.nextLine();

            // BenutzerDBHandler speichereBenutzer = new speichereBenutzer(benutzerName, benutzerPasswort, benutzerID, mitgliedschaft);


        // System.out.println("Benutzer erfolgreich registriert: " + benutzerName);

        // Nur für Debug Zwecke
        System.out.println("Benutzer erfolgreich registriert: " + username + password + userID + membership);
        
    }
}
}