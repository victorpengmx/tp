package connectify.model.person;

import static java.util.Objects.requireNonNull;
import static connectify.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PeopleList {
    private final List<Person> people = new ArrayList<>();

    /**
     * Constructs a {@code PeopleList}.
     * {@code people} must be present and all people must be not null.
     *
     * @param peopleList The list of people.
     */
    public PeopleList(PeopleList peopleList) {
        requireAllNonNull(peopleList);
        people.addAll(peopleList.people);
    }

    /**
     * Constructs an empty {@code PeopleList}.
     */
    public PeopleList() {}

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
    public PeopleList addPerson(Person toAdd) {
        requireNonNull(toAdd);
        PeopleList edited = new PeopleList(this);
        edited.people.add(toAdd);
        return edited;
    }

    /**
     * Removes a person from the list.
     *
     * @param toRemove The person to remove from the list.
     * @return The modified person list.
     */
    public PeopleList removePerson(Person toRemove) {
        requireNonNull(toRemove);
//        if (!contains(toRemove)) {
//            throw new PersonNotFoundException();
//        }
        PeopleList edited = new PeopleList(this);
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
    public static PeopleList fromList(List<Person> list) {
        PeopleList peopleList = new PeopleList();
        peopleList.people.addAll(list);
        return peopleList;
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

        if (!(other instanceof PeopleList)) {
            return false;
        }

        PeopleList otherPeople = (PeopleList) other;
        return otherPeople.asList().equals(asList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(people);
    }
}