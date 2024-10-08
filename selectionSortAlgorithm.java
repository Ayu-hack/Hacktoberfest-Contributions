public class selectionSortAlgorithm {
  
    /**
     * Sort an array with selectionSort algorithm.
     * 
     * @param arr array to sort
     */
  
    public static void selectionSort(int[] arr) {
        var len = arr.length;

        for (var i = 0; i < len - 1; i++) {
            var minIndex = i;

            for (var j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            var tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }
    }
}
