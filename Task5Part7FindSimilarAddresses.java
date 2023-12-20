package Java_Strings_2DArrays_Userdefinedfunction;

import java.util.Scanner;

public class Task5Part7FindSimilarAddresses {

         // Check if two addresses are similar.
        private static boolean areAddressSimilar(String address1, String address2) {
            // Normalize addresses by removing spaces and converting to lowercase
            String normalizedAddress1 = address1.replaceAll("\\s", "").toLowerCase();
            String normalizedAddress2 = address2.replaceAll("\\s", "").toLowerCase();

            // Use a similarity threshold based on your requirements
            double similarThreshold = 0.8;

            // Calculate Jaccard similarity between normalized addresses
            double jaccardSimilarity = calculateJaccardSimilarity(normalizedAddress1, normalizedAddress2);

            // Compare Jaccard similarity with the threshold
            return jaccardSimilarity >= similarThreshold;
        }


         // Calculate Jaccard similarity between two strings.
        private static double calculateJaccardSimilarity(String str1, String str2) {
            int intersectSize = 0;
            int unionSize = Math.max(str1.length(), str2.length());

            for (char c : str1.toCharArray()) {
                if (str2.indexOf(c) != -1) {
                    intersectSize++;
                }
            }

            return (double) intersectSize / unionSize;
        }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the source address : ");
        String address1 = scanner.nextLine();

        System.out.print("Enter the destination address : ");
        String address2 = scanner.nextLine();

        System.out.print("Enter the destination address : ");
        String address3 = scanner.nextLine();

        // Check similarity between addresses
        boolean areSimilar = areAddressSimilar(address1, address2);
        System.out.println("Are address1 and address2 similar? " + areSimilar);

        areSimilar = areAddressSimilar(address1, address3);
        System.out.println("Are address1 and address3 similar? " + areSimilar);
    }
    }

