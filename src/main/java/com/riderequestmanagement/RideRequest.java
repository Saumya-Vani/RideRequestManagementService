package com.riderequestmanagement;

import java.util.*;

public class RideRequest {
    String requestId;
    String passengerName;
    String pickupLocation;
    String destination;
    long timestamp;

    public RideRequest(String passengerName, String pickupLocation, String destination) {
        this.requestId = UUID.randomUUID().toString();
        this.passengerName = passengerName;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "[" + requestId + ", " + passengerName + ", " + pickupLocation + " -> " + destination + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false;

        RideRequest that = (RideRequest) obj;
        return Objects.equals(passengerName, that.passengerName) &&
               Objects.equals(pickupLocation, that.pickupLocation) &&
               Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerName, pickupLocation, destination);
    }

}