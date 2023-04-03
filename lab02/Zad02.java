package lab02;

import java.util.Scanner;

class Zad02 {
    static Scanner skaner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(
                "Program służący do operacji na walcu: ustawiania oraz wyświetlania"
                        + "\npromienia podstawy oraz wysokości i obliczania pola podstawy,"
                        + "\npowierzchni bocznej oraz powierzchni całkowitej i objętości.");
        Walec walec = new Walec();

        boolean pracuje = true;
        while (pracuje) {
            int opcja = wybierzOpcje();

            if (opcja == 1) {
                wyswietlWalec(walec);
            } else if (opcja == 2) {
                zmienDaneWalca(walec);
            } else if (opcja == 3) {
                wyswietlPoleIObjetoscWalca(walec);
            } else {
                pracuje = false;
            }
        }

        System.out.println("Zakończenie działania programu.");
    }

    static int wybierzOpcje() {
        System.out.print("Dostępne opcje:\n"
                + "1) wyświetl wysokość i promień podstawy walca\n"
                + "2) zmień promień i wysokość walca\n"
                + "3) oblicz pole powierzchni i objętość walca\n"
                + "4) wyjdź z programu\n"
                + "Wybierz opcje: ");

        int odp = skaner.nextInt();

        return odp;
    }

    static void wyswietlWalec(Walec walec) {
        System.out.println("Promień podstawy walca: " + walec.getR()
                + "\nWysokość walca: " + walec.getH());
    }

    static void zmienDaneWalca(Walec walec) {
        System.out.print("Podaj nowy promień podstawy: ");
        double nowyPromien = skaner.nextDouble();

        System.out.print("Podaj nową wysokość: ");
        double nowaWysokosc = skaner.nextDouble();

        walec.setR(nowyPromien);
        walec.setH(nowaWysokosc);
        System.out.println("Ustawiono nowe wartości.");
    }

    static void wyswietlPoleIObjetoscWalca(Walec walec) {
        System.out.println("Pole podstawy: " + walec.polePodstawy()
                + "\nPole boczne: " + walec.poleBoczne()
                + "\nPole całkowite: " + walec.poleCalkowite()
                + "\nObjętość: " + walec.objetosc());
    }
}