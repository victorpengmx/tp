package connectify.logic.commands;

import static connectify.commons.util.CollectionUtil.requireAllNonNull;
import static connectify.logic.parser.CliSyntax.PREFIX_NOTE;
import static connectify.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.person.PersonNote;
import connectify.model.person.Person;

/**
 * Changes the note of an existing person in the address book.
 */
public class PersonNoteCommand extends Command {
    public static final String COMMAND_WORD = "notePerson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the note of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing note will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE + "[NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "Likes to swim.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Person: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Person: %1$s";

    private final Index index;
    private final PersonNote note;

    /**
     * @param index of the person in the filtered person list to edit the note
     * @param note of the person to be updated to
     */
    public PersonNoteCommand(Index index, PersonNote note) {
        requireAllNonNull(index, note);
        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), note, personToEdit.getPriority());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the note is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !note.getContent().isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonNoteCommand)) {
            return false;
        }

        // state check
        PersonNoteCommand e = (PersonNoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
