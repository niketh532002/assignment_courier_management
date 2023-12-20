package Java_Loops_and_Iteration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Order {
    private String customerName;
    private String destination;
    private double shipmentLoad;
    private char orderId;

    public Order(String customerName, String destination, double shipmentLoad, char orderId) {
        this.customerName = customerName;
        this.destination = destination;
        this.shipmentLoad = shipmentLoad;
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDestination() {
        return destination;
    }

    public double getShipmentLoad() {
        return shipmentLoad;
    }
    public char orderId()
    {
        return orderId;
    }
}

public class Task3Part1ForLoop {
    public static void main(String[] args) {
        List<Order> orders = initializeOrders();

        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the customer name
        System.out.print("Enter customer name to display orders: ");
        String customerToDisplay = scanner.nextLine();

        // Use a for loop to display orders for the specified customer
        boolean foundOrders = false;
        //For loop is used here below
        for (Order order : orders) {
            if (order.getCustomerName().equalsIgnoreCase(customerToDisplay)) {
                System.out.println("Customer: " + order.getCustomerName() +
                        ", Destination: " + order.getDestination() +
                        ", Shipment Load: " + order.getShipmentLoad() + " kg" +
                        ", Order Id: " + order.orderId());
                foundOrders = true;
            }
        }

        if (!foundOrders) {
            System.out.println("No orders found for customer: " + customerToDisplay);
        }

        scanner.close();
    }

    // Initialize a list of orders
    private static List<Order> initializeOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Customer1", "Destination1", 50,'O'));
        orders.add(new Order("Customer2", "Destination2", 30,'P'));
        orders.add(new Order("Customer3", "Destination3", 40,'T'));
        // Add more orders as needed
        return orders;
    }
}
