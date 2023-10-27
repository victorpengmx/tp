package connectify.testutil;

import connectify.logic.commands.AddCompanyCommand;
import connectify.model.company.Company;

import static connectify.logic.parser.CliSyntax.*;

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
        sb.append(PREFIX_NAME + company.getName().fullName + " ");
        sb.append(PREFIX_INDUSTRY + company.getIndustry().value + " ");
        sb.append(PREFIX_LOCATION + company.getLocation().value + " ");
        sb.append(PREFIX_DESCRIPTION + company.getDescription() + " ");
        sb.append(PREFIX_WEBSITE + company.getWebsite().value + " ");
        sb.append(PREFIX_EMAIL + company.getEmail().value + " ");
        sb.append(PREFIX_PHONE + company.getPhone().value + " ");
        sb.append(PREFIX_ADDRESS + company.getAddress().value + " ");
        return sb.toString();
    }

}
