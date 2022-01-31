package duke.usertask;

/**
 * UserTaskException encapsulate the invalid arguments that is being supplied to UserTasks when instantiating
 * objects of that type.
 */
public class UserTaskException extends Exception {
    /**
     * Constructs a UserTaskException with the respective error message.
     *
     * @param errorMessage The message error that was encountered.
     */
    public UserTaskException(String errorMessage) {
        super(errorMessage);
    }
}
