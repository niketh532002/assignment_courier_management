package com.hexaware.connectionutil;
import java.sql.Connection;
import com.hexaware.connectionutil.DBConnection;
//import com.hexaware.dao.CourierServiceDb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class ParcelTrackingRevenueStatus {
    private static Connection connection1;
    // Constructor for ParcelTrackingRevenueStatus
    public ParcelTrackingRevenueStatus() {
        // Assuming getConnection method is static in DBConnection class
        connection1 = DBConnection.getConnection();

        // Call methods to perform database operations
        if (connection1 != null) {
            retrievedisplayhistory();
            shipmentstatusreport();
            revenuereport();
            // Call other methods as needed...
        } else {
            try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                if (input == null) {
                    System.out.println("Sorry, unable to find db.properties");
                    return;
                }

                // load a properties file from class path, inside static method
                Properties prop = new Properties();
                prop.load(input);

                // get the property value and connect to the database
                String url = prop.getProperty("url");
                String username = prop.getProperty("username");
                String password = prop.getProperty("password");

                connection1 = DriverManager.getConnection(url, username, password);

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of CourierServiceDb
        ParcelTrackingRevenueStatus parcelTrackingRevenueStatus = new ParcelTrackingRevenueStatus();

        // Check if the connection is successful
        if (parcelTrackingRevenueStatus.connection1 != null) {
            System.out.println("\n Database connection successful!");
        } else {
            System.out.println("Failed to establish a database connection.");
        }
    }


    public void retrievedisplayhistory() {
        String query = "SELECT * FROM Courier WHERE TrackingNumber = 'TN774128'";
        try (PreparedStatement preparedStatement = connection1.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Parcel History is as :");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                int column1 = resultSet.getInt("CourierID");
                String column2 = resultSet.getString("SenderName");
                String column3 = resultSet.getString("SenderAddress");
                String column4 = resultSet.getString("ReceiverName");
                String column5 = resultSet.getString("ReceiverAddress");
                double column6 = resultSet.getDouble("Weight");
                String column7 = resultSet.getString("Status");
                String column8 = resultSet.getString("TrackingNumber");
                Date column9 = resultSet.getDate("DeliveryDate");
                // Process the data as needed
                System.out.println("Courier ID: " + column1 + ", SenderName: " + column2 + ", SenderAddress: " +
                        column3 + ", ReceiverName: " + column4 + "ReceiverAddress: " + column5 + ", Weight: " + column6 + ", Status: " +
                        column7 + ", TrackingNumber: " + column8 + ", DeliveryDate: " + column9);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Example method to display shipmentstatus for specfic parcel as pending based on status
    public void shipmentstatusreport() {
        String query = "SELECT * FROM Courier WHERE Status = 'Pending'";
        try (PreparedStatement preparedStatement = connection1.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\n Shipment Status Report for parcel status as Pending is :");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                int column1 = resultSet.getInt("CourierID");
                String column2 = resultSet.getString("SenderName");
                String column3 = resultSet.getString("SenderAddress");
                String column4 = resultSet.getString("ReceiverName");
                String column5 = resultSet.getString("ReceiverAddress");
                double column6 = resultSet.getDouble("Weight");
                String column7 = resultSet.getString("Status");
                String column8 = resultSet.getString("TrackingNumber");
                Date column9 = resultSet.getDate("DeliveryDate");
                // Process the data as needed
                System.out.println("Courier ID: " + column1 + ", SenderName: " + column2 + ", SenderAddress: " +
                        column3 + ", ReceiverName: " + column4 + "ReceiverAddress: " + column5 + ", Weight: " + column6 + ", Status: " +
                        column7 + ", TrackingNumber: " + column8 + ", DeliveryDate: " + column9);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Example method to display revenue report for all parcels
    public void revenuereport() {
        String query = "SELECT * FROM PaymentTable";
        try (PreparedStatement preparedStatement = connection1.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("\n Revenue Report for all parcels is as :");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                int column1 = resultSet.getInt("PaymentID");
                int column2 = resultSet.getInt("CourierID");
                int column3 = resultSet.getInt("LocationID");
                double column4 = resultSet.getDouble("Amount");
                Date column5 = resultSet.getDate("PaymentDate");
                // Process the data as needed
                System.out.println("Payment ID: " + column1 + ", Courier ID: " + column2 + ", Location ID: " +
                        column3 + ", Amount: " + column4 + ", PaymentDate: " + column5);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
