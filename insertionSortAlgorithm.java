public class insertionSortAlgorithm {
  
    /**
     * Sort an array with insertionSort algorithm.
     * 
     * @param arr array to sort
     */

    public static void insertionSort(int[] arr) {
        for (var i = 1; i < arr.length; i++) {
            var tmp = arr[i];
            var j = i - 1;
        
            while (j >= 0 && arr[j] > tmp) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = tmp;
        }
    }
}
