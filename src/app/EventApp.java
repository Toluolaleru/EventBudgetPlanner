package app;

import exception.EventNotFoundException;
import model.Event;
import model.ExpenseRecord;
import model.PaymentRecord;
import service.EventManager;
import util.Util;

import java.util.List;
import java.util.Scanner;

/**
 * Main application runner for the Event Management System.
 * Provides the interactive console menu loop with enhanced UX and validation.
 */
public class EventApp {	
    public static void main(String[] args) {
    	
        EventManager eventManager = new EventManager();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("==============================================");
        System.out.println("   WELCOME TO EVENT MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        
        while (choice != 0) {
            System.out.println("\n--- Event Management Menu ---");
            System.out.println("1. Add New Event");
            System.out.println("2. View All Events");
            System.out.println("3. Record Expense");
            System.out.println("4. Record Payment");
            System.out.println("5. View Financial & Budget Report");
            System.out.println("6. Delete Event");
            System.out.println("0. Exit");
            
            choice = Util.getInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addNewEvent(eventManager, scanner);
                    break;
                case 2:
                    viewAllEvents(eventManager);
                    break;
                case 3:
                    recordExpense(eventManager, scanner);
                    break;
                case 4:
                    recordPayment(eventManager, scanner);
                    break;
                case 5:
                    viewFinancialReport(eventManager, scanner);
                    break;
                case 6:
                    deleteEvent(eventManager, scanner);
                    break;
                case 0:
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 6.");
            }
        }
        Util.closeScanner();
    }

    private static void addNewEvent(EventManager manager, Scanner scanner) {
        System.out.println("\n--- Add New Event ---");
        String name = Util.getString("Enter Event Name: ");
        String date = Util.getString("Enter Event Date (DD/MM/YYYY): ");
        String location = Util.getString("Enter Location: ");
        String category = Util.getString("Enter Category: ");
        double budget = Util.getDouble("Enter Allocated Budget ($): ");

        Event newEvent = new Event(name, date, location, category, budget);
        manager.addEvent(newEvent);
        System.out.println("-> Success! Event '" + name + "' has been created.");
    }

