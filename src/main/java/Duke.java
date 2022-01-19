import usertask.Deadline;
import usertask.Event;
import usertask.ToDo;
import usertask.UserTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    enum Command {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE
    }

    /* CONSTANTS */
    private final static String CREW_MATE_LOGO = "ඞ";
    private final static String MESSAGE_BUFFER = "                  ";
    private final static String DIVIDER =
            " ---------------  ---------------  ---------------  ---------------  ---------------\n" +
                    " -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::-\n" +
                    " ---------------  ---------------  ---------------  ---------------  ---------------\n";
    private final static String GREETING_MESSAGE =
            colourStringRed(
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀\n" +
                            "⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀\n" +
                            "⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀\n" +
                            "⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀\n" +
                            "⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀        " +
                            "ඞ Hello, I am Red from Among Us.\n" +
                            "⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⠀\n" +
                            "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀           " +
                            "ඞ We are currently facing a crisis onboard --\n" +
                            "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀          " +
                            "-- there seems to be an imposter among us...\n" +
                            "⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀\n" +
                            "⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀          " +
                            "ඞ My job is to handle chat requests,\n" +
                            "⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀⠀        " +
                            "so although I might get murdered any moment now...\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀            " +
                            "... how can I help you?\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n") +
                    DIVIDER;
    /* END CONSTANTS */

    private final static List<UserTask> tasks = new ArrayList<>(100);
    private static boolean isRunning = true;

    /* HELPER FUNCTIONS */
    private static String colourStringRed(String out) {
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_RED + out + ANSI_RESET;
    }
    /* END HELPER FUNCTIONS */

    /* IO FUNCTIONS */
    private static void printGreeting() {
        System.out.println(GREETING_MESSAGE);
    }

    private static void printFromRed(String message) {
        String redMessageSuffix = MESSAGE_BUFFER + ">>> " + CREW_MATE_LOGO + " > ";
        System.out.println(colourStringRed(redMessageSuffix + message));
    }

    private static void printExitMessage() {
        printFromRed("Thank you for chatting with me... bye forever");
        System.out.println();
        System.out.println(DIVIDER +
                ". 　　　。　　　　•　 　ﾟ　　。 　　.\n" +
                "\n" +
                "　　　.　　　 　　.　　　　　。　　 。　. 　\n" +
                "\n" +
                ".　　 。　　　　　 " + colourStringRed(CREW_MATE_LOGO) + " 。 . 　　 • 　　　　•\n" +
                "\n" +
                "　　ﾟ　　 Red was not An Impostor.　 。　.\n" +
                "\n" +
                "　　'　　　     2 Impostors remain 　 　　。\n" +
                "\n" +
                "　　ﾟ　　　.　　　. ,　　　　.　 .");
    }

    private static String awaitInputFromUser(Scanner sc) {
        System.out.print(MESSAGE_BUFFER + ">>> ☺ YOU > ");
        return sc.nextLine();
    }

    private static void listTasks() {
        String tableDivider = colourStringRed(MESSAGE_BUFFER + "----------------");
        System.out.println(tableDivider);
        System.out.println(colourStringRed(MESSAGE_BUFFER + "TOTAL: " + tasks.size()));
        System.out.println(tableDivider);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(MESSAGE_BUFFER + colourStringRed((i + 1) + ". " + tasks.get(i).toString()));
        }
        System.out.println(tableDivider);
        System.out.println();
    }
    /* END IO FUNCTIONS */

    /* USER TASKS FUNCTIONS */

    /**
     * Takes in the 1-index of the task, adjusts it for 0-indexing to retrieve
     * from the tasks ArrayList.
     *
     * @param number 1-index
     * @return userTask.UserTask based on 0-indexing of the ArrayList
     */
    private static UserTask getTaskOfNumber(int number) {
        return tasks.get(number - 1);
    }

    private static UserTask deleteTaskOfNumber(int number) {
        return tasks.remove(number - 1);
    }
    /* END USER TASKS FUNCTIONS */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Duke.printGreeting();

        while (Duke.isRunning) {
            try {
                String userInput = Duke.awaitInputFromUser(sc);
                String[] userInputSplit = userInput.split("\\s+", 2);

                Command userCommand;
                try {
                    userCommand = Command.valueOf(userInputSplit[0].toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException e) {
                    throw new DukeException("Unknown command.");
                }

                String userArgument = null;
                if (userInputSplit.length == 2) {
                    userArgument = userInputSplit[1];
                }

                switch (userCommand) {
                    case BYE:
                        Duke.isRunning = false;
                        break;
                    case LIST:
                        Duke.printFromRed("Alright, here are your recorded tasks.");
                        if (Duke.tasks.size() == 0) {
                            Duke.printFromRed("It seems you have no tasks at the moment, why not add one?");
                        }
                        Duke.listTasks();
                        break;
                    case MARK:
                        if (userArgument == null || userArgument.isBlank()) {
                            throw new DukeException("Please indicate a task item number to mark.");
                        }
                        int taskNumber;
                        try {
                            taskNumber = Integer.parseInt(userArgument);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Your tasks are identified by numbers! " +
                                    "Please input a valid number.");
                        }
                        if (taskNumber > Duke.tasks.size()) {
                            throw new DukeException("There are currently " + Duke.tasks.size() + " tasks. " +
                                    "Please enter a valid number to mark");
                        }
                        if (taskNumber <= 0) {
                            throw new DukeException("Are you trying to be funny?");
                        }
                        UserTask task = Duke.getTaskOfNumber(taskNumber);
                        task.setDone();
                        Duke.printFromRed("Good job! Let's keep it going, this spaceship needs you!");
                        Duke.printFromRed(task + "\n");
                        break;
                    case UNMARK:
                        if (userArgument == null || userArgument.isBlank()) {
                            throw new DukeException("Please indicate a task item number to unmark.");
                        }
                        try {
                            taskNumber = Integer.parseInt(userArgument);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Your tasks are identified by numbers! " +
                                    "Please input a valid number.");
                        }
                        if (taskNumber > Duke.tasks.size()) {
                            throw new DukeException("There are currently " + Duke.tasks.size() + " tasks. " +
                                    "Please enter a valid number to unmark");
                        }
                        if (taskNumber <= 0) {
                            throw new DukeException("Are you trying to be funny?");
                        }
                        task = Duke.getTaskOfNumber(taskNumber);
                        task.setUndone();
                        Duke.printFromRed("I thought you were done with it?");
                        Duke.printFromRed(task + "\n");
                        break;
                    case TODO:
                        if (userArgument == null || userArgument.isBlank()) {
                            throw new DukeException("ToDo items must have a description.");
                        }
                        UserTask newToDo = new ToDo(userArgument);
                        tasks.add(newToDo);
                        Duke.printFromRed("Added task #" + (tasks.size()) + ": " + newToDo + "\n");
                        break;
                    case DEADLINE:
                        if (userArgument == null || userArgument.isBlank()) {
                            throw new DukeException("Deadline items must have a description and due date.\n");
                        }
                        String[] parsedArguments = userArgument.split(" /by ");
                        if (parsedArguments.length < 2) {
                            throw new DukeException("Deadline items must have a description and a due date.\n");
                        }
                        String taskName = parsedArguments[0];
                        String date = parsedArguments[1];
                        if (taskName.isBlank()) {
                            throw new DukeException("Deadline items must have a description.");
                        }
                        if (date.isBlank()) {
                            throw new DukeException("Deadline items must have a due date.");
                        }
                        UserTask newDeadline = new Deadline(taskName, date);
                        tasks.add(newDeadline);
                        Duke.printFromRed("Added task #" + (tasks.size()) + ": " + newDeadline + "\n");
                        break;
                    case EVENT:
                        if (userArgument == null || userArgument.isBlank()) {
                            throw new DukeException("Event items must have a description and a date.\n");
                        }
                        parsedArguments = userInputSplit[1].split(" /at ");
                        if (parsedArguments.length < 2) {
                            throw new DukeException("Event items must have a description and a date.\n");
                        }
                        taskName = parsedArguments[0];
                        date = parsedArguments[1];
                        if (taskName.isBlank()) {
                            throw new DukeException("Event items must have a description.");
                        }
                        if (date.isBlank()) {
                            throw new DukeException("Event items must have a date.");
                        }
                        UserTask newEvent = new Event(taskName, date);
                        tasks.add(newEvent);
                        Duke.printFromRed("Added task #" + (tasks.size()) + ": " + newEvent + "\n");
                        break;
                    case DELETE:
                        if (userArgument == null || userArgument.isBlank()) {
                            throw new DukeException("Please indicate a task item number to delete.");
                        }
                        try {
                            taskNumber = Integer.parseInt(userArgument);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Your tasks are identified by numbers! " +
                                    "Please input a valid number.");
                        }
                        if (taskNumber > Duke.tasks.size()) {
                            throw new DukeException("There are currently " + Duke.tasks.size() + " tasks. " +
                                    "Please enter a valid number to delete.");
                        }
                        if (taskNumber <= 0) {
                            throw new DukeException("Are you trying to be funny?");
                        }
                        UserTask deletedTask = Duke.deleteTaskOfNumber(taskNumber);
                        Duke.printFromRed("Alright! Getting rid of the following task: ");
                        Duke.printFromRed(deletedTask + "\n");
                        break;
                    default:
                        throw new DukeException("Something went terribly wrong, unknown command.");
                }
            } catch (DukeException e) {
                Duke.printFromRed("Oops, something went wrong: ");
                Duke.printFromRed("** " + e.getMessage() + "\n");
            }
        }

        Duke.printExitMessage();
        sc.close();
    }
}
