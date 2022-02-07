package duke.usertask;

import java.util.Locale;

/**
 * The UserTask abstract class encapsulates the tasks that a user submits to the chat-bot. It maintains information
 * such as the name and isDone status of the task. It provides several methods to update the state of the task.
 */
public abstract class UserTask implements DukeSavable {
    private static int saveIdSerial = 0;

    private final String name;
    private boolean isDone;
    private final int saveId;

    UserTask(String name) {
        this.name = name;
        this.isDone = false;
        this.saveId = saveIdSerial;
        saveIdSerial++;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as undone.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task's name contains the given query.
     *
     * @param query The specified query to be matched against.
     * @return True if the match is successful.
     */
    public boolean nameContains(String query) {
        return name.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT));
    }

    /**
     * Returns the string representation of the task in DukeSaveFormat.
     *
     * @return The string representation of the task in DUkeSaveFormat.
     */
    @Override
    public String toDukeSaveFormat() {
        return this.saveId + "|%s|" + (isDone ? "1" : "0") + "|" + name;
    }

    /**
     * Returns the user-friendly string representation of this task.
     *
     * @return Returns a string that represents this task which is user-friendly.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }
}
