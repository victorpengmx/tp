package connectify.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import connectify.model.AddressBook;
import connectify.model.Note;
import connectify.model.ReadOnlyAddressBook;
import connectify.model.company.Company;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyIndustry;
import connectify.model.company.CompanyLocation;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyPhone;
import connectify.model.company.CompanyWebsite;
import connectify.model.person.Person;
import connectify.model.person.PersonAddress;
import connectify.model.person.PersonEmail;
import connectify.model.person.PersonName;
import connectify.model.person.PersonPhone;
import connectify.model.person.PersonPriority;
import connectify.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Note EMPTY_NOTE = new Note("");
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new PersonName("Alex Yeoh"), new PersonPhone("87438807"),
                new PersonEmail("alexyeoh@example.com"),
                new PersonAddress("Blk 30 Geylang Street 29, #06-40"), EMPTY_NOTE,
                getTagSet("friends"), new PersonPriority("1")),
            new Person(new PersonName("Bernice Yu"), new PersonPhone("99272758"),
                new PersonEmail("berniceyu@example.com"),
                new PersonAddress("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_NOTE,
                getTagSet("colleagues", "friends"), new PersonPriority("2")),
            new Person(new PersonName("Charlotte Oliveiro"), new PersonPhone("93210283"),
                new PersonEmail("charlotte@example.com"),
                new PersonAddress("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_NOTE,
                getTagSet("neighbours"), new PersonPriority("3")),
            new Person(new PersonName("David Li"), new PersonPhone("91031282"),
                new PersonEmail("lidavid@example.com"),
                new PersonAddress("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_NOTE,
                getTagSet("family"), new PersonPriority("4")),
            new Person(new PersonName("Irfan Ibrahim"), new PersonPhone("92492021"),
                new PersonEmail("irfan@example.com"),
                new PersonAddress("Blk 47 Tampines Street 20, #17-35"), EMPTY_NOTE,
                getTagSet("classmates"), new PersonPriority("5")),
            new Person(new PersonName("Roy Balakrishnan"), new PersonPhone("92624417"),
                new PersonEmail("royb@example.com"),
                new PersonAddress("Blk 45 Aljunied Street 85, #11-31"), EMPTY_NOTE,
                getTagSet("colleagues"), new PersonPriority("6"))
        };
    }

    public static Company[] getSampleCompanies() {
        return new Company[] {
            new Company(new CompanyName("Unassigned"), new CompanyIndustry("Unassigned"),
                    new CompanyLocation("Unassigned"), "Unassigned",
                    new CompanyWebsite("Unassigned"), new CompanyEmail("samplecompany@email.com"),
                    new CompanyPhone("12345678"), new CompanyAddress("Unassigned"), EMPTY_NOTE),
                    // To be protected from deletion in future
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
