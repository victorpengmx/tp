package connectify.ui;

import connectify.model.Entity;
import connectify.model.company.Company;
import connectify.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Panel containing the list of entities. Supports tabbing between different entity types on the UI
 */
public class EntityListPanel extends UiPart<Region> {
    public static final String FXML = "EntityListPanel.fxml";
    /* The TabPane that contains the different entity types */
    @FXML
    private TabPane entityTabPane;

    /**
     * Creates a {@code EntityListPanel} with the given {@code ObservableList}.
     * @param entityList The list of entities to display
     * @param personList The list of people to display
     * @param companyList The list of companies to display
     * @param mode The mode to display the list in
     */
    public EntityListPanel(ObservableList<? extends Entity> entityList, ObservableList<Person> personList,
                           ObservableList<Company> companyList, String mode) {
        super(FXML);
        Tab tab1 = createEntityTab("All", entityList);
        Tab tab2 = createEntityTab("People", personList);
        Tab tab3 = createEntityTab("Company", companyList);

        TabPane entityTabPane = new TabPane(tab1, tab2, tab3);
        entityTabPane.getSelectionModel().select(getMode(mode));

        entityTabPane.getTabs().addAll(tab1, tab2, tab3);
        entityTabPane.getSelectionModel().select(getMode(mode));
        entityTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    class EntityListViewCell extends ListCell<Entity> {
        @Override
        protected void updateItem(Entity entity, boolean empty) {
            super.updateItem(entity, empty);
            if (empty || entity == null) {
                setGraphic(null);
                setText(null);
            } else if (entity instanceof Company) {
                setGraphic(new CompanyCard((Company) entity, getIndex() + 1).getRoot());
            } else if (entity instanceof Person) {
                setGraphic(new PersonCard((Person) entity, getIndex() + 1).getRoot());
            }
        }
    }

    private Accordion createCompanyAccordion(ObservableList<Company> companyList) {
        Accordion companyAccordion = new Accordion();
        companyList.forEach(company -> {
            TitledPane companyPane = createCompanyPane(company);
            companyAccordion.getPanes().add(companyPane);
        });
        return companyAccordion;
    }
    private TitledPane createCompanyPane(Company company) {
        TitledPane companyPane = new TitledPane();
        companyPane.setText(company.getName().toString());

        // Create a ListView for people within the company
        ListView<Person> companyPeopleListView = new ListView<>();
        companyPeopleListView.setItems(FXCollections.observableArrayList(company.getPersonList().asList()));
        companyPeopleListView.setCellFactory(listView -> new PersonListViewCell());

        // Add the ListView to a VBox to ensure proper layout
        VBox companyPaneContent = new VBox(companyPeopleListView);

        companyPane.setContent(companyPaneContent);
        return companyPane;
    }

    private Tab createEntityTab(String tabName, ObservableList<? extends Entity> entityList) {
        Tab tab = new Tab(tabName);

        ListView<Entity> entityListView = new ListView<>();
        entityListView.setItems(FXCollections.observableArrayList(entityList));
        entityListView.setCellFactory(listView -> new EntityListViewCell());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(entityListView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        tab.setContent(scrollPane);
        return tab;
    }

    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    public static int getMode(String modeName) {
        switch (modeName) {
        case "people":
            return 1;
        case "companies":
            return 2;
        default:
            return 0;
        }
    }
}
