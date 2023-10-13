package connectify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import connectify.model.company.Company;
import connectify.testutil.CompanyBuilder;

public class JsonAdaptedCompanyTest {

    private static final Company COMPANY_1 = new CompanyBuilder().withName("Company1").build();

    @Test
    public void toModelType_validCompanyDetails_returnsCompany() throws Exception {
        JsonAdaptedCompany company = new JsonAdaptedCompany(COMPANY_1);
        assertEquals(COMPANY_1, company.toModelType());
    }

}
