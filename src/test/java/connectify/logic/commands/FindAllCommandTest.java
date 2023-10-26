package connectify.logic.commands;

import static connectify.logic.Messages.MESSAGE_PEOPLE_AND_COMPANIES_LISTED_OVERVIEW;
import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalCompanies.COMPANY_1;
import static connectify.testutil.TypicalPersons.CARL;
import static connectify.testutil.TypicalPersons.ELLE;
import static connectify.testutil.TypicalPersons.FIONA;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import connectify.model.Entity;
import connectify.model.EntityNameContainsKeywordsPredicate;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;




/**
 * Contains integration tests (interaction with the Model) for {@code FindAllCommand}.
 */
public class FindAllCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        EntityNameContainsKeywordsPredicate firstPredicate =
                new EntityNameContainsKeywordsPredicate(Collections.singletonList("first"));
        EntityNameContainsKeywordsPredicate secondPredicate =
                new EntityNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindAllCommand findFirstCommand = new FindAllCommand(firstPredicate);
        FindAllCommand findSecondCommand = new FindAllCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAllCommand findFirstCommandCopy = new FindAllCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different entity -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noEntityFound() {
        String expectedMessage = String.format(MESSAGE_PEOPLE_AND_COMPANIES_LISTED_OVERVIEW, 0);
        EntityNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindAllCommand command = new FindAllCommand(predicate);
        expectedModel.updateFilteredEntityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEntityList());
    }

    @Test
    public void execute_multipleKeywords_multipleEntitiesFound() {
        String expectedMessage = String.format(MESSAGE_PEOPLE_AND_COMPANIES_LISTED_OVERVIEW, 4);
        EntityNameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz Company1");
        FindAllCommand command = new FindAllCommand(predicate);
        expectedModel.updateFilteredEntityList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Entity> expectedEntities = Arrays.asList(COMPANY_1, CARL, ELLE, FIONA);
        List<Entity> actualEntities = new ArrayList<>(model.getFilteredEntityList());

        assertTrue(expectedEntities.size() == actualEntities.size()
                &&
                IntStream.range(0, expectedEntities.size())
                        .allMatch(i -> expectedEntities.get(i).getName().equals(actualEntities.get(i).getName())));
    }

    /**
     * Parses {@code userInput} into a {@code EntityNameContainsKeywordsPredicate}.
     */
    private EntityNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new EntityNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
