package Java_Strings_2DArrays_Userdefinedfunction;
import java.util.Scanner;
public class Task5Part2CustomerDataValidation {
        /**
         * Inorder to check the customer information based on specified criteria.
         * It will return the detail in form of information (name, address, phone number)
         */
        private static void validateCustomerInfo(String data, String detail) {
            switch (detail.toLowerCase()) {
                case "name":
                    if (isValidName(data)) {
                        System.out.println("Name is valid and it's value is : " + data);
                    } else {
                        System.out.println("Invalid name: " + data);
                    }
                    break;
                case "address":
                    if (isValidAddress(data)) {
                        System.out.println("Address is valid and it's value is : " + data);
                    } else {
                        System.out.println("Invalid address: " + data);
                    }
                    break;
                case "phone number":
                    if (isValidPhoneNumber(data)) {
                        System.out.println("Phone number is valid and it's value is : " + data);
                    } else {
                        System.out.println("Invalid phone number: " + data);
                    }
                    break;
                default:
                    System.out.println("Invalid detail type.");
            }
        }

        /**
         * Inorder to check if the name contains only letters and is properly capitalized.
         * It will return True if the name is valid, false otherwise
         */
        private static boolean isValidName(String name) {
            return name.matches("[A-Za-z]+\\s[A-Za-z]+");
        }

        /**
         * Inorder to check if the address does not contain special characters.
         * Here it will return  True if the address is valid, false otherwise
         */
        private static boolean isValidAddress(String address) {
            return address.matches("[\\w\\s]+");
        }

        /*
         * Inorder to check if the phone number follows the specific format (###-###-####).
         * It will return True if the phone number is valid, false otherwise
         */
        private static boolean isValidPhoneNumber(String phoneNumber) {
            return phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}");
        }

    public static void main(String[] args) {

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Prompt user input for name
        System.out.print("Enter name of the customer : ");
        String name = sc.nextLine();

        System.out.print("Enter address of the customer : ");
        String address = sc.nextLine();

        System.out.print("Enter phone number of the customer : ");
        String phoneNumber = sc.nextLine();

        validateCustomerInfo(name, "name");
        validateCustomerInfo(address, "address");
        validateCustomerInfo(phoneNumber, "phone number");
    }
}


