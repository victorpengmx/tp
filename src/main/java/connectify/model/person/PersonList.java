package connectify.model.person;

import static java.util.Objects.requireNonNull;
import static connectify.commons.util.CollectionUtil.requireAllNonNull;

import connectify.model.person.exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PersonList {
    private final List<Person> people = new ArrayList<>();

    /**
     * Constructs a {@code PeopleList}.
     * {@code people} must be present and all people must be not null.
     *
     * @param personList The list of people.
     */
    public PersonList(PersonList personList) {
        requireAllNonNull(personList);
        people.addAll(personList.people);
    }

    /**
     * Constructs an empty {@code PeopleList}.
     */
    public PersonList() {}

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     *
     * @param toCheck The person to check for in the list.
     * @return true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return people.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a person to the list.
     *
     * @param toAdd The person to add to the list.
     * @return The modified person list.
     */
    public PersonList addPerson(Person toAdd) {
        requireNonNull(toAdd);
        PersonList edited = new PersonList(this);
        edited.people.add(toAdd);
        return edited;
    }

    /**
     * Removes a person from the list.
     *
     * @param toRemove The person to remove from the list.
     * @return The modified person list.
     */
    public PersonList removePerson(Person toRemove) {
        requireNonNull(toRemove);
        if (!contains(toRemove)) {
            throw new PersonNotFoundException();
        }
        PersonList edited = new PersonList(this);
        edited.people.remove(toRemove);
        return edited;
    }

    /**
     * Returns an immutable person list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable person list.
     */
    public List<Person> asList() {
        return Collections.unmodifiableList(people);
    }

    /**
     * Returns a person list with the elements from {@code list}.
     *
     * @param list The list of {@code Person}s to use.
     * @return The new PeopleList.
     */
    public static PersonList fromList(List<Person> list) {
        PersonList personList = new PersonList();
        personList.people.addAll(list);
        return personList;
    }

    /**
     * Returns true only if both people have the same data fields.
     *
     * @param other The other {@code Person} object to check.
     * @return true only if both people have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonList)) {
            return false;
        }

        PersonList otherPeople = (PersonList) other;
        return otherPeople.asList().equals(asList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(people);
    }
}