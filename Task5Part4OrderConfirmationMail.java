package Java_Strings_2DArrays_Userdefinedfunction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Task5Part4OrderConfirmationMail {
         // Generate an order confirmation email.
        private static String generateOrderConfirmEmail(String customerName, int orderNumber, String deliveryAddress, Date expectedDeliveryDate) {
            // Format the expected delivery date
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
            String formattedDeliveryDate = dateFormat.format(expectedDeliveryDate);

            // Construct the order confirmation email
            StringBuilder emailBuild = new StringBuilder();
            emailBuild.append("Dear ").append(customerName).append(",\n\n");
            emailBuild.append("Thank you for your order! Your order confirmation details are as follows:\n\n");
            emailBuild.append("Your Order Number as : ").append(orderNumber).append("\n");
            emailBuild.append("Your Delivery Address as : ").append(deliveryAddress).append("\n");
            emailBuild.append("And Expected Delivery Date is as : ").append(formattedDeliveryDate).append("\n\n");
            emailBuild.append("If you have any questions, please contact us.\n\n");
            emailBuild.append("Sincerely,\nThe Order Confirmation Team");

            return emailBuild.toString();
        }
    public static void main(String[] args) {

        Date expectedDeliveryDate = new Date(); // Use the current date as an example

        // Scanner for user input
        Scanner sc = new Scanner(System.in);

        // Prompt user input for name
        System.out.print("Enter the customer name : ");
        String custName = sc.nextLine();

        System.out.print("Enter the delivery address : ");
        String deliveryAddress = sc.nextLine();

        System.out.print("Enter order number of the product : ");
        int ordNumber = sc.nextInt();

        // Generate order confirmation email
        String confirmationEmail = generateOrderConfirmEmail(custName, ordNumber, deliveryAddress, expectedDeliveryDate);

        // Print the generated email
        System.out.println(confirmationEmail);
    }
    }


