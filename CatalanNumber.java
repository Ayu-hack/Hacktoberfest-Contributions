import java.util.Scanner;

/**
 * This file contains an implementation of finding the nth Catalan number using dynamic programming
 * <a href="https://en.wikipedia.org/wiki/Catalan_number">Wikipedia</a>
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 *
 * @author <a href="https://github.com/Rekklessss">Divyanshu Pabia</a>
 */
public final class CatalanNumber {
    private CatalanNumber() {
    }

    /**
     * Finds the nth Catalan number using dynamic programming.
     *
     * @param n the index of the Catalan number to calculate (n should be less than or equal to 50).
     * For n > 50, BigInteger should be used instead of long due to the large value of the Catalan number.
     *
     * @return the nth Catalan number
     */
    static long calculateCatalan(int n) {
        // Array to store calculated Catalan numbers from 0 to n
        long[] catalanNumbers = new long[n + 1];

        // Initializing base cases: C₀ = 1 and C₁ = 1
        catalanNumbers[0] = 1;
        catalanNumbers[1] = 1;

        // Calculate the nth Catalan number using the recurrence relation:
        // C_n = Σ (C_i * C_{n-1-i}), for i = 0 to n-1, n > 0
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                catalanNumbers[i] += catalanNumbers[j] * catalanNumbers[i - j - 1];
            }
        }

        return catalanNumbers[n];
    }

    // Main method to take user input and display the nth Catalan number
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the value of n to find the nth Catalan number (n <= 50):");
        int n = scanner.nextInt();
        System.out.println(n + "th Catalan number is " + calculateCatalan(n));

        scanner.close();
    }
}
