package com.hexaware.dao;

import java.sql.Connection;
import com.hexaware.connectionutil.DBConnection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class CourierServiceDb {
    private static Connection connection;

    // Constructor for CourierServiceDb
    public CourierServiceDb() {
        // Assuming getConnection method is static in DBConnection class
        connection = DBConnection.getConnection();

        // Call methods to perform database operations
        if (connection != null) {
            fetchCourierDetails();
            fetchLocationDetails();
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

                connection = DriverManager.getConnection(url, username, password);

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Create an instance of CourierServiceDb
        CourierServiceDb courierServiceDb = new CourierServiceDb();

        // Check if the connection is successful
        if (courierServiceDb.connection != null) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Failed to establish a database connection.");
        }
    }

    // Example method to fetch courier details
    public void fetchCourierDetails() {
        String query = "SELECT * FROM Courier";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Courier Details are as follows:");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                int column1 = resultSet.getInt("CourierID");
                String column2 = resultSet.getString("SenderName");
                String column3 = resultSet.getString("ReceiverName");
                // Process the data as needed
                System.out.println("Courier ID: " + column1 + ", SenderName: " + column2 + ", ReceiverName: " + column3);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Example method to fetch Location details
    public void fetchLocationDetails() {
        String query1 = "SELECT * FROM LocationTable";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query1);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Location Details are as follows:");

            // Process the result set
            while (resultSet.next()) {
                // Access the data from the result set
                int column1 = resultSet.getInt("LocationID");
                String column2 = resultSet.getString("LocationName");
                String column3 = resultSet.getString("Address");
                // Process the data as needed
                System.out.println("Location ID: " + column1 + ", LocationName: " + column2 + ", Address: " + column3);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
