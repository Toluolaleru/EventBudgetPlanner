package exception;

/**
 * Custom exception thrown when an event lookup fails because the specified 
 * event name does not exist in the system.
 */
public class EventNotFoundException extends Exception {
    
    private static final long serialVersionUID = 1L;

	/**
     * Constructs a new EventNotFoundException with the specified detail message.
     */
    public EventNotFoundException(String message) {
        super(message);
    }
}