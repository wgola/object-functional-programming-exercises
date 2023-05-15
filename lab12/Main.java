package lab12;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

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
                case 2 -> addTask(calendar);
                case 3 -> deleteEvent(calendar, "spotkanie");
                case 4 -> deleteEvent(calendar, "zadanie");
                case 5 -> showMeetingsFromDay(calendar);
                case 6 -> showTasksFromDay(calendar);
                case 7 -> showMeetingsFromDayWithPriority(calendar);
                case 8 -> showTasksFromDayWithStatus(calendar);
                case 9 -> showMeetingsFromDayWithPriorityFromTime(calendar);
                case 10 -> showTasksFromDayWithStatusToTime(calendar);
                case 11 -> {
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
                2) Dodanie zadania w danym dniu
                3) Usunięcie spotkania z danego dnia
                4) Usunięcie zadania z danego dnia
                5) Wyświetlenie wszystkich spotkań z dnia
                6) Wyświetlenie wszystkich zadań z dnia
                7) Wyświetlenie wszystkich spotkań z dnia o danym priorytecie
                8) Wyświetlenie wszystkich zadań z dnia o danym statusie
                9) Wyświetlenie wszystkich spotkań z dnia o danym priorytecie zaczynających się po podanym czasie
                10) Wyświetlenie wszystkich zadań z dnia o danym priorytecie kończących się przed podanym czasem
                11) Wyjście z programu""");

        int option = numbersScanner.nextInt();

        return option;
    }

    static void addEvent(Wpis event) {
        LocalTime startTime = getStartTime();

        LocalTime endTime = getEndTime();

        while (startTime.isAfter(endTime)) {
            System.out.println(
                    "Godzina zakończenia nie może być wcześniejsza niż godzina rozpoczęcia! Spróbuj ponownie: ");
            startTime = getStartTime();
            endTime = getEndTime();
        }

        event.setStartTime(startTime);
        event.setEndTime(endTime);

        System.out.println("Podaj opis: ");
        String description = stringsScanner.nextLine();
        event.setDescription(description);
    }

    static void addTask(Kalendarz calendar) {
        int day = getDay();

        Zadanie newTask = new Zadanie();

        addEvent(newTask);

        Status status = getStatus();

        newTask.setStatus(status);

        calendar.addEvent(day, newTask);

        System.out.println("Dodano zadanie.");
    }

    static void addMeeting(Kalendarz calendar) {
        int day = getDay();

        Spotkanie newMeeting = new Spotkanie();

        addEvent(newMeeting);

        Priority priority = getPriority();

        newMeeting.setPriority(priority);

        calendar.addEvent(day, newMeeting);

        System.out.println("Dodano spotkanie.");
    }

    static void deleteEvent(Kalendarz calendar, String eventType) {
        int day = getDay();

        List<Wpis> allEventsByType = new ArrayList<>();

        if (eventType.equals("zadanie")) {
            allEventsByType = calendar.getAllEventsFromDay(day, w -> w instanceof Zadanie);
        } else if (eventType.equals("spotkanie")) {
            allEventsByType = calendar.getAllEventsFromDay(day, w -> w instanceof Spotkanie);
        }

        displayEventList(allEventsByType, "Brak wpisów w wybranym dniu!");

        if (allEventsByType.size() != 0) {
            System.out.println("Podaj indeks do usunięcia: ");
            int taskID = numbersScanner.nextInt() - 1;

            if (taskID < 0 || taskID >= allEventsByType.size()) {
                System.out.println("Podano niepoprawny indeks!");
            } else {
                calendar.deleteEvent(day, allEventsByType.get(taskID));

                System.out.println("Usunięto " + eventType + ".");
            }
        }
    }

    static void showTasksFromDay(Kalendarz calendar) {
        int day = getDay();

        List<Wpis> tasksFromDay = calendar.getAllEventsFromDay(day, w -> w instanceof Zadanie);

        displayEventList(tasksFromDay, "Brak zadań w tym dniu.");
    }

    static void showMeetingsFromDay(Kalendarz calendar) {
        int day = getDay();

        List<Wpis> meetingsFromDay = calendar.getAllEventsFromDay(day, w -> w instanceof Spotkanie);

        displayEventList(meetingsFromDay, "Brak spotkań w tym dniu.");
    }

    static void showTasksFromDayWithStatus(Kalendarz calendar) {
        int day = getDay();

        Status status = getStatus();

        Predicate<Wpis> filter = w -> w instanceof Zadanie t && t.getStatus().equals(status);
        List<Wpis> tasksFromDayWithStatus = calendar.getAllEventsFromDay(day, filter);

        displayEventList(tasksFromDayWithStatus, "Brak zadań z wybranym statusem w tym dniu.");
    }

    static void showMeetingsFromDayWithPriority(Kalendarz calendar) {
        int day = getDay();

        Priority priority = getPriority();

        Predicate<Wpis> filter = w -> w instanceof Spotkanie m && m.getPriority().equals(priority);
        List<Wpis> meetingsFromDayWithPriority = calendar.getAllEventsFromDay(day, filter);

        displayEventList(meetingsFromDayWithPriority, "Brak spotkań z podanym priorytetem w tym dniu.");
    }

    static void showTasksFromDayWithStatusToTime(Kalendarz calendar) {
        int day = getDay();

        Status status = getStatus();

        LocalTime endTime = getEndTime();

        Predicate<Wpis> filter = w -> w instanceof Zadanie t && t.getStatus().equals(status)
                && (t.getEndTime().isBefore(endTime) || t.getEndTime().equals(endTime));
        List<Wpis> tasksFromDayWithStatusToTime = calendar.getAllEventsFromDay(day, filter);

        displayEventList(tasksFromDayWithStatusToTime,
                "Brak zadań z wybranem statusem kończących się przed podaną godziną w danym dniu.");
    }

    static void showMeetingsFromDayWithPriorityFromTime(Kalendarz calendar) {
        int day = getDay();

        Priority priority = getPriority();

        LocalTime starTime = getStartTime();

        Predicate<Wpis> filter = w -> w instanceof Spotkanie m && m.getPriority().equals(priority)
                && (m.getStartTime().isAfter(starTime) || m.getStartTime().equals(starTime));
        List<Wpis> meetingsFromDayWithPriorityFromTime = calendar.getAllEventsFromDay(day, filter);

        displayEventList(meetingsFromDayWithPriorityFromTime,
                "Brak spotkań z wybranym priorytetem zaczynających się po podanej godzinie w danym dniu");
    }

    static void displayEventList(List<Wpis> listOfEvents, String errorMessage) {
        if (listOfEvents.size() == 0) {
            System.out.println(errorMessage);
        } else {
            for (int i = 0; i < listOfEvents.size(); i++) {
                System.out.println(i + 1 + ". " + listOfEvents.get(i));
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
                if (Spotkanie.EARLIEST_BEGINNING_TIME.isAfter(startTime)) {
                    System.out.println("Godzina rozpoczęcia nie może być wcześniejsza niż "
                            + Spotkanie.EARLIEST_BEGINNING_TIME + ". Spróbuj ponownie: ");
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

    static Status getStatus() {
        System.out.println("Podaj status (1 - planowane, 2 - potwierdzone, 3 - realizowane, 4 - wykonane): ");
        int intStatus = numbersScanner.nextInt();
        while (intStatus < 1 || intStatus > 4) {
            System.out.println("Podano zły status! Spróbuj ponownie: ");
            intStatus = numbersScanner.nextInt();
        }

        switch (intStatus) {
            case 1 -> {
                return Status.PLANNED;
            }
            case 2 -> {
                return Status.CONFIRMED;
            }
            case 3 -> {
                return Status.REALIZED;
            }
            default -> {
                return Status.DONE;
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
