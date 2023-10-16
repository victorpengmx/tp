package connectify.logic.commands;

import static connectify.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static connectify.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static java.util.Objects.requireNonNull;

import connectify.model.Model;

/**
 * Lists all entities (both persons and companies) in the address book to the user.
 */
public class ListAllCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons and companies";

    public static final String EMPTY_LIST_MESSAGE = "There are no entities in Connectify";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        model.updateToAllEntities();
        Integer numberOfEntities = model.getFilteredEntityList().size();
        if (numberOfEntities == 0) {
            return new CommandResult(EMPTY_LIST_MESSAGE);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other instanceof ListAllCommand) {
            return true;
        }

        return false;
    }
}
