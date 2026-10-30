# Event Management System

A console-based Java application built to manage events, track payments, log expenses, and monitor financial health. 

---

## Key Features

- Create, view, and delete events with custom details (Name, Date, Location, Category, and Allocated Budget).
- Seamlessly record and categorize individual expenses and payment sources for any event.
- Automatically calculates remaining budgets and net balances, triggering clear warning alerts if an event goes over budget or operates at a financial deficit.
- JUnit test suite verifying core manager logic, exception handling, and financial calculations.

---

## Technical Architecture & Design

The application follows a clean separation-of-concerns pattern:
* **`model` Package (`Event`, `ExpenseRecord`, `PaymentRecord`): Includes core data attributes and domain logic.
* **`service` Package (`EventManager`): Handles business logic, event collections, and state modifications.
* **`exception` Package (`EventNotFoundException`):Provides custom exception handling for missing records.
* **`app` Package (`App`):** Manages the console UI loop, user inputs and menu navigation.

---

## Tech Stack
* **Language:** Java
* **Testing Framework:** JUnit 6
* **Version Control:** Git & GitHub

---

## Setup & Installation
1. **Clone the repository:**
   ```bash
   git clone <repo-url>

---

## How to Run the Application

1. Locate the main application file in your Package Explorer (app package -> EventApp.java).
2. Right-click EventApp.java and choose Run As → Java Application.
3. Follow the interactive console menu to manage events, record finances, and generate financial reports!
   
---

## Running the Tests
- Unit tests are implemented with JUnit 6 to ensure code stability and accurate financial math
1. In the Package Explorer, right-click the src/test/java folder (or the EventManagerTest or the EventTest class).
2. Choose Run As → JUnit Test.

---

## Notes
- This project stores data exclusively in-memory using Java collections (ArrayList); data is not persisted across application restarts.