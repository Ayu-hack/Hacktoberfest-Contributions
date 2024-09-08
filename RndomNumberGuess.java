
import java.util.Random;
import java.util.Scanner;

public class RandomNumberGuess {

    public static void main(String[] args) {
        // TODO auto-generated method

        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name??");
        String name = scan.nextLine();

        System.out.println("Well," + name.toUpperCase() + " I am thinking of a number between 1 & 100");
        System.out.println("So you have a maximum guess of 6 trials");
        System.out.println("letssss goooo!!!!!");
        int myNumber = getRandomNumber(1, 101);

        for (int i = 1; i < 7; i++) {
            Scanner scan2 = new Scanner(System.in);
            System.out.println("Your guess starts here \r\n Attempt number: " + i);
            int yourGuess = scan2.nextInt();

            if (yourGuess == myNumber) {
                System.out.println("you guessed correctly");
                break;
            } else if (yourGuess < myNumber) {
                System.out.println("your guess is too low");
                System.out.println("\n ");
            } else if (yourGuess > myNumber) {
                System.out.println("your guess is too high");
                System.out.println("\n ");
            }
            if (i >= 6) {
                // for limited number of tries
                System.out.println();
                System.out.println("Nope the number I was thinking is " + myNumber + "!!!!");
            }

        }
    }

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();

    }

}
