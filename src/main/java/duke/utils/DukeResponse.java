package duke.utils;

/**
 * The DukeResponse class encapsulates the data that might be returned by an execution of the Duke bot.
 */
public class DukeResponse {
    /**
     * The possible response statuses of a DukeResponse that is received from executing some command in Duke.
     */
    public enum ResponseStatus { SUCCESS, FAIL, EXIT }

    private final ResponseStatus status;
    private final String message;

    /**
     * Creates an instance of the DukeResponse object.
     *
     * @param status The status of the execution.
     * @param message The message associated with the execution and its status.
     */
    public DukeResponse(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Creates an instance of the DukeResponse object which has no message.
     *
     * @param status The status of the execution.
     */
    public DukeResponse(ResponseStatus status) {
        this.status = status;
        this.message = "";
    }

    public ResponseStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
