import java.util.Scanner;
import java.util.Stack;

class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int answer[] = new int[n];
        for(int i=0; i<n; i++) {
            while(!stack.empty() && temperatures[i]>temperatures[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }
        return answer;
    }
    public static void main(String args[]) {
        Scanner sc= new Scanner(System.in);
        System.out.print("How many values do you want to add in temperatures array: ");
        int n = sc.nextInt();
        int temperatures[] = new int[n];
        System.out.print("Enter elements: ");
        for(int i=0; i<n; i++) {
            temperatures[i] = sc.nextInt();
        }
        sc.close();
        int result[] = dailyTemperatures(temperatures);
        System.out.print("\nResult: ");
        for(int i=0; i<n; i++) {
            System.out.print(result[i]+" ");
        }
    }
}