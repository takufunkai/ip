package duke.textui;

import duke.utils.Utils;

import java.util.Scanner;

public class TextUi {
    public final static String CREW_MATE_LOGO = "ඞ";
    public final static String MESSAGE_BUFFER = "                  ";
    public final static String DIVIDER =
            " ---------------  ---------------  ---------------  ---------------  ---------------\n" +
                    " -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::-  -:::::::::::::-\n" +
                    " ---------------  ---------------  ---------------  ---------------  ---------------\n";
    public final static String GREETING_MESSAGE =
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

    public TextUi() {
        this.sc = new Scanner(System.in);
    }

    public void printGreeting() {
        System.out.println(GREETING_MESSAGE);
    }

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

    public void printWithBuffer(String message) {
        System.out.print(Utils.colourStringRed(MESSAGE_BUFFER + message));
    }

    public void printFromRed(String message) {
        this.printWithBuffer(">>> " + CREW_MATE_LOGO + " > " + message);
    }

    public String awaitInputFromUser() {
        printWithBuffer(">>> ☺ YOU > ");
        return this.sc.nextLine();
    }

}
