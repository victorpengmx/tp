package connectify.logic.commands;

import static connectify.logic.Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW;
import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalCompanies.COMPANY_1;
import static connectify.testutil.TypicalCompanies.COMPANY_2;
import static connectify.testutil.TypicalCompanies.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;
import connectify.model.company.CompanyNameContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCompaniesCommand}.
 */
public class FindCompaniesCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyNameContainsKeywordsPredicate firstPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CompanyNameContainsKeywordsPredicate secondPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCompaniesCommand findFirstCommand = new FindCompaniesCommand(firstPredicate);
        FindCompaniesCommand findSecondCommand = new FindCompaniesCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCompaniesCommand findFirstCommandCopy = new FindCompaniesCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCompanyFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 0);
        CompanyNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCompaniesCommand command = new FindCompaniesCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCompanyList());
    }

    @Test
    public void execute_multipleKeywords_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 2);
        CompanyNameContainsKeywordsPredicate predicate = preparePredicate("Company1 Banana");
        FindCompaniesCommand command = new FindCompaniesCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Company> expectedCompanies = Arrays.asList(COMPANY_1, COMPANY_2);
        assertEquals(expectedCompanies, model.getFilteredCompanyList());
    }

    /**
     * Parses {@code userInput} into a {@code EntityNameContainsKeywordsPredicate}.
     */
    private CompanyNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CompanyNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
