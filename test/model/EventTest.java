package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testNetBalanceCalculation() {
        // 1. Arrange: Create an event with a budget
        Event event = new Event("Tech Gala", "2026-12-01", "Toronto", "Conference", 1000.0);
        
        // Add an expense and a payment record (using the correct 2-argument constructor)
        event.addExpense(new ExpenseRecord("Venue", 300.0));
        event.addPayment(new PaymentRecord("Sponsorship", 500.0));
        
        // 2. Act: Calculate net balance (1000 + 500 - 300 = 1200)
        double result = event.getNetBalance();
        
        // 3. Assert: Verify the math is correct
        assertEquals(1200.0, result, 0.001);
    }
    
    @Test
    public void testRemainingBudgetWithZeroExpenses() {
        // 1. Arrange: Create an event with a $2000 budget and no expenses
        Event event = new Event("Charity Gala", "2026-12-15", "Toronto", "Non-Profit", 2000.0);
        
        // 2. Act: Get the remaining budget
        double result = event.getRemainingBudget();
        
        // 3. Assert: Remaining budget should equal the full budget
        assertEquals(2000.0, result, 0.001);
    }
}