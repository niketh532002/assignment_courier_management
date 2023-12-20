package com.hexaware.entities;

import java.util.Date;
import java.util.Scanner;
// Updating the Courier Class
public class Courier {
    private long courierID;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    private double weight;
    private String status;
    private long trackingNumber;
    private Date deliveryDate;
    private int userId;
    private int withdrawAmount;

    // Default constructor
    public Courier() {}
    
    // Parameterized constructor
    public Courier(long courierID, String senderName, String senderAddress, String receiverName, String receiverAddress,
                   double weight, String status, long trackingNumber, Date deliveryDate, int userId)
    {
        this.courierID = courierID;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.weight = weight;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.deliveryDate = deliveryDate;
        this.userId = userId;
    }

    // Getters and setters start
    public long getCourierID() {
        return courierID;
    }
    public void setCourierID(long courierID) {
        this.courierID = courierID;
    }

    public String getSenderName()
    {
        return senderName;
    }
    public void setSenderName(String senderName)
    {
        this.senderName = senderName;
    }

    public String getSenderAddress()
    {
        return senderAddress;
    }
    public void setSenderAddress(String senderAddress)
    {
        this.senderAddress = senderAddress;
    }

    public String getReceiverName()
    {
        return receiverName;
    }
    public void setReceiverName(String receiverName)
    {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress()
    {
        return receiverAddress;
    }
    public void setReceiverAddress(String receiverAddress)
    {
        this.receiverAddress = receiverAddress;
    }

    public double getWeight()
    {
        return weight;
    }
    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public long getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(long trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    // Getters and setters end
// Static method to create a Courier object
    // This below method is created for using in CourierUserServiceImpl class created in Service_Implementation package
    // As per rules we need to create this class createCourier() here only inside Courier.java file that's why created here
    public static Courier createCourier() {
        Scanner scanner = new Scanner(System.in);

        // Getting user input for Courier details
        System.out.println("Courier Details:");
        System.out.print("Enter Courier ID: ");
        long courierID = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Sender Name: ");
        String senderName = scanner.nextLine();
        System.out.print("Enter Sender Address: ");
        String senderAddress = scanner.nextLine();
        System.out.print("Enter Receiver Name: ");
        String receiverName = scanner.nextLine();
        System.out.print("Enter Receiver Address: ");
        String receiverAddress = scanner.nextLine();
        System.out.print("Enter Weight : ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Status : ");
        String status = scanner.nextLine();
        System.out.print("Enter Tracking Number : ");
        long trackingNumber = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter User ID : ");
        int userID = scanner.nextInt();

        // Creating an instance of Courier with user input
        Courier courier = new Courier(courierID, senderName, senderAddress, receiverName, receiverAddress, weight, status, trackingNumber, null, userID);

        scanner.close();

        return courier;
    }
    // toString method
    @Override
    public String toString() {
        return "Courier{" +
                "courierID=" + courierID +
                ", senderName='" + senderName + '\'' +
                ", senderAddress='" + senderAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", weight=" + weight +
                ", status='" + status + '\'' +
                ", trackingNumber=" + trackingNumber +
                ", deliveryDate=" + deliveryDate +
                ", userId=" + userId +
                '}';
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting user input for User details
        System.out.println("User Courier Details:");
        System.out.print("Enter Courier ID: ");
        long courierID = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Sender Name: ");
        String senderName = scanner.nextLine();
        System.out.print("Enter Sender Address: ");
        String senderAddress = scanner.nextLine();
        System.out.print("Enter Receiver Name: ");
        String receiverName = scanner.nextLine();
        System.out.print("Enter Receiver Address: ");
        String receiverAddress = scanner.nextLine();
        System.out.print("Enter Weight : ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Status : ");
        String status = scanner.nextLine();
        System.out.print("Enter Tracking Number : ");
        long trackingNumber = scanner.nextLong();
        scanner.nextLine();
//        System.out.print("Enter DeliveryDate : ");
//        String paymentDateStr = scanner.nextLine();
        Date deliveryDate = new Date();
        System.out.print("Enter User ID : ");
        int userID = scanner.nextInt();

        // Creating an instance of Courier with user input
        Courier co = new Courier(courierID,senderName,senderAddress,receiverName,receiverAddress,weight,status,trackingNumber,
                deliveryDate,userID);

        // Displaying the details of the Courier
        System.out.println("\nCourier Details:");
        System.out.println("Courier ID: " + co.getCourierID());
        System.out.println("Sender Name: " + co.getSenderName());
        System.out.println("Sender Address: " + co.getSenderAddress());
        System.out.println("Receiver Name: " + co.getReceiverName());
        System.out.println("Receiver Address: " + co.getReceiverAddress());
        System.out.println("Weight: " + co.getWeight());
        System.out.println("Status: " + co.getStatus());
        System.out.println("Tracking Number: " + co.getTrackingNumber());
        System.out.println("Delivery Date: " + co.getDeliveryDate());
        System.out.println("User ID: " + co.getUserId());

        scanner.close();
    }

    public void withdrawAmount() {
    }
}
