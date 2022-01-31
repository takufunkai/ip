package duke.usertask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void toDukeSaveFormat() throws UserTaskException {
        Event e1 = new Event("Conference talk", "19-09-2030");
        assertEquals("E|0|Conference talk|19-09-2030 00:00", e1.toDukeSaveFormat());

        e1.setDone();
        assertEquals("E|1|Conference talk|19-09-2030 00:00", e1.toDukeSaveFormat());

        Event e2 = new Event("Presentation day (testing)", "31-01-2011 15:30");
        assertEquals("E|0|Presentation day (testing)|31-01-2011 15:30", e2.toDukeSaveFormat());
    }
}
