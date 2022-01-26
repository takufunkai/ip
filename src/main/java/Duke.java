import usertask.TaskList;

import java.util.Locale;
import java.util.Scanner;

public class Duke {
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

    protected final static TaskList tasks = new TaskList((100));
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

    protected static void printFromRed(String message) {
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

    protected static void listTasks() {
        Duke.listTasks("");
    }

    protected static void listTasks(String date) {
        String tableDivider = colourStringRed(MESSAGE_BUFFER + "----------------");
        TaskList filteredTasks;
        if (!date.isBlank()) {
            filteredTasks = Duke.tasks.filterByDate(date);
        } else {
            filteredTasks = Duke.tasks;
        }

        System.out.println(tableDivider + "\n" +
                colourStringRed(MESSAGE_BUFFER + "TOTAL: " + filteredTasks.getTasksCount()) + "\n" +
                tableDivider);
        StringBuilder sb = new StringBuilder();
        String[] taskStringsList = filteredTasks.toString().split("\\r?\\n");
        for (String s : taskStringsList) {
            sb.append(MESSAGE_BUFFER).append(s).append("\n");
        }
        System.out.print(colourStringRed(sb.toString()));
        System.out.println(tableDivider + "\n");
    }
    /* END IO FUNCTIONS */

    protected static void terminate() {
        Duke.isRunning = false;
    }

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
                userCommand.validateAndExecute(userInputSplit.length == 1 ? "" : userInputSplit[1]);
            } catch (DukeException e) {
                Duke.printFromRed("Oops, something went wrong: ");
                Duke.printFromRed("** " + e.getMessage() + "\n");
            }
        }
        Duke.printExitMessage();
        sc.close();
    }
}
