package ServiceProvider_Interface;
import java.util.List;
import java.util.Date;
import com.hexaware.entities.Courier;
import com.hexaware.entities.Employee;

public interface ICourierUserService {

    long placeOrder(Courier courierObj);

    String getOrderStatus(long trackingNumber);

    boolean cancelOrder(long trackingNumber);

    boolean assignCourier(long trackingNumber, int courierStaffId);

    boolean markOrderDelivered(long trackingNumber);

    List<Long> getAssignedOrders(int courierStaffId);
}



