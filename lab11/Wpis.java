package lab11;

import java.time.LocalTime;

public abstract sealed class Wpis permits Spotkanie, Zadanie {
    public static final LocalTime EARLIEST_BEGINNING_TIME = LocalTime.of(8, 0, 0);

    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    protected Wpis(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    protected Wpis() {
    }

    @Override
    public String toString() {
        return "Godzina rozpoczęcia: " + this.startTime + ", godzina zakończenia: " + this.endTime + ", opis: "
                + this.description;
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
}
