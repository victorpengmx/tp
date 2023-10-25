package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CliSyntax.PREFIX_NOTE;
import static connectify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static connectify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.commands.NoteCommand;
import connectify.model.Note;

public class NoteCommandParserTest {
    private NoteCommandParser parser = new NoteCommandParser();
    private final String nonEmptyNote = "Some note.";

    @Test
    public void parse_indexSpecified_success() {
        // have note
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE + nonEmptyNote;
        NoteCommand expectedCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(nonEmptyNote));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no note
        userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE;
        expectedCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, NoteCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, NoteCommand.COMMAND_WORD + " " + nonEmptyNote, expectedMessage);
    }
}