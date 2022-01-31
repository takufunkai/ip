package duke.usertask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * The TaskList class encapsulates the list of tasks that the chat-bot has to maintain, and which are saved from
 * the user. It includes the necessary information, which is a <code>List</code> of <code>UserTask</code>s, and also
 * the methods that are required to perform CRUD actions on the tasks.
 * <p>
 * UserTasks stored in the List object are 1-indexed -- index 0 of <code>tasks</code> is null.
 */
public class TaskList implements DukeSavable {
    private final List<UserTask> tasks;

    /**
     * Constructs the TaskList object, and initializes a List containing UserTasks of size
     * <code>initialCapacity</code>.
     *
     * @param initialCapacity The initial capacity of the list of UserTasks.
     */
    public TaskList(int initialCapacity) {
        this.tasks = new ArrayList<>(initialCapacity + 1);
        this.tasks.add(null); // Index 0 is empty
    }

    /**
     *  Removes task from the List, which index is equal to the specified number.
     *
     * @param number The index of the task to be removed.
     * @return The deleted UserTask.
     */
    public UserTask deleteTask(int number) {
        return this.tasks.remove(number);
    }

    /**
     * Returns the total number of UserTasks.
     *
     * @return The total number of UserTasks.
     */
    public int getTasksCount() {
        return tasks.size() - 1; // Account for empty index 0
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param number The index of the task to be marked.
     * @return The UserTask that has been marked.
     */
    public UserTask markTask(int number) {
        UserTask targetTask = this.tasks.get(number);
        targetTask.setDone();
        return targetTask;
    }

    /**
     * Un-marks the task at the specified index as done.
     *
     * @param number The index of the task to be un-marked.
     * @return The UserTask that has been un-marked.
     */
    public UserTask unmarkTask(int number) {
        UserTask targetTask = this.tasks.get(number);
        targetTask.setUndone();
        return targetTask;
    }

    /**
     * Adds the UserTask to the list of tasks.
     *
     * @param task The UserTask to be added to the list of tasks.
     */
    public void addTask(UserTask task) {
        this.tasks.add(task);
    }

    /**
     * Returns the TaskList with tasks whose name matches the given task-name search query.
     *
     * @param taskName The specified search query that will be matched against the Task's name.
     * @return The filtered TaskList.
     */
    public TaskList filterByName(String taskName) {
        TaskList filteredTaskList = new TaskList(100);
        filteredTaskList.tasks.addAll(
                this.tasks.subList(1, tasks.size())
                        .stream()
                        .filter((task) -> task.nameContains(taskName))
                        .collect(Collectors.toList()));
        return filteredTaskList;
    }

    /**
     * Returns the TaskList with tasks whose name matches the given task-date search query. Tasks with no
     * time associated with it (i.e. does not inherit from class UserTaskWithTime) will not be included.
     *
     * @param date The specified search query that will be matched against the Task's date (if any).
     * @return The filtered TaskList.
     */
    public TaskList filterByDate(LocalDateTime date) throws DukeException {
        TaskList filteredTaskList = new TaskList(100);
        try {
            for (UserTask currTask : tasks) {
                if (currTask instanceof UserTaskWithTime
                        && ((UserTaskWithTime) currTask).isDated(date)) {
                    filteredTaskList.addTask(currTask);
                }
            }
        } catch (UserTaskException e) {
            throw new DukeException("Failed to get filtered tasks: " + e.getMessage());
        }
        return filteredTaskList;
    }

    /**
     * Returns each of the tasks in the TaskList in DukeSave format.
     *
     * @return A string of all the tasks in the TaskList in DukeSave format.
     */
    @Override
    public String toDukeSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toDukeSaveFormat()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a user-friendly indexed list of the tasks that are being stored in the TaskList.
     *
     * @return An indexed list of the stored tasks, and their information and states.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tasks.size(); i++) {
            sb.append(i).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
