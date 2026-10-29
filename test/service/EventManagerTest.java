package service; 

import model.Event;
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
}