import usertask.Deadline;
import usertask.ToDo;
import usertask.UserTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    /* CONSTANTS */
    private final static String CREW_MATE_LOGO = "ඞ";
    private final static String SMILEY_LOGO = "☺";

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final static String MESSAGE_BUFFER = "                  ";
    private final static String DIVIDER =
            " ---------------  ---------------  ---------------  ---------------  --------------- \n" +
                    " -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::- \n" +
                    " ---------------  ---------------  ---------------  ---------------  --------------- \n";
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

    private final static String EXIT_COMMAND = "bye";
    private final static String LIST_COMMAND = "list";
    private final static String MARK_COMMAND = "mark";
    private final static String UNMARK_COMMAND = "unmark";
    private final static String ADD_TODO_COMMAND = "todo";
    private final static String ADD_DEADLINE_COMMAND = "deadline";
    /* END CONSTANTS */

    private final static List<UserTask> tasks = new ArrayList<>(100);

    /* HELPER FUNCTIONS */
    private static String colourStringRed(String out) {
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
        System.out.print(MESSAGE_BUFFER + ">>> " + SMILEY_LOGO + " YOU > ");
        return sc.nextLine();
    }

    private static void listTasks() {
        System.out.println(colourStringRed(MESSAGE_BUFFER + "----------------"));
        System.out.println(colourStringRed(MESSAGE_BUFFER + "TOTAL: " + tasks.size()));
        System.out.println(colourStringRed(MESSAGE_BUFFER + "----------------"));
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(MESSAGE_BUFFER + colourStringRed((i + 1) + ". " + tasks.get(i).toString()));
        }
        System.out.println(colourStringRed(MESSAGE_BUFFER + "----------------"));
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
    /* END USER TASKS FUNCTIONS */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printGreeting();

        boolean isRunning = true;
        while (isRunning) {
            String userInput = awaitInputFromUser(sc);
            String[] userInputSplit = userInput.split("\\s+", 2);
            String userCommand = userInputSplit[0];
            switch (userCommand.toLowerCase(Locale.ROOT)) {
                case EXIT_COMMAND:
                    isRunning = false;
                    break;
                case LIST_COMMAND:
                    printFromRed("Alright, here are your recorded tasks.");
                    listTasks();
                    break;
                case MARK_COMMAND:
                    UserTask task = getTaskOfNumber(Integer.parseInt(userInputSplit[1]));
                    task.setDone();
                    printFromRed("Good job! Let's keep it going, this spaceship needs you!");
                    printFromRed(task + "\n");
                    break;
                case UNMARK_COMMAND:
                    task = getTaskOfNumber(Integer.parseInt(userInputSplit[1]));
                    task.setUndone();
                    printFromRed("I thought you were done with it?");
                    printFromRed(task + "\n");
                    break;
                case ADD_TODO_COMMAND:
                    UserTask newToDo = new ToDo(userInputSplit[1]);
                    tasks.add(newToDo);
                    printFromRed("Added task #" + (tasks.size()) + ": " + newToDo + "\n");
                    break;
                case ADD_DEADLINE_COMMAND:
                    String[] parsedArguments = userInputSplit[1].split(" /by ");
                    UserTask newDeadline = new Deadline(parsedArguments[0], parsedArguments[1]);
                    tasks.add(newDeadline);
                    printFromRed("Added task #" + (tasks.size()) + ": " + newDeadline + "\n");
                    break;
                default:
                    printFromRed("Unknown command, try again?");
            }
        }

        printExitMessage();
        sc.close();
    }
}
