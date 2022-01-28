package duke.textui;

import duke.utils.Utils;

import java.util.Scanner;

/**
 * The TextUi class handles interaction of the chat-bot with the user. The object can be seen as the interaction layer
 * between the user and the chat-bot. It encapsulates the Scanner IO object that is being used to interact with
 * the user, and also other information such as greeting messages.
 * <p>
 * It is almost always preferable to use the TextUi object to interact with the user instead of manually
 * using some other output methods such as <code>System.out::println</code>, as the methods here are formatted
 * to a specific style that abides by the stylistic theme of the chat-bot.
 */
public class TextUi {
    private final static String CREW_MATE_LOGO = "ඞ";
    private final static String MESSAGE_BUFFER = "                  ";
    private final static String DIVIDER =
            " ---------------  ---------------  ---------------  ---------------  ---------------\n" +
                    " -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::-\n" +
                    " ---------------  ---------------  ---------------  ---------------  ---------------\n";
    private final static String GREETING_MESSAGE =
            Utils.colourStringRed(
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

    private final Scanner sc;

    /**
     * Creates a new TextUi object. It initializes a new Scanner object that reads from stdin by default.
     */
    public TextUi() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome greeting to the user.
     */
    public void printGreeting() {
        System.out.println(GREETING_MESSAGE);
    }

    /**
     * Prints an exit greeting to the user.
     */
    public void printExitMessage() {
        printFromRed("Thank you for chatting with me... bye forever");
        System.out.println();
        System.out.println(DIVIDER +
                ". 　　　。　　　　•　 　ﾟ　　。 　　.\n" +
                "\n" +
                "　　　.　　　 　　.　　　　　。　　 。　. 　\n" +
                "\n" +
                ".　　 。　　　　　 " + Utils.colourStringRed(CREW_MATE_LOGO) + " 。 . 　　 • 　　　　•\n" +
                "\n" +
                "　　ﾟ　　 Red was not An Impostor.　 。　.\n" +
                "\n" +
                "　　'　　　     2 Impostors remain 　 　　。\n" +
                "\n" +
                "　　ﾟ　　　.　　　. ,　　　　.　 .");
    }

    /**
     * Prints the specified message after prepending <code>MESSAGE_BUFFER</code> and colouring it red.
     *
     * @param message The message that the user will read.
     */
    public void printWithBuffer(String message) {
        System.out.print(Utils.colourStringRed(MESSAGE_BUFFER + message));
    }

    /**
     * Prints using <code>printWithBuffer</code>, and includes a prefix that indicates that the message was
     * sent directly from Red, the chat-bot mascot.
     *
     * @param message The message that the user will read.
     */
    public void printFromRed(String message) {
        this.printWithBuffer(">>> " + CREW_MATE_LOGO + " > " + message);
    }

    /**
     * Prints a helper message that indicates that the chat-bot is awaiting some input from the user. Then, reads
     * the incoming line from the user.
     *
     * @return A Scanner::nextLine execution that reads the user input up to the next line-separator specified.
     */
    public String awaitInputFromUser() {
        printWithBuffer(">>> ☺ YOU > ");
        return this.sc.nextLine();
    }

}
