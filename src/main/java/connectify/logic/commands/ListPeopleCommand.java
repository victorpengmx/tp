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


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ListPeopleCommand; // instanceof handles nulls
    }
}
