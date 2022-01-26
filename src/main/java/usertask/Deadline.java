package usertask;

public class Deadline extends UserTaskWithTime {
    public Deadline(String name, String dateTime) throws UserTaskException {
        super(name, dateTime);
    }

    @Override
    public String toString() {
        return String.format(super.toString(), "[D]", "by");
    }
}
