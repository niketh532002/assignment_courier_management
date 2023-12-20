package Service_Implementation;
import ServiceProvider_Interface.CourierAdminServiceImplementation;
import ServiceProvider_Interface.CourierUserServiceImplementation;
import ServiceProvider_Interface.ICourierAdminService;
import ServiceProvider_Interface.ICourierUserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.hexaware.entities.Courier;
import com.hexaware.entities.Employee;
import com.hexaware.entities.CourierCompany;
import com.hexaware.entities.Location;

public class CourierUserServiceImpl implements ICourierUserService {

    private static long nextTrackingNumber = 1001L; // Starting value for tracking numbers
    private Map<Long, Courier> courierDatabase = new HashMap<>(); // Database to store courier orders
    private Map<Integer, Integer> assignedOrders = new HashMap<>(); // Database to store assigned orders

    private CourierCompany companyObj; // Assuming CourierCompany is a class that holds Object Arrays

    public CourierUserServiceImpl(CourierCompany companyObj) {
        this.companyObj = companyObj;
    }

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


    public static void main(String[] args) {
        // Create an instance of the implementation class with sample data
        Employee[] employees = {
                new Employee(1, "Rohan Modi", "rohan.modi@gmail.com", 1234567890, "Courier Staff", 50000),
                new Employee(2, "Ankush Khare", "ankush.khare@gmail.com", 1458792343, "Courier Staff", 55000)
                // Add more employees if needed
        };

        Courier[] couriers = {
                new Courier(1001, "Sender1", "SenderAddress1", "Receiver1", "ReceiverAddress1", 2.5, "Ordered", 1001, null, 1),
                new Courier(1002, "Sender2", "SenderAddress2", "Receiver2", "ReceiverAddress2", 3.0, "Ordered", 1002, null, 2)
                // Add more couriers if needed
        };

        Location[] locations = {
                new Location(1, "Location1", "LocationAddress1"),
                new Location(2, "Location2", "LocationAddress2")
        };

        CourierCompany company = new CourierCompany("ABC Company", couriers, employees, locations);
        CourierUserServiceImpl courierService = new CourierUserServiceImpl(company);
        Courier courier1 = Courier.createCourier();
        long trackingNumber1 = courierService.placeOrder(courier1);
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
}
