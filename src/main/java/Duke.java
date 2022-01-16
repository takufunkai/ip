import java.util.Scanner;

public class Duke {
    /* CONSTANTS */
    private final static String CREW_MATE_LOGO = "ඞ";
    private final static String SMILEY_LOGO = "☺";

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final static String MESSAGE_BUFFER = "                 ";
    private final static String DIVIDER =
        " ---------------  ---------------  ---------------  ---------------  --------------- \n" +
        " -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::- \n" +
        " ---------------  ---------------  ---------------  ---------------  --------------- \n";
    private final static String GREETING_MESSAGE =
        wrapStringRed(
    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
        "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀\n" +
        "⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀\n" +
        "⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀\n" +
        "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀\n" +
        "⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀\n" +
        "⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀\n" +
        "⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀\n" +
        "⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀        ඞ Hello, I am Red from Among Us.\n" +
        "⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⠀\n" +
        "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀            ඞ We are currently facing a crisis onboard --\n" +
        "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀          -- there seems to be an imposter among us...\n" +
        "⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀\n" +
        "⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀          ඞ My job is to handle chat requests,\n" +
        "⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀⠀        so although I might get murdered any moment now...\n" +
        "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀            ... how can I help you?\n" +
        "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀\n" +
        "⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀\n" +
        "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n") +
        DIVIDER;

    private final static String EXIT_COMMAND = "bye";
    /* END CONSTANTS */

    /* HELPER FUNCTIONS */
    private static String wrapStringRed(String out) {
        return ANSI_RED + out + ANSI_RESET;
    }
    /* END HELPER FUNCTIONS */

    private static void printGreeting() {
        System.out.println(GREETING_MESSAGE);
    }

    private static void printFromRed(String message) {
        String redMessageSuffix = MESSAGE_BUFFER + " >>> " + CREW_MATE_LOGO + " > ";
        System.out.println(wrapStringRed(redMessageSuffix + message + "\n"));
    }

    private static void printInputMessage() {
        System.out.print(MESSAGE_BUFFER + " >>> " + SMILEY_LOGO + " YOU > ");
    }

    private static void handleUserExit() {
        printFromRed("Thank you for chatting with me... bye forever");
        System.out.println(DIVIDER +
            ". 　　　。　　　　•　 　ﾟ　　。 　　.\n" +
            "\n" +
            "　　　.　　　 　　.　　　　　。　　 。　. 　\n" +
            "\n" +
            ".　　 。　　　　　 "+wrapStringRed(CREW_MATE_LOGO)+" 。 . 　　 • 　　　　•\n" +
            "\n" +
            "　　ﾟ　　 Red was not An Impostor.　 。　.\n" +
            "\n" +
            "　　'　　　     2 Impostors remain 　 　　。\n" +
            "\n" +
            "　　ﾟ　　　.　　　. ,　　　　.　 .");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printGreeting();

        String userInput = "";
        while (true) {
            printInputMessage();
            userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
                handleUserExit();
                break;
            }
            printFromRed(userInput);
        }
        sc.close();
    }
}
