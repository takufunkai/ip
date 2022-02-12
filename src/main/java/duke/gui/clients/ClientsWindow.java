package duke.gui.clients;

import java.io.IOException;
import java.util.Map;

import duke.Duke;
import duke.client.Client;
import duke.gui.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ClientsWindow extends AnchorPane {
    @FXML
    private ListView<Client> clientListView;

    private AddClientFormBox clientForm;
    private ObservableList<Client> clients;
    private Duke duke;

    public ClientsWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ClientsWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clientForm = new AddClientFormBox(this::addClientToList);
        this.getChildren().add(this.clientForm);
        this.clientForm.setLayoutY(50);
        this.clientForm.setVisible(false);

        clients = FXCollections.observableArrayList();

        clientListView.setItems(clients);

        clientListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            System.out.println("New client: " + newValue);
        });
    }

    @FXML
    private void toggleShowClientForm() {
        this.clientForm.setVisible(!this.clientForm.visibleProperty().getValue());
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    private Void addClientToList(Map<String, String> clientData) {
        Client client = this.duke.addClient(clientData);
        clients.add(client);
        return null;
    }
}
