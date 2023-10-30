package connectify.logic.commands;

import static connectify.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static connectify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connectify.logic.parser.CliSyntax.PREFIX_NAME;
import static connectify.logic.parser.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import connectify.commons.core.index.Index;
import connectify.commons.util.CollectionUtil;
import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.company.Company;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyIndustry;
import connectify.model.company.CompanyLocation;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyNote;
import connectify.model.company.CompanyPhone;
import connectify.model.company.CompanyWebsite;
import connectify.model.person.PersonList;

public class EditCompanyCommand extends Command {

    public static final String COMMAND_WORD = "editCompany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the company identified "
            + "by the index number used in the displayed company list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "NAME "
            + "PHONE "
            + "EMAIL "
            + "ADDRESS "
            + "TAG...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME
            + "TechCorp "
            + PREFIX_PHONE
            + "91234567 "
            + PREFIX_EMAIL
            + "techcorp@gmail.com"
            + PREFIX_ADDRESS
            + "123, Jurong West Ave 6, #08-111";

    public static final String MESSAGE_SUCCESS = "Edited Company: %1$s";
    public static final String MESSAGE_DUPLICATE_COMPANY = "This company already exists in Connectify.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    private final Index companyIndex;
    private final EditCompanyDescriptor editCompanyDescriptor;

    /**
     * @param companyIndex of the company in the filtered company list to edit
     * @param editCompanyDescriptor details to edit the company with
     */
    public EditCompanyCommand(Index companyIndex, EditCompanyDescriptor editCompanyDescriptor) {
        requireNonNull(companyIndex);
        requireNonNull(editCompanyDescriptor);

        this.companyIndex = companyIndex;
        this.editCompanyDescriptor = new EditCompanyDescriptor(editCompanyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (companyIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToEdit = lastShownList.get(companyIndex.getZeroBased());
        Company editedCompany = createEditedCompany(companyToEdit, editCompanyDescriptor);

        if (!companyToEdit.isSameCompany(editedCompany) && model.hasCompany(editedCompany)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMPANY);
        }

        model.setCompany(companyToEdit, editedCompany);
        model.updateFilteredCompanyList(Model.PREDICATE_SHOW_ALL_COMPANIES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedCompany));
    }

    /**
     * Creates and returns a {@code Company} with the details of {@code companyToEdit}
     * edited with {@code editCompanyDescriptor}.
     */
    public Company createEditedCompany(Company companyToEdit, EditCompanyDescriptor editCompanyDescriptor) {
        requireNonNull(companyToEdit);

        CompanyName updatedName = editCompanyDescriptor.getName().orElse(companyToEdit.getName());
        CompanyIndustry updatedIndustry = editCompanyDescriptor.getIndustry().orElse(companyToEdit.getIndustry());
        CompanyLocation updatedLocation = editCompanyDescriptor.getLocation().orElse(companyToEdit.getLocation());
        String updatedDescription = editCompanyDescriptor.getDescription().orElse(companyToEdit.getDescription());
        CompanyWebsite updatedWebsite = editCompanyDescriptor.getWebsite().orElse(companyToEdit.getWebsite());
        CompanyPhone updatedPhone = editCompanyDescriptor.getPhone().orElse(companyToEdit.getPhone());
        CompanyEmail updatedEmail = editCompanyDescriptor.getEmail().orElse(companyToEdit.getEmail());
        CompanyAddress updatedAddress = editCompanyDescriptor.getAddress().orElse(companyToEdit.getAddress());
        CompanyNote updatedCompanyNote = editCompanyDescriptor.getCompanyNote().orElse(companyToEdit.getNote());
        PersonList updatedPersonList = editCompanyDescriptor.getPersonList().orElse(companyToEdit.getPersonList());

        return new Company(updatedName, updatedIndustry, updatedLocation, updatedDescription, updatedWebsite,
                updatedEmail, updatedPhone, updatedAddress, updatedCompanyNote, updatedPersonList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCompanyCommand)) {
            return false;
        }

