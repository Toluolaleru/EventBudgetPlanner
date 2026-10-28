import java.util.Scanner;
import java.util.List;

public class EventApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventManager eventManager = new EventManager();
        boolean running = true;

        while (running) {
            // Display your exact 6-option menu
            System.out.println("\n======================================");    
            System.out.println("Welcome to Event Management System");
            System.out.println("1. Create New Event");
            System.out.println("2. View Event Details");
            System.out.println("3. Manage Expenses");
            System.out.println("4. Record a Payment");
            System.out.println("5. View Budget Summary");
            System.out.println("6. Save and Exit");
            System.out.println("Select a number from the menu listed above:");

            // Safety check for user input
            if (!scanner.hasNextInt()) {
                System.out.println("[Error] Please enter a valid number.");
                scanner.next(); // clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1: // --- CREATE NEW EVENT ---
                	System.out.println("\n========= Create New Event ==========");
                    System.out.println("Enter event name:"); 
                    String name = scanner.nextLine();

                    if (eventManager.eventExists(name)) {
                        System.out.println("[Error] An event with that name already exists!");
                    } else {
                        System.out.println("Enter event type:");
                        String type = scanner.nextLine();
                        
                        System.out.println("Enter event date:");
                        String date = scanner.nextLine();
                        
                        System.out.println("Enter event location:");
                        String location = scanner.nextLine();
                        
                        // Strict validation for Budget (Must be a number and >= 0)
                        float budget = -1;
                        while (true) {
                            System.out.println("Enter event budget ($):");
                            if (scanner.hasNextFloat()) {
                                budget = scanner.nextFloat();
                                scanner.nextLine(); // consume newline
                                
                                if (budget >= 0) {
                                    break;
                                } else {
                                    System.out.println("-> Error! Budget cannot be negative. Please try again:");
                                }
                            } else {
                                System.out.println("-> Error! Please enter a valid number for the budget:");
                                scanner.next();
                            }
                        }

                        // Create the Event object with all attributes and save it
                        Event newEvent = new Event(name, type, date, location, budget);
                        eventManager.addEvent(name, newEvent);
                        System.out.println("-> Event created successfully!");
                    }
                    break;

                case 2: // --- VIEW EVENT DETAILS ---
                    System.out.println("\n========= View Event Details ==========");
                    if (eventManager.isEmpty()) {
                        System.out.println("-> No events found. Please create one first.");
                        break;
                    }

                    List<String> eventNames = eventManager.getEventNames();
                    System.out.println("\nSelect an event:");
                    for (int i = 0; i < eventNames.size(); i++) {
                        System.out.println((i + 1) + ". " + eventNames.get(i));
                    }

                    int selectionIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (selectionIndex >= 0 && selectionIndex < eventNames.size()) {
                        Event selectedEvent = eventManager.getEvent(eventNames.get(selectionIndex));
                        System.out.println("\n--- Details for " + selectedEvent.getName() + " ---");
                        System.out.println("Type: " + selectedEvent.getType());
                        System.out.println("Date: " + selectedEvent.getDate());
                        System.out.println("Location: " + selectedEvent.getLocation());
                        System.out.println("Budget: $" + selectedEvent.getBudget());
                    } else {
                        System.out.println("-> Error! Invalid selection. Try again");
                    }
                    break;

                case 3: // --- MANAGE EXPENSES (Multi-entry loop) ---
                    if (eventManager.isEmpty()) {
                        System.out.println("-> No events found. Please create an event first.");
                        break;
                    }

                    List<String> expenseEventList = eventManager.getEventNames();
                    System.out.println("\n========= Manage Expenses ==========");
                    System.out.println("Select an event to add expenses to:");
                    for (int i = 0; i < expenseEventList.size(); i++) {
                        System.out.println((i + 1) + ". " + expenseEventList.get(i));
                    }

                    int expenseIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (expenseIndex >= 0 && expenseIndex < expenseEventList.size()) {
                        Event selectedEvent = eventManager.getEvent(expenseEventList.get(expenseIndex));
                        
                        boolean addingExpenses = true;
                        while (addingExpenses) {
                            System.out.println("\nEnter expense description (or type 'done' to finish):");
                            String description = scanner.nextLine();
                            
                            if (description.equalsIgnoreCase("done")) {
                                addingExpenses = false;
                                break;
                            }
                            
                            // Strict validation for Expense Amount (Must be a number and >= 0)
                            float amount = -1;
                            while (true) {
                                System.out.println("Enter expense amount ($):");
                                if (scanner.hasNextFloat()) {
                                    amount = scanner.nextFloat();
                                    scanner.nextLine(); // consume newline
                                    
                                    if (amount >= 0) {
                                        break;
                                    } else {
                                        System.out.println("-> Error! Expense amount cannot be negative. Please try again:");
                                    }
                                } else {
                                    System.out.println("-> Please enter a valid number for the amount:");
                                    scanner.next();
                                }
                            }

                            selectedEvent.addExpense(description, amount);
                            System.out.println("-> Expense recorded successfully!\n");
                        }
                        
                        System.out.println("Finished managing expenses for " + selectedEvent.getName() + ".");
                    } else {
                        System.out.println("-> Invalid event number. Try again!");
                    }
                    break;

                case 4: // --- RECORD A PAYMENT (Multi-entry loop) ---
                    if (eventManager.isEmpty()) {
                        System.out.println("->  No events found. Please create an event first.");
                        break;
                    }

                    List<String> paymentEventList = eventManager.getEventNames();
                    System.out.println("\n========= Record a Payment ==========");
                    System.out.println("Select an event to record payments for:");
                    for (int i = 0; i < paymentEventList.size(); i++) {
                        System.out.println((i + 1) + ". " + paymentEventList.get(i));
                    }

                    int paymentIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Clear the Enter key trap

                    if (paymentIndex >= 0 && paymentIndex < paymentEventList.size()) {
                        Event selectedEvent = eventManager.getEvent(paymentEventList.get(paymentIndex));
                        
                        boolean addingPayments = true;
                        while (addingPayments) {
                            System.out.println("\nEnter payment description (or type 'done' to finish):");
                            String description = scanner.nextLine();
                            
                            if (description.equalsIgnoreCase("done")) {
                                addingPayments = false;
                                break;
                            }
                            
                            // Strict validation for Payment Amount (Must be a number and >= 0)
                            float amount = -1;
                            while (true) {
                                System.out.println("Enter payment amount ($):");
                                if (scanner.hasNextFloat()) {
                                    amount = scanner.nextFloat();
                                    scanner.nextLine(); // consume newline
                                    
                                    if (amount >= 0) {
                                        break;
                                    } else {
                                        System.out.println("-> Error! Payment amount cannot be negative. Please try again:");
                                    }
                                } else {
                                    System.out.println("-> Please enter a valid number for the amount:");
                                    scanner.next();
                                }
                            }

                            selectedEvent.addPayment(description, amount);
                            System.out.println("-> Payment recorded successfully!\n");
                        }
                        
                        System.out.println("Finished recording payments for " + selectedEvent.getName() + ".");
                    } else {
                        System.out.println("-> Invalid event number. Try again!");
                    }
                    break;

                case 5: // --- VIEW BUDGET SUMMARY ---
                    if (eventManager.isEmpty()) {
                        System.out.println("-> No events found. Please create an event first.");
                        break;
                    }

                    List<String> summaryEventList = eventManager.getEventNames();
                    System.out.println("\n========= View Budget Summary ==========");
                    System.out.println("Select an event to view its budget summary:");
                    for (int i = 0; i < summaryEventList.size(); i++) {
                        System.out.println((i + 1) + ". " + summaryEventList.get(i));
                    }

                    int summaryIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (summaryIndex >= 0 && summaryIndex < summaryEventList.size()) {
                        Event selectedEvent = eventManager.getEvent(summaryEventList.get(summaryIndex));
                        
                        System.out.println("\n======================================");
                        System.out.println("    BUDGET SUMMARY FOR: " + selectedEvent.getName());
                        System.out.println("======================================");
                        System.out.println("Type: " + selectedEvent.getType());
                        System.out.println("Date: " + selectedEvent.getDate());
                        System.out.println("Location: " + selectedEvent.getLocation());
                        System.out.println("  - Budget: $" + selectedEvent.getBudget());
                        System.out.println("  - Expenses: $" + selectedEvent.getTotalExpenses());
                        System.out.println("  - Payments: $" + selectedEvent.getTotalPayments());
                        System.out.println("  - Remaining Budget: $" + selectedEvent.getRemainingBudget());
                        System.out.println("  - Net Balance: $" + selectedEvent.getNetBalance());
                        System.out.println("======================================");
                    } else {
                        System.out.println("-> Invalid selection.");
                    }
                    break;

                case 6: // --- SAVE AND EXIT ---
                    System.out.println("\nThank you for using Event Management System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("-> Please choose a number between 1 and 6.");
            }
        }
        scanner.close();
    }
}