package connectify.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import connectify.commons.exceptions.IllegalValueException;
import connectify.model.AddressBook;
import connectify.model.ReadOnlyAddressBook;
import connectify.model.company.Company;
import connectify.model.person.Person;


/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_COMPANY = "Companies list contains duplicate company(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedCompany> companies = new ArrayList<>();

    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("companies") List<JsonAdaptedCompany> companies) {
        if (persons != null) {
            this.persons.addAll(persons);
        }
        if (companies != null) {
            this.companies.addAll(companies);
        }
    }

    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        companies.addAll(source.getCompanyList().stream().map(JsonAdaptedCompany::new).collect(Collectors.toList()));
    }

    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedCompany jsonAdaptedCompany : companies) {
            Company company = jsonAdaptedCompany.toModelType();
            if (addressBook.hasCompany(company)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COMPANY);
            }
            addressBook.addCompany(company);
        }

        return addressBook;
    }
}
