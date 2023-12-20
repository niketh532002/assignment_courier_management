package Java_Loops_and_Iteration;

import java.util.Random;

public class Task3Part2WhileLoop {
    public static void main(String[] args) {
        // Simulate courier's initial position
        int courierPosition = 0;

        // Simulate destination position
        int destination = 100;

        // Create a Random object to simulate movement
        Random random = new Random();

        // Simulate tracking the courier until it reaches the destination
        while (courierPosition < destination) {
            // Simulate movement
            int movement = random.nextInt(11); // Random movement between 0 and 10
            courierPosition += movement;

            // Ensure the courier doesn't overshoot the destination
            if (courierPosition > destination) {
                courierPosition = destination;
            }

            // Print current position
            System.out.println("Courier's current position: " + courierPosition);

            // Simulate delay (representing real-time tracking)
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Courier has reached the destination!");
    }
}
