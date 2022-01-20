package bullscows;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        execute();

    }

    private static void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String input = scanner.nextLine();
        int length = 0;
        int range = 0;
        if (input.matches("\\d+") && Integer.parseInt(input) > 0 && Integer.parseInt(input) < 37) {
            length = Integer.parseInt(input);
        } else {
            System.out.println("Error: \"" + input + "\" isn't a valid number.");
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        input = scanner.nextLine();
        if (input.matches("\\d+") && Integer.parseInt(input) > 0 && Integer.parseInt(input) < 37) {
            range = Integer.parseInt(input);
            if (length > range) {
                System.out.print("Error: it's not possible to generate a code with a length of ");
                System.out.println(length + " with " + range + " unique symbols.");
                return;
            }
        } else {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        String secretCode = secretCode(length, range);
        char[] secret = new char[length];
        char[] answer = new char[length];
        for (int i = 0; i < length; i++) {
            secret[i] = secretCode.charAt(i);
        }
        System.out.print("The secret is prepared: ");
        System.out.print("*".repeat(length));
        if (range == 1) {
            System.out.println(" (0).");
        } else if (range > 1 && range < 11) {
            System.out.println(" (0-" + (range -1) + ").");
        } else if (range == 11) {
            System.out.println(" (0-9, a).");
        } else if (range > 11 && range <= 36) {
            System.out.println(" (0-9, a-" + (char) ('a' - 11 + range) + ").");
        }
        System.out.println("Okay, let's start a game!");
        int bulls = 0;
        int turn = 1;
        do {
            int cows = 0;
            bulls = 0;
            System.out.println("Turn " + turn++ + ". Answer:");
            input = scanner.next();
            if (notValid(input, length, range)) {
                System.out.println("Error: \"" + input + "\" isn't a valid.");

                break;
            }
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
        if (bulls == length) {
            System.out.print("Congratulations! You guessed the secret code.");
        }
    }

    private static boolean notValid(String input, int length, int range) {
        if (input.length() != length) {
            return true;
        }
        String temp = ("0123456789abcdefghijklmnopqrstuvwxyz").substring(0, range);
        for (int i = 0; i < length; i++) {
            if (!temp.contains(String.valueOf(input.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    private static String secretCode(int length, int range) {
        Random random = new Random();
        String result = new String("");
        final String temp = new String("0123456789abcdefghijklmnopqrstuvwxyz");
        do {
            char ch = temp.charAt(random.nextInt(range));
            result = result.indexOf(ch) < 0 ? result + ch : result;
        } while (result.length() < length);
        return result;
    }
}