package lab07;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner numbersScanner = new Scanner(System.in);
    static Scanner stringsScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("""
                Program służący do dodawania spotkań składających się z godziny rozpoczęcia,
                zakończenia, opisu oraz priorytetu do kalendarza oraz ich wyszukiwania i usuwania.""");
        Kalendarz calendar = new Kalendarz();

        boolean run = true;
        while (run) {
            int option = chooseOption();

            switch (option) {
                case 1 -> addMeeting(calendar);
                case 2 -> deleteMeeting(calendar);
                case 3 -> showMeetingsFromDay(calendar);
                case 4 -> showMeetingsFromDayWithPriority(calendar);
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
                1) Dodanie spotkania w danym dniu
                2) Usunięcie spotkania z wybranego dnia
                3) Wyświetlenie wszystkich spotkań z dnia
                4) Wyświetlenie wszystkich spotkań z dnia o zadanym priorytecie
                5) Wyjście z programu""");

        int option = numbersScanner.nextInt();

        return option;
    }

    static void addMeeting(Kalendarz calendar) {
        int day = getDay();

        LocalTime startTime = getStartTime();

        LocalTime endTime = getEndTime();

        while (startTime.isAfter(endTime)) {
            System.out.println(
                    "Godzina zakończenia nie może być wcześniejsza niż godzina rozpoczęcia! Spróbuj ponownie: ");
            startTime = getStartTime();
            endTime = getEndTime();
        }

        System.out.println("Podaj opis: ");
        String description = stringsScanner.nextLine();

        Priority priority = getPriority();

        Spotkanie newMeeting = new Spotkanie(description, startTime, endTime, priority);
        calendar.addMeeting(day, newMeeting);

        System.out.println("Dodano spotkanie.");
    }

    static void deleteMeeting(Kalendarz calendar) {
        int day = getDay();
        System.out.println("Podaj indeks spotkania: ");

        int meetingID = numbersScanner.nextInt();
        List<Spotkanie> allMeetings = calendar.getAllMeetingsFromDay(day);

        if (meetingID - 1 < 0 || meetingID - 1 > allMeetings.size()) {
            System.out.println("Brak spotkania o podanym ID");
        } else {
            calendar.deleteMeeting(day, meetingID - 1);

            System.out.println("Usunięto spotkanie.");
        }
    }

    static void showMeetingsFromDay(Kalendarz calendar) {
        int day = getDay();

        List<Spotkanie> meetingsFromDay = calendar.getAllMeetingsFromDay(day);

        if (meetingsFromDay.size() == 0) {
            System.out.println("Brak spotkań w tym dniu.");
        } else {
            for (int i = 0; i < meetingsFromDay.size(); i++) {
                System.out.println(i + 1 + ". " + meetingsFromDay.get(i));
            }
        }
    }

    static void showMeetingsFromDayWithPriority(Kalendarz calendar) {
        int day = getDay();

        Priority priority = getPriority();

        List<Spotkanie> meetingsFromDayWithPriority = calendar.getAllMettingsFromDayWithPriority(day, priority);

        if (meetingsFromDayWithPriority.size() == 0) {
            System.out.println("Brak spotkań z podanym priorytetem w tym dniu.");
        } else {
            for (int i = 0; i < meetingsFromDayWithPriority.size(); i++) {
                System.out.println(i + 1 + ". " + meetingsFromDayWithPriority.get(i));
            }
        }
    }

    static LocalTime getStartTime() {
        System.out.println("Podaj godzinę rozpoczęcia (HH:MM:SS): ");
        LocalTime startTime = null;
        boolean gettingTime = true;

        while (gettingTime) {
            try {
                startTime = LocalTime.parse(stringsScanner.nextLine());
                if (Spotkanie.EARLIEST_MEETING_HOUR.isAfter(startTime)) {
                    System.out.println("Godzina rozpoczęcia nie może być wcześniejsza niż "
                            + Spotkanie.EARLIEST_MEETING_HOUR + ". Spróbuj ponownie: ");
                } else {
                    gettingTime = false;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Podano zły format godziny! Spróbuj ponownie: ");
            }
        }

        return startTime;
    }

    static LocalTime getEndTime() {
        System.out.println("Podaj godzinę zakończenia (HH:MM:SS): ");
        LocalTime endTime = null;
        boolean gettingTime = true;

        while (gettingTime) {
            try {
                endTime = LocalTime.parse(stringsScanner.nextLine());
                gettingTime = false;
            } catch (DateTimeParseException e) {
                System.out.println("Podano zły format godziny! Spróbuj ponownie: ");
            }
        }

        return endTime;
    }

    static Priority getPriority() {
        System.out.println("Podaj priorytet (1 - niski, 2 - średni, 3 - wysoki): ");
        int intPriority = numbersScanner.nextInt();
        while (intPriority < 1 || intPriority > 3) {
            System.out.println("Podano zły priorytet! Spróbuj ponownie: ");
            intPriority = numbersScanner.nextInt();
        }

        switch (intPriority) {
            case 1 -> {
                return Priority.LOW;
            }
            case 2 -> {
                return Priority.MEDIUM;
            }
            default -> {
                return Priority.HIGH;
            }
        }
    }

    static int getDay() {
        System.out.println("Podaj dzień tygodnia (0-6): ");
        int day = numbersScanner.nextInt();
        while (day < 0 || day > 6) {
            System.out.println("Podano zły dzień tygodnia! Spróbuj ponownie: ");
            day = numbersScanner.nextInt();
        }

        return day;
    }
}
