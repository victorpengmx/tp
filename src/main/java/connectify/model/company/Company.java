package connectify.model.company;

import static connectify.commons.util.CollectionUtil.requireAllNonNull;

public class Company {
    private String name;
    private String industry;
    private String location;
    private String description;
    private String website;
    private String email;
    private String phone;
    private String fax;
    private String address;

    public Company(String name, String industry, String location, String description, String website, String email,
                   String phone, String fax, String address) {
        requireAllNonNull(name, industry, location, description, website, email, phone, fax, address);
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.fax = fax;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        return otherCompany.getName().equals(getName())
                && otherCompany.getIndustry().equals(getIndustry())
                && otherCompany.getLocation().equals(getLocation())
                && otherCompany.getDescription().equals(getDescription())
                && otherCompany.getWebsite().equals(getWebsite())
                && otherCompany.getEmail().equals(getEmail())
                && otherCompany.getPhone().equals(getPhone())
                && otherCompany.getFax().equals(getFax())
                && otherCompany.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Industry: ")
                .append(getIndustry())
                .append(" Location: ")
                .append(getLocation())
                .append(" Description: ")
                .append(getDescription())
                .append(" Website: ")
                .append(getWebsite())
                .append(" Email: ")
                .append(getEmail())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Fax: ")
                .append(getFax())
                .append(" Address: ")
                .append(getAddress());
        return builder.toString();
    }
}