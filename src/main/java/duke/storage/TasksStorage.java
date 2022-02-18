package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;
import duke.usertask.Deadline;
import duke.usertask.DukeSavable;
import duke.usertask.Event;
import duke.usertask.TaskList;
import duke.usertask.ToDo;
import duke.usertask.UserTask;
import duke.usertask.UserTaskException;

/**
 * TasksStorage is the class that handles all storage-related information and methods of the Duke chat-bot.
 * A TasksStorage object encapsulates the necessary information for storing and restoring of the user's saved tasks.
 * This includes the directory which is being used as the storage, and some Enums being used to parse
 * the saved strings.
 */
public class TasksStorage implements Storage {
    private static final String DATA_DIRECTORY = "data";
    private static final String DATA_FILEPATH = DATA_DIRECTORY + "/duke.txt";

    enum TaskCode {
        T, D, E
    }

    /**
     * Creates a new TasksStorage object which is able to save and restore tasks to a pre-defined location.
     * TasksStorage attempts to create the necessary directories and files without explicitly checking if they
     * already exist. Since creating existing directories and files only fails silently, there is no explicit checks
     * done.
     *
     * @throws IOException Thrown if the creation/opening of the files and directories failed.
     */
    public TasksStorage() throws IOException {
        File dir = new File(DATA_DIRECTORY);
        if (dir.mkdirs()) {
            System.out.println("Creating a file directory for save file now: " + DATA_DIRECTORY);
        } else {
            System.out.println("Save file directory already exists: " + DATA_DIRECTORY);
        }
        File f = new File(DATA_FILEPATH);
        if (f.createNewFile()) {
            System.out.println("Creating a save file now: " + DATA_FILEPATH);
        } else {
            System.out.println("Save file already exists: " + DATA_FILEPATH);
        }

        assert f.exists() : "Data save file does not exist.";
    }

    /**
     * Saves the list of tasks specified to the storage. The existing storage data is overwritten.
     *
     * @param tasks List of tasks to save.
     */
    @Override
    public void saveAndOverwrite(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(DATA_FILEPATH);
            fw.write(tasks.toDukeSaveFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks specified to the storage. The existing storage data is overwritten.
     *
     * @param tasks List of tasks to save.
     */
    @Override
    public void save(DukeSavable ...tasks) {
        try {
            for (DukeSavable t : tasks) {
                String saveValue = t.toDukeSaveFormat() + "\n";
                Files.write(
                        Path.of(DATA_FILEPATH), saveValue.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND
                );
            }
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    /**
     * Removes the item with saveId from the list of saved items in the data file.
     *
     * @param task The task to be removed.
     */
    @Override
    public void remove(DukeSavable task) {
        String taskSaveFormat = task.toDukeSaveFormat();
        try {
            List<String> fileContent = new ArrayList<>(
                    Files.readAllLines(Path.of(DATA_FILEPATH), StandardCharsets.UTF_8)
            );
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(taskSaveFormat)) {
                    fileContent.remove(i);
                    break;
                }
            }
            Files.write(Path.of(DATA_FILEPATH), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Unable to remove task: " + e.getMessage());
        }
    }

    /**
     * Updates the item in the list of saved items in the data file.
     *
     * @param updatedSave The task after it was updated.
     */
    @Override
    public void update(DukeSavable updatedSave) {
        int saveId = Integer.parseInt(updatedSave.toDukeSaveFormat().split("\\|")[0]);
        try {
            List<String> fileContent = new ArrayList<>(
                    Files.readAllLines(Path.of(DATA_FILEPATH), StandardCharsets.UTF_8)
            );
            for (int i = 0; i < fileContent.size(); i++) {
                try {
                    int currSaveId = Integer.parseInt(fileContent.get(i).split("\\|")[0]);
                    if (currSaveId == saveId) {
                        fileContent.set(i, updatedSave.toDukeSaveFormat());
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Failed to parse: " + fileContent.get(i).split("\\|")[0]);
                    System.out.println(e.getMessage());
                }
            }
            Files.write(Path.of(DATA_FILEPATH), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Unable to update task: " + e.getMessage());
        }
    }

    /**
     * Restores the list of tasks saved on a previous execution of the Duke chat-bot into a <code>TaskList</code>
     * object.
     *
     * @param emptyTasks The <code>TaskList</code> object being maintained by Duke.
     * @throws DukeException Thrown if the restoration failed.
     */
    @Override
    public void restore(TaskList emptyTasks) throws DukeException {
        assert emptyTasks.getTasksCount() == 0 : "TaskList given to restore should be empty";

        try {
            File saveFile = new File(DATA_FILEPATH);
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNext()) {
                String savedTask = sc.nextLine();
                String[] savedTaskData = savedTask.split("\\|");

                if (savedTaskData.length < 4) {
                    throw new DukeException("Saved item has incorrect format");
                }

                TaskCode code = TaskCode.valueOf(savedTaskData[1]);
                boolean isDone = savedTaskData[2].equals("1");
                String taskName = savedTaskData[3];

                UserTask newTask;
                try {
                    switch (code) {
                    case T:
                        newTask = new ToDo(taskName);
                        break;
                    case D:
                        String date = savedTaskData[4];
                        newTask = new Deadline(taskName, date);
                        break;
                    case E:
                        date = savedTaskData[4];
                        newTask = new Event(taskName, date);
                        break;
                    default:
                        throw new DukeException("Unknown task type for saved item.");
                    }
                } catch (UserTaskException e) {
                    throw new DukeException("Failed to restore save file: " + e.getMessage());
                }

                if (isDone) {
                    newTask.setDone();
                }
                emptyTasks.addTask(newTask);
            }

            // Clear the existing data file and update it with updated saveId-tasks.
            saveAndOverwrite(emptyTasks);
        } catch (IOException | DukeException e) {
            System.out.println("Failed to load save-file: " + e.getMessage());
        }
    }
}
