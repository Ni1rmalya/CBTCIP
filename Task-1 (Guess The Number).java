import java.util.Random;
import java.util.Scanner;
public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10;
        boolean playAgain = true;
        int totalScore = 0;
        System.out.println("Welcome to Guess the Number!");
        while (playAgain) {
            int numToGuess = random.nextInt(100) + 1;
            int numOfAttempts = 0;
            boolean hasWon = false;

            System.out.println("\nI have randomly chosen a number between 1 and 100.");
            System.out.println("Try to guess it in 10 attempts or less.");

            while (numOfAttempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                numOfAttempts++;

                if (guess < numToGuess) {
                    System.out.println("It's higher than " + guess);
                } else if (guess > numToGuess) {
                    System.out.println("It's lower than " + guess);
                } else {
                    hasWon = true;
                    break;
                }
            }
            if (hasWon) {
                int points = maxAttempts - numOfAttempts + 1;
                totalScore += points;
                System.out.println("Congratulations! You guessed the number in " + numOfAttempts + " attempts.");
                System.out.println("You earned " + points + " points. Total score: " + totalScore);
            } else {
                System.out.println("Game Over! The number was " + numToGuess);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = sc.next().equalsIgnoreCase("yes");
        }
        System.out.println("Thanks for playing! Your total score is: " + totalScore);
        sc.close();
    }
}
