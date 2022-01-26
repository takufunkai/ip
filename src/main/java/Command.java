import usertask.Deadline;
import usertask.Event;
import usertask.ToDo;
import usertask.UserTask;

public enum Command {
    LIST {
        @Override
        public void validateAndExecute(String[] input) {
            Duke.printFromRed("Alright, here are your recorded tasks.");
            if (Duke.tasks.getTasksCount() == 0) {
                Duke.printFromRed("It seems you have no tasks at the moment, why not add one?");
            }
            Duke.listTasks();
        }
    },
    MARK {
        @Override
        public void validateAndExecute(String[] input) throws DukeException {
            if (input.length == 0 || input[0].isBlank()) {
                throw new DukeException("Please indicate a task item number to mark.");
            }
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(input[0]);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! " +
                        "Please input a valid number.");
            }
            if (taskNumber > Duke.tasks.getTasksCount()) {
                throw new DukeException("There are currently " + Duke.tasks.getTasksCount() + " tasks. " +
                        "Please enter a valid number to mark");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }
            UserTask task = Duke.tasks.markTask(taskNumber);
            Duke.printFromRed("Good job! Let's keep it going, this spaceship needs you!");
            Duke.printFromRed(task + "\n");
        }
    },
    UNMARK {
        @Override
        public void validateAndExecute(String[] input) throws DukeException {
            if (input.length == 0 || input[0].isBlank()) {
                throw new DukeException("Please indicate a task item number to unmark.");
            }
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(input[0]);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! " +
                        "Please input a valid number.");
            }
            if (taskNumber > Duke.tasks.getTasksCount()) {
                throw new DukeException("There are currently " + Duke.tasks.getTasksCount() + " tasks. " +
                        "Please enter a valid number to unmark");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }
            UserTask task = Duke.tasks.unmarkTask(taskNumber);
            Duke.printFromRed("I thought you were done with it?");
            Duke.printFromRed(task + "\n");
        }
    },
    TODO {
        @Override
        public void validateAndExecute(String[] input) throws DukeException {
            if (input.length != 1 || input[0].isBlank()) {
                throw new DukeException("ToDo items must have a description.");
            }
            String taskName = input[0];
            UserTask task = new ToDo(taskName);
            Duke.tasks.addTask(task);
            Duke.printFromRed("Added task #" + (Duke.tasks.getTasksCount()) + ": " + task + "\n");
        }
    },
    DEADLINE {
        @Override
        public void validateAndExecute(String[] input) throws DukeException {
            if (input.length != 3) {
                throw new DukeException("Deadline items must have a description and due date.\n");
            }
            if (!input[1].equalsIgnoreCase("/by")) {
                throw new DukeException("Delimiter \"/by\" not found.");
            }
            String taskName = input[0];
            String date = input[2];
            if (taskName.isBlank()) {
                throw new DukeException("Deadline items must have a description.");
            }
            if (date.isBlank()) {
                throw new DukeException("Deadline items must have a due date.");
            }
            UserTask task = new Deadline(taskName, date);
            Duke.tasks.addTask(task);
            Duke.printFromRed("Added task #" + (Duke.tasks.getTasksCount()) + ": " + task + "\n");
        }
    },
    EVENT {
        @Override
        public void validateAndExecute(String[] input) throws DukeException {
            if (input.length != 3) {
                throw new DukeException("Event items must have a description and a date.\n");
            }
            if (!input[1].equalsIgnoreCase("/at")) {
                throw new DukeException("Delimiter \"/at\" not found.");
            }
            String taskName = input[0];
            String date = input[1];
            if (taskName.isBlank()) {
                throw new DukeException("Event items must have a description.");
            }
            if (date.isBlank()) {
                throw new DukeException("Event items must have a date.");
            }
            UserTask task = new Event(taskName, date);
            Duke.tasks.addTask(task);
            Duke.printFromRed("Added task #" + (Duke.tasks.getTasksCount()) + ": " + task + "\n");
        }
    },
    DELETE {
        @Override
        public void validateAndExecute(String[] input) throws DukeException {
            if (input.length != 1 || input[0].isBlank()) {
                throw new DukeException("Please indicate a task item number to delete.");
            }
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(input[0]);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! " +
                        "Please input a valid number.");
            }
            if (taskNumber > Duke.tasks.getTasksCount()) {
                throw new DukeException("There are currently " + Duke.tasks.getTasksCount() + " tasks. " +
                        "Please enter a valid number to delete.");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }
            UserTask deletedTask = Duke.tasks.deleteTask(taskNumber);
            Duke.printFromRed("Alright! Getting rid of the following task: ");
            Duke.printFromRed(deletedTask + "\n");
        }
    },
    BYE {
        @Override
        public void validateAndExecute(String[] input) {
            Duke.terminate();
        }

    };

    public abstract void validateAndExecute(String[] input) throws DukeException;
}
