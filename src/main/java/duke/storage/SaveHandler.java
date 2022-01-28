package duke.storage;

import duke.DukeException;
import duke.usertask.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * SaveHandler is the class that handles all storage-related information and methods of the Duke chat-bot.
 * A SaveHandler object encapsulates the necessary information for storing and restoring of the user's saved tasks.
 * This includes the directory which is being used as the storage, and some Enums being used to parse
 * the saved strings.
 */
public class SaveHandler {
    private final static String DATA_DIRECTORY = "data";
    private final static String DATA_FILEPATH = DATA_DIRECTORY + "/duke.txt";

    enum TaskCode {
        T, D, E
    }

    /**
     * Creates a new SaveHandler object which is able to save and restore tasks to a pre-defined location.
     * SaveHandler attempts to create the necessary directories and files without explicitly checking if they
     * already exist. Since creating existing directories and files only fails silently, there is no explicit checks
     * done.
     *
     * @throws IOException Thrown if the creation/opening of the files and directories failed.
     */
    public SaveHandler() throws IOException {
        File dir = new File(DATA_DIRECTORY);
        dir.mkdirs();
        File f = new File(DATA_FILEPATH);
        f.createNewFile();
    }

    /**
     * Saves the list of tasks specified to the storage. The existing storage data is overwritten.
     *
     * @param tasks List of tasks to save.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(DATA_FILEPATH);
            fw.write(tasks.toDukeSaveFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    /**
     * Restores the list of tasks saved on a previous execution of the Duke chat-bot into a <code>TaskList</code>
     * object.
     *
     * @param emptyTasks The <code>TaskList</code> object being maintained by Duke.
     * @throws DukeException Thrown if the restoration failed.
     */
    public void restore(TaskList emptyTasks) throws DukeException {
        try {
            File saveFile = new File(DATA_FILEPATH);
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNext()) {
                String savedTask = sc.nextLine();
                String[] parsedTask = savedTask.split("\\|");
                TaskCode code = TaskCode.valueOf(parsedTask[0]);
                if (parsedTask.length < 3) {
                    throw new DukeException("Saved item has incorrect format");
                }
                String taskName = parsedTask[2];
                boolean isDone = parsedTask[1].equals("1");
                try {
                    switch (code) {
                    case T:
                        emptyTasks.addTask(new ToDo(taskName), isDone);
                        break;
                    case D:
                        emptyTasks.addTask(new Deadline(taskName, parsedTask[3]), isDone);
                        break;
                    case E:
                        emptyTasks.addTask(new Event(taskName, parsedTask[3]), isDone);
                        break;
                    default:
                        throw new DukeException("Unknown task type for saved item.");
                    }
                } catch (UserTaskException e) {
                    throw new DukeException("Failed to restore save file: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load save-file: " + e.getMessage());
        }
    }
}
