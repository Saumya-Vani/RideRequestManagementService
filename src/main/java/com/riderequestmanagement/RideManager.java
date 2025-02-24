package com.riderequestmanagement;

public class RideManager {
    public static void main(String[] args) {
        RideService rideService = new RideService(1000);
        if (args.length > 0) {
            rideService.loadFromCSV(args[0]);
        } else {
            rideService.runInteractive();
        }
    }
}