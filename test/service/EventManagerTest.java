package service; 

import model.Event;
import model.ExpenseRecord;
import model.PaymentRecord;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventManagerTest {

    @Test
    public void testAddEvent() {
        // 1. Arrange: Create manager and event
        EventManager manager = new EventManager();
        Event event = new Event("Design Workshop", "2026-11-10", "Toronto", "Educational", 500.0);
        
        // 2. Act: Add the event
        manager.addEvent(event);
        
        // 3. Assert: Verify list size is 1
        assertEquals(1, manager.getAllEvents().size());
    }

    @Test
    public void testFindEventByName() throws Exception {
        // 1. Arrange: Create manager and add an event
        EventManager manager = new EventManager();
        Event event = new Event("Tech Summit", "2026-12-05", "Toronto", "Conference", 1500.0);
        manager.addEvent(event);
        
        // 2. Act: Search for the event by name
        Event found = manager.findEventByName("Tech Summit");
        
        // 3. Assert: Verify it was found successfully
        assertNotNull(found);
        assertEquals("Tech Summit", found.getEventName());
    }
    
    @Test
    public void testFindEventNotFound() {
        // 1. Arrange: Create an empty manager
        EventManager manager = new EventManager();
        
        // 2. Act & Assert: Searching for a non-existent event should throw EventNotFoundException
        assertThrows(exception.EventNotFoundException.class, () -> {
            manager.findEventByName("NonExistent Event");
        });
    }
    
    @Test
    public void testAddExpenseToEvent() throws Exception {
        // 1. Arrange: Create manager, event, and an expense record
        EventManager manager = new EventManager();
        Event event = new Event("Tech Summit", "2026-12-05", "Toronto", "Conference", 1500.0);
        manager.addEvent(event);
        
        ExpenseRecord expense = new ExpenseRecord("Venue Rental", 500.0);
        
        // 2. Act: Add expense to the event through the manager
        manager.addExpenseToEvent("Tech Summit", expense);
        
        // 3. Assert: Verify the expense was recorded in the event
        assertEquals(1, event.getExpenses().size());
    }
    
    @Test
    public void testAddPaymentToEvent() throws Exception {
        // 1. Arrange: Create manager, event, and a payment record
        EventManager manager = new EventManager();
        Event event = new Event("Tech Summit", "2026-12-05", "Toronto", "Conference", 1500.0);
        manager.addEvent(event);
        
        PaymentRecord payment = new PaymentRecord("Sponsorship", 1000.0);
        
        // 2. Act: Add payment to the event through the manager
        manager.addPaymentToEvent("Tech Summit", payment);
        
        // 3. Assert: Verify the payment was recorded in the event
        assertEquals(1, event.getPayments().size());
    }
    
    @Test
    public void testAddExpenseEventNotFound() {
        EventManager manager = new EventManager();
        ExpenseRecord expense = new ExpenseRecord("Venue Rental", 500.0);

        assertThrows(exception.EventNotFoundException.class, () -> {
            manager.addExpenseToEvent("Ghost Event", expense);
        });
    }

    @Test
    public void testDeleteEvent() throws Exception {
        // 1. Arrange: Create manager and add an event
        EventManager manager = new EventManager();
        Event event = new Event("Tech Summit", "2026-12-05", "Toronto", "Conference", 1500.0);
        manager.addEvent(event);
        
        // 2. Act: Delete the event
        boolean isDeleted = manager.deleteEvent("Tech Summit");
        
        // 3. Assert: Verify it was removed successfully and list is empty
        assertTrue(isDeleted);
        assertTrue(manager.getAllEvents().isEmpty());
    }
    
}