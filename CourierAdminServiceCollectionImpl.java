package com.hexaware.dao;

import com.hexaware.dto.CourierCompanyCollection;
import com.hexaware.entities.Courier;
import com.hexaware.entities.Employee;
import com.hexaware.dao.CourierUserServiceCollectionImpl;
import ServiceProvider_Interface.ICourierAdminService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourierAdminServiceCollectionImpl extends CourierUserServiceCollectionImpl implements ICourierAdminService {

    public CourierAdminServiceCollectionImpl(CourierCompanyCollection companyObj) {
        super(companyObj);
    }

    @Override
    public int addCourierStaff(Employee employee) {
        List<Employee> courierStaffList = companyObj.getEmployeeDetails();
        int newEmployeeID = generateUniqueEmployeeID();
        employee.setEmployeeID(newEmployeeID);
        courierStaffList.add(employee);
        return newEmployeeID;
    }

    @Override
    public boolean removeCourierStaff(int courierStaffId) {
        List<Employee> courierStaffList = companyObj.getEmployeeDetails();
        Employee employeeToRemove = findEmployeeById(courierStaffList, courierStaffId);
        if (employeeToRemove != null) {
            courierStaffList.remove(employeeToRemove);
            return true;
        }
        return false;
    }

    @Override
    public String generateDeliveryReport(Date startDate, Date endDate) {
        StringBuilder report = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        report.append("Delivery Report for the period ")
                .append(dateFormat.format(startDate))
                .append(" to ")
                .append(dateFormat.format(endDate))
                .append(":\n");

        List<Employee> courierStaffList = companyObj.getEmployeeDetails();
        List<Courier> courierList = companyObj.getCourierDetails();

        for (Employee employee : courierStaffList) {
            List<String> deliveredOrders = getDeliveredOrdersInDateRange(employee.getEmployeeID(), courierList, startDate, endDate);

            if (!deliveredOrders.isEmpty()) {
                report.append("Employee ID: ").append(employee.getEmployeeID())
                        .append(", Name: ").append(employee.getEmployeeName())
                        .append("\nDelivered Orders:\n").append(String.join("\n", deliveredOrders))
                        .append("\n\n");
            }
        }

        return report.toString();
    }

    private List<String> getDeliveredOrdersInDateRange(long employeeID, List<Courier> courierList, Date startDate, Date endDate) {
        List<String> deliveredOrders = new ArrayList<>();

        for (Courier courier : courierList) {
            if (courier.getUserId() == employeeID && courier.getDeliveryDate() != null
                    && courier.getDeliveryDate().after(startDate) && courier.getDeliveryDate().before(endDate)) {
                deliveredOrders.add("Courier ID: " + courier.getCourierID() +
                        ", Tracking Number: " + courier.getTrackingNumber() +
                        ", Delivery Date: " + new SimpleDateFormat("dd/MM/yyyy").format(courier.getDeliveryDate()));
            }
        }
        return deliveredOrders;
    }

    private Employee findEmployeeById(List<Employee> courierStaffList, int employeeId) {
        for (Employee employee : courierStaffList) {
            if (employee.getEmployeeID() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    // Rest of the methods remain the same

    private int generateUniqueEmployeeID() {
        List<Employee> courierStaffList = companyObj.getEmployeeDetails();
        long lastAssignedEmployeeID = 1000; // Initialize with a starting value

        for (Employee employee : courierStaffList) {
            if (employee.getEmployeeID() > lastAssignedEmployeeID) {
                lastAssignedEmployeeID = employee.getEmployeeID();
            }
        }

        return (int) (lastAssignedEmployeeID + 1);
    }

    public static void main(String[] args) {
        // Creating an instance of CourierCompanyCollection
        CourierCompanyCollection companyCollection = new CourierCompanyCollection();

        // Code for accessing functionalities of CourierAdminServiceCollectionImpl class
        CourierAdminServiceCollectionImpl courierAdminService = new CourierAdminServiceCollectionImpl(companyCollection);

        // Adding some initial data for testing
        Employee employee1 = new Employee(1, "Rohan Modi", "rohan.modi@example.com", 1234567890, "Courier Staff", 50000);
        Employee employee2 = new Employee(2, "Ankush Khare", "ankush.khare@example.com", 1245789756, "Courier Staff", 55000);

        courierAdminService.addCourierStaff(employee1);
        courierAdminService.addCourierStaff(employee2);

        Courier courier1 = new Courier(1, "Rahul Sender", "Address1", "Priya Receiver", "Address2",
                2.5, "Delivered", 123456, parseDate("01/01/2023"), 1);
        Courier courier2 = new Courier(2, "Amit Sender", "Address3", "Sonia Receiver", "Address4",
                3.0, "In Transit", 654321, parseDate("02/01/2023"), 2);

        companyCollection.getCourierDetails().add(courier1);
        companyCollection.getCourierDetails().add(courier2);

        // Displaying the details of the CourierCompany (companyCollection)
        System.out.println("\nCourier Company Details:");
        System.out.println(companyCollection);

        // Accessing functionalities of CourierAdminServiceCollectionImpl class
        long trackingNumber1 = courierAdminService.placeOrder(courier1);
        System.out.println("Placed order with tracking number: " + trackingNumber1);

        String orderStatus1 = courierAdminService.getOrderStatus(trackingNumber1);
        System.out.println("Order status: " + orderStatus1);

        boolean cancelResult = courierAdminService.cancelOrder(trackingNumber1);
        System.out.println("Cancel result: " + cancelResult);

        boolean assignResult = courierAdminService.assignCourier(trackingNumber1, 1); // assuming courier staff ID 1
        System.out.println("Assign result: " + assignResult);

        boolean markDeliveredResult = courierAdminService.markOrderDelivered(trackingNumber1);
        System.out.println("Mark delivered result: " + markDeliveredResult);

        List<Long> assignedOrders = courierAdminService.getAssignedOrders(1); // assuming courier staff ID 1
        System.out.println("Assigned orders: " + assignedOrders);

        // Adding more test cases as needed

        // Rest of your main method code...
    }

    // Helper method for parsing date strings
    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
