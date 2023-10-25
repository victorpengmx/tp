package connectify.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NoteTest {
    @Test
    public void equals() {
        Note note = new Note("Test");
        // same object -> returns true
        assertTrue(note.equals(note));
        // null -> returns false
        assertFalse(note.equals(null));
        // different type -> returns false
        assertFalse(note.equals(5));
        // different note -> returns false
        assertFalse(note.equals(new Note("Test2")));
        // same content -> returns true
        assertTrue(note.equals(new Note ("Test")));
    }
}
