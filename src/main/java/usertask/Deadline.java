package usertask;

public class Deadline extends UserTask {
    private final String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
