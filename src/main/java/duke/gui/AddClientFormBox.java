package duke.gui;

import java.io.IOException;

import duke.client.Gender;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    private AddClientFormBox() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/AddClientFormBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        genderSelectField.getItems().addAll(Gender.MALE, Gender.FEMALE);
    }

    public static AddClientFormBox getAddClientForm() {
        return new AddClientFormBox();
    }

    @FXML
    private void handleUserInput() {
        System.out.println("Hello there");
    }
}
