import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Provides methods to calculate the Levenshtein distance between two strings.
 *
 * The Levenshtein distance is a measure of similarity between two strings by calculating
 * the minimum number of single-character edits (insertions, deletions, or substitutions)
 * required to transform one string into the other.
 */
public final class LevenshteinDistance {
    private LevenshteinDistance() {
    }

    /**
     * Calculates the Levenshtein distance between two strings using a naive dynamic programming approach.
     *
     * @param source The source string.
     * @param target The target string.
     * @return The Levenshtein distance between the two input strings.
     */
    public static int calculateNaiveLevenshteinDistance(final String source, final String target) {
        // Initialize a matrix to store distances between substrings of source and target
        int[][] dpMatrix = IntStream.rangeClosed(0, source.length())
                .mapToObj(i -> IntStream.rangeClosed(0, target.length())
                .map(j -> (i == 0) ? j : (j == 0) ? i : 0)
                .toArray()).toArray(int[][]::new);

        // Fill the matrix using the recurrence relation
        IntStream.range(1, source.length() + 1).forEach(i -> 
            IntStream.range(1, target.length() + 1).forEach(j -> {
                final int cost = (source.charAt(i - 1) == target.charAt(j - 1)) ? 0 : 1;
                dpMatrix[i][j] = Math.min(dpMatrix[i - 1][j - 1] + cost,
                        Math.min(dpMatrix[i][j - 1] + 1, dpMatrix[i - 1][j] + 1));
            })
        );

        return dpMatrix[source.length()][target.length()];
    }

    /**
     * Calculates the Levenshtein distance between two strings using an optimized dynamic programming approach.
     *
     * @param source The source string.
     * @param target The target string.
     * @return The Levenshtein distance between the two input strings.
     */
    public static int calculateOptimizedLevenshteinDistance(final String source, final String target) {
        // Handle edge case where source string is empty
        if (source.isEmpty()) {
            return target.length();
        }

        // Initialize an array to store distances for the current row
        int[] currentDistances = IntStream.rangeClosed(0, source.length()).toArray();

        // Iterate over each character in the target string
        for (int j = 1; j <= target.length(); j++) {
            int previousDistance = currentDistances[0];
            currentDistances[0] = j;

            // Iterate over each character in the source string
            for (int i = 1; i <= source.length(); i++) {
                final int deletionCost = currentDistances[i] + 1;
                final int insertionCost = currentDistances[i - 1] + 1;
                final int substitutionCost = (source.charAt(i - 1) == target.charAt(j - 1)) ? previousDistance : previousDistance + 1;
                previousDistance = currentDistances[i];
                currentDistances[i] = Math.min(deletionCost, Math.min(insertionCost, substitutionCost));
            }
        }

        return currentDistances[source.length()];
    }

    /**
     * Main method for taking user inputs and testing the Levenshtein distance calculation methods.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user inputs for source and target strings
        System.out.println("Enter the first string:");
        String source = scanner.nextLine();

        System.out.println("Enter the second string:");
        String target = scanner.nextLine();

        // Calculate and display the Levenshtein distance using the naive approach
        int naiveDistance = calculateNaiveLevenshteinDistance(source, target);
        System.out.printf("Naive Levenshtein distance between \"%s\" and \"%s\" is: %d%n", source, target, naiveDistance);

        // Calculate and display the Levenshtein distance using the optimized approach
        int optimizedDistance = calculateOptimizedLevenshteinDistance(source, target);
        System.out.printf("Optimized Levenshtein distance between \"%s\" and \"%s\" is: %d%n", source, target, optimizedDistance);

        // Close the scanner
        scanner.close();
    }
}
