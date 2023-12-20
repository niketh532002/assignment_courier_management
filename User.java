package com.hexaware.entities;
import java.util.Date;
import java.util.Scanner;
public class User {
    private int userID;
    private String userName;
    private String email;
    private String password;
    private long contactNumber;
    private String address;

    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(int userID, String userName, String email, String password, long contactNumber, String address) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // Getters and setters start
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public long getContactNumber()
    {
        return contactNumber;
    }
    public void setContactNumber(long contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    // Getters and setters end

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNumber=" + contactNumber +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting user input for User details
        System.out.println("User Details:");
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        long contactNumber = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        // Creating an instance of User with user input
        User user = new User(userId, userName, email, password, contactNumber, address);

        // Displaying the details of the User
        System.out.println("\nUser Details are as :");
        System.out.println("User ID: " + user.getUserID());
        System.out.println("User Name: " + user.getUserName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Contact Number: " + user.getContactNumber());
        System.out.println("Address: " + user.getAddress());

        scanner.close();
    }
}

