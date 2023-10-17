package connectify.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static connectify.testutil.Assert.assertThrows;
import static connectify.testutil.TypicalPersons.ALICE;
import static connectify.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import connectify.testutil.PersonBuilder;
import connectify.testutil.PersonListBuilder;

public class PersonListTest {
    @Test
    public void addPerson_personAdded() {
        PersonList persons = new PersonList();
        PersonList newPersons = new PersonListBuilder(persons).withPerson(ALICE).build();
        assertEquals(newPersons, persons.addPerson(ALICE));
    }

    @Test
    public void removePerson_personRemoved() {
        Person newPerson = new PersonBuilder().build();
        PersonList persons = new PersonListBuilder().withPerson(ALICE).withPerson(newPerson).build();
        PersonList newPersons = new PersonListBuilder().withPerson(newPerson).build();
        assertEquals(newPersons, persons.removePerson(ALICE));
    }

    @Test
    public void equals() {
        PersonList persons = new PersonListBuilder().withPerson(ALICE).withPerson(new PersonBuilder().build()).build();
        PersonList personsCopy = new PersonListBuilder(persons).build();

        assertTrue(persons.equals(personsCopy));
    }

    @Test
    public void asList_modifyList_throwsUnsupportedOperationException() {
        PersonList persons = new PersonListBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> persons.asList().remove(0));
    }
}