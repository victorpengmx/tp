package connectify.model.company;

import static connectify.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static connectify.testutil.Assert.assertThrows;
import static connectify.testutil.TypicalCompanies.COMPANY_1;
import static connectify.testutil.TypicalCompanies.COMPANY_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import connectify.model.company.exceptions.CompanyNotFoundException;
import connectify.model.company.exceptions.DuplicateCompanyException;
import connectify.testutil.CompanyBuilder;

public class UniqueCompanyListTest {

    private final UniqueCompanyList uniqueCompanyList = new UniqueCompanyList();

    @Test
    public void contains_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.contains(null));
    }

    @Test
    public void contains_companyNotInList_returnsFalse() {
        assertFalse(uniqueCompanyList.contains(COMPANY_1));
    }

    @Test
    public void contains_companyInList_returnsTrue() {
        uniqueCompanyList.add(COMPANY_1);
        assertTrue(uniqueCompanyList.contains(COMPANY_1));
    }

    @Test
    public void contains_companyWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCompanyList.add(COMPANY_1);
        Company editedCompany = new CompanyBuilder(COMPANY_1).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(uniqueCompanyList.contains(editedCompany));
    }

    @Test
    public void add_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.add(null));
    }

    @Test
    public void add_duplicateCompany_throwsDuplicateCompanyException() {
        uniqueCompanyList.add(COMPANY_1);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.add(COMPANY_1));
    }

    @Test
    public void setCompany_nullTargetCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompany(null, COMPANY_1));
    }

    @Test
    public void setCompany_nullEditedCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompany(COMPANY_1, null));
    }

    @Test
    public void setCompany_targetCompanyNotInList_throwsCompanyNotFoundException() {
        assertThrows(CompanyNotFoundException.class, () -> uniqueCompanyList.setCompany(COMPANY_1, COMPANY_1));
    }

    @Test
    public void setCompany_editedCompanyIsSameCompany_success() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.setCompany(COMPANY_1, COMPANY_1);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(COMPANY_1);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompany_editedCompanyHasSameIdentity_success() {
        uniqueCompanyList.add(COMPANY_1);
        Company editedCompany1 = new CompanyBuilder(COMPANY_1).withAddress(VALID_ADDRESS_BOB)
                .build();
        uniqueCompanyList.setCompany(COMPANY_1, editedCompany1);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(editedCompany1);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompany_editedCompanyHasDifferentIdentity_success() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.setCompany(COMPANY_1, COMPANY_2);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(COMPANY_2);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompany_editedCompanyHasNonUniqueIdentity_throwsDuplicateCompanyException() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.add(COMPANY_2);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.setCompany(COMPANY_1, COMPANY_2));
    }

    @Test
    public void remove_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.remove(null));
    }

    @Test
    public void remove_companyDoesNotExist_throwsCompanyNotFoundException() {
        assertThrows(CompanyNotFoundException.class, () -> uniqueCompanyList.remove(COMPANY_1));
    }

    @Test
    public void remove_existingCompany_removesCompany() {
        uniqueCompanyList.add(COMPANY_1);
        uniqueCompanyList.remove(COMPANY_1);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompanies_nullUniqueCompanyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompanies((UniqueCompanyList) null));
    }

    @Test
    public void setCompanies_uniqueCompanyList_replacesOwnListWithProvidedUniqueCompanyList() {
        uniqueCompanyList.add(COMPANY_1);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(COMPANY_2);
        uniqueCompanyList.setCompanies(expectedUniqueCompanyList);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompanies_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCompanyList.setCompanies((List<Company>) null));
    }

    @Test
    public void setCompanies_list_replacesOwnListWithProvidedList() {
        uniqueCompanyList.add(COMPANY_1);
        List<Company> companyList = Collections.singletonList(COMPANY_2);
        uniqueCompanyList.setCompanies(companyList);
        UniqueCompanyList expectedUniqueCompanyList = new UniqueCompanyList();
        expectedUniqueCompanyList.add(COMPANY_2);
        assertEquals(expectedUniqueCompanyList, uniqueCompanyList);
    }

    @Test
    public void setCompanies_listWithDuplicateCompanies_throwsDuplicateCompanyException() {
        List<Company> listWithDuplicateCompanies = Arrays.asList(COMPANY_1, COMPANY_1);
        assertThrows(DuplicateCompanyException.class, () -> uniqueCompanyList.setCompanies(listWithDuplicateCompanies));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueCompanyList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueCompanyList.asUnmodifiableObservableList().toString(), uniqueCompanyList.toString());
    }
}
