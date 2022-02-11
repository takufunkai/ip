package duke.gui;

import java.io.IOException;
import java.util.function.Consumer;

import duke.client.Gender;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

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

    private Consumer<String> addClient;

    private AddClientFormBox(Consumer<String> addClient) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/AddClientFormBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addClient = addClient;

        genderSelectField.getItems().addAll(Gender.MALE, Gender.FEMALE);
    }

    @FXML
    public void initialize() {
        additionalInformation.managedProperty().bind(additionalInformation.visibleProperty());
    }


    public static AddClientFormBox getAddClientForm(Consumer<String> addClient) {
        return new AddClientFormBox(addClient);
    }

    @FXML
    private void handleUserInput() {
        boolean inputIsValid = validateInput();
        if (!inputIsValid) {
            return;
        }

        String response = String.format("firstName:%s", firstNameField.getText());

        if (!lastNameField.getText().isBlank()) {
            response += String.format(",lastName:%s", lastNameField.getText());
        }
        if (!phoneNumberField.getText().isBlank()) {
            response += String.format(",phoneNumber:%s", phoneNumberField.getText());
        }
        if (genderSelectField.getValue() != null) {
            response += String.format(",gender:%s", genderSelectField.getValue().toString());
        }
        addClient.accept("Success!" + response);
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

    private void hideAdditionalInformation() {
        additionalInformation.setText("");
        additionalInformation.visibleProperty().set(false);
    }
}
