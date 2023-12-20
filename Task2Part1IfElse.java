package Java_Control_Flow_Statements;
import java.util.Scanner;

public class Task2Part1IfElse {
    public static void main(String[] args) {
        //Note order status can only be either Delivered, Cancelled or Processing
        Scanner stringScanner = new Scanner(System.in);
        System.out.println("Kindly enter the order Status : ");
        String orderStatus = stringScanner.next();
        if ("Processing".equals(orderStatus)) {
            System.out.println("Status: Processing");
        } else if ("Delivered".equals(orderStatus)) {
            System.out.println("Status: Delivered");
        } else if ("Cancelled".equals(orderStatus)) {
            System.out.println("Status: Cancelled");
        } else {
            System.out.println("Invalid order status. Please enter Processing, Delivered, or Cancelled.");
        }
    }
}
