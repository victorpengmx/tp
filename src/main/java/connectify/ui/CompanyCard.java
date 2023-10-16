package connectify.ui;

import connectify.model.company.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
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
    private Label id;
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

    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code CompanyCard} with the given {@code Company} and index to display.
     */

    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        name.setText(company.getName());
        area.setText(company.getLocation());
        description.setText(company.getDescription());
        website.setText(company.getWebsite());
        email.setText(company.getEmail());
        phone.setText(company.getPhone());
        address.setText(company.getAddress());
        Label label = new Label("Company");
        label.setStyle("-fx-background-color: #FF4F79");
        tags.getChildren().add(label);
        tags.getChildren().add(new Label(company.getIndustry()));
    }
}
