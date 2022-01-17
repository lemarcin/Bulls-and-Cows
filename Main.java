package bullscows;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int length = scanner.nextInt();
        if (length > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
        } else {

            int secretCode = secretCode(length);
            int[] secret = new int[length];
            int[] answer = new int[length];
            System.out.println("Okay, let's start a game!");
            int bulls = 0;
            int turn = 1;
            for (int i = length - 1; i > -1; i--) {
                secret[i] = secretCode % 10;
                secretCode /= 10;
            }
            do {
                int cows = 0;
                bulls = 0;
                System.out.printf("Turn %d. Answer:\n", turn++);
                int input = scanner.nextInt();
                for (int i = length - 1; i > -1; i--) {
                    answer[i] = input % 10;
                    input /= 10;
                }
                for (int i = 0; i < length; i++) {
                    for (int j = 0; j < length; j++) {
                        if (answer[i] == secret[j]) {
                            cows++;
                            if (answer[i] == secret[i]) {
                                cows--;
                                bulls++;
                            }
                            break;
                        }
                    }
                }
                System.out.print("Grade: ");
                if (cows == 0 && bulls == 0) System.out.print("None");
                else if (bulls == 1) System.out.print("1 bull");
                else if (bulls > 1) System.out.print(bulls + " bulls");
                if (cows > 0 && bulls > 0) System.out.print(" and ");
                if (cows == 1) System.out.print("1 cow");
                else if (cows > 1) System.out.print(cows + " cows");
                System.out.println(".");
            } while (bulls < length);
            System.out.print("Congratulations! You guessed the secret code.");
        }
    }


    public static int secretCode(int length) {
        String result = new String("");
        do {
            String pseudoRandom = String.valueOf((long) System.nanoTime()).replaceAll("0*$", "");
            for (int i = pseudoRandom.length() - 1; i >= 0; i--) {
                if (result.indexOf(pseudoRandom.charAt(i)) < 0) {
                    result += pseudoRandom.charAt(i);
                }
                if (result.length() == length) {
                    break;
                }
            }
        } while (result.length() < length);
        return Integer.parseInt(result);
    }
}