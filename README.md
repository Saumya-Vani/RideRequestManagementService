# Ride Request Management System

Efficiently manage ride requests using different data structures (LinkedList, Queue, and Circular Queue) and analyze their performance.

## Problem Statement

In a **Ride Request Management System**, passengers request rides between locations. These requests must be efficiently stored, retrieved, and processed. The system evaluates different data structures (**LinkedList, Queue, and Circular Queue**) to determine the best-performing implementation.

## Project Structure

```
RideRequestManagementSystem/
│── src/
│   ├── main/java/com/riderequestmanagement/
│   │   ├── RideList.java           # LinkedList Implementation
│   │   ├── RideQueue.java          # Simple Queue Implementation
│   │   ├── RideCircularQueue.java  # Circular Queue Implementation
│   │   ├── RideManager.java        # Main method for execution
│   │   ├── RideService.java        # Menu-driven service logic
│   └── test/java/com/riderequestmanagement/
│   |   ├── RideListTest.java
│   |   ├── RideQueueTest.java
│   |   ├── RideCircularQueueTest.java
│   └── main/resources/
│   |   ├── DatasetProducer.py
│   |   ├── ride_requests.csv
│   |   ├── ride_requests_medium.csv
│   └── ├── ride_requests_large.csv
│── README.md  # Documentation
│── pom.xml    # Maven Dependencies
```

## Data Structure Implementations

### RideList (LinkedList)
- Implements ride request management using **a LinkedList**.
- **Advantages**: Supports **dynamic memory allocation** and easy insertions/removals.
- **Disadvantages**: Higher **time complexity for searches** compared to Queue.

### RideQueue (Simple Queue - FIFO)
- Implements **a standard queue** where requests are processed **in the order they arrive**.
- **FIFO (First In, First Out)** ensures fairness.

### RideCircularQueue (Efficient Queue)
- Uses a **circular queue** to optimize memory usage.
- Supports **efficient enqueuing and dequeuing** using `modulo (%)` operations.

## RideManager Class (Main Method)

The `RideManager.java` file **orchestrates the program execution**.
- Loads ride requests from **CSV files**.
- **Creates instances of the different data structures**.
- **Processes ride requests** by removing them from the data structures.

## RideService Class (Menu-Driven Execution)

### Features

1. **Load Rides from CSV**  
2. **Process a Ride Request**  
3. **Search for a Ride**  
4. **Show Current Status of Data Structures**  
0. **Exit the Program**  

### Running Example
```
--- Ride Manager Menu ---
[1] Load Rides from CSV
[2] Process Ride
[3] Search Ride
[4] Show Status
[0] Exit
Choose an option: 1

Enter CSV File Name: ride_requests_small.csv
Successfully loaded 500 rides!

Choose an option: 2
Processing ride: Alice | New York -> Chicago

Choose an option: 3
Enter passenger name to search: Alice
Ride found in Queue!

Choose an option: 0
Exiting...
```

## Test Classes

Each data structure has **dedicated JUnit test files** to ensure correctness.

### Test Classes

- `RideListTest.java` → Tests for **LinkedList-based Ride Management**.
- `RideQueueTest.java` → Tests for **Queue-based Ride Management**.
- `RideCircularQueueTest.java` → Tests for **Circular Queue-based Ride Management**.

## Dependencies (Maven)

```xml
<dependencies>
    <!-- JUnit 5 for testing -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.12.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.12.0</version>
    </dependency>

    <!-- OpenCSV for reading ride requests from CSV -->
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.7.1</version>
    </dependency>
</dependencies>
```

## Summary

- Implements LinkedList, Queue, and Circular Queue for ride requests.  
- Loads ride data from CSV for realistic testing.  
- Provides menu-driven interaction for users.  
- Benchmarks performance of different data structures.  
- Unit-tested for correctness.
