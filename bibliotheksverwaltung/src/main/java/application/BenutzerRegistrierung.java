package application;

import java.util.Scanner;

public class BenutzerRegistrierung {

    public void registriereBenutzer() {
        Scanner scanner = new Scanner(System.in);
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

            //                BenutzerDBHandler speichereBenutzer = new speichereBenutzer(benutzerName, benutzerPasswort, benutzerID, mitgliedschaft);


        System.out.println("Benutzer erfolgreich registriert: " + benutzerName);
    }
}