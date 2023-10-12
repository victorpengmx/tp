package connectify.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import connectify.commons.exceptions.IllegalValueException;
import connectify.model.company.Company;


/**
 * Jackson-friendly version of {@link Company}.
 */
public class JsonAdaptedCompany {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Company's %s field is missing!";

    private final String name;
    private final String industry;
    private final String location;
    private final String description;
    private final String website;
    private final String email;
    private final String phone;
    private final String address;

    /**
     * Constructs a {@code JsonAdaptedCompany} with the given company details.
     */
    @JsonCreator
    public JsonAdaptedCompany(@JsonProperty("name") String name, @JsonProperty("industry") String industry,
                              @JsonProperty("location") String location, @JsonProperty("description") String description,
                              @JsonProperty("website") String website, @JsonProperty("email") String email,
                              @JsonProperty("phone") String phone, @JsonProperty("address") String address) {
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Converts a given {@code Company} into this class for Jackson use.
     */
    public JsonAdaptedCompany(Company source) {
        name = source.getName();
        industry = source.getIndustry();
        location = source.getLocation();
        description = source.getDescription();
        website = source.getWebsite();
        email = source.getEmail();
        phone = source.getPhone();
        address = source.getAddress();
    }

    /**
     * Converts this Jackson-friendly adapted company object into the model's {@code Company} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted company.
     */
    public Company toModelType() throws IllegalValueException {
        return new Company(name, industry, location, description, website, email, phone, address);
    }
}
