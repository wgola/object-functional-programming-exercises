package lab07;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

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

        int option = in.nextInt();

        return option;
    }

    static void addMeeting(Kalendarz calendar) {
        try {
            int day = getDay();

            LocalTime startTime = getStartTime();

            LocalTime endTime = getEndTime();

            if (startTime.isAfter(endTime)) {
                throw new Exception("Godzina zakończenia nie może być wcześniejsza niż godzina rozpoczęcia!");
            }

            System.out.println("Podaj opis: ");
            String description = in.nextLine();

            Priority priority = getPriority();

            Spotkanie newMeeting = new Spotkanie(description, startTime, endTime, priority);
            calendar.addMeeting(day, newMeeting);

            System.out.println("Dodano spotkanie.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void deleteMeeting(Kalendarz calendar) {
        try {
            int day = getDay();
            System.out.println("Podaj indeks spotkania: ");

            int meetingID = in.nextInt();
            calendar.deleteMeeting(day, meetingID - 1);

            System.out.println("Usunięto spotkanie.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void showMeetingsFromDay(Kalendarz calendar) {
        try {
            int day = getDay();

            List<Spotkanie> meetingsFromDay = calendar.getAllMeetingsFromDay(day);

            for (int i = 0; i < meetingsFromDay.size(); i++) {
                System.out.println(i + 1 + ". " + meetingsFromDay.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void showMeetingsFromDayWithPriority(Kalendarz calendar) {
        try {
            int day = getDay();

            Priority priority = getPriority();

            List<Spotkanie> meetingsFromDayWithPriority = calendar.getAllMettingsFromDayWithPriority(day, priority);

            for (int i = 0; i < meetingsFromDayWithPriority.size(); i++) {
                System.out.println(i + 1 + ". " + meetingsFromDayWithPriority.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static LocalTime getStartTime() throws Exception {
        System.out.println("Podaj godzinę rozpoczęcia (HH:MM:SS): ");
        in.nextLine();
        try {
            LocalTime startTime = LocalTime.parse(in.nextLine());
            if (Spotkanie.EARLIEST_MEETING_HOUR.isAfter(startTime)) {
                throw new Exception(
                        "Godzina rozpoczęcia nie może być wcześniejsza niż " + Spotkanie.EARLIEST_MEETING_HOUR);
            }

            return startTime;
        } catch (DateTimeParseException e) {
            throw new Exception("Podano zły format godziny!");
        }
    }

    static LocalTime getEndTime() throws Exception {
        System.out.println("Podaj godzinę zakończenia (HH:MM:SS): ");
        try {
            LocalTime startTime = LocalTime.parse(in.nextLine());

            return startTime;
        } catch (DateTimeParseException e) {
            throw new Exception("Podano zły format godziny!");
        }
    }

    static Priority getPriority() throws Exception {
        System.out.println("Podaj priorytet (1 - niski, 2 - średni, 3 - wysoki): ");
        int intPriority = in.nextInt();
        if (intPriority == 1) {
            return Priority.LOW;
        } else if (intPriority == 2) {
            return Priority.MEDIUM;
        } else if (intPriority == 3) {
            return Priority.HIGH;
        }

        throw new Exception("Podano zły priorytet!");
    }

    static int getDay() throws Exception {
        System.out.println("Podaj dzień tygodnia (0-6): ");
        int day = in.nextInt();
        if (day < 0 || day > 6) {
            throw new Exception("Podano zły dzień tygodnia!");
        }

        return day;
    }
}
