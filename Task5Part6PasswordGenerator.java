package Java_Strings_2DArrays_Userdefinedfunction;
import java.security.SecureRandom;

public class Task5Part6PasswordGenerator {

//          Generate a secure password with a mix of uppercase letters, lowercase letters,
//          numbers, and special characters.

        private static String generateSecurePassword(int length) {
            String uppercaseLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowercaseLetter = "abcdefghijklmnopqrstuvwxyz";
            String numbers = "0123456789";
            String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

            String allCharacter = uppercaseLetter + lowercaseLetter + numbers + specialCharacters;

            SecureRandom random = new SecureRandom();
            StringBuilder passwordBuilder = new StringBuilder();

            // Make Ensure at least one character from each category is included
            passwordBuilder.append(uppercaseLetter.charAt(random.nextInt(uppercaseLetter.length())));
            passwordBuilder.append(lowercaseLetter.charAt(random.nextInt(lowercaseLetter.length())));
            passwordBuilder.append(numbers.charAt(random.nextInt(numbers.length())));
            passwordBuilder.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

            // Fill the remaining length with random characters from all categories
            for (int i = 4; i < length; i++) {
                passwordBuilder.append(allCharacter.charAt(random.nextInt(allCharacter.length())));
            }

            // Shuffle the password characters to enhance security
            return shuffleString(passwordBuilder.toString());
        }


//         Shuffle the characters in a string.
        private static String shuffleString(String input) {
            char[] characters = input.toCharArray();
            SecureRandom random = new SecureRandom();

            for (int i = characters.length - 1; i > 0; i--) {
                int index = random.nextInt(i + 1);
                char temp = characters[index];
                characters[index] = characters[i];
                characters[i] = temp;
            }

            return new String(characters);
        }
    public static void main(String[] args) {
        // Example usage to generate a password of length 12
        String generatedPassword = generateSecurePassword(12);
        System.out.println("Generated Password is as : " + generatedPassword);
    }
    }

