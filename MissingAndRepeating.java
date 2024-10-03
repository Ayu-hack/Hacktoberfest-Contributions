/* Given an unsorted array arr of of positive integers. 
One number A from set [1, 2,....,n] is missing and one number B occurs twice in array. 
Find numbers A and B.

Input: arr[] = [2, 2]
Output: 2 1

Input: arr[] = [1, 3, 3] 
Output: 3 2

 */



public class MissingAndRepeating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {1, 3, 3};
		//int arr[] = {1, 2, 3}
		
		int repeating = 0, missing = 0;

        // Step 1: Identify the repeating number
        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]) - 1;
            if (arr[index] < 0) {
                repeating = Math.abs(arr[i]);
            } else {
                arr[index] = -arr[index];  // Mark as visited
            }
        }

        // Step 2: Identify the missing number
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {  // Positive means the index+1 is the missing number
                missing = i + 1;
            }
        }
        if(missing != 0) System.out.println("Missing number is : " + missing);
        else System.out.println("No missing number found");
        if(repeating != 0) System.out.println("Repeating number is : " + repeating);
        else System.out.println("No repeating number found");
		
	}

}
