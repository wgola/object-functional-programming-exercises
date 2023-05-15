package lab12;

import java.time.LocalTime;

public final class Zadanie extends Wpis {
    private Status status;

    public Zadanie(String description, LocalTime startTime, LocalTime endTime, Status status) {
        super(description, startTime, endTime);
        this.status = status;
    }

    public Zadanie() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + ", status: " + this.status;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
