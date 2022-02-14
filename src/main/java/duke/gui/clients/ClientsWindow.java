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

/**
 * The ClientsWindow class is the Controller for the ClientsWindow in the Duke app. It handles the button to open the
 * add client form, as well as the form itself. It also handles the list of clients that the Duke bot has and displays
 * it.
 */
public class ClientsWindow extends AnchorPane {
    @FXML
    private ListView<Client> clientListView;

    private AddClientFormBox clientForm;
    private ObservableList<Client> clients;
    private Duke duke;

    /**
     * Creates an instance of the controller. Apart from setting up the stylistic logic of the window, this
     * constructor also sets up the data structure containing the list of clients and attaches a listener between it
     * and the ListView object.
     */
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
