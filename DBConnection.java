package com.hexaware.connectionutil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
// Changing the DBConnection Java file
public class DBConnection {
    private static Connection conn = null;

    private DBConnection() {
        // private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (conn != null) {
            return conn;
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

                conn = DriverManager.getConnection(url, username, password);

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

            return conn;
        }
    }

    public static void main(String[] args) {
        try (Connection testConnection = getConnection()) {
            if (testConnection != null) {
                System.out.println("Database connection successful!");

                // Example of executing a query
                String query = "SELECT * FROM EmployeeTable";
                try (Statement statement = testConnection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    System.out.println("Employee Details are as : ");

                    // Process the result set
                    while (resultSet.next()) {
                        // Access the data from the result set
                        int column1 = resultSet.getInt("EmployeeID");
                        String column2 = resultSet.getString("Name");
                        // Process the data as needed
                        System.out.println("Employee ID: " + column1 + ", Name: " + column2);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Example of another query start
                String query1 = "SELECT * FROM Courier WHERE Status = 'Pending';";
                try (Statement statement = testConnection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query1)) {

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
                // Example of another query ends

                // Example of a prepared statement
                String query3 = "SELECT * FROM Courier WHERE CourierID = ?";
                try (PreparedStatement preparedStatement = testConnection.prepareStatement(query3)) {
                    preparedStatement.setInt(1, 2); // Set the parameter value

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        System.out.println("Details for CourierID = 2:");

                        while (resultSet.next()) {
                            int column1 = resultSet.getInt("CourierID");
                            String column2 = resultSet.getString("SenderName");
                            String column3 = resultSet.getString("ReceiverName");
                            System.out.println("Courier ID: " + column1 + ", SenderName: " + column2 + ", ReceiverName: " + column3);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //NOTE : All query condition will come under if (testConnection != null) as this is main if condtion in which
                // our Database connection successful! message is printed
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




//package com.hexaware.connectionutil;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Properties;
//
//public class DBConnection {
//
//    public static Connection getDBConnection(String cString) {
//        Connection con = null;
//        try {
//            String className = PropertyUtil.getClassName("db.properties");
//            Class.forName(className);
//            con = DriverManager.getConnection(cString, "root", "Ronny121");
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//
//            e.printStackTrace();
//        }
//        return con;
//    }
//    public static void main(String[] args) {
//        String conStr = PropertyUtil.getConnectionString("db.properties");
//        Connection con = DBConnection.getDBConnection(conStr);
//        String query = " select  * from employee where EmployeeID between 202 and 205";
//        try {
//            Statement s = con.createStatement();
//            ResultSet rs = s.executeQuery(query);
//
//            while(rs.next()) {
//                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
//            }
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }
//
//
//        System.out.println(con);
//
//    }
//
//}
