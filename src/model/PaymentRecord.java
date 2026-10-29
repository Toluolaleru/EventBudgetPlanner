package model;

/**
 * Represents an individual payment record contributed toward an event,
 * capturing the payment description and the amount.
 */
public class PaymentRecord {
    private String description;
    private double amount;

    // Constructs a new PaymentRecord with the specified description and amount.
    
    public PaymentRecord(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment: " + description + " | Amount: $" + amount;
    }
}