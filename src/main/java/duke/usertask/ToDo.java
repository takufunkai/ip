package duke.usertask;

public class ToDo extends UserTask {
    /**
     * Constructs a ToDo object.
     *
     * @param name The name of the ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     * Supplies "T" to the parent string's unformatted string, to complete the DukeSaveFormat string.
     */
    @Override
    public String toDukeSaveFormat() {
        return String.format(super.toDukeSaveFormat(), "T");
    }

    /**
     * {@inheritDoc}
     * Supplies the correct ToDo-specific information to the display string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
