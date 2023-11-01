package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import connectify.commons.core.index.Index;
import connectify.logic.commands.SharePersonCommand;
import connectify.logic.parser.exceptions.ParseException;

/**
 * Parses a Person object into a SharePersonCommand for execution.
 */
public class SharePersonCommandParser implements Parser<SharePersonCommand> {

    /**
     * Parses the given {@code Person} object and returns a SharePersonCommand object for execution.
     * @throws ParseException if the parsing is unsuccessful
     */
    public SharePersonCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");

            if (splitArgs.length != 2) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            Index companyIndex = ParserPersonUtil.parseIndex(splitArgs[0]);
            Index personIndex = ParserCompanyUtil.parseIndex(splitArgs[1]);

            return new SharePersonCommand(companyIndex, personIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SharePersonCommand.MESSAGE_USAGE), pe);
        }
    }
}
