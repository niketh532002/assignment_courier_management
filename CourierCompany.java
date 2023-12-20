package com.hexaware.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class CourierCompany {
    private String companyName;
    private Courier[] courierDetails;
    private Employee[] employeeDetails;
    private Location[] locationDetails;

    public CourierCompany() {
    }

    public CourierCompany(String companyName, Courier[] courierDetails, Employee[] employeeDetails, Location[] locationDetails) {
        this.companyName = companyName;
        this.courierDetails = courierDetails;
        this.employeeDetails = employeeDetails;
        this.locationDetails = locationDetails;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Courier[] getCourierDetails() {
        return courierDetails;
    }

    public void setCourierDetails(Courier[] courierDetails) {
        this.courierDetails = courierDetails;
    }

    public Employee[] getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(Employee[] employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public Location[] getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(Location[] locationDetails) {
        this.locationDetails = locationDetails;
    }

    @Override
    public String toString() {
        return "CourierCompany{" +
                "companyName='" + companyName + '\'' +
                ", courierDetails=" + Arrays.toString(courierDetails) +
                ", employeeDetails=" + Arrays.toString(employeeDetails) +
                ", locationDetails=" + Arrays.toString(locationDetails) +
                '}';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting user input for CourierCompany details
        System.out.println("Enter Courier Company Details:");
        System.out.print("Enter Company Name: ");
        String companyName = scanner.nextLine();

        // Enter the number of couriers
        System.out.print("Enter the number of couriers: ");
        int numCouriers = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Create an array to store courier details
        Courier[] couriers = new Courier[numCouriers];

        // Loop to input courier details
        for (int i = 0; i < numCouriers; i++) {
            System.out.println("Enter Courier Details for Courier " + (i + 1) + ":");

            System.out.print("Enter Courier ID: ");
            long courierID = scanner.nextLong();
            scanner.nextLine();  // Consume the newline character

            System.out.print("Enter Sender Name: ");
            String senderName = scanner.nextLine();

            System.out.print("Enter Sender Address: ");
            String senderAddress = scanner.nextLine();

            System.out.print("Enter Receiver Name: ");
            String receiverName = scanner.nextLine();

            System.out.print("Enter Receiver Address: ");
            String receiverAddress = scanner.nextLine();

            System.out.print("Enter Weight: ");
            double weight = scanner.nextDouble();
            scanner.nextLine();  // Consume the newline character

            System.out.print("Enter Status: ");
            String status = scanner.nextLine();

            System.out.print("Enter Tracking Number: ");
            long trackingNumber = scanner.nextLong();
            scanner.nextLine();  // Consume the newline character

//            System.out.print("Enter Delivery Date: ");
//            String paymentDateStr = scanner.nextLine();
            Date deliveryDate = new Date();

            System.out.print("Enter User ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            // Create Courier object
            couriers[i] = new Courier(courierID, senderName, senderAddress, receiverName, receiverAddress,
                    weight, status, trackingNumber, deliveryDate, userId);

        }

        // Enter the number of employees
        System.out.print("Enter the number of employees: ");
        int numEmployees = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Create an array to store employee details
        Employee[] employees = new Employee[numEmployees];

        // Loop to input employee details
        for (int i = 0; i < numEmployees; i++) {
            System.out.println("Enter Employee Details for Employee " + (i + 1) + ":");

            System.out.print("Enter Employee ID: ");
            long employeeID = scanner.nextLong();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter Employee Name: ");
            String employeeName = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Contact Number: ");
            long contactNumber = scanner.nextLong();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter Role: ");
            String role = scanner.nextLine();
            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();

            // Creating an instance of Employee with user input
            employees[i] = new Employee(employeeID, employeeName, email, contactNumber, role, salary);

        }

        // Enter the number of locations
        System.out.print("Enter the number of locations: ");
        int numLocations = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Create an array to store location details
        Location[] locations = new Location[numLocations];

        // Loop to input location details
        for (int i = 0; i < numLocations; i++) {
            System.out.println("Enter Location Details for Location " + (i + 1) + ":");

            System.out.print("Enter Location ID: ");
            long locationID = scanner.nextLong();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Enter Location Name: ");
            String locationName = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            // Creating an instance of Location with user input
            locations[i] = new Location(locationID, locationName, address);

        }

        // Create an instance of CourierCompany with user input
        CourierCompany courierCompany = new CourierCompany(companyName, couriers, employees, locations);

        // Displaying the details of the CourierCompany
        System.out.println("\nCourier Company Details:");
        System.out.println(courierCompany);

        scanner.close();
    }
}
