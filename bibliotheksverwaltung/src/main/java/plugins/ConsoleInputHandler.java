package plugins;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles console input operations - separated from ConsoleFrontend
 * Provides validated input methods
 */
public class ConsoleInputHandler {

    private Scanner scanner;

    public ConsoleInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int readMenuOption() {
        int selection = -1;

        while (true) {
            try {
                selection = scanner.nextInt();
                scanner.nextLine(); // consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // consume invalid input
            }
        }

        return selection;
    }

    public String readString() {
        String value = scanner.next();
        scanner.nextLine(); // consume remaining line
        return value;
    }
}