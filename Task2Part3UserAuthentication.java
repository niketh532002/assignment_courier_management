package Java_Control_Flow_Statements;
import java.util.Scanner;

public class Task2Part3UserAuthentication {
    public static void main(String[] args) {
        String employeeUsername = "employee";
        String employeePassword = "employee123";
        String customerUsername = "customer";
        String customerPassword = "customer123";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Login System!");

        // Prompt the user to enter their role (Employee or Customer)
        System.out.print("Enter your role (Employee/Customer): ");
        String role = scanner.nextLine();

        // Validate the user's role
        if (role.equalsIgnoreCase("Employee")) {
            authenticateUser(employeeUsername, employeePassword, "Employee");
        } else if (role.equalsIgnoreCase("Customer")) {
            authenticateUser(customerUsername, customerPassword, "Customer");
        } else {
            System.out.println("Invalid role entered.");
        }

        scanner.close();
    }

    // Authenticate the user based on the provided username, password, and role
    private static void authenticateUser(String correctUsername, String correctPassword, String userRole) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Check if credentials match
        if (username.equals(correctUsername) && password.equals(correctPassword)) {
            System.out.println(userRole + " Login Successful!");
            // Proceed with role-specific functionalities
        } else {
            System.out.println("Invalid username or password for " + userRole + ".");
        }

        scanner.close();
    }
}
