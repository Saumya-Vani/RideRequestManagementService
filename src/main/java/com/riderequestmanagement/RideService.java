package com.riderequestmanagement;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class RideService {
    // Circular queue for handling ride requests efficiently
    private RideCircularQueue<RideRequest> rideCircularQueue;
    
    // List for managing ride requests dynamically
    private RideList<RideRequest> rideList;
    
    // Standard queue for processing ride requests in FIFO order
    private RideQueue<RideRequest> rideQueue;

    /**
     * Constructor to initialize RideService with a given capacity.
     * @param capacity The maximum capacity for all data structures.
     */
    public RideService(int capacity) {
        rideCircularQueue = new RideCircularQueue<>(capacity);
        rideList = new RideList<>(capacity);
        rideQueue = new RideQueue<>(capacity);
    }

    /**
     * Loads ride requests from a CSV file and adds them to all data structures.
     * @param filePath The path of the CSV file containing ride requests.
     */
    public void loadFromCSV(String filePath) {
        File file = new File(filePath);
        
        // Check if the file exists
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            
            // Read each line from the file
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header line
                    continue;
                }
                if (line.trim().isEmpty()) continue; // Ignore empty lines

                String[] values = line.split(",");
                if (values.length < 3) continue; // Ensure valid entries

                // Create a new RideRequest object and add it to all data structures
                RideRequest ride = new RideRequest(values[0].trim(), values[1].trim(), values[2].trim());
                rideCircularQueue.enqueue(ride);
                rideList.add(ride);
                rideQueue.enqueue(ride);
            }
            System.out.println("Successfully loaded ride requests from CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes a ride request by removing the next available ride from each data structure.
     */
    public void processRide() {
        // Process ride from Circular Queue
        if (!rideCircularQueue.isEmpty()) {
            System.out.println("Processing (Circular queue) Ride from Circular Queue: " + rideCircularQueue.dequeue());
        } else {
            System.out.println("No rides in circular queue to process.");
        }

        // Process ride from List
        if (!rideList.isEmpty()) {
            System.out.println("Processing (Remove) Ride from List: " + rideList.remove(0));
        } else {
            System.out.println("No rides in list to remove.");
        }

        // Process ride from Standard Queue
        if (!rideQueue.isEmpty()) {
            System.out.println("Processing (Queue) Ride from Queue: " + rideQueue.dequeue());
        } else {
            System.out.println("No rides in queue to remove.");
        }
    }

    /**
     * Searches for a ride request in all data structures.
     * @param passenger The passenger's name.
     * @param pickup The pickup location.
     * @param destination The destination location.
     */
    public void searchRide(String passenger, String pickup, String destination) {
        // Create a RideRequest object to search for
        RideRequest searchRide = new RideRequest(passenger, pickup, destination);
        
        // Search in Circular Queue
        Optional<RideRequest> circQueSearch = rideCircularQueue.search(searchRide);
        
        // Search in List
        Optional<RideRequest> listSearch = rideList.search(searchRide);
        
        // Search in Standard Queue
        Optional<RideRequest> queueSearch = rideQueue.search(searchRide);

        // Display search results
        System.out.println(circQueSearch.isPresent() ? "Ride Found in Circular Queue: " + circQueSearch.get() : "Ride Not Found in Circular Queue");
        System.out.println(listSearch.isPresent() ? "Ride Found in List: " + listSearch.get() : "Ride Not Found in List");
        System.out.println(queueSearch.isPresent() ? "Ride Found in Queue: " + queueSearch.get() : "Ride Not Found in Queue");
    }

    /**
     * Displays the current status of all data structures.
     */
    public void showStatus() {
        System.out.println("\nRide Circular Queue:");
        System.out.println(rideCircularQueue);
        
        System.out.println("\nRide List:");
        System.out.println(rideList);
        
        System.out.println("\nRide Queue:");
        System.out.println(rideQueue);
    }

    /**
     * Runs an interactive menu to manage ride requests.
     */
    public void runInteractive() {
        Scanner sc = new Scanner(System.in);

        // Loop to continuously display the menu
        while (true) {
            System.out.println("\n--- Ride Manager Menu ---");
            System.out.println("[1] Load Rides from CSV");
            System.out.println("[2] Process a Ride Request");
            System.out.println("[3] Search for a Ride");
            System.out.println("[4] Show Current Status of Data Structures");
            System.out.println("[0] Exit the Program");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter CSV File Path: ");
                    String path = sc.nextLine();
                    loadFromCSV(path);
                    break;
                case 2:
                    processRide();
                    break;
                case 3:
                    System.out.print("Enter Passenger Name: ");
                    String passenger = sc.nextLine();
                    System.out.print("Enter Pickup Location: ");
                    String pickup = sc.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();
                    searchRide(passenger, pickup, destination);
                    break;
                case 4:
                    showStatus();
                    break;
                case 0:
                    sc.close(); // Close scanner before exiting
                    System.out.println("Exiting Ride Manager...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
