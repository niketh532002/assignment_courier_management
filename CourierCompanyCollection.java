package com.hexaware.dto;

import com.hexaware.entities.Courier;
import com.hexaware.entities.Employee;
import com.hexaware.entities.Location;
import com.hexaware.entities.CourierCompany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//Here in this entire program we have created arraylist and replaced entire array data for courierDetails, employeedetails,
// and locationdetails to array lists
public class CourierCompanyCollection {
    private String companyName;
    private List<Courier> courierDetails;
    private List<Employee> employeeDetails;
    private List<Location> locationDetails;

    public CourierCompanyCollection() {
        this.courierDetails = new ArrayList<>();
        this.employeeDetails = new ArrayList<>();
        this.locationDetails = new ArrayList<>();
    }

    public CourierCompanyCollection(String companyName, List<Courier> courierDetails,
                                    List<Employee> employeeDetails, List<Location> locationDetails) {
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

    public List<Courier> getCourierDetails() {
        return courierDetails;
    }

    public void setCourierDetails(List<Courier> courierDetails) {
        this.courierDetails = courierDetails;
    }

    public List<Employee> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<Employee> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public List<Location> getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(List<Location> locationDetails) {
        this.locationDetails = locationDetails;
    }

    @Override
    public String toString() {
        return "CourierCompanyCollection{" +
                "companyName='" + companyName + '\'' +
                ", courierDetails=" + courierDetails +
                ", employeeDetails=" + employeeDetails +
                ", locationDetails=" + locationDetails +
                '}';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting user input for CourierCompany details
        System.out.println("Enter Courier Company Details:");
        System.out.print("Enter Company Name: ");
        String companyName = scanner.nextLine();

        CourierCompanyCollection courierCompany = new CourierCompanyCollection(companyName,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Enter the number of couriers
        System.out.print("Enter the number of couriers: ");
        int numCouriers = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Loop to input courier details
        for (int i = 0; i < numCouriers; i++) {
            System.out.println("Enter Courier Details for Courier " + (i + 1) + ":");
            courierCompany.getCourierDetails().add(createCourier(scanner));
        }

        // Enter the number of employees
        System.out.print("Enter the number of employees: ");
        int numEmployees = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // For Loop to input employee details
        for (int i = 0; i < numEmployees; i++) {
            System.out.println("Enter Employee Details for Employee " + (i + 1) + ":");
            courierCompany.getEmployeeDetails().add(createEmployee(scanner));
        }

        // Kindly Enter the number of locations
        System.out.print("Enter the number of locations: ");
        int numLocations = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Loop to input location details
        for (int i = 0; i < numLocations; i++) {
            System.out.println("Enter Location Details for Location " + (i + 1) + ":");
            courierCompany.getLocationDetails().add(createLocation(scanner));
        }

        // Displaying the details of the CourierCompany
        System.out.println("\nCourier Company Details:");
        System.out.println(courierCompany);

        scanner.close();
    }

    private static Courier createCourier(Scanner scanner) {
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

        Date deliveryDate = new Date();

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        return new Courier(courierID, senderName, senderAddress, receiverName, receiverAddress,
                weight, status, trackingNumber, deliveryDate, userId);
    }

    private static Employee createEmployee(Scanner scanner) {
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

        return new Employee(employeeID, employeeName, email, contactNumber, role, salary);
    }

    private static Location createLocation(Scanner scanner) {
        System.out.print("Enter Location ID: ");
        long locationID = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Location Name: ");
        String locationName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        return new Location(locationID, locationName, address);
    }
}
