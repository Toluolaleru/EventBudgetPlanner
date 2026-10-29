package service;

import exception.EventNotFoundException;
import model.Event;
import model.ExpenseRecord;
import model.PaymentRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class managing the core business logic for events, 
 * includes creation, find, dynamic financial logging, and deletion.
 */
public class EventManager {
    private List<Event> events;

    // Initializes an empty EventManager service.
    public EventManager() {
        this.events = new ArrayList<>();
    }

    // Adds a new event to the managed event list.
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Retrieves all events currently stored in the system.
     * 
     * @return a List of all events
     */
    public List<Event> getAllEvents() {
        return events;
    }

    /**
     * Searches for an event by name.
     * 
     * @param name the name of the event to look up
     * @return the matching Event object
     * @throws EventNotFoundException if no event matching the given name exists
     */
    public Event findEventByName(String name) throws EventNotFoundException {
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(name.trim())) {
                return event;
            }
        }
        throw new EventNotFoundException("Event with name '" + name + "' was not found.");
    }

    /**
     * Records a new expense against a specific event.
     */
    public void addExpenseToEvent(String eventName, ExpenseRecord expense) throws EventNotFoundException {
        Event event = findEventByName(eventName);
        event.addExpense(expense);
    }

    /**
     * Records a new payment against a specific event.
     */
    public void addPaymentToEvent(String eventName, PaymentRecord payment) throws EventNotFoundException {
        Event event = findEventByName(eventName);
        event.addPayment(payment);
    }

    /**
     * Removes an event from the system by its name.
     */
    public boolean deleteEvent(String eventName) throws EventNotFoundException {
        Event event = findEventByName(eventName);
        return events.remove(event);
    }
}