package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int[] secret = new int[4];
        int[] answer = new int[4];
        int bulls = 0;
        int cows = 0;
        int input = scanner.nextInt();
        for (int i = answer.length -1; i > -1; i--) {
            secret[i] = random.nextInt(10);
            answer[i] = input % 10;
            input /= 10;
        }
        for (int i = 0; i < secret.length; i++) {
            for (int j = 0; j < secret.length; j++) {
                if (answer[i] == secret[j]) {
                    cows++;
                    if (answer[i] == secret[i]) {
                        cows--;
                        bulls++;
                    }
                    continue;
                }
            }
        }
        System.out.print("Grade: ");
        if (cows == 0 && bulls == 0) System.out.print("None");
        if (bulls > 0) System.out.print(bulls + " bull(s)");
        if (cows > 0 && bulls > 0) System.out.print(" and ");
        if (cows > 0) System.out.print(cows + " cow(s)");
        System.out.print(". ");
        System.out.print("The secret code is ");
        System.out.print(Arrays.toString(secret).replaceAll("[\\[\\]\\,\\ ]", "") + ".");
    }
}