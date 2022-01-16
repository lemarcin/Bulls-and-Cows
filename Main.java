package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        if (length > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", length);
        } else {
            String result = new String("");
            do {
                String pseudoRandom = String.valueOf((long) System.nanoTime()).replaceAll("0*$", "");
                for (int i = pseudoRandom.length() - 1; i >= 0; i--) {
                    if (result.indexOf(pseudoRandom.charAt(i)) < 0 ) { result += pseudoRandom.charAt(i); }
                    if (result.length() == length) { break; }
                }
            } while (result.length() < length);
            System.out.println(result);
        }
    }
}