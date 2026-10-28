import java.util.HashMap;

    // The Event class acts as our Model. 
public class Event {
    
    private String name;
    private String type;
    private String date;
    private String location;
    private float budget;
    
    // Created a separate HashMap collections to store financial entries securely for this specific event
    private HashMap<String, Float> expenses;
    private HashMap<String, Float> payments;

    // Constructor: This will run automatically when we create a new Event object.
    public Event(String name, String type, String date, String location, float budget) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.location = location;
        this.budget = budget;
        
        // Initialize empty maps for expenses and payments when the event is created
        this.expenses = new HashMap<>();
        this.payments = new HashMap<>();
    }

    // 2. Getters: Safe accessor methods to read private field values from other classes
    public String getName() { return name; }
    public String getType() { return type; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public float getBudget() { return budget; }
    
    public HashMap<String, Float> getExpenses() { return expenses; }
    public HashMap<String, Float> getPayments() { return payments; }

    // 3. Business Logic: Methods that handle data manipulation and calculations
    
    // Adds an expense description and amount to the event's expense map
    public void addExpense(String description, float amount) {
        expenses.put(description, amount);
    }

    // Adds a payment description and amount to the event's payment map
    public void addPayment(String description, float amount) {
        payments.put(description, amount);
    }

    // Loops through all stored expenses and sums them up
    public float getTotalExpenses() {
        float total = 0;
        for (float amount : expenses.values()) {
            total += amount;
        }
        return total;
    }

    // Loops through all stored payments and sums them up
    public float getTotalPayments() {
        float total = 0;
        for (float amount : payments.values()) {
            total += amount;
        }
        return total;
    }

    // Calculates how much budget is left after subtracting total expenses
    public float getRemainingBudget() {
        return budget - getTotalExpenses();
    }

    // Calculates the final finances: remaining budget + any incoming payments
    public float getNetBalance() {
        return getRemainingBudget() + getTotalPayments();
    }
}