package com.riderequestmanagementtest;

import org.junit.jupiter.api.Test;

import com.riderequestmanagement.RideList;
import com.riderequestmanagement.RideRequest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RideListPerformanceTest {

    private static final String CSV_FILE = "src/main/resources/ride_requests_large.csv";
    
    @Test
    void testRideListPerformance() {
        RideList<RideRequest> rideList = new RideList<>(100000);
        int rideCount = 0;

        System.out.println("Starting RideList Performance Test...");

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
                rideList.add(ride);
                rideCount++;
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            fail("Failed to read CSV");
        }

        long loadTime = System.nanoTime();

        while (!rideList.isEmpty()) {
            rideList.remove(0);
        }

        long endTime = System.nanoTime();
        System.out.println("Total Rides Loaded: " + rideCount);
        System.out.println("RideList Load Time: " + (loadTime - startTime) / 1_000_000 + " ms");
        System.out.println("RideList Processing Time: " + (endTime - loadTime) / 1_000_000 + " ms");
        System.out.println("RideList Total Execution Time: " + (endTime - startTime) / 1_000_000 + " ms \n \n");

        assertTrue(rideList.isEmpty());
    }
}
