package ServiceProvider_Interface;

import Service_Implementation.CourierUserServiceImpl;
import com.hexaware.entities.Courier;

import java.util.*;

public class CourierUserServiceImplementation implements ICourierUserService {
//NextTracking number is defined as below
    private static long nextTrackingNumber = 1005L; // Starting value for tracking numbers
    private Map<Long, Courier> courierDatabase = new HashMap<>(); // Database to store courier orders
    private Map<Integer, Integer> assignedOrders = new HashMap<>(); // Database to store assigned orders

    @Override
    public long placeOrder(Courier courierObj) {
        // Generate a unique tracking number and assign it to the courier order
        long trackingNumber = nextTrackingNumber++;
        courierObj.setTrackingNumber(trackingNumber);

        // Store the courier order in the database
        courierDatabase.put(trackingNumber, courierObj);

        return trackingNumber;
    }

    @Override
    public String getOrderStatus(long trackingNumber) {
        // Retrieve the courier order from the database and get its status
        Courier courier = courierDatabase.get(trackingNumber);
        if (courier != null) {
            return courier.getStatus();
        } else {
            return "Order not found";
        }
    }

    @Override
    public boolean cancelOrder(long trackingNumber) {
        // Remove the courier order from the database
        return courierDatabase.remove(trackingNumber) != null;
    }
    
    @Override
    public boolean assignCourier(long trackingNumber, int courierStaffId) {
        // Store the assignment information in the database
        assignedOrders.put(courierStaffId, assignedOrders.getOrDefault(courierStaffId, 0) + 1);

        // Update the courier order status
        Courier courier = courierDatabase.get(trackingNumber);
        if (courier != null) {
            courier.setStatus("Assigned");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean markOrderDelivered(long trackingNumber) {
        // Update the courier order status
        Courier courier = courierDatabase.get(trackingNumber);
        if (courier != null) {
            courier.setStatus("Delivered");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Long> getAssignedOrders(int courierStaffId) {
        // Retrieve the number of assigned orders for the courier staff
        int numberOfAssignedOrders = assignedOrders.getOrDefault(courierStaffId, 0);

        // Generate a list of assigned order tracking numbers
        List<Long> assignedOrderList = new ArrayList<>();
        for (Map.Entry<Long, Courier> entry : courierDatabase.entrySet()) {
            if ("Assigned".equals(entry.getValue().getStatus())) {
                assignedOrderList.add(entry.getKey());
            }
        }

        // Return the list of assigned orders
        return assignedOrderList;
    }

    public static void main(String[] args) {
        // Create an instance of the implementation class
        CourierUserServiceImplementation courierService = new CourierUserServiceImplementation();
        Courier courier1 = Courier.createCourier();
        Long trackingNumber1 = courierService.placeOrder(courier1);
        System.out.println("Placed order with tracking number: " + trackingNumber1);

        String orderStatus1 = courierService.getOrderStatus(trackingNumber1);
        System.out.println("Order status: " + orderStatus1);

        boolean cancelResult = courierService.cancelOrder(trackingNumber1);
        System.out.println("Cancel result: " + cancelResult);

        boolean assignResult = courierService.assignCourier(trackingNumber1, 1); // assuming courier staff ID 1
        System.out.println("Assign result: " + assignResult);

        boolean markDeliveredResult = courierService.markOrderDelivered(trackingNumber1);
        System.out.println("Mark delivered result: " + markDeliveredResult);

        List<Long> assignedOrders = courierService.getAssignedOrders(1); // assuming courier staff ID 1
        System.out.println("Assigned orders: " + assignedOrders);
    }
    // Helper method to create Courier object

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

}
