package Java_Strings_2DArrays_Userdefinedfunction;
import java.util.Scanner;

public class Task5Part1ParcelTracking {

    public static void main(String[] args) {
        String[][] rawparcelData = {
                {"123456", "Parcel in transit"},
                {"789012", "Parcel out for delivery"},
                {"345678", "Parcel delivered"}
        };



        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter parcel tracking number: ");
        String inputTrackingNumber = sc.nextLine();

        // Check if the tracking number exists in the array
        boolean found = false;
        for (String[] parcel : rawparcelData) {
            if (parcel[0].equals(inputTrackingNumber)) {
                found = true;
                displayStatusMessage(parcel[1]);
                break;
            }
        }

        if (!found) {
            System.out.println("Tracking number not found in database.");
        }

        // Close the scanner class
        sc.close();
    }

    // Method to display status messages based on tracking number status
    private static void displayStatusMessage(String statusofParcel) {
        switch (statusofParcel.toLowerCase()) {
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
