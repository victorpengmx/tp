package connectify.logic.commands;

import static java.util.Objects.requireNonNull;

import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.model.EntityNameContainsKeywordsPredicate;
import connectify.model.Model;

/**
 * Finds and lists all persons and companies in the address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindAllCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons and companies whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice google";

    private final EntityNameContainsKeywordsPredicate predicate;

    public FindAllCommand(EntityNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);
        model.updateFilteredEntityList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PEOPLE_AND_COMPANIES_LISTED_OVERVIEW, model.getNumberOfEntities()));


    }



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindAllCommand)) {
            return false;
        }

        FindAllCommand otherFindAllCommand = (FindAllCommand) other;

        return predicate.equals(otherFindAllCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
