import usertask.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveHandler {
    private final static String DATA_DIRECTORY = "data";
    private final static String DATA_FILEPATH = "data/duke.txt";

    public SaveHandler() throws IOException {
        File dir = new File(DATA_DIRECTORY);
        // TODO: remove if else, only for debugging
        if (dir.mkdirs()) {
            System.out.println("Creating data path");
        } else {
            System.out.println("Data path already exists");
        }
        File f = new File(DATA_FILEPATH);
        // TODO: remove if else, only for debugging
        if (f.createNewFile()) {
            System.out.println("Creating " + DATA_FILEPATH);
        } else {
            System.out.println(DATA_FILEPATH + " already exists.");
        }
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
}
