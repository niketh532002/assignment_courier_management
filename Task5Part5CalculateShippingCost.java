package Java_Strings_2DArrays_Userdefinedfunction;
import java.util.Scanner;

public class Task5Part5CalculateShippingCost {

       // Calculate the shipping cost based on distance and parcel weight.
        private static double calculateShipCost(String sourceAddress, String destAddress, double parcelWeight) {
            // Assume a flat rate for shipping cost per kilogram and a fixed cost per kilometer
            double costPerKg = 2.5; // Example cost per kilogram
            double costPerKm = 0.1; // Example cost per kilometer

            // Calculate the distance between source and destination addresses (for illustration purposes)
            double distanceInKm = calculateDist(sourceAddress, destAddress);

            // Calculate the total shipping cost
            double totalShipCost = costPerKg * parcelWeight + costPerKm * distanceInKm;

            return totalShipCost;
        }

         // Calculate the distance between two locations.
        private static double calculateDist(String sourceAddress, String destAddress) {
            return Math.random() * 100;
        }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the source address : ");
        String sourceAddress = scanner.nextLine();

        System.out.print("Enter the destination address : ");
        String destAddress = scanner.nextLine();

        System.out.print("Enter the parcel weight of the product : ");
        double parcelWeight = scanner.nextDouble();

        double shippingCost = calculateShipCost(sourceAddress, destAddress, parcelWeight);
        System.out.println("Shipping Cost is as : ₹" + shippingCost);
    }

}

