package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import connectify.logic.commands.FindAllCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.EntityNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindAllCommandParser implements Parser<FindAllCommand> {

    private static final Logger logger = Logger.getLogger(FindCompaniesCommandParser.class.getName());


    /**
     * Parses the given {@code String} of arguments in the context of the FindAllCommand
     * and returns a FindAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAllCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            logger.log(Level.WARNING, "Invalid arguments provided for FindCompaniesCommand: Empty string.");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAllCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        logger.log(Level.INFO,
                "Successfully parsed FindCompaniesCommand with keywords: " + Arrays.toString(nameKeywords));

        return new FindAllCommand(new EntityNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
