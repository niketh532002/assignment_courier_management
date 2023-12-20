package Exception_Handling;
import java.util.Scanner;

public class ExceptionHandlingTrackingNumber {

    public static void main(String[] args) {
        String[][] trackingInformation = {
                {"123456", "Parcel in transit"},
                {"789012", "Parcel out for delivery"},
                {"345678", "Parcel delivered"}
        };

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter parcel tracking number: ");
        String inputTrackingNumber = sc.nextLine();

        try {
            // Check if the tracking number exists in the array
            String stats = getStatusByTrackingNumber(trackingInformation, inputTrackingNumber);
            displayStatusMessage(stats);
        } catch (TrackingNumberNotFoundException e) {
            System.out.println("Handling TrackingNumberNotFoundException: " + e.getMessage());
        }
        finally {
            System.out.println("Finally block executed.");
        }

        // Close the scanner class
        sc.close();
    }

    // Method to get status by tracking number
    private static String getStatusByTrackingNumber(String[][] parcelData, String trackingNumber) throws TrackingNumberNotFoundException {
        for (String[] parcel : parcelData) {
            if (parcel[0].equals(trackingNumber)) {
                return parcel[1];
            }
        }
        throw new TrackingNumberNotFoundException("Tracking number not found: " + trackingNumber);
    }

    // Method to display status messages based on tracking number status
    private static void displayStatusMessage(String statusOfParcel) {
        switch (statusOfParcel.toLowerCase()) {
            case "parcel in transit":
                System.out.println("Parcel is in transit.");
                break;
            case "parcel out for delivery":
                System.out.println("Parcel is out for delivery.");
                break;
            case "parcel delivered":
                System.out.println("Parcel has been delivered.");
                break;
            default:
                System.out.println("Invalid status.");
        }
    }
}

class TrackingNumberNotFoundException extends RuntimeException {

    public TrackingNumberNotFoundException(String message) {
        super(message);
    }
}
