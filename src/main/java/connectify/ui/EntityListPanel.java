package connectify.ui;

import java.util.logging.Logger;

import connectify.commons.core.LogsCenter;
import connectify.model.Entity;
import connectify.model.company.Company;
import connectify.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of companies and persons.
 */
public class EntityListPanel extends UiPart<Region> {
    public static final String FXML = "EntityListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EntityListPanel.class);

    @FXML
    private ListView<Entity> entityListView;

    /**
     * Creates a {@code EntityListPanel} with the given {@code ObservableList}.
     */

    public <T extends Entity> EntityListPanel(ObservableList<T> entityList) {
        super(FXML);

        // Create a new ObservableList<Entity> and add elements from entityList
        ObservableList<Entity> castedEntityList = FXCollections.observableArrayList(entityList);

        entityListView.setItems(castedEntityList);
        entityListView.setCellFactory(listView -> new EntityListViewCell());
    }


    /**
     * Custom {@code ListCell} that displays the graphics of either a {@code Company} or {@code Person}
     * using a {@code CompanyCard} or {@code PersonCard} respectively.
     */

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
}
