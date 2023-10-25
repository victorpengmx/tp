package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static connectify.logic.parser.CliSyntax.PREFIX_COMPANY;
import static connectify.logic.parser.CliSyntax.PREFIX_NOTE;
import static connectify.testutil.Assert.assertThrows;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import connectify.logic.commands.*;
import connectify.model.Note;
import org.junit.jupiter.api.Test;

import connectify.logic.commands.EditPersonCommand.EditPersonDescriptor;
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
        assertEquals(new AddPersonCommand(person, INDEX_FIRST_COMPANY), command);
    }

    @Test
    public void parseCommand_addCompany() throws Exception {
        Company company = new CompanyBuilder().build();
        AddCompanyCommand command = (AddCompanyCommand) parser.parseCommand(CompanyUtil.getAddCommand(company));
        assertEquals(new AddCompanyCommand(company), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteCompany() throws Exception {
        DeleteCompanyCommand command = (DeleteCompanyCommand) parser.parseCommand(
                DeleteCompanyCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY.getOneBased());
        assertEquals(new DeleteCompanyCommand(INDEX_FIRST_COMPANY), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_COMPANY.getOneBased()
                        +
                        " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON), command);
    }


    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_COMPANY
                + INDEX_FIRST_COMPANY.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON, INDEX_FIRST_COMPANY, descriptor), command);
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
    public void parseCommand_listCompanies() throws Exception {
        assertTrue(parser.parseCommand(ListCompaniesCommand.COMMAND_WORD) instanceof ListCompaniesCommand);
        assertTrue(parser.parseCommand(ListCompaniesCommand.COMMAND_WORD + " 3") instanceof ListCompaniesCommand);
    }

    @Test
    public void parseCommand_listPeople() throws Exception {
        assertTrue(parser.parseCommand(ListPeopleCommand.COMMAND_WORD) instanceof ListPeopleCommand);
        assertTrue(parser.parseCommand(ListPeopleCommand.COMMAND_WORD + " 3") instanceof ListPeopleCommand);
    }

    @Test
    public void parseCommand_note() throws Exception {
        final Note note = new Note("Some note");
        NoteCommand command = (NoteCommand) parser.parseCommand(NoteCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_NOTE + note.getContent());
        assertEquals(new NoteCommand(INDEX_FIRST_PERSON, note), command);
    }

    @Test
    public void parseCommand_listAllEntities() throws Exception {
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD + " 3") instanceof ListAllCommand);
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
