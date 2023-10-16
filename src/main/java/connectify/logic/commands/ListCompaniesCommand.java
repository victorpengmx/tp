package connectify.logic.commands;

import static connectify.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static java.util.Objects.requireNonNull;

import connectify.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCompaniesCommand extends Command {

    public static final String COMMAND_WORD = "companies";

    public static final String MESSAGE_SUCCESS = "Listed all companies";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
