package Service_Implementation;

import ServiceProvider_Interface.CourierAdminServiceImplementation;
import ServiceProvider_Interface.ICourierAdminService;
import com.hexaware.entities.Employee;
import com.hexaware.entities.CourierCompany;

import com.hexaware.entities.Courier;
import com.hexaware.entities.Location;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourierAdminServiceImpl extends CourierUserServiceImpl implements ICourierAdminService {
    public CourierAdminServiceImpl(CourierCompany companyObj) {
        super(companyObj); // Call the constructor of the superclass with the required argument
    }

    private static int lastAssignedEmployeeID = 1000; // Initialize with a starting value
    private List<Employee> courierStaffList;
    private List<Courier> courierList; // Assuming this list holds all courier orders


    @Override
    public int addCourierStaff(Employee obj) {
        int newEmployeeID = generateUniqueEmployeeID();
        obj.setEmployeeID(newEmployeeID);
        courierStaffList.add(obj);
        return newEmployeeID;
    }

    @Override
    public boolean removeCourierStaff(int courierStaffId) {
        Employee employeeToRemove = findEmployeeById(courierStaffId);
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

        for (Employee employee : courierStaffList) {
            List<String> deliveredOrders = getDeliveredOrdersInDateRange(employee.getEmployeeID(), startDate, endDate);

            if (!deliveredOrders.isEmpty()) {
                report.append("Employee ID: ").append(employee.getEmployeeID())
                        .append(", Name: ").append(employee.getEmployeeName())
                        .append("\nDelivered Orders:\n").append(String.join("\n", deliveredOrders))
                        .append("\n\n");
            }
        }

        return report.toString();
    }

    private List<String> getDeliveredOrdersInDateRange(long employeeID, Date startDate, Date endDate) {
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


    private int generateUniqueEmployeeID() {
        lastAssignedEmployeeID++;
        return lastAssignedEmployeeID;
    }

    private Employee findEmployeeById(int employeeId) {
        for (Employee employee : courierStaffList) {
            if (employee.getEmployeeID() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    public static void main(String[] args) {
    // Here CourierAdminService Impl class functionalities starts from
    //which implements ICourierAdminService interface.
    List<Employee> courierStaffList = new ArrayList<>();
    List<Courier> courierList = new ArrayList<>();
    //Code for accessing functionalities of CourierUserServiceImpl class starts
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

        CourierAdminServiceImpl courierAdminService = new CourierAdminServiceImpl(company);
        Courier courier1 = Courier.createCourier();
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

        //Here for above code we can see like child class object(CourierAdminServiceImpl) is easily able to access
        //Parent class (CourierUserServiceImpl) with the object name as courierAdminService
        //Code for accessing functionalities of CourierUserServiceImpl class ends

         ICourierAdminService courierAdminService1 = new CourierAdminServiceImplementation(courierStaffList, courierList);
    Employee employee1 = new Employee(1, "Rohan Modi", "rohan.modi@example.com", 1234567890, "Courier Staff", 50000);
    Employee employee2 = new Employee(2, "Ankush Khare", "ankush.khare@example.com", 1245789756, "Courier Staff", 55000);
        courierAdminService1.addCourierStaff(employee1);
        courierAdminService1.addCourierStaff(employee2);
    Courier courier3 = new Courier(1, "Rahul Sender", "Address1", "Priya Receiver", "Address2",
            2.5, "Delivered", 123456, parseDate("01/01/2023"), 1);
    Courier courier2 = new Courier(2, "Amit Sender", "Address3", "Sonia Receiver", "Address4",
            3.0, "In Transit", 654321, parseDate("02/01/2023"), 2);
        courierList.add(courier3);
        courierList.add(courier2);

    Employee newEmployee = new Employee();
        newEmployee.setEmployeeID(3);
        newEmployee.setEmployeeName("Mike Johnson");
        newEmployee.setEmail("mike@example.com");
        newEmployee.setContactNumber(8765432109L);
        newEmployee.setRole("Courier Staff");
        newEmployee.setSalary(60000);

        System.out.println("Generated Employee IDs: "+employee1.getEmployeeID()+", "+employee2.getEmployeeID());
        System.out.println("Added Courier Staff: "+courierAdminService1.addCourierStaff(newEmployee));

        System.out.println("Removed Courier Staff with ID 1: "+courierAdminService1.removeCourierStaff(1));

    Date startDate = parseDate("01/01/2023");
    Date endDate = parseDate("03/01/2023");
    String deliveryReport = courierAdminService1.generateDeliveryReport(startDate, endDate);
        System.out.println(deliveryReport);
}
    // Helper method in order to parse date strings
    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

