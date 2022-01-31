package duke.usertask;

/**
 * The Deadline class encapsulates a task that has a deadline.
 */
public class Event extends UserTaskWithTime {
    /**
     * Constructs a new Event object.
     *
     * @param name The name of the Event task.
     * @param dateTime The dateTime of the occurrence of the Event in string format.
     * @throws UserTaskException If the specified dateTime is not in the correct format that the chat-bot understands.
     */
    public Event(String name, String dateTime) throws UserTaskException {
        super(name, dateTime);
    }

    /**
     * {@inheritDoc}
     * Supplies "E" to the parent string's unformatted string, to complete the DukeSaveFormat string.
     */
    @Override
    public String toDukeSaveFormat() {
        return String.format(super.toDukeSaveFormat(), "E");
    }

    /**
     * {@inheritDoc}
     * Supplies the correct Event-specific information to the display string.
     */
    @Override
    public String toString() {
        return String.format(super.toString(), "[E]", "at");
    }
}
