package service;

import exception.EventNotFoundException;
import model.Event;
import model.ExpenseRecord;
import model.PaymentRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class managing the core business logic for events, 
 * including creation, lookup, dynamic financial logging, and deletion.
 */
public class EventManager {
    private List<Event> events;

    /**
     * Initializes an empty EventManager service.
     */
    public EventManager() {
        this.events = new ArrayList<>();
    }

    /**
     * Adds a new event to the managed event list.
     * 
     * @param event the Event object to store
     */
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
     * Searches for an event by name (case-insensitive).
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
     * 
     * @param eventName the name of the target event
     * @param expense the ExpenseRecord to attach
     * @throws EventNotFoundException if the event does not exist
     */
    public void addExpenseToEvent(String eventName, ExpenseRecord expense) throws EventNotFoundException {
        Event event = findEventByName(eventName);
        event.addExpense(expense);
    }

    /**
     * Records a new payment against a specific event.
     * 
     * @param eventName the name of the target event
     * @param payment the PaymentRecord to attach
     * @throws EventNotFoundException if the event does not exist
     */
    public void addPaymentToEvent(String eventName, PaymentRecord payment) throws EventNotFoundException {
        Event event = findEventByName(eventName);
        event.addPayment(payment);
    }

    /**
     * Removes an event from the system by its name.
     * 
     * @param eventName the name of the event to delete
     * @return true if successfully removed
     * @throws EventNotFoundException if the target event is not found
     */
    public boolean deleteEvent(String eventName) throws EventNotFoundException {
        Event event = findEventByName(eventName);
        return events.remove(event);
    }
}