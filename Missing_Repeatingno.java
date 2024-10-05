import java.util.Arrays;

public class MissingAndRepeating {
    public static void findMissingAndRepeating(int[] arr, int n) {
       
        long S1 = (long) n * (n + 1) / 2;
        long S2 = (long) n * (n + 1) * (2 * n + 1) / 6;

        // Calculate actual sum and sum of squares from the array
        long actualSum = 0, actualSumSquares = 0;
        for (int i = 0; i < n; i++) {
            actualSum += arr[i];
            actualSumSquares += (long) arr[i] * arr[i];
        }

        // Solve the equations
        long diffSum = S1 - actualSum; // M - R
        long diffSumSquares = S2 - actualSumSquares; // M^2 - R^2

        // M^2 - R^2 = (M - R)(M + R) => diffSumSquares = diffSum * (M + R)
        long sumMR = diffSumSquares / diffSum; // M + R

        // Now we have:
        // M - R = diffSum
        // M + R = sumMR

        long missing = (diffSum + sumMR) / 2;
        long repeating = sumMR - missing;

        System.out.println("Missing Number: " + missing);
        System.out.println("Repeating Number: " + repeating);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 5, 6, 2}; // Example array
        int n = arr.length;
        findMissingAndRepeating(arr, n);
    }
}
