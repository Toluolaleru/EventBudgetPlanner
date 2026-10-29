package util;

import java.util.Scanner;

/**
 * Utility helper class for handling safe console inputs 
 * with built-in validation for numbers, strings, and non-negative ranges.
 */
public class Util {

    // A shared, safe scanner instance for reading console inputs
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user with a message and reads a trimmed string input.
     * Continuously loops until valid text is provided.
     */
    public static String getString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("-> Error!: Input cannot be empty. Please try again.");
        }
    }

    /**
     * Prompts the user for an integer and ensures valid input format.
     */
    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("-> Error!: Invalid integer format. Please enter a valid whole number.");
            }
        }
    }

    /**
     * Prompts the user for a decimal number and ensures 
     * the entered value is a valid number and not negative (>= 0).
     */
    public static double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= 0) {
                    return value;
                }
                System.out.println("-> Error!: Amount cannot be negative. Please enter a value of 0 or greater.");
            } catch (NumberFormatException e) {
                System.out.println("-> Error!: Invalid number format. Please enter a valid decimal number.");
            }
        }
    }

    /**
     * Closes the scanner when the application exits.
     */
    public static void closeScanner() {
        scanner.close();
    }
}