package connectify.logic.commands;

import static connectify.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import connectify.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListPeopleCommand extends Command {

    public static final String COMMAND_WORD = "people";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String EMPTY_LIST_MESSAGE = "There are no people in Connectify";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        Integer size = model.getNumberOfPeople();
        if (size == 0) {
            return new CommandResult(EMPTY_LIST_MESSAGE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof ListPeopleCommand)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "ListPeopleCommand";
    }
}
