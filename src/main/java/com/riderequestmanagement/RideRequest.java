package com.riderequestmanagement;

import java.util.*;

public class RideRequest {
    String requestId; // Unique ID for the ride request
    String passengerName; // Name of the passenger
    String pickupLocation; // Pickup location of the ride
    String destination; // Destination location of the ride
    long timestamp; // Timestamp when the request was created

    /**
     * Constructor to create a new RideRequest.
     * @param passengerName The name of the passenger.
     * @param pickupLocation The pickup location of the ride.
     * @param destination The destination of the ride.
     * A unique request ID is generated, and the current timestamp is stored.
     */
    public RideRequest(String passengerName, String pickupLocation, String destination) {
        this.requestId = UUID.randomUUID().toString(); // Generate a unique request ID
        this.passengerName = passengerName;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.timestamp = System.currentTimeMillis(); // Store the current timestamp
    }

    /**
     * Provides a string representation of the RideRequest.
     * @return A formatted string containing request ID, passenger name, pickup location, and destination.
     */
    @Override
    public String toString() {
        return "[" + requestId + ", " + passengerName + ", " + pickupLocation + " -> " + destination + "]";
    }
    
    /**
     * Compares two RideRequest objects for equality.
     * @param obj The object to compare with the current RideRequest.
     * @return true if both objects have the same passenger name, pickup location, and destination; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // If the same instance, return true
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null or different class type

        RideRequest that = (RideRequest) obj;
        return Objects.equals(passengerName, that.passengerName) &&
               Objects.equals(pickupLocation, that.pickupLocation) &&
               Objects.equals(destination, that.destination);
    }

    /**
     * Generates a hash code for the RideRequest object.
     * @return An integer hash code based on passenger name, pickup location, and destination.
     */
    @Override
    public int hashCode() {
        return Objects.hash(passengerName, pickupLocation, destination);
    }
}
