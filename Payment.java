package com.hexaware.entities;

import java.util.Date;
import java.util.Scanner;

public class Payment {
    private long paymentID;
    private long courierID;
    private double amount;
    private Date paymentDate;

    // Default constructor
    public Payment() {}

    // Parameterized constructor
    public Payment(long paymentID, long courierID, double amount, Date paymentDate) {
        this.paymentID = paymentID;
        this.courierID = courierID;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    // Getters and setters start
    public long getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(long paymentID) {
        this.paymentID = paymentID;
    }

    public long getCourierID() {
        return courierID;
    }
    public void setCourierID(long courierID) {
        this.courierID = courierID;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    // Getters and setters end

    // toString method
    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", courierID=" + courierID +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting user input for Payment details
        System.out.println("Enter Payment Details:");
        System.out.print("Enter Payment ID: ");
        long paymentID = scanner.nextLong();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Enter Courier ID: ");
        long courierID = scanner.nextLong();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline character

//        System.out.print("Enter Payment Date: ");
//        String paymentDateStr = scanner.nextLine();
        // Convert String to Date (you may need to handle parsing)
        Date paymentDate = new Date();  // Replace this with actual parsing logic

        // Create Payment object
        Payment payment = new Payment(paymentID, courierID, amount, paymentDate);

        // Displaying the details of the Payment
        System.out.println("\nPayment Details:");
        System.out.println("Payment ID is as : " + payment.getPaymentID());
        System.out.println("Courier ID is as : " + payment.getCourierID());
        System.out.println("Amount is as : " + payment.getAmount());
        System.out.println("Payment Date is as : " + payment.getPaymentDate());

        scanner.close();
    }
}
