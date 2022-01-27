import usertask.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveHandler {
    private final static String DATA_DIRECTORY = "data";
    private final static String DATA_FILEPATH = DATA_DIRECTORY + "/duke.txt";

    enum TaskCode {
        T, D, E
    }

    public SaveHandler() throws IOException {
        File dir = new File(DATA_DIRECTORY);
        dir.mkdirs();
        File f = new File(DATA_FILEPATH);
        f.createNewFile();
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(DATA_FILEPATH);
            fw.write(tasks.toDukeSaveFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

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
                    System.out.println("Failed to restore saved task: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load save-file: " + e.getMessage());
        }
    }
}
