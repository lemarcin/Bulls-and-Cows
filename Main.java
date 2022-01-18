package bullscows;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int length = scanner.nextInt();
        if (length > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
        } else {

            String secretCode = secretCode(length);
            char[] secret = new char[length];
            char[] answer = new char[length];
            System.out.println("Okay, let's start a game!");
            int bulls = 0;
            int turn = 1;
            for (int i = 0; i < length; i++) {
                secret[i] = secretCode.charAt(i);
            }
            do {
                int cows = 0;
                bulls = 0;
                System.out.println("Turn " + turn++ + ". Answer:");
                String input = scanner.next();
                for (int i = 0; i < length; i++) {
                    answer[i] = input.charAt(i);
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


    public static String secretCode(int length){
        Random random = new Random();
        String result = new String("");
        do {
            char ch = (char) (random.nextInt(10) + '0');
            result = result.indexOf(ch) < 0 ? result + ch : result;
        } while (result.length() < length);
        return result;
    }
}