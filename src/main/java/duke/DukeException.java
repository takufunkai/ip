package duke;

/**
 * DukeException encapsulates the checked exceptions that the chat-bot might face in its execution.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the respective error message.
     *
     * @param errorMessage The message error that was encountered.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
