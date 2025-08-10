import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    static final int MAX_ATTEMPTS = 7;
    static final int MAX_ROUNDS = 5;
    static final int RANGE_MIN = 1;
    static final int RANGE_MAX = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int round = 1;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            System.out.println("\nRound " + round + " begins!");
            int score = playSingleRound(scanner);
            if (score > 0) {
                totalScore += score;
                System.out.println("Round " + round + " complete! You earned " + score + " points.");
            } else {
                System.out.println("You ran out of attempts! No points this round.");
            }

            round++;

            if (round > MAX_ROUNDS) {
                System.out.println("\nYou've reached the maximum number of rounds.");
                break;
            }

            System.out.print("Want to play another round? (yes/no): ");
        } while (scanner.next().trim().equalsIgnoreCase("yes"));

        System.out.println("\nGame Over! Your total score is: " + totalScore);
        scanner.close();
    }

    private static int playSingleRound(Scanner scanner) {
        Random random = new Random();
        int target = random.nextInt(RANGE_MAX - RANGE_MIN + 1) + RANGE_MIN;
        int attemptsLeft = MAX_ATTEMPTS;
        int score = 0;

        while (attemptsLeft > 0) {
            System.out.print("Enter your guess (" + RANGE_MIN + " to " + RANGE_MAX + "): ");
            int guess;

            try {
                guess = Integer.parseInt(scanner.next().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (guess < RANGE_MIN || guess > RANGE_MAX) {
                System.out.println("Guess out of range! Try again.");
                continue;
            }

            attemptsLeft--;

            if (guess == target) {
                score = attemptsLeft + 1;
                System.out.println("Correct! You guessed the number.");
                break;
            } else if (guess < target) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }

            if (attemptsLeft > 0) {
                System.out.println("Attempts left: " + attemptsLeft);
            }
        }

        return score;
    }
}