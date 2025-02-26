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
│   │   ├── RideRequest.java        # Core Entity class 
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
[2] Process a Ride Request
[3] Search for a Ride
[4] Show Current Status of Data Structures
[0] Exit the Program
Choose an option: 1
Enter CSV File Path: src/main/resources/ride_requests.csv
Successfully loaded ride requests from CSV.

Choose an option: 2
Processing (Circular queue) Ride from  Circular Queue: [31b9620b-4621-411a-9c67-57b6c7743944, Alice, New York -> Chicago]
Processing (Remove) Ride from List: [31b9620b-4621-411a-9c67-57b6c7743944, Alice, New York -> Chicago]
Processing (Queue) Ride from Queue: [31b9620b-4621-411a-9c67-57b6c7743944, Alice, New York -> Chicago]

Choose an option: 3
Enter Passenger Name: Frank
Enter Pickup Location: Austin
Enter Destination: Denver
Search Success in Circular Queue: [ef493a4c-0b1c-4df3-be03-58e05ec843ef, Frank, Austin -> Denver] found at index 5
Search Success in List: [ef493a4c-0b1c-4df3-be03-58e05ec843ef, Frank, Austin -> Denver] found at index 4
Search Success in Queue: [ef493a4c-0b1c-4df3-be03-58e05ec843ef, Frank, Austin -> Denver] found at index 5
Ride Found in DeQue: [d2fab583-4911-4464-b07d-35abb3c4d905, Frank, Austin -> Denver]
Ride Found in List: [d2fab583-4911-4464-b07d-35abb3c4d905, Frank, Austin -> Denver]
Ride Found in Queue: [d2fab583-4911-4464-b07d-35abb3c4d905, Frank, Austin -> Denver]

Choose an option: 4

Ride Circular Queue:
Front -> [[06c31b06-86cf-4b6c-a89a-5e3e3134d41b, Bob, Los Angeles -> San Francisco]] -> [[b0b00570-7e6f-4f48-a7ce-387ff4ea2f29, Charlie, Houston -> Miami]] -> [[9aee17f9-f196-4ee2-89c7-cc492af64791, David, Seattle -> Boston]] -> [[57db7750-0dc6-422d-b563-0e9e5ef39124, Eve, Dallas -> Las Vegas]] -> [[d2fab583-4911-4464-b07d-35abb3c4d905, Frank, Austin -> Denver]] -> [[93cffd32-bdd9-425d-ad65-671bd16539a8, Grace, San Diego -> Phoenix]] <- Rear (size=6)

Ride List:
1. [06c31b06-86cf-4b6c-a89a-5e3e3134d41b, Bob, Los Angeles -> San Francisco]
2. [b0b00570-7e6f-4f48-a7ce-387ff4ea2f29, Charlie, Houston -> Miami]
3. [9aee17f9-f196-4ee2-89c7-cc492af64791, David, Seattle -> Boston]
4. [57db7750-0dc6-422d-b563-0e9e5ef39124, Eve, Dallas -> Las Vegas]
5. [d2fab583-4911-4464-b07d-35abb3c4d905, Frank, Austin -> Denver]
6. [93cffd32-bdd9-425d-ad65-671bd16539a8, Grace, San Diego -> Phoenix]


Ride Queue:
Front -> [[06c31b06-86cf-4b6c-a89a-5e3e3134d41b, Bob, Los Angeles -> San Francisco]] -> [[b0b00570-7e6f-4f48-a7ce-387ff4ea2f29, Charlie, Houston -> Miami]] -> [[9aee17f9-f196-4ee2-89c7-cc492af64791, David, Seattle -> Boston]] -> [[57db7750-0dc6-422d-b563-0e9e5ef39124, Eve, Dallas -> Las Vegas]] -> [[d2fab583-4911-4464-b07d-35abb3c4d905, Frank, Austin -> Denver]] -> [[93cffd32-bdd9-425d-ad65-671bd16539a8, Grace, San Diego -> Phoenix]] <- Rear (size=6)

Choose an option: 0
Exiting Ride Manager...
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