        // state check
        EditCompanyCommand e = (EditCompanyCommand) other;
        return companyIndex.equals(e.companyIndex)
                && editCompanyDescriptor.equals(e.editCompanyDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companyIndex", companyIndex)
                .add("editCompanyDescriptor", editCompanyDescriptor)
                .toString();
    }


    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditCompanyDescriptor {
        private CompanyName companyName;
        private CompanyIndustry companyIndustry;
        private CompanyLocation companyLocation;
        private String companyDescription;
        private CompanyWebsite companyWebsite;
        private CompanyEmail companyEmail;
        private CompanyPhone companyPhone;

        private CompanyAddress companyAddress;
        private CompanyNote companyNote;
        private PersonList personList;



        public EditCompanyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCompanyDescriptor(EditCompanyDescriptor toCopy) {
            setName(toCopy.companyName);
            setIndustry(toCopy.companyIndustry);
            setLocation(toCopy.companyLocation);
            setDescription(toCopy.companyDescription);
            setWebsite(toCopy.companyWebsite);
            setPhone(toCopy.companyPhone);
            setEmail(toCopy.companyEmail);
            setAddress(toCopy.companyAddress);
            setCompanyNote(toCopy.companyNote);
            setPersonList(toCopy.personList);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(companyName, companyIndustry, companyLocation, companyDescription,
                    companyWebsite, companyEmail, companyPhone, companyAddress, companyNote, personList);
        }

        public void setName(CompanyName companyName) {
            this.companyName = companyName;
        }

        public Optional<CompanyName> getName() {
            return Optional.ofNullable(companyName);
        }

        public void setIndustry(CompanyIndustry companyIndustry) {
            this.companyIndustry = companyIndustry;
        }

        public Optional<CompanyIndustry> getIndustry() {
            return Optional.ofNullable(companyIndustry);
        }

        public void setLocation(CompanyLocation location) {
            this.companyLocation = location;
        }

        public Optional<CompanyLocation> getLocation() {
            return Optional.ofNullable(companyLocation);
        }

        public void setDescription(String companyDescription) {
            this.companyDescription = companyDescription;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(companyDescription);
        }

        public void setWebsite(CompanyWebsite website) {
            this.companyWebsite = website;
        }

        public Optional<CompanyWebsite> getWebsite() {
            return Optional.ofNullable(companyWebsite);
        }

        public void setPhone(CompanyPhone companyPhone) {
            this.companyPhone = companyPhone;
        }

        public Optional<CompanyPhone> getPhone() {
            return Optional.ofNullable(companyPhone);
        }

        public void setEmail(CompanyEmail companyEmail) {
            this.companyEmail = companyEmail;
        }

        public Optional<CompanyEmail> getEmail() {
            return Optional.ofNullable(companyEmail);
        }

        public void setAddress(CompanyAddress companyAddress) {
            this.companyAddress = companyAddress;
        }

        public Optional<CompanyAddress> getAddress() {
            return Optional.ofNullable(companyAddress);
        }

        public void setCompanyNote (CompanyNote companyNote) {
            this.companyNote = companyNote;
        }

        public Optional<CompanyNote> getCompanyNote() {
            return Optional.ofNullable(companyNote);
        }

        public void setPersonList(PersonList personList) {
            this.personList = personList;
        }

        public Optional<PersonList> getPersonList() {
            return Optional.ofNullable(personList);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonCommand.EditPersonDescriptor)) {
                return false;
            }

            EditCompanyCommand.EditCompanyDescriptor e = (EditCompanyCommand.EditCompanyDescriptor) other;
            return getName().equals(e.getName())
                    && getIndustry().equals(e.getIndustry())
                    && getLocation().equals(e.getLocation())
                    && getDescription().equals(e.getDescription())
                    && getWebsite().equals(e.getWebsite())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getCompanyNote().equals(e.getCompanyNote())
                    && getPersonList().equals(e.getPersonList());
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", getName())
                    .add("industry", getIndustry())
                    .add("location", getLocation())
                    .add("description", getDescription())
                    .add("website", getWebsite())
                    .add("phone", getPhone())
                    .add("email", getEmail())
                    .add("address", getAddress())
                    .add("companyNote", getCompanyNote())
                    .add("personList", getPersonList())
                    .toString();
        }
    }
}
