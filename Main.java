package bullscows;

import java.util.Arrays;



public class Main {

    public static void main(String[] args) {
        int[] secret = new int[4];
        int[] answer = new int[4];
        int[] input = new int[8];
        int bulls;
        int turn = 0;
        input[0] = 1234;
        input[1] = 5678;
        input[2] = 9012;
        input[3] = 9087;
        input[4] = 1087;
        input[5] = 9205;
        input[6] = 9305;
        input[7] = 9305;
        for (int i = 0; i < secret.length; i++) {
            secret[i] = input[7] % 10;
            input[7] /= 10;
        }
        System.out.println("The secret code is prepared: ****.");
        do {
            bulls = 0;
            int cows = 0;
            System.out.printf("\nTurn %d. Answer:\n", turn + 1);
            System.out.println(input[turn]);
            for (int i = 0; i < answer.length; i++) {
                answer[i] = input[turn] % 10;
                input[turn] /= 10;
                for (int j = secret.length - 1; j > -1; j--) {
                    if (secret[j] == answer[i]) {
                        cows++;
                        if (i == j) {
                            cows--;
                            bulls++;
                        }
                    }
                }
            }
            turn++;
            System.out.print("Grade: ");
            if (cows == 0 && bulls == 0) System.out.print("None");
            else if (cows == 1) System.out.print("1 cow");
            else if (cows > 1) System.out.print(cows + "cows");
            if (cows > 0 && bulls > 0) System.out.print(" and ");
            if (bulls == 1) System.out.print("1 bull");
            else if (bulls > 1) System.out.print(bulls + " bulls");
            System.out.println(".");
        } while (bulls < 4);
        System.out.print("Congrats! The secret code is ");
        System.out.print(Arrays.toString(secret).replaceAll("[\\[\\]\\,\\ ]", "") + ".");
    }
}