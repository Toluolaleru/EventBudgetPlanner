package model;

// this represents an individual expense record associated with an event
// it captures the expense description and amount.

public class ExpenseRecord {
	private String description;
	private double amount;

// constructor: a new ExpenseRecord with the specified description and amount.

	public ExpenseRecord (String description, double amount) {
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
	
	public void setAmount(double Amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Expense: " + description + " | Amount: $" + amount;
	}
}