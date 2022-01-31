package duke.usertask;

/**
 * The Deadline class encapsulates a task that has a deadline.
 */
public class Deadline extends UserTaskWithTime {
    /**
     * Constructs a new Deadline object.
     *
     * @param name The name of the task.
     * @param dateTime The deadline of the task in the proper dateTime format.
     * @throws UserTaskException If the specified dateTime is not in the correct format that the chat-bot understands.
     */
    public Deadline(String name, String dateTime) throws UserTaskException {
        super(name, dateTime);
    }

    /**
     * {@inheritDoc}
     * Supplies "D" to the parent string's unformatted string, to complete the DukeSaveFormat string.
     */
    @Override
    public String toDukeSaveFormat() {
        return String.format(super.toDukeSaveFormat(), "D");
    }

    /**
     * {@inheritDoc}
     * Supplies the correct Deadline-specific information to the display string.
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "[D]", "by");
    }
}
