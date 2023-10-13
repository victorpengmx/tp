package connectify.testutil;

import static connectify.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static connectify.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static connectify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connectify.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static connectify.logic.parser.CliSyntax.PREFIX_LOCATION;
import static connectify.logic.parser.CliSyntax.PREFIX_NAME;
import static connectify.logic.parser.CliSyntax.PREFIX_PHONE;
import static connectify.logic.parser.CliSyntax.PREFIX_WEBSITE;

import connectify.logic.commands.AddCompanyCommand;
import connectify.model.company.Company;

/**
 * A utility class for Company.
 */
public class CompanyUtil {

    /**
     * Returns an add command string for adding the {@code company}.
     */
    public static String getAddCommand(Company company) {
        return AddCompanyCommand.COMMAND_WORD + " " + getCompanyDetails(company);
    }

    /**
     * Returns the part of command string for the given {@code company}'s details.
     */
    public static String getCompanyDetails(Company company) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + company.getName() + " ");
        sb.append(PREFIX_INDUSTRY + company.getIndustry() + " ");
        sb.append(PREFIX_LOCATION + company.getLocation() + " ");
        sb.append(PREFIX_DESCRIPTION + company.getDescription() + " ");
        sb.append(PREFIX_WEBSITE + company.getWebsite() + " ");
        sb.append(PREFIX_EMAIL + company.getEmail() + " ");
        sb.append(PREFIX_PHONE + company.getPhone() + " ");
        sb.append(PREFIX_ADDRESS + company.getAddress() + " ");
        return sb.toString();
    }

}
