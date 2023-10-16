package connectify.ui;

import connectify.model.company.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Company}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyListCard.fxml";

    public final Company company;

    @FXML
    private HBox cardPane;

    @FXML
    private Label name;
    @FXML
    private Label industry;
    @FXML
    private Label area; // "location" is a reserved keyword
    @FXML
    private Label description;
    @FXML
    private Label website;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label address;

    /**
     * Creates a {@code CompanyCard} with the given {@code Company} and index to display.
     */

    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        name.setText(company.getName());
        industry.setText(company.getIndustry());
        area.setText(company.getLocation());
        description.setText(company.getDescription());
        website.setText(company.getWebsite());
        email.setText(company.getEmail());
        phone.setText(company.getPhone());
        address.setText(company.getAddress());
    }
}
