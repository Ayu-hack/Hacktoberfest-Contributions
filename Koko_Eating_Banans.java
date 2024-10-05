public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        // Left is the minimum possible speed, right is the maximum pile size
        int left = 1, right = Arrays.stream(piles).max().getAsInt();

        // Binary search to find the minimum speed
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, h, mid)) {
                right = mid; // Try smaller speed
            } else {
                left = mid + 1; // Increase speed
            }
        }
        return left;
    }

    // Helper function to calculate if Koko can eat all bananas within h hours at speed k
    private boolean canFinish(int[] piles, int h, int k) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + k - 1) / k; // Equivalent to Math.ceil(pile / k)
        }
        return hours <= h;
    }

    public static void main(String[] args) {
        KokoEatingBananas koko = new KokoEatingBananas();

        // Example 1
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println(koko.minEatingSpeed(piles1, h1));  // Output: 4

        // Example 2
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println(koko.minEatingSpeed(piles2, h2));  // Output: 30
    }
}
