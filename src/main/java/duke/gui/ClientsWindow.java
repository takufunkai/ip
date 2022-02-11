package duke.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ClientsWindow extends AnchorPane {
    @FXML
    private ListView<String> myListView;

    private AddClientFormBox clientForm;
    private ObservableList<String> data;

    public ClientsWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ClientsWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clientForm = new AddClientFormBox(this::addClient);
        this.getChildren().add(this.clientForm);
        this.clientForm.setLayoutY(50);
        this.clientForm.setVisible(false);
        data = FXCollections.observableArrayList("chocolate", "blue");
        myListView.setItems(data);

        myListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            System.out.println("New client: " + newValue);
        });

    }

    @FXML
    private void initialize() {
        this.clientForm.managedProperty().bind(this.clientForm.visibleProperty());
    }

    @FXML
    public void toggleShowClientForm() {
        this.clientForm.setVisible(!this.clientForm.visibleProperty().getValue());
    }

    private Void addClient(String message) {
        data.add(message);
        return null;
    }
}
