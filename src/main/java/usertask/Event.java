package usertask;

public class Event extends UserTaskWithTime {
    public Event(String name, String dateTime) throws UserTaskException {
        super(name, dateTime);
    }

    @Override
    public String toString() {
        return String.format(super.toString(), "[E]", "at");
    }
}
