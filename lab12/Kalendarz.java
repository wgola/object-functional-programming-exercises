package lab12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Kalendarz {
    private Map<Integer, List<Wpis>> listOfMeetings = new HashMap<>(7);

    public Kalendarz() {
        this(7);
    }

    public Kalendarz(int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            this.listOfMeetings.put(i, new ArrayList<>());
        }
    }

    public void addEvent(int day, Wpis event) {
        this.listOfMeetings.get(day).add(event);
    }

    public void deleteEvent(int day, Wpis event) {
        this.listOfMeetings.get(day).remove(event);
    }

    public List<Wpis> getAllEventsFromDay(int day, Predicate<Wpis> filter) {
        List<Wpis> filteredMeetings = new ArrayList<>();
        List<Wpis> meetingsFromDay = this.listOfMeetings.get(day);

        for (Wpis meeting : meetingsFromDay) {
            if (filter.test(meeting)) {
                filteredMeetings.add(meeting);
            }
        }

        return filteredMeetings;
    }
}
