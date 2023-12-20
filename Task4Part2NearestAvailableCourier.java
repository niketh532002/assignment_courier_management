package Java_Arrays_and_DataStructures;

public class Task4Part2NearestAvailableCourier {
        public static void main(String[] args) {
            // Example usage
            double custLocation = 27.0;
            double[] courierLocations = { 11.0, 28.0, 39.0, 48.0, 21.0 };

            int nearestCourierIndexLocation = findNearestCourier(custLocation, courierLocations);

            if (nearestCourierIndexLocation != -1) {
                System.out.println("Nearest available courier index is as: " + nearestCourierIndexLocation);
            } else {
                System.out.println("No couriers available");
            }
        }

        /**
         * Find the index of the nearest available courier based on customer location.
         *
         * @param custLocation   The location of the customer
         * @param courierLocations   An array of courier locations
         * @return                    The index of the nearest available courier, or -1 if no available couriers
         */
        private static int findNearestCourier(double custLocation, double[] courierLocations) {
            if (courierLocations.length == 0) {
                return -1; // No couriers available
            }

            double minDistance = Math.abs(courierLocations[0] - custLocation);
            int nearestCourierIndexLocation = 0;

            for (int i = 1; i < courierLocations.length; i++) {
                double distance = Math.abs(courierLocations[i] - custLocation);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCourierIndexLocation = i;
                }
            }

            return nearestCourierIndexLocation;
        }
    }

//    This output is based on the example input where the customer's location is 15.0, and the array of courier locations
//        is {11.0, 28.0, 39.0, 48.0, 21.0}. The courier at index 0 (location 11.0) has the minimum distance from the
//        customer's location, so the program prints the index 0 as the nearest available courier index.
//Like if the customer location is 27.0 then the array of courier location at index 1 (location 28.0) has the minimum
//distance from the customer's location so the program prints the index 1 as the nearest available courier index.

