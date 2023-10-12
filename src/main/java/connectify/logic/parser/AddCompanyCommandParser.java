package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import connectify.logic.parser.exceptions.ParseException;
import connectify.logic.commands.AddCompanyCommand;
import connectify.model.company.Company;

/**
 * Parses input arguments and creates a new AddCompanyCommand object
 */
public class AddCompanyCommandParser implements Parser<AddCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCompanyCommand
     * and returns an AddCompanyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCompanyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_WEBSITE, CliSyntax.PREFIX_INDUSTRY,
                        CliSyntax.PREFIX_LOCATION, CliSyntax.PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_WEBSITE, CliSyntax.PREFIX_INDUSTRY,
                CliSyntax.PREFIX_LOCATION,
                CliSyntax.PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
        }

        /*
        Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get());
        Website website = ParserUtil.parseWebsite(argMultimap.getValue(CliSyntax.PREFIX_WEBSITE).get());
        Industry industry = ParserUtil.parseIndustry(argMultimap.getValue(CliSyntax.PREFIX_INDUSTRY).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(CliSyntax.PREFIX_LOCATION).get());
        Description description = ParserUtil.parseDescription(
        argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
        *
         */

        Company company = new Company("Google", "tech",
                "SG", "big tech", "cool.com", "hello@gmail.com",
                "123323232", "SG");

        return new AddCompanyCommand(company);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
