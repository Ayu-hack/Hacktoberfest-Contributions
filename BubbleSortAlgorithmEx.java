public class BubbleSortAlgorithmEx {
    public static void main(String[] args) {
        int[] array ={6,10,1,8,7,5,2};

        System.out.println("Bubble Sort Algorithm");
        System.out.println("----------------------");
        System.out.println("All elements in the array : ");
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
        bubbleSort(array);

        System.out.println("Sorted Array : ");
        for (int j : array) {
            System.out.print(j + " ");
        }
    }
    static void bubbleSort(int[] array) {
        int n = array.length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(array[j-1] > array[j]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}