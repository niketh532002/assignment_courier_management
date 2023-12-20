package Java_Strings_2DArrays_Userdefinedfunction;
import java.util.Scanner;

public class Task5Part3AddressFormatting {

         // Capitalize the first letter of each word in a string.

        private static String capitalize(String input) {
            String[] words = input.split("\\s");
            StringBuilder result = new StringBuilder();

            for (String word : words) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }

            return result.toString().trim();
        }

         // Format the zip code by adding hyphen if not present.

        private static String formattingZipCode(String zipCode) {
            // Ensure the zip code is numeric
            if (zipCode.matches("\\d+")) {
                // Add hyphen if the length is 9 and there is no hyphen present
                if (zipCode.length() == 9 && !zipCode.contains("-")) {
                    return zipCode.substring(0, 5) + "-" + zipCode.substring(5);
                }
            }
            return zipCode;
        }

    private static String formattingAddress(String street, String city, String state, String zipCode) {
        // Capitalize the first letter of each word in street, city, and state
        street = capitalize(street);
        city = capitalize(city);
        state = capitalize(state);

        // Format the zip code
        zipCode = formattingZipCode(zipCode);

        // Concatenate the formatted parts to create the full address
        return street + ", " + city + ", " + state + " " + zipCode;
    }

    public static void main(String[] args) {

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Prompt user input for name
        System.out.print("Enter name of the street : ");
        String street = sc.nextLine();

        System.out.print("Enter name of the city : ");
        String city = sc.nextLine();

        System.out.print("Enter state of the city : ");
        String state = sc.nextLine();

        System.out.print("Enter ZipCode of the city : ");
        String zipCode = sc.nextLine();

        String formattedAddress = formattingAddress(street, city, state, zipCode);
        System.out.println("Entire Formatted Address is as : " + formattedAddress);
    }
}

