package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Event entity containing its core details, location, category,
 * allocated budget, and lists of associated expense and payment records.
 */
public class Event {
    private String eventName;
    private String eventDate;
    private String location;
    private String category;
    private double allocatedBudget;
    private List<ExpenseRecord> expenses;
    private List<PaymentRecord> payments;

    // Constructs a new Event with complete details and an allocated budget.

    public Event(String eventName, String eventDate, String location, String category, double allocatedBudget) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.category = category;
        this.allocatedBudget = allocatedBudget;
        this.expenses = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    // --- Getters and Setters ---

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAllocatedBudget() {
        return allocatedBudget;
    }

    public void setAllocatedBudget(double allocatedBudget) {
        this.allocatedBudget = allocatedBudget;
    }

    public List<ExpenseRecord> getExpenses() {
        return expenses;
    }

    public List<PaymentRecord> getPayments() {
        return payments;
    }

    // --- Record Management ---

    /**
     * Adds an expense record to this event's expense list.
     */
    public void addExpense(ExpenseRecord expense) {
        this.expenses.add(expense);
    }

    /**
     * Adds a payment record to this event's payment list.
     */
    public void addPayment(PaymentRecord payment) {
        this.payments.add(payment);
    }

    // --- Financial Calculations ---

    /**
     * Calculates the total sum of all recorded expenses for this event.
     */
    public double getTotalExpenses() {
        double total = 0;
        for (ExpenseRecord e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    /**
     * Calculates the total sum of all recorded payments/funding for this event.
     */
    public double getTotalPayments() {
        double total = 0;
        for (PaymentRecord p : payments) {
            total += p.getAmount();
        }
        return total;
    }
    
    /**
     * Calculates the remaining budget (Allocated Budget minus Total Expenses).
     */
    public double getRemainingBudget() {
        return allocatedBudget - getTotalExpenses();
    }

    /**
     * Calculates the net financial balance (Allocated Budget + Total Payments - Total Expenses).
     */
    public double getNetBalance() {
        return allocatedBudget + getTotalPayments() - getTotalExpenses();
    }
    

    @Override
    public String toString() {
        return "Event: " + eventName + 
               " | Date: " + eventDate + 
               " | Location: " + location + 
               " | Category: " + category + 
               " | Budget: $" + allocatedBudget + 
               " | Total Expenses: $" + getTotalExpenses();
    }
}