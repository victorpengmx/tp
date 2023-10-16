package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalCompanies.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAllCommand.
 */

public class ListAllCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAllCommand(), model, ListAllCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ListAllCommand listAllCommand1 = new ListAllCommand();
        ListAllCommand listAllCommand2 = new ListAllCommand();

        // same object -> returns true
        assertTrue(listAllCommand1.equals(listAllCommand1));

        // null -> returns false
        assertFalse(listAllCommand1.equals(null));

    }
}