    private static void viewAllEvents(EventManager manager) {
        System.out.println("\n--- See All Available Events ---");
        List<Event> events = manager.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("-> No events found in the system. Please create an event");
            return;
        }
        int index = 1;
        for (Event event : events) {
            System.out.println(index + ". Event: " + event.getEventName());
            System.out.println("   Date:     " + event.getEventDate());
            System.out.println("   Location: " + event.getLocation());
            System.out.println("   Category: " + event.getCategory());
            System.out.println("   Budget:   $" + event.getAllocatedBudget());
            System.out.println("   ----------------------------------------");
            index++;
        }
    }

    private static Event selectEventByIndex(EventManager manager, Scanner scanner) {
        List<Event> events = manager.getAllEvents();
        if (events.isEmpty()) {
            System.out.println("-> No events available in the system. Please create an event");
            return null;
        }

        System.out.println("\nSelect an Event:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).getEventName());
        }

        int selection = Util.getInt("Enter event number: ");
        if (selection < 1 || selection > events.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        return events.get(selection - 1);
    }

    private static void recordExpense(EventManager manager, Scanner scanner) {
        System.out.println("\n--- Record Expense ---");
        Event selectedEvent = selectEventByIndex(manager, scanner);
        if (selectedEvent == null) return;

        System.out.println("Adding expenses to: " + selectedEvent.getEventName());
        System.out.println("Type 'done' for the description when you are finished adding expenses.");

        while (true) {
            System.out.print("\nEnter Expense Description (or type 'done' to finish): ");
            String desc = scanner.nextLine().trim();
            if (desc.equalsIgnoreCase("done")) {
                break;
            }
            if (desc.isEmpty()) {
                System.out.println("Description cannot be empty.");
                continue;
            }

            double amount = Util.getDouble("Enter Expense Amount ($): ");

            try {
                manager.addExpenseToEvent(selectedEvent.getEventName(), new ExpenseRecord(desc, amount));
                System.out.println("-> Success! Expense recorded.");
            } catch (EventNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
    }

    private static void recordPayment(EventManager manager, Scanner scanner) {
        System.out.println("\n--- Record Payment ---");
        Event selectedEvent = selectEventByIndex(manager, scanner);
        if (selectedEvent == null) return;

        System.out.println("Adding payments to: " + selectedEvent.getEventName());
        System.out.println("Type 'done' for the description when you are finished adding payments.");

        while (true) {
            System.out.print("\nEnter Payment Description (or type 'done' to finish): ");
            String desc = scanner.nextLine().trim();
            if (desc.equalsIgnoreCase("done")) {
                break;
            }
            if (desc.isEmpty()) {
                System.out.println("Description cannot be empty.");
                continue;
            }

            double amount = Util.getDouble("Enter Payment Amount ($): ");

            try {
                manager.addPaymentToEvent(selectedEvent.getEventName(), new PaymentRecord(desc, amount));
                System.out.println("-> Success! Payment recorded.");
            } catch (EventNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
    }

    private static void viewFinancialReport(EventManager manager, Scanner scanner) {
        System.out.println("\n--- Financial & Budget Report ---");
        Event selectedEvent = selectEventByIndex(manager, scanner);
        if (selectedEvent == null) return;

        double budget = selectedEvent.getAllocatedBudget();
        double totalExp = selectedEvent.getTotalExpenses();
        double totalPay = selectedEvent.getTotalPayments();
        double remainingBudget = budget - totalExp;
        double netBalance = budget + totalPay - totalExp;
        
  
        System.out.println("\n========================================");
        System.out.println("REPORT FOR: " + selectedEvent.getEventName());
        System.out.println("Date: " + selectedEvent.getEventDate() + " | Location: " + selectedEvent.getLocation());
        System.out.println("----------------------------------------");
        System.out.println("Allocated Budget: $" + budget);
        System.out.println("Total Expenses: $" + totalExp);
        System.out.println("Remaining Budget: $" + remainingBudget);
        
     // --- ADD BUDGET ALERT MESSAGE HERE ---
        if (remainingBudget < 0) {
            System.out.printf("⚠️ Budget Alert: Over budget by $%.2f!\n", Math.abs(remainingBudget));// absolute value
        } else {
            System.out.printf("✅ Budget Status: You have $%.2f remaining.\n", remainingBudget);
        }
        
        System.out.println("----------------------------------------");
        System.out.println("Total Payments/Funding: $" + totalPay);
        System.out.println("Net Balance / Remaining: $" + netBalance);
        
     // --- ADD NET BALANCE / DEFICIT ALERT MESSAGE HERE ---
        if (netBalance < 0) {
            System.out.printf("⚠️ Deficit Alert: Net loss of $%.2f.\n", Math.abs(netBalance));
        } else {
            System.out.printf("✅ Net Balance Status: Positive balance of $%.2f.\n", netBalance);
        }
        
        System.out.println("----------------------------------------");
        System.out.println("Expense Breakdown:");
        if (selectedEvent.getExpenses().isEmpty()) {
            System.out.println("  (No expenses recorded)");
        } else {
            for (ExpenseRecord exp : selectedEvent.getExpenses()) {
                System.out.println("  - " + exp);
            }
        }
        System.out.println("Payment Breakdown:");
        if (selectedEvent.getPayments().isEmpty()) {
            System.out.println("  (No payments recorded)");
        } else {
            for (PaymentRecord pay : selectedEvent.getPayments()) {
                System.out.println("  - " + pay);
            }
        }
        System.out.println("========================================");
    }

    private static void deleteEvent(EventManager manager, Scanner scanner) {
        System.out.println("\n--- Delete Event ---");
        Event selectedEvent = selectEventByIndex(manager, scanner);
        if (selectedEvent == null) return;

        try {
            manager.deleteEvent(selectedEvent.getEventName());
            System.out.println("Success! Event '" + selectedEvent.getEventName() + "' has been deleted.");
        } catch (EventNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}