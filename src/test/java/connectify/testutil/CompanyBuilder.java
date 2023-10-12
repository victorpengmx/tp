package connectify.testutil;

import connectify.model.company.Company;

/**
 * A utility class to help with building Company objects.
 */
public class CompanyBuilder {
    public static final String DEFAULT_NAME = "Google";
    public static final String DEFAULT_INDUSTRY = "Technology";
    public static final String DEFAULT_LOCATION = "Singapore";
    public static final String DEFAULT_DESCRIPTION = "Google is an American multinational technology company "
            + "specializing in Internet-related services and products.";
    public static final String DEFAULT_WEBSITE = "https://www.google.com/";
    public static final String DEFAULT_EMAIL = "";

    public static final String DEFAULT_PHONE = "12345678";
    public static final String DEFAULT_ADDRESS = "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA";
    private String name;
    private String industry;
    private String location;
    private String description;
    private String website;
    private String email;
    private String phone;
    private String address;


    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        this.name = DEFAULT_NAME;
        this.industry = DEFAULT_INDUSTRY;
        this.location = DEFAULT_LOCATION;
        this.description = DEFAULT_DESCRIPTION;
        this.website = DEFAULT_WEBSITE;
        this.email = DEFAULT_EMAIL;
        this.phone = DEFAULT_PHONE;
        this.address = DEFAULT_ADDRESS;
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code companyToCopy}.
     * @param companyToCopy
     */
    public CompanyBuilder(Company companyToCopy) {
        this.name = companyToCopy.getName();
        this.industry = companyToCopy.getIndustry();
        this.location = companyToCopy.getLocation();
        this.description = companyToCopy.getDescription();
        this.website = companyToCopy.getWebsite();
        this.email = companyToCopy.getEmail();
        this.phone = companyToCopy.getPhone();
        this.address = companyToCopy.getAddress();
    }

    /**
     * Sets the {@code Name} of the {@code Company} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code Company} that we are building.
     */
    public CompanyBuilder withIndustry(String industry) {
        this.industry = industry;
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Company} that we are building.
     */
    public CompanyBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Company} that we are building.
     */
    public CompanyBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code Website} of the {@code Company} that we are building.
     */
    public CompanyBuilder withWebsite(String website) {
        this.website = website;
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Company} that we are building.
     */
    public CompanyBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Company} that we are building.
     */
    public CompanyBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Company} that we are building.
     */
    public CompanyBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Builds a company.
     * @return Company
     */
    public Company build() {
        return new Company(name, industry, location, description, website, email, phone, address);
    }
}
