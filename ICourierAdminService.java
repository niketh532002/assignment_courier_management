package ServiceProvider_Interface;

import com.hexaware.entities.Employee;

import java.util.Date;

public interface ICourierAdminService {

    int addCourierStaff(Employee obj);

    boolean removeCourierStaff(int courierStaffId);

    String generateDeliveryReport(Date startDate, Date endDate);
}
