import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EventManager {
    
    // Main storage: Maps an event name string directly to its corresponding Event object
    private HashMap<String, Event> events;

    // Constructor: Initializes an empty map of events when the manager is created.  
    public EventManager() {
        this.events = new HashMap<>();
    }

     // Checks if an event with the given name already exists.
    public boolean eventExists(String name) {
        return events.containsKey(name);
    }

     // Adds a new Event to our collection if it doesn't already exist.
    public boolean addEvent(String name, Event event) {
        if (eventExists(name)) {
            return false; // Duplicate name found, cannot add
        }
        events.put(name, event);
        return true;
    }

     // Retrieves a specific Event by its name.
    public Event getEvent(String name) {
        return events.get(name);
    }

    // Checks if our event collection is completely empty.
    public boolean isEmpty() {
        return events.isEmpty();
    }


    // Returns a list of all event names so we can display them with numbers in the menu.
    public List<String> getEventNames() {
        return new ArrayList<>(events.keySet());
    }
}