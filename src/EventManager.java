import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class EventManager {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		//initialize this as a hashmap
		HashMap<String, HashMap > event = new HashMap<>();
		
		// Display menu options to the user
		System.out.println ("Welcome to Event Management System");
		System.out.println ("1. Create New Event ");
		System.out.println ("2. View Event Details ");
		System.out.println ("3. Manage Expenses ");
		System.out.println ("4. Record a Payment ");
		System.out.println ("5. View Budget Summary ");
		System.out.println ("6. Save and Exit ");
		System.out.println ("Select from the menu listed above:");
		
		//Read user's inputs from the menu
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		if (choice == 1) {
			System.out.println("Fill in the fields below to create a new event:");
			
			System.out.println("Enter event name:"); 
			String name = scanner.nextLine(); // Get what the user types.
			
			System.out.println("Enter event type:");
			String type = scanner.nextLine();
			
			System.out.println("Enter event date:");
			String date = scanner.nextLine();
			
			System.out.println("Enter event location:");
			String location = scanner.nextLine();
			
			System.out.println("Enter event budget:");
			Float budget = scanner.nextFloat();
			
			//create an inner hashmap to hold the specific event's details
			HashMap eventDetails = new HashMap<>();
			eventDetails.put("Date", date);
			eventDetails.put("Type", type);
			eventDetails.put("Location", location);
			eventDetails.put("Budget", budget);
			
			//put the entire event into the main event map
			event.put(name, eventDetails);
			
			System.out.println(event);
			
		} else {
			System.out.println("Invalid choice");
			
		}
		
		scanner.close();

	}

}
