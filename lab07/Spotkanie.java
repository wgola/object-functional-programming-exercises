package lab07;

import java.time.LocalTime;

public class Spotkanie {
    public static final LocalTime EARLIEST_MEETING_HOUR = LocalTime.of(8, 0, 0);

    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;

    public Spotkanie(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        this.startTime = startTime;
        this.description = description;
        this.endTime = endTime;
        this.priority = priority;
    }

    public Spotkanie() {
    }

    @Override
    public String toString() {
        return "Godzina rozpoczęcia: " + this.startTime + ", godzina zakończenia: " + this.endTime + ", opis: "
                + this.description + ", priorytet: " + this.priority;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
