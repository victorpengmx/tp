package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static connectify.testutil.Assert.assertThrows;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import connectify.logic.commands.AddPersonCommand;
import connectify.logic.commands.AddCompanyCommand;
import connectify.logic.commands.ClearCommand;
import connectify.logic.commands.DeletePersonCommand;
import connectify.logic.commands.EditCommand;
import connectify.logic.commands.EditCommand.EditPersonDescriptor;
import connectify.logic.commands.ExitCommand;
import connectify.logic.commands.FindCommand;
import connectify.logic.commands.HelpCommand;
import connectify.logic.commands.ListCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.company.Company;
import connectify.model.person.NameContainsKeywordsPredicate;
import connectify.model.person.Person;
import connectify.testutil.CompanyBuilder;
import connectify.testutil.CompanyUtil;
import connectify.testutil.EditPersonDescriptorBuilder;
import connectify.testutil.PersonBuilder;
import connectify.testutil.PersonUtil;

public class ConnectifyParserTest {

    private final ConnectifyParser parser = new ConnectifyParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddPersonCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_addCompany() throws Exception {
        Company company = new CompanyBuilder().build();
        AddCompanyCommand command = (AddCompanyCommand) parser.parseCommand(CompanyUtil.getAddPersonCommand(company));
        assertEquals(new AddCompanyCommand(company), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
