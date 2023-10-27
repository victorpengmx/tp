package connectify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import connectify.commons.core.index.Index;
import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.model.person.PersonList;

/**
 * Deletes a person identified using its displayed index from a specified company and from the address book.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "deletePerson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number from the specified company "
            + "and also removes them from the address book.\n"
            + "Parameters: COMPANY_INDEX (must be a positive integer) PERSON_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index companyIndex;
    private final Index personIndex;

    /**
     * Constructs a DeletePersonCommand to delete a person from a company using their respective indices.
     *
     * @param companyIndex The index of the company from which the person should be deleted.
     * @param personIndex The index of the person to be deleted within the specified company.
     */
    public DeletePersonCommand(Index companyIndex, Index personIndex) {
        this.companyIndex = companyIndex;
        this.personIndex = personIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Company> companyList = model.getFilteredCompanyList();

        if (companyIndex.getZeroBased() >= companyList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToUpdate = companyList.get(companyIndex.getZeroBased());
        PersonList companyPersonsList = companyToUpdate.getPersonList();

        if (personIndex.getZeroBased() >= companyPersonsList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = companyPersonsList.get(personIndex.getZeroBased());

        Company editedCompany = companyToUpdate.deletePersonFromCompany(personToDelete);
        model.setCompany(companyToUpdate, editedCompany);

        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeletePersonCommand)) {
            return false;
        }

        DeletePersonCommand otherDeleteCommand = (DeletePersonCommand) other;
        return personIndex.equals(otherDeleteCommand.personIndex)
                && companyIndex.equals(otherDeleteCommand.companyIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companyIndex", companyIndex)
                .add("personIndex", personIndex)
                .toString();
    }
}
