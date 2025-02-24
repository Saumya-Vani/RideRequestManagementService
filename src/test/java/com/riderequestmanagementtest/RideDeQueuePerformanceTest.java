package com.riderequestmanagementtest;

import org.junit.jupiter.api.Test;

import com.riderequestmanagement.RideDeQueue;
import com.riderequestmanagement.RideRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RideDeQueuePerformanceTest {

    private static final String CSV_FILE = "src/main/resources/ride_requests_large.csv";

    @Test
    void testRideDeQueuePerformance() {
        RideDeQueue<RideRequest> rideQueue = new RideDeQueue<>(100000); 
        int rideCount = 0;

        System.out.println("Starting RideDeQueue Performance Test...");

        long startTime = System.nanoTime();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            boolean firstLine = true; 
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                if (values.length < 3) continue;

                RideRequest ride = new RideRequest(values[0].trim(), values[1].trim(), values[2].trim());
                rideQueue.enqueue(ride);
                rideCount++;
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            fail("Failed to read CSV");
        }

        long loadTime = System.nanoTime();
        while (!rideQueue.isEmpty()) {
            rideQueue.dequeue();
        }

        long endTime = System.nanoTime();
        System.out.println("Total Rides Loaded: " + rideCount);
        System.out.println("RideDeQueue Load Time: " + (loadTime - startTime) / 1_000_000 + " ms");
        System.out.println("RideDeQueue Processing Time: " + (endTime - loadTime) / 1_000_000 + " ms");
        System.out.println("RideDeQueue Total Execution Time: " + (endTime - startTime) / 1_000_000 + " ms");

        assertTrue(rideQueue.isEmpty());
    }
}
