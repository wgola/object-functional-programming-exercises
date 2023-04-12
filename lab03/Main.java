package lab03;

import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(
                "Program służący do zapisywania ocen oraz obliczania ich średniej i wyświetlania najmniejszej i największej z nich.\n");
        GradeList listOfGrades = new GradeList();

        boolean run = true;
        while (run) {
            int option = chooseOption();

            switch (option) {
                case 1 -> addGrade(listOfGrades);
                case 2 -> showAvgGrade(listOfGrades);
                case 3 -> showMaxGrade(listOfGrades);
                case 4 -> showMinGrade(listOfGrades);
                case 5 -> {
                    System.out.println("Kończenie działania programu.");
                    run = false;
                }
                default -> System.out.println("Wybrano niepoprawną opcję!");
            }
        }
    }

    static int chooseOption() {
        System.out.println("""
                Wybierz opcję:
                1) Dodanie nowej oceny
                2) Wyliczenie średniej ocen
                3) Wyświetlenie najwyższej oceny
                4) Wyświetlenie najniższej oceny
                5) Wyjście z programu""");

        int option = in.nextInt();

        return option;
    }

    static void addGrade(GradeList listOfGrades) {
        System.out.println("Podaj ocenę w skali od 2 do 5:");
        double newGrade = in.nextDouble();

        if (newGrade < 2 || newGrade > 5) {
            System.out.println("Wprowadzono błędną ocenę!\n");
            return;
        }

        listOfGrades.addGrade(newGrade);
        System.out.println("Dodano ocenę do listy.\n");
    }

    static void showAvgGrade(GradeList listOfGrades) {
        double avg = listOfGrades.getAvgGrade();

        if (avg == -1) {
            System.out.println("Nie można obliczyć średniej - brak ocen na liście!\n");
            return;
        }

        System.out.println("Średnia ocen: " + avg + "\n");
    }

    static void showMinGrade(GradeList listOfGrades) {
        double minGrade = listOfGrades.getMinGrade();

        if (minGrade == -1) {
            System.out.println("Nie można wyświetlić najniższej oceny - brak ocen na liście!\n");
            return;
        }

        System.out.println("Najniższa ocena: " + minGrade + "\n");
    }

    static void showMaxGrade(GradeList listOfGrades) {
        double maxGrade = listOfGrades.getMaxGrade();

        if (maxGrade == -1) {
            System.out.println("Nie można wyświetlić największej oceny - brak ocen na liście!\n");
            return;
        }

        System.out.println("Najwyższa ocena: " + maxGrade + "\n");
    }
}