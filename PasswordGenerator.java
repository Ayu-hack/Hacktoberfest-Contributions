import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}|;:',.<>?/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        System.out.print("Enter the desired length of the password: ");
        int length = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUppercase = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLowercase = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Include digits? (y/n): ");
        boolean includeDigits = scanner.nextLine().equalsIgnoreCase("y");

        System.out.print("Include symbols? (y/n): ");
        boolean includeSymbols = scanner.nextLine().equalsIgnoreCase("y");

        String password = generatePassword(length, includeUppercase, includeLowercase, includeDigits, includeSymbols, random);
        System.out.println("Generated Password: " + password);

        scanner.close();
    }

    private static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
                                           boolean includeDigits, boolean includeSymbols, SecureRandom random) {
        StringBuilder characterSet = new StringBuilder();

        if (includeLowercase) {
            characterSet.append(LOWERCASE);
        }
        if (includeUppercase) {
            characterSet.append(UPPERCASE);
        }
        if (includeDigits) {
            characterSet.append(DIGITS);
        }
        if (includeSymbols) {
            characterSet.append(SYMBOLS);
        }

        if (characterSet.length() == 0) {
            throw new IllegalArgumentException("At least one character set must be selected.");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }

        return password.toString();
    }
}
