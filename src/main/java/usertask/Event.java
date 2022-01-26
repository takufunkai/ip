package usertask;

public class Event extends UserTask {
    private final String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toDukeSaveFormat() {
        return "E|" + super.toDukeSaveFormat() + "|" + this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
