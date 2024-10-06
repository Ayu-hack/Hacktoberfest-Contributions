public class SingleNonDuplicate {
    public static int findSingleNonDuplicate(int[] nums) {
        // Initialize two pointers for binary search
        int low = 0;
        int high = nums.length - 1;
        
        // Binary search loop
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // Check if mid is even
            if (mid % 2 == 1) {
                mid--; // Ensure mid is even so that pairs are aligned correctly
            }

            // Compare mid with the next element
            if (nums[mid] == nums[mid + 1]) {
                // If they are equal, the single element must be in the second half
                low = mid + 2;
            } else {
                // Otherwise, the single element is in the first half
                high = mid;
            }
        }
        
        // After the loop, low will point to the single element
        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 5, 5};
        System.out.println("Single non-duplicate element: " + findSingleNonDuplicate(nums));
    }
}
