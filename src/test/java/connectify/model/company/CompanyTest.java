package connectify.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static connectify.testutil.TypicalCompanies.COMPANY_1;
import static connectify.testutil.TypicalCompanies.COMPANY_2;

import org.junit.jupiter.api.Test;

import connectify.testutil.CompanyBuilder;

public class CompanyTest {
    @Test
    public void equals() {
        // same values -> returns true
        Company companyCopy = new CompanyBuilder(COMPANY_1).build();
        assertTrue(COMPANY_1.equals(companyCopy));

        // same object -> returns true
        assertTrue(COMPANY_1.equals(COMPANY_1));

        // null -> returns false
        assertFalse(COMPANY_1.equals(null));

        // different type -> returns false
        assertFalse(COMPANY_1.equals(5));

        // different company -> returns false
        assertFalse(COMPANY_1.equals(COMPANY_2));

        // different name -> returns false
        Company editedCompany1 = new CompanyBuilder(COMPANY_1).withName("Apple Inc").build();
        assertFalse(COMPANY_1.equals(editedCompany1));

        // different phone -> returns false
        editedCompany1 = new CompanyBuilder(COMPANY_1).withPhone("87654321").build();
        assertFalse(COMPANY_1.equals(editedCompany1));

        // different email -> returns false
        editedCompany1 = new CompanyBuilder(COMPANY_1).withEmail("fakeCompany1@gmail.com").build();
        assertFalse(COMPANY_1.equals(editedCompany1));

        // different address -> returns false
        editedCompany1 = new CompanyBuilder(COMPANY_1).withAddress("Blk 456, Ang Mo Kio Ave 10, #-09-123").build();
        assertFalse(COMPANY_1.equals(editedCompany1));
        assertFalse(COMPANY_1.equals(editedCompany1));

        // different industry -> returns false
        editedCompany1 = new CompanyBuilder(COMPANY_1).withIndustry("Technology").build();
        assertFalse(COMPANY_1.equals(editedCompany1));

        // different location -> returns false
        editedCompany1 = new CompanyBuilder(COMPANY_1).withLocation("Singapore").build();
        assertFalse(COMPANY_1.equals(editedCompany1));

        // different description -> returns false
        editedCompany1 = new CompanyBuilder(COMPANY_1).withDescription("A company that sells phones").build();
        assertFalse(COMPANY_1.equals(editedCompany1));

        // different website -> returns false
        editedCompany1 = new CompanyBuilder(COMPANY_1).withWebsite("www.apple.com").build();
        assertFalse(COMPANY_1.equals(editedCompany1));
    }
}
