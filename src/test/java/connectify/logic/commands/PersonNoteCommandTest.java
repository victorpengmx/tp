package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static connectify.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static connectify.logic.commands.CommandTestUtil.assertCommandFailure;
import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showPersonAtIndex;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static connectify.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.model.AddressBook;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.model.person.PersonNote;
import connectify.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for NoteCommand.
 */
public class PersonNoteCommandTest {
    private static final String NOTE_STUB = "Some note";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addNoteUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withNote(NOTE_STUB)
                                            .withParentCompany(firstPerson.getParentCompany()).build();

        PersonNoteCommand personNoteCommand = new PersonNoteCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON,
                new PersonNote(editedPerson.getNote().getContent()));

        String expectedMessage = String.format(PersonNoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        Company expectedCompanyToUpdate = expectedModel.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = expectedCompanyToUpdate.setPerson(firstPerson, editedPerson);
        expectedModel.setCompany(expectedCompanyToUpdate, editedCompany);

        assertCommandSuccess(personNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteNoteUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withNote("").build();

        PersonNoteCommand personNoteCommand = new PersonNoteCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON,
                new PersonNote(editedPerson.getNote().toString()));

        String expectedMessage = String.format(PersonNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        Company expectedCompanyToUpdate = expectedModel.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = expectedCompanyToUpdate.setPerson(firstPerson, editedPerson);
        expectedModel.setCompany(expectedCompanyToUpdate, editedCompany);

        assertCommandSuccess(personNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withNote(NOTE_STUB).build();

        PersonNoteCommand personNoteCommand = new PersonNoteCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON,
                new PersonNote(editedPerson.getNote().getContent()));

        String expectedMessage = String.format(PersonNoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        Company expectedCompanyToUpdate = expectedModel.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = expectedCompanyToUpdate.setPerson(firstPerson, editedPerson);
        expectedModel.setCompany(expectedCompanyToUpdate, editedCompany);

        assertCommandSuccess(personNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PersonNoteCommand personNoteCommand = new PersonNoteCommand(INDEX_FIRST_COMPANY, outOfBoundIndex,
                new PersonNote(VALID_NOTE_BOB));

        assertCommandFailure(personNoteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where the index is larger than the size of the filtered list,
     * but smaller than the size of the address book.
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_THIRD_PERSON;
        // Ensures that outOfBoundIndex is still in the bounds of the address book list.
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PersonNoteCommand personNoteCommand = new PersonNoteCommand(INDEX_FIRST_COMPANY, outOfBoundIndex,
                new PersonNote(VALID_NOTE_BOB));

        assertCommandFailure(personNoteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PersonNoteCommand standardCommand = new PersonNoteCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON,
                new PersonNote(VALID_NOTE_AMY));
        // Same values -> returns true
        PersonNoteCommand commandWithSameValues = new PersonNoteCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON,
                new PersonNote(VALID_NOTE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // Same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // Null -> returns false
        assertFalse(standardCommand.equals(null));

        // Different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // Different index -> returns false
        assertFalse(standardCommand.equals(new PersonNoteCommand(INDEX_FIRST_COMPANY, INDEX_SECOND_PERSON,
                new PersonNote(VALID_NOTE_AMY))));
        // Different note -> returns false
        assertFalse(standardCommand.equals(new PersonNoteCommand(INDEX_FIRST_COMPANY, INDEX_SECOND_PERSON,
                new PersonNote(VALID_NOTE_BOB))));
    }
}
