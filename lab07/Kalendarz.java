package lab07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kalendarz {
    private HashMap<Integer, List<Spotkanie>> listOfMeetings = new HashMap<>(7);

    public Kalendarz() {
        for (int i = 0; i < 7; i++) {
            this.listOfMeetings.put(i, new ArrayList<>());
        }
    }

    public void addMeeting(int day, Spotkanie meeting) {
        this.listOfMeetings.get(day).add(meeting);
    }

    public void deleteMeeting(int day, int meetingID) {
        this.listOfMeetings.get(day).remove(meetingID);
    }

    public List<Spotkanie> getAllMeetingsFromDay(int day) {
        return this.listOfMeetings.get(day);
    }

    public List<Spotkanie> getAllMettingsFromDayWithPriority(int day, Priority priority) {
        List<Spotkanie> filteredMeetings = new ArrayList<>();
        List<Spotkanie> meetingsFromDay = this.listOfMeetings.get(day);

        for (Spotkanie meeting : meetingsFromDay) {
            if (meeting.getPriority().equals(priority)) {
                filteredMeetings.add(meeting);
            }
        }

        return filteredMeetings;
    }
}
