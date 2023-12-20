package com.hexaware.connectionutil;
import com.hexaware.entities.Courier;
import jdk.jshell.Snippet;

import java.util.Date;
import java.util.Scanner;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CrudOperationCourier {
    private static Connection courier = null;

    // Default constructor
    private CrudOperationCourier() {
        // private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (courier != null) {
            return courier;
        } else {
            try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                if (input == null) {
                    System.out.println("Sorry, unable to find db.properties");
                    return null;
                }

                // load a properties file from class path, inside static method
                Properties prop = new Properties();
                prop.load(input);

                // get the property value and connect to the database
                String url = prop.getProperty("url");
                String username = prop.getProperty("username");
                String password = prop.getProperty("password");

                courier = DriverManager.getConnection(url, username, password);

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

            return courier;
        }
    }

    public static void main(String[] args) {
        try (Connection testCourierConnection = getConnection()) {
            if (testCourierConnection != null) {
                System.out.println("Database connection successful!");

                // Retrieving Data from courier Table, here retrieve means to get
                String query1 = "SELECT * FROM Courier;";
                try (Statement statement = testCourierConnection.createStatement();
                     ResultSet rSet = statement.executeQuery(query1)) {

                    System.out.println("Retrieved Courier Data from Courier table as :");

                    // Process the result set
                    while (rSet.next()) {
                        // Access the data from the result set
                        int column1 = rSet.getInt("CourierID");
                        String column2 = rSet.getString("SenderName");
                        String column3 = rSet.getString("SenderAddress");
                        String column4 = rSet.getString("ReceiverName");
                        String column5 = rSet.getString("ReceiverAddress");
                        double column6 = rSet.getDouble("Weight");
                        String column7 = rSet.getString("Status");
                        String column8 = rSet.getString("TrackingNumber");
                        Date column9 = rSet.getDate("DeliveryDate");

                        // Process the data as needed
                        System.out.println("Courier ID: " + column1 + ", SenderName: " + column2 + ", SenderAddress: " +
                                column3 + ", ReceiverName: " + column4 + "ReceiverAddress: " + column5 + ", Weight: " + column6 + ", Status: " +
                                column7 + ", TrackingNumber: " + column8 + ", DeliveryDate: " + column9);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Inserting Data in courier Table
                String query2 = "INSERT INTO Courier (CourierID, SenderName, SenderAddress, ReceiverName, ReceiverAddress, Weight, Status, TrackingNumber, DeliveryDate)\n" +
                        "VALUES (10, 'Jitendra Kumar', '23/5 Block 1, Alkapuri Dewas', 'Kevin Parker', '128 Stephen Park, California', 4.2, 'Delivered', 'TN289761', '2023-04-13')";
                try (Statement statement = testCourierConnection.createStatement()) {
                    int rowsAffected = statement.executeUpdate(query2);
                    if (rowsAffected > 0) {
                        System.out.println("\nAfter Inserting a new row in Courier table, it will look as :");

                        // Fetch and display the newly inserted row
                        try (ResultSet rSet = statement.executeQuery("SELECT * FROM Courier WHERE CourierID = 10")) {
                            while (rSet.next()) {
                                int column1 = rSet.getInt("CourierID");
                                String column2 = rSet.getString("SenderName");
                                String column3 = rSet.getString("SenderAddress");
                                String column4 = rSet.getString("ReceiverName");
                                String column5 = rSet.getString("ReceiverAddress");
                                double column6 = rSet.getDouble("Weight");
                                String column7 = rSet.getString("Status");
                                String column8 = rSet.getString("TrackingNumber");
                                Date column9 = rSet.getDate("DeliveryDate");

                                System.out.println("Courier ID: " + column1 + ", SenderName: " + column2 + ", SenderAddress: " +
                                        column3 + ", ReceiverName: " + column4 + "ReceiverAddress: " + column5 + ", Weight: " + column6 + ", Status: " +
                                        column7 + ", TrackingNumber: " + column8 + ", DeliveryDate: " + column9);
                            }
                        }
                    } else {
                        System.out.println("Insertion failed!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Updating Status in courier Table
                String updateQuery = "UPDATE Courier SET Status = 'Out for Delivery' WHERE CourierID = 10";
                try (Statement updateStatement = testCourierConnection.createStatement()) {
                    int rowsAffected = updateStatement.executeUpdate(updateQuery);
                    if (rowsAffected > 0) {
                        System.out.println("\n After Updating status for CourierID 10 in Courier table, it will look as :");

                        // Fetch and display the updated row
                        try (ResultSet rSet = updateStatement.executeQuery("SELECT * FROM Courier WHERE CourierID = 10")) {
                            while (rSet.next()) {
                                int column1 = rSet.getInt("CourierID");
                                String column2 = rSet.getString("SenderName");
                                String column3 = rSet.getString("SenderAddress");
                                String column4 = rSet.getString("ReceiverName");
                                String column5 = rSet.getString("ReceiverAddress");
                                double column6 = rSet.getDouble("Weight");
                                String column7 = rSet.getString("Status");
                                String column8 = rSet.getString("TrackingNumber");
                                Date column9 = rSet.getDate("DeliveryDate");

                                System.out.println("Courier ID: " + column1 + ", SenderName: " + column2 + ", SenderAddress: " +
                                        column3 + ", ReceiverName: " + column4 + "ReceiverAddress: " + column5 + ", Weight: " + column6 + ", Status: " +
                                        column7 + ", TrackingNumber: " + column8 + ", DeliveryDate: " + column9);
                            }
                        }
                    } else {
                        System.out.println("Update failed!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            // If nothing works inside main if condition then print this below else statement
            else {
                System.out.println("Failed to establish a database connection.");
            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

