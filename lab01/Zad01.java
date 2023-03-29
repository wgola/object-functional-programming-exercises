package lab01;

import java.util.Scanner;

class Zad01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Program calculating value of n! (factorial) using for-loop.");

        boolean run = true;
        while (run) {
            System.out.print("Enter natural number: ");

            int n = in.nextInt();
            if (n < 0) {
                System.out.println("Provide correct number!");
            } else {
                long calculatedFactorial = factorial(n);

                System.out.println("Factorial of " + n + " equals " + calculatedFactorial);
                System.out.print("Do you want to calculate another factorial? (Y/N) ");

                in.nextLine();
                String answer = in.nextLine();
                if (answer.equals("N")) {
                    System.out.println("Exiting program");
                    run = false;
                }
            }
        }

        in.close();
    }

    static long factorial(int n) {
        long result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }
}