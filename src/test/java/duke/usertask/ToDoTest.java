package duke.usertask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * ToDoTest tests ToDo objects.
 */
public class ToDoTest {
    @Test
    void toDukeSaveFormat() {
        SaveFormatParser parser = new SaveFormatParser();

        ToDo t1 = new ToDo("Wash dishes");
        assertEquals("T|0|Wash dishes", parser.getSaveValue(t1));

        t1.setDone();
        assertEquals("T|1|Wash dishes", parser.getSaveValue(t1));
    }
}
