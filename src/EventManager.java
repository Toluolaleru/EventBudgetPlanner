import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class EventManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Initialize the main events map
        HashMap<String, HashMap> events = new HashMap<>();
        
        // Hardcoded event 1
        HashMap<String, Object> event1Details = new HashMap<>();
        event1Details.put("Type", "Birthday");
        event1Details.put("Date", "2026-05-15");
        event1Details.put("Location", "London");
        event1Details.put("Budget", 50000.0f);
        events.put("Tolu Birthday", event1Details);
        
        // Hardcoded event 2
        HashMap<String, Object> event2Details = new HashMap<>();
        event2Details.put("Type", "Wedding");
        event2Details.put("Date", "2026-12-15");
        event2Details.put("Location", "Canada Hotel");
        event2Details.put("Budget", 12000.0f);
        events.put("Tola's Wedding", event2Details);
        
        int choice;
        
        // Start of the persistent menu loop
        do {
            // Display menu options to the user
            System.out.println("\n======================================");    
            System.out.println("Welcome to Event Management System");
            System.out.println("1. Create New Event");
            System.out.println("2. View Event Details");
            System.out.println("3. Manage Expenses");
            System.out.println("4. Record a Payment");
            System.out.println("5. View Budget Summary");
            System.out.println("6. Save and Exit");
            System.out.println("Select a number from the menu listed above:");
            
            // Read user's input from the menu
            choice = scanner.nextInt();
            scanner.nextLine();
            
            // Option 1: Create a New Event
            if (choice == 1) {
                System.out.println("\n========= Create New Event ==========");
                System.out.println("Enter event name:"); 
                String name = scanner.nextLine();
                
                System.out.println("Enter event type:");
                String type = scanner.nextLine();
                
                System.out.println("Enter event date:");
                String date = scanner.nextLine();
                
                System.out.println("Enter event location:");
                String location = scanner.nextLine();
                
                System.out.println("Enter event budget ($):");
                Float budget = scanner.nextFloat();
                scanner.nextLine(); 
                
                // Create an inner HashMap to hold the specific event's details
                HashMap<String, Object> eventDetails = new HashMap<>();
                eventDetails.put("Date", date);
                eventDetails.put("Type", type);
                eventDetails.put("Location", location);
                eventDetails.put("Budget", budget);
                
                // Put the entire event into the main event map
                events.put(name, eventDetails);
                System.out.println("-> Event created successfully!");
                
            // Option 2: View Event Details using Numbered Selection
            } else if (choice == 2) {
                System.out.println("\n========= View Event Details ==========");
                
                // Check if the events map is empty
                if (events.isEmpty()) {
                    System.out.println("No event available");
                } else {
                    System.out.println("See available events below:");

                    // Convert the hashmap keys into a list for index numbering
                    List<String> keys = new ArrayList<>(events.keySet());
                
                    // Print the event list with numbers    
                    for (int i = 0; i < keys.size(); i++) {
                        System.out.println((i + 1) + ". " + keys.get(i));
                    }
                     
                    System.out.println("Enter the number of the event you want to view:");
                    int selectedEvent = scanner.nextInt(); 
                    scanner.nextLine(); 
                    
                    // Check if the selected number is within valid range
                    if (selectedEvent >= 1 && selectedEvent <= keys.size()) {
                        
                        // Map the user's number to the exact String name using index alignment
                        String eventName = keys.get(selectedEvent - 1);
                        
                        // Get the inner hashmap that holds the details of the event selected
                        HashMap<String, Object> details = events.get(eventName);
                        
                        // Print each event detail to the user
                        System.out.println("\n--- Details for " + eventName + " ---");
                        System.out.println("Type: " + details.get("Type"));
                        System.out.println("Date: " + details.get("Date"));
                        System.out.println("Location: " + details.get("Location"));
                        System.out.println("Budget: $" + details.get("Budget"));
                        
                    } else {
                        System.out.println("Event not found. Try again!");
                    }
                }
                
             // Option 3: Manage Expenses with Multi-Entry Loop
            } else if (choice == 3) {    
                System.out.println("\n========= Manage Expenses ==========");

                // Step 1: Check if any events exist in our main map
                if (events.isEmpty()) {
                    System.out.println("No events available to manage expenses.");
                } else {
                    System.out.println("See available events below:");

                    // Step 2: Convert map keys into an ArrayList so we can display them with numbers
                    List<String> keys = new ArrayList<>(events.keySet());
                    for (int i = 0; i < keys.size(); i++) {
                        System.out.println((i + 1) + ". " + keys.get(i));
                    }
                    
                    // Step 3: Ask the user to choose an event by typing its number
                    System.out.println("Enter the number of the event you want to add expenses to:");
                    int selectedEvent = scanner.nextInt();
                    scanner.nextLine(); 
                    
                    // Step 4: Validate that the number entered falls within our list's valid range
                    if (selectedEvent >= 1 && selectedEvent <= keys.size()) {
                        
                        // Map the number back to the event name string using index alignment
                        String eventName = keys.get(selectedEvent - 1);
                        HashMap<String, Object> details = events.get(eventName);
                        
                        // Step 5: Check if this event already has an Expenses storage; if not, create one
                        if (!details.containsKey("Expenses")) {
                            details.put("Expenses", new HashMap<String, Float>());
                        }
                        
                        // Retrieve the inner expense map safely
                        @SuppressWarnings("unchecked")
                        HashMap<String, Float> eventExpenses = (HashMap<String, Float>) details.get("Expenses");
                        
                        // Step 6: Use a continuous while loop to allow user to enter multiple expenses
                        boolean addingExpenses = true;
                        while (addingExpenses) {
                            System.out.println("Enter expense name (or type 'done' to finish):");
                            String expenseName = scanner.nextLine();
                            
                            // Exit the loop if the user types 'done' (ignoring case)
                            if (expenseName.equalsIgnoreCase("done")) {
                                addingExpenses = false;
                                break;
                            }
                            
                            System.out.println("Enter expense amount ($):");
                            float expenseAmount = scanner.nextFloat();
                            scanner.nextLine();
                            
                            // Store the expense name and amount into the event's expense map
                            eventExpenses.put(expenseName, expenseAmount);
                            System.out.println("-> Expense recorded successfully!\n");
                        }
                        
                        System.out.println("Finished managing expenses for " + eventName + ".");
                        
                    } else {
                        System.out.println("Invalid event number. Try again!");
                    }
                    
                }
    
             // Option 4: Record a Payment with Multi-Entry Loop
            } else if (choice == 4) {
                System.out.println("\n========= Record a Payment ==========");

                // Step 1: Check if any events exist in our main map
                if (events.isEmpty()) {
                    System.out.println("No events available to record payments.");
                } else {
                    System.out.println("See available events below:");

                    // Step 2: Convert map keys into an ArrayList so we can display them with numbers
                    List<String> keys = new ArrayList<>(events.keySet());
                    for (int i = 0; i < keys.size(); i++) {
                        System.out.println((i + 1) + ". " + keys.get(i));
                    }
                    
                    // Step 3: Ask the user to choose an event by typing its number
                    System.out.println("Enter the number of the event you want to add payments to:");
                    int selectedEvent = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character left in the scanner buffer
                    
                    // Step 4: Validate that the number entered falls within our list's valid range
                    if (selectedEvent >= 1 && selectedEvent <= keys.size()) {
                        
                        // Map the number back to the event name string using index alignment (- 1)
                        String eventName = keys.get(selectedEvent - 1);
                        HashMap<String, Object> details = events.get(eventName);
                        
                        // Step 5: Check if this event already has a "Payments" bucket; if not, create one
                        if (!details.containsKey("Payments")) {
                            details.put("Payments", new HashMap<String, Float>());
                        }
                        
                        // Retrieve the inner payments map safely
                        @SuppressWarnings("unchecked")
                        HashMap<String, Float> eventPayments = (HashMap<String, Float>) details.get("Payments");
                        
                        // Step 6: Open a continuous while loop to allow logging multiple payments
                        boolean addingPayments = true;
                        while (addingPayments) {
                            System.out.println("Enter payment description (or type 'done' to finish):");
                            String paymentSource = scanner.nextLine();
                            
                            // Exit the loop if the user types 'done' (ignoring case)
                            if (paymentSource.equalsIgnoreCase("done")) {
                                addingPayments = false;
                                break;
                            }
                            
                            System.out.println("Enter payment amount ($):");
                            float paymentAmount = scanner.nextFloat();
                            scanner.nextLine(); 
                            
                            // Store the payment source and amount into the event's payment map
                            eventPayments.put(paymentSource, paymentAmount);
                            System.out.println("-> Payment recorded successfully!\n");
                        }
                        
                        System.out.println("Finished recording payments for " + eventName + ".");
                        
                    } else {
                        System.out.println("Invalid event number. Try again!");
                    }
                }
               
             // Option 5: View Budget Summary with Explicit Calculation Steps
            } else if (choice == 5) {
                System.out.println("\n========= View Budget Summary ==========");

                // Step 1: Check if any events exist in our main map
                if (events.isEmpty()) {
                    System.out.println("No events available to view budget summary.");
                } else {
                    System.out.println("See available events below:");

                    // Step 2: Convert map keys into an ArrayList for numbered selection
                    List<String> keys = new ArrayList<>(events.keySet());
                    for (int i = 0; i < keys.size(); i++) {
                        System.out.println((i + 1) + ". " + keys.get(i));
                    }
                    
                    // Step 3: Ask the user to choose an event number
                    System.out.println("Enter the number of the event you want to view the summary for:");
                    int selectedEvent = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    
                    // Step 4: Validate the selected number range
                    if (selectedEvent >= 1 && selectedEvent <= keys.size()) {
                        
                        // Map the number back to the event name string using index alignment (- 1)
                        String eventName = keys.get(selectedEvent - 1);
                        HashMap<String, Object> details = events.get(eventName);
                        
                        // Step 5: Extract the Initial Budget
                        float initialBudget = (float) details.get("Budget");
                        
                        // Print out the header for the financial report
                        System.out.println("\n========================================");
                        System.out.println("          BUDGET SUMMARY: " + eventName);
                        System.out.println("========================================");
                        System.out.println("Initial Budget: $" + initialBudget);
                        
                        // Step 6: Process and Display Expenses Breakdown
                        float totalExpenses = 0.0f;
                        System.out.println("\n--- Expenses Breakdown ---");
                        if (details.containsKey("Expenses")) {
                            @SuppressWarnings("unchecked")
                            HashMap<String, Float> eventExpenses = (HashMap<String, Float>) details.get("Expenses");
                            
                            if (eventExpenses.isEmpty()) {
                                System.out.println("No expenses recorded yet.");
                            } else {
                                for (Entry<String, Float> entry : eventExpenses.entrySet()) {
                                    System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
                                    totalExpenses += entry.getValue();
                                }
                            }
                        } else {
                            System.out.println("No expenses recorded yet.");
                        }
                        System.out.println("Total Expenses: $" + totalExpenses);
                        
                        // Step 7: Calculate and Display Remaining Budget with Formula
                        float remainingBudget = initialBudget - totalExpenses;
                        System.out.println("\n----------------------------------------");
                        System.out.println("Calculation: Initial ($" + initialBudget + ") - Expenses ($" + totalExpenses + ")");
                        System.out.println("Remaining Budget: $" + remainingBudget);
                        
                        // Step 8: Process and Display Payments Breakdown
                        float totalPayments = 0.0f;
                        System.out.println("\n--- Payments Breakdown ---");
                        if (details.containsKey("Payments")) {
                            @SuppressWarnings("unchecked")
                            HashMap<String, Float> eventPayments = (HashMap<String, Float>) details.get("Payments");
                            
                            if (eventPayments.isEmpty()) {
                                System.out.println("No payments recorded yet.");
                            } else {
                                for (Entry<String, Float> entry : eventPayments.entrySet()) {
                                    System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
                                    totalPayments += entry.getValue();
                                }
                            }
                        } else {
                            System.out.println("No payments recorded yet.");
                        }
                        System.out.println("Total Payments Received: $" + totalPayments);
                        
                        // Step 9: Calculate and Display Net Balance with Formula
                        float netBalance = remainingBudget + totalPayments;
                        System.out.println("----------------------------------------");
                        System.out.println("Calculation: Remaining ($" + remainingBudget + ") + Payments ($" + totalPayments + ")");
                        System.out.println("Final Net Balance: $" + netBalance);
                        System.out.println("========================================");
                        
                    } else {
                        System.out.println("Invalid event number. Try again!");
                    }
                }
                
            // Option 6: Save and Exit
            } else if (choice == 6) {
                System.out.println("\nThank you for using the Event Management System. Now saving and exiting application. Goodbye!");
                
            } else {
                System.out.println("Invalid choice. Try again!");
            }
            
        } while (choice != 6); // End the loop when choice is 6
        
        scanner.close();
    }
}