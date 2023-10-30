package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showPersonAtIndex;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SharePersonCommand.
 */
public class SharePersonCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfiltered_showsShareSuccessMessage() {
        Index index = INDEX_FIRST_PERSON;
        Person personToShare = model.getFilteredPersonList().get(index.getZeroBased());

        SharePersonCommand shareCommand = new SharePersonCommand(index);
        String expectedMessage = String.format(SharePersonCommand.MESSAGE_SHARE_PERSON_SUCCESS,
                SharePersonCommand.formatInput(Messages.format(personToShare)));

        assertCommandSuccess(shareCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfiltered_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SharePersonCommand shareCommand = new SharePersonCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(shareCommand, model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFiltered_showsShareSuccessMessage() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index index = INDEX_FIRST_PERSON;
        Person personToShare = model.getFilteredPersonList().get(index.getZeroBased());

        SharePersonCommand shareCommand = new SharePersonCommand(index);
        String expectedMessage = String.format(SharePersonCommand.MESSAGE_SHARE_PERSON_SUCCESS,
                SharePersonCommand.formatInput(Messages.format(personToShare)));

        assertCommandSuccess(shareCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFiltered_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SharePersonCommand shareCommand = new SharePersonCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(shareCommand, model,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SharePersonCommand shareCommand1 = new SharePersonCommand(INDEX_FIRST_PERSON);
        SharePersonCommand shareCommand2 = new SharePersonCommand(INDEX_FIRST_PERSON);

        // same object -> returns true
        assertTrue(shareCommand1.equals(shareCommand1));

        // same values -> returns true
        assertTrue(shareCommand1.equals(shareCommand2));

        // different types -> returns false
        assertFalse(shareCommand1.equals(1));

        // null -> returns false
        assertFalse(shareCommand1.equals(null));

        // different index -> returns false
        SharePersonCommand differentCommand = new SharePersonCommand(Index.fromOneBased(2));
        assertFalse(shareCommand1.equals(differentCommand));
    }
}
