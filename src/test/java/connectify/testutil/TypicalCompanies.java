package connectify.testutil;

import connectify.model.company.Company;

public class TypicalCompanies {
    public static final Company COMPANY_1 = new CompanyBuilder().withName("Apple").withPhone("12345678")
            .withEmail("company1@gmail.com").withAddress("Blk 456, Ang Mo Kio Ave 10, #-09-123").build();
    public static final Company COMPANY_2 = new CompanyBuilder().withName("Banana").withPhone("87654321")
            .withEmail("company2@gmail.com").withAddress("Blk 456, Ang Mo Kio Ave 10, #-09-123").build();

    private TypicalCompanies() {} // prevents instantiation
}
