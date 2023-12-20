package com.hexaware.entities;
import java.util.Scanner;

    public class Location {
        private long locationID;
        private String locationName;
        private String address;

        // Default constructor
        public Location() {}

        // Parameterized constructor
        public Location(long locationID, String locationName, String address) {
            this.locationID = locationID;
            this.locationName = locationName;
            this.address = address;
        }

        // Getters and setters start
        public long getLocationID() {
            return locationID;
        }
        public void setLocationID(long locationID) {
            this.locationID = locationID;
        }

        public String getLocationName() {
            return locationName;
        }
        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        // Getters and setters end

        @Override
        public String toString() {
            return "Location{" +
                    "locationID=" + locationID +
                    ", locationName='" + locationName + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Getting user input for Location details
            System.out.println("Enter Location Details:");
            System.out.print("Enter Location ID: ");
            long locationID = scanner.nextLong();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter Location Name: ");
            String locationName = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            // Creating an instance of Location with user input
            Location location = new Location(locationID, locationName, address);

            // Displaying the details of the Location
            System.out.println("\nLocation Details:");
            System.out.println("Location ID: " + location.getLocationID());
            System.out.println("Location Name: " + location.getLocationName());
            System.out.println("Address: " + location.getAddress());

            scanner.close();
        }
    }


