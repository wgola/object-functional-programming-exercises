package lab11;

import java.time.LocalTime;

public final class Spotkanie extends Wpis {
    private Priority priority;

    public Spotkanie(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        super(description, startTime, endTime);
        this.priority = priority;
    }

    public Spotkanie() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + ", priorytet: " + this.priority;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
