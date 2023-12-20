package com.hexaware.dao;

import com.hexaware.dto.CourierCompanyCollection;
import com.hexaware.entities.Courier;
import com.hexaware.entities.Employee;
import com.hexaware.entities.Location;
import ServiceProvider_Interface.ICourierUserService;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import java.util.*;

public class CourierUserServiceCollectionImpl implements ICourierUserService {
    private static long nextTrackingNumber = 1005L; // Starting value for tracking numbers
    private Map<Long, Courier> courierDatabase = new HashMap<>(); // Database to store courier orders
    private Map<Integer, Integer> assignedOrders = new HashMap<>(); // Database to store assigned orders
    protected CourierCompanyCollection companyObj;

    public CourierUserServiceCollectionImpl(CourierCompanyCollection companyObj) {
        this.companyObj = companyObj;
    }

    @Override
    public long placeOrder(Courier courierObj) {
        List<Courier> courierDetails = companyObj.getCourierDetails();
        long trackingNumber = generateTrackingNumber();
        courierObj.setTrackingNumber(trackingNumber);
        courierDetails.add(courierObj);
        return trackingNumber;
    }

    @Override
    public String getOrderStatus(long trackingNumber) {
        List<Courier> courierDetails = companyObj.getCourierDetails();
        for (Courier courier : courierDetails) {
            if (courier.getTrackingNumber() == trackingNumber) {
                return courier.getStatus();
            }
        }
        return "Order not found";
    }

    @Override
    public boolean cancelOrder(long trackingNumber) {
        List<Courier> courierDetails = companyObj.getCourierDetails();
        for (Courier courier : courierDetails) {
            if (courier.getTrackingNumber() == trackingNumber) {
                courierDetails.remove(courier);
                return true;
            }
        }
        return false;
    }



    @Override
    public boolean assignCourier(long trackingNumber, int courierStaffId) {
        // Store the assignment information in the database
        assignedOrders.put(courierStaffId, assignedOrders.getOrDefault(courierStaffId, 0) + 1);

        // Update the courier order status using collections
        List<Courier> courierDetails = companyObj.getCourierDetails();
        Iterator<Courier> iterator = courierDetails.iterator();
        while (iterator.hasNext()) {
            Courier courier = iterator.next();
            if (courier.getTrackingNumber() == trackingNumber) {
                courier.setStatus("Assigned");
                iterator.remove();
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean markOrderDelivered(long trackingNumber) {
        List<Courier> courierDetails = companyObj.getCourierDetails();
        for (Courier courier : courierDetails) {
            if (courier.getTrackingNumber() == trackingNumber) {
                courier.setStatus("Delivered");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Long> getAssignedOrders(int courierStaffId) {
        // Implement the logic to retrieve assigned orders for the courier staff
        // You can use the assignedOrders ArrayList in companyObj for this
        // Retrieve the number of assigned orders for the courier staff
        int numberOfAssignedOrders = assignedOrders.getOrDefault(courierStaffId, 0);

        // Generate a list of assigned order tracking numbers
        List<Long> assignedOrderList = new ArrayList<>();
        for (Map.Entry<Long, Courier> entry : courierDatabase.entrySet()) {
            if ("Assigned".equals(entry.getValue().getStatus())) {
                assignedOrderList.add(entry.getKey());
            }
        }
        return new ArrayList<>();
    }

    private long generateTrackingNumber() {
        // Implement the logic to generate a unique tracking number
        // You can use a sequence generator or any other logic based on your requirements
        long trackingNumber = nextTrackingNumber++;
        return trackingNumber;
    }

    public static void main(String[] args) {
        // Create courier details, employee details, and location details
        List<Courier> couriers = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<Location> locations = new ArrayList<>();

        // Creating an instance of CourierCompanyCollection
        CourierCompanyCollection companyCollection = new CourierCompanyCollection("ABC Company", couriers, employees, locations);

        // Creating an instance of CourierUserServiceCollectionImpl
        CourierUserServiceCollectionImpl courierService = new CourierUserServiceCollectionImpl(companyCollection);

        // Place an order
        Courier courier1 = new Courier(1, "John Sender", "Address1", "Jane Receiver", "Address2",
                2.5, "Ordered", 123456, parseDate("01/01/2023"), 1);
        long trackingNumber1 = courierService.placeOrder(courier1);
        System.out.println("Order placed with tracking number: " + trackingNumber1);

        // Get order status
        String orderStatus1 = courierService.getOrderStatus(trackingNumber1);
        System.out.println("Order status is as : " + orderStatus1);

        // Cancel an order
        boolean cancelResult = courierService.cancelOrder(trackingNumber1);
        System.out.println("Cancel result: " + cancelResult);

        // Assign a courier
        boolean assignResult = courierService.assignCourier(trackingNumber1, 1);
        System.out.println("Assigned result is as : " + assignResult);

        // Mark an order as delivered
        boolean markDeliveredResult = courierService.markOrderDelivered(trackingNumber1);
        System.out.println("Mark delivered result: " + markDeliveredResult);

        // Get assigned orders
        List<Long> assignedOrders = courierService.getAssignedOrders(1);
        System.out.println("Assigned orders are as : " + assignedOrders);
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
    }
}
