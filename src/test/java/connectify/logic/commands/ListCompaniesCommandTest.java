package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static connectify.testutil.TypicalCompanies.getTypicalAddressBook;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCompaniesCommand.
 */

public class ListCompaniesCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCompaniesCommand(), model, ListCompaniesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);
        assertCommandSuccess(new ListCompaniesCommand(), model, ListCompaniesCommand.MESSAGE_SUCCESS, expectedModel);
    }


}
