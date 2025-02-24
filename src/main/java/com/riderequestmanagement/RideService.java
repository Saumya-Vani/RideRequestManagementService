package com.riderequestmanagement;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class RideService {
    private RideCircularQueue<RideRequest> rideCircularQueue;
    private RideList<RideRequest> rideList;
    private RideQueue<RideRequest> rideQueue;

    public RideService(int capacity) {
        rideCircularQueue = new RideCircularQueue<>(capacity);
        rideList = new RideList<>(capacity);
        rideQueue = new RideQueue<>(capacity);
    }

    public void loadFromCSV(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (line.trim().isEmpty()) continue;
                String[] values = line.split(",");
                if (values.length < 3) continue;
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

    public void processRide() {
        if (!rideCircularQueue.isEmpty()) {
            System.out.println("Processing (Circular queue) Ride from  Circular Queue: " + rideCircularQueue.dequeue());
        } else {
            System.out.println("No rides in circular queue to process.");
        }
        if (!rideList.isEmpty()) {
            System.out.println("Processing (Remove) Ride from List: " + rideList.remove(0));
        } else {
            System.out.println("No rides in list to remove.");
        }
        if (!rideQueue.isEmpty()) {
            System.out.println("Processing (Queue) Ride from Queue: " + rideQueue.dequeue());
        } else {
            System.out.println("No rides in queue to remove.");
        }
    }

    public void searchRide(String passenger, String pickup, String destination) {
        RideRequest searchRide = new RideRequest(passenger, pickup, destination);
        Optional<RideRequest> circQueSearch = rideCircularQueue.search(searchRide);
        Optional<RideRequest> listSearch = rideList.search(searchRide);
        Optional<RideRequest> queueSearch = rideQueue.search(searchRide);
        System.out.println(circQueSearch.isPresent() ? "Ride Found in DeQue: " + circQueSearch.get() : "Ride Not Found in Circular Queue");
        System.out.println(listSearch.isPresent() ? "Ride Found in List: " + listSearch.get() : "Ride Not Found in List");
        System.out.println(queueSearch.isPresent() ? "Ride Found in Queue: " + queueSearch.get() : "Ride Not Found in Queue");
    }

    public void showStatus() {
        System.out.println("\nRide Circular Queue:");
        System.out.println(rideCircularQueue);
        System.out.println("\nRide List:");
        System.out.println(rideList);
        System.out.println("\nRide Queue:");
        System.out.println(rideQueue);
    }

    public void runInteractive() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Ride Manager Menu ---");
            System.out.println("[1] Load Rides from CSV");
            System.out.println("[2] Process Ride");
            System.out.println("[3] Search Ride");
            System.out.println("[4] Show Status");
            System.out.println("[0] Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
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
                    sc.close();
                    System.out.println("Exiting Ride Manager...");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}