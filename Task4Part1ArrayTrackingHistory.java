package Java_Arrays_and_DataStructures;

import java.util.Random;

public class Task4Part1ArrayTrackingHistory {
    public static void main(String[] args) {
        // Simulate destination position
        int finalDestination = 100;

        // Create an array to store tracking history
        int[] locationHistory = new int[finalDestination + 1]; // Add 1 to include the destination

        // Simulate parcel's initial position
        int currentLocation = 0;
        locationHistory[0] = currentLocation;

        // Create a Random object to simulate movement
        Random r = new Random();

        // Simulate tracking the parcel until it reaches the destination
        while (currentLocation < finalDestination) {
            // Simulate movement
            int movement = r.nextInt(11); // Random movement between 0 and 10
            currentLocation += movement;

            // Ensure the parcel doesn't overshoot the destination
            if (currentLocation > finalDestination) {
                currentLocation = finalDestination;
            }

            // Store the current location in the tracking history array
            locationHistory[currentLocation] = currentLocation;

            // Print current position
            System.out.println("Parcel's current location: " + currentLocation);

            // Simulate delay (representing real-time tracking)
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Parcel has reached the destination!");

        // Print tracking history
        System.out.println("\nTracking History:");
        for (int i = 0; i <= finalDestination; i++) {
            System.out.println("Location update " + i + ": " + locationHistory[i]);
        }
    }
}

