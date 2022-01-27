package duke.utils;

public final class Utils {
    public static String colourStringRed(String out) {
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_RED + out + ANSI_RESET;
    }


}
