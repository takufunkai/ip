package duke.usertask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * DeadlineTest tests Deadline objects.
 */
public class DeadlineTest {
    @Test
    void toDukeSaveFormat() throws UserTaskException {
        SaveFormatParser parser = new SaveFormatParser();

        Deadline d1 = new Deadline("Wash dishes", "27-09-1999");
        assertEquals("D|0|Wash dishes|27-09-1999 00:00", parser.getSaveValue(d1));

        d1.setDone();
        assertEquals("D|1|Wash dishes|27-09-1999 00:00", parser.getSaveValue(d1));

        Deadline d2 = new Deadline("Water plants", "31-01-2011 15:30");
        assertEquals("D|0|Water plants|31-01-2011 15:30", parser.getSaveValue(d2));
    }
}
