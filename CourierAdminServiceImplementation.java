package ServiceProvider_Interface;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.hexaware.entities.Employee;
import com.hexaware.entities.Courier;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class CourierAdminServiceImplementation implements ICourierAdminService {

    private List<Employee> courierStaffList;
    private static int lastAssignedEmployeeID = 1000; // Initialize with a starting value
    private List<Courier> courierList; // Assuming this list holds all courier orders


    // Constructor or initialization method where we basically initialize the courierStaffList
    public CourierAdminServiceImplementation(List<Employee> courierStaffList, List<Courier> courierList) {
        this.courierStaffList = courierStaffList;
        this.courierList = courierList;
    }

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
        List<Employee> courierStaffList = new ArrayList<>();
        List<Courier> courierList = new ArrayList<>();

        ICourierAdminService courierAdminService = new CourierAdminServiceImplementation(courierStaffList, courierList);

        Employee employee1 = new Employee(1, "John Doe", "john@example.com", 1234567890, "Courier Staff", 50000);
        Employee employee2 = new Employee(2, "Jane Doe", "jane@example.com", 1245789756, "Courier Staff", 55000);

        courierAdminService.addCourierStaff(employee1);
        courierAdminService.addCourierStaff(employee2);

        Courier courier1 = new Courier(1, "John Sender", "Address1", "Jane Receiver", "Address2",
                2.5, "Delivered", 123456, parseDate("01/01/2023"), 1);
        Courier courier2 = new Courier(2, "Alice Sender", "Address3", "Bob Receiver", "Address4",
                3.0, "In Transit", 654321, parseDate("02/01/2023"), 2);

        courierList.add(courier1);
        courierList.add(courier2);

        System.out.println("Generated Employee IDs: " + employee1.getEmployeeID() + ", " + employee2.getEmployeeID());

        Employee newEmployee = new Employee();
        newEmployee.setEmployeeID(3);
        newEmployee.setEmployeeName("Mike Johnson");
        newEmployee.setEmail("mike@example.com");
        newEmployee.setContactNumber(8765432109L);
        newEmployee.setRole("Courier Staff");
        newEmployee.setSalary(60000);

        System.out.println("Added Courier Staff: " + courierAdminService.addCourierStaff(newEmployee));

        System.out.println("Removed Courier Staff with ID 1: " + courierAdminService.removeCourierStaff(1));

        Date startDate = parseDate("01/01/2023");
        Date endDate = parseDate("03/01/2023");
        String deliveryReport = courierAdminService.generateDeliveryReport(startDate, endDate);
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