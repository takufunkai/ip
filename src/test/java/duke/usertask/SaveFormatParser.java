package duke.usertask;

/**
 * The SaveFormatParser class is meant to extract the important client-facing information from the Duke toSaveFormat
 * method return String. Specifically, it removes the saveId value from the String. This is important as:
 * <p></p>
 * 1. A task's saveId is only important for identification during save-file updates that occur during the program
 * execution.
 * 2. Ensuring that IDs are created in a consistent manner is unimportant.
 * <p></p>
 * This is to ensure that we can test the important aspects of the save-string without affecting the test quality.
 * Also, the id field itself is important, and is implicitly tested through the parsing.
 */
public class SaveFormatParser {
    String getSaveValue(UserTask saveTask) {
        return saveTask.toDukeSaveFormat().split("\\|", 2)[1];
    }
}
