package connectify.testutil;

import connectify.logic.commands.EditCompanyCommand.EditCompanyDescriptor;
import connectify.model.company.*;
import connectify.model.person.PersonList;

/**
 * A utility class to help with building EditCompanyDescriptor objects.
 */
public class EditCompanyDescriptorBuilder {

    private final EditCompanyDescriptor descriptor;

    public EditCompanyDescriptorBuilder() {
        descriptor = new EditCompanyDescriptor();
    }

    public EditCompanyDescriptorBuilder(EditCompanyDescriptor descriptor) {
        this.descriptor = new EditCompanyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCompanyDescriptor} with fields containing {@code company}'s details.
     */
    public EditCompanyDescriptorBuilder(Company company) {
        descriptor = new EditCompanyDescriptor();
        descriptor.setName(company.getName());
        descriptor.setIndustry(company.getIndustry());
        descriptor.setLocation(company.getLocation());
        descriptor.setDescription(company.getDescription());
        descriptor.setWebsite(company.getWebsite());
        descriptor.setPhone(company.getPhone());
        descriptor.setEmail(company.getEmail());
        descriptor.setAddress(company.getAddress());
        descriptor.setCompanyNote(company.getNote());
        descriptor.setPersonList(company.getPersonList());
    }

    /**
     * Sets the {@code Name} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withName(String name) {
        descriptor.setName(new CompanyName(name));
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withIndustry(String industry) {
        descriptor.setIndustry(new CompanyIndustry(industry));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new CompanyLocation(location));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(description);
        return this;
    }

    /**
     * Sets the {@code Website} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withWebsite(String website) {
        descriptor.setWebsite(new CompanyWebsite(website));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new CompanyPhone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new CompanyEmail(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new CompanyAddress(address));
        return this;
    }

    /**
     * Sets the {@code CompanyNote} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withCompanyNote(CompanyNote companyNote) {
        descriptor.setCompanyNote(companyNote);
        return this;
    }

    /**
     * Sets the {@code PersonList} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withPersonList(PersonList personList) {
        descriptor.setPersonList(personList);
        return this;
    }

    public EditCompanyDescriptor build() {
        return descriptor;
    }
}