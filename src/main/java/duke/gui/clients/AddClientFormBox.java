package duke.gui.clients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import duke.client.Gender;
import duke.gui.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Controller for the AddClientFormBox fxml component. It handles all the fields, buttons and labels associated with
 * the form box. The form box is not meant to be used as a stand-alone page, but as a component that can be reused
 * wherever necessary.
 */
public class AddClientFormBox extends HBox {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private ComboBox<Gender> genderSelectField;
    @FXML
    private Button submitButton;
    @FXML
    private Label additionalInformation;

    private Function<Map<String, String>, Void> addClientFunction;

    /**
     * Creates a new instance of the AddClientFormBox controller.
     *
     * @param func A function that accepts a Mapping between the data field names and the value of the associated data,
     *             then creates a client using the given mapping.
     */
    public AddClientFormBox(Function<Map<String, String>, Void> func) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/AddClientFormBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        genderSelectField.getItems().addAll(Gender.UNKNOWN, Gender.MALE, Gender.FEMALE);
        this.addClientFunction = func;
    }

    @FXML
    private void initialize() {
        additionalInformation.managedProperty().bind(additionalInformation.visibleProperty());
    }

    @FXML
    private void handleUserInput() {
        boolean inputIsValid = validateInput();
        if (!inputIsValid) {
            return;
        }

        Map<String, String> clientData = new HashMap<>();

        clientData.put("firstName", firstNameField.getText());
        clientData.put("lastName", lastNameField.getText());
        clientData.put("phoneNumber", phoneNumberField.getText());
        clientData.put(
                "gender",
                genderSelectField.getValue() == null ? Gender.UNKNOWN.name() : genderSelectField.getValue().name()
        );

        addClientFunction.apply(clientData);

        firstNameField.clear();
        lastNameField.clear();
        phoneNumberField.clear();
        genderSelectField.setValue(Gender.UNKNOWN);
    }

    private boolean validateInput() {
        if (firstNameField.getText().isBlank()) {
            showAdditionalInformation("First name is required.");
            return false;
        }
        return true;
    }

    private void showAdditionalInformation(String text) {
        additionalInformation.setText(text);
        additionalInformation.visibleProperty().set(true);
    }

    @FXML
    private void hideAdditionalInformation() {
        additionalInformation.setText("");
        additionalInformation.visibleProperty().set(false);
    }
}
