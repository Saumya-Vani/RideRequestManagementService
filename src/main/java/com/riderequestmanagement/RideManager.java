package com.riderequestmanagement;

public class RideManager {
    /**
     * Main method to run the RideManager program.S
     * @param args Optional command-line argument for loading rides from a CSV file.
     */
    public static void main(String[] args) {
        RideService rideService = new RideService(7); // Initialize RideService with a capacity of 7
        
        // Check if a CSV file path is provided as a command-line argument
        if (args.length > 0) {
            rideService.loadFromCSV(args[0]); // Load ride requests from the provided CSV file
        } else {
            rideService.runInteractive(); // Run the interactive mode if no file is provided
        }
    }
}
