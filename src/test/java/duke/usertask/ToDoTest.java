package duke.usertask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    void toDukeSaveFormat() {
        ToDo t1 = new ToDo("Wash dishes");
        assertEquals("T|0|Wash dishes", t1.toDukeSaveFormat());

        t1.setDone();
        assertEquals("T|1|Wash dishes", t1.toDukeSaveFormat());
    }
}
