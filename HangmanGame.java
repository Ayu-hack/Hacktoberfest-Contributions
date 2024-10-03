import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private static final String[] WORDS = {
        "java", "programming", "hangman", "development", "computer", "language", "code"
    };
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String wordToGuess = getRandomWord();
        List<Character> guessedLetters = new ArrayList<>();
        int attemptsLeft = MAX_ATTEMPTS;

        System.out.println("Welcome to Hangman!");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the word.");

        while (attemptsLeft > 0) {
            displayWord(wordToGuess, guessedLetters);
            System.out.print("Guess a letter: ");
            char guess = scanner.nextLine().toLowerCase().charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("You've already guessed that letter. Try again.");
            } else {
                guessedLetters.add(guess);
                if (wordToGuess.indexOf(guess) == -1) {
                    attemptsLeft--;
                    System.out.println("Wrong guess! Attempts left: " + attemptsLeft);
                }
            }

            if (isWordGuessed(wordToGuess, guessedLetters)) {
                System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
                break;
            }
        }

        if (attemptsLeft == 0) {
            System.out.println("Game Over! The word was: " + wordToGuess);
        }

        scanner.close();
    }

    private static String getRandomWord() {
        Random random = new Random();
        return WORDS[random.nextInt(WORDS.length)];
    }

    private static void displayWord(String word, List<Character> guessedLetters) {
        StringBuilder display = new StringBuilder();
        for (char letter : word.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                display.append(letter).append(" ");
            } else {
                display.append("_ ");
            }
        }
        System.out.println("Word: " + display.toString().trim());
    }

    private static boolean isWordGuessed(String word, List<Character> guessedLetters) {
        for (char letter : word.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }
}
