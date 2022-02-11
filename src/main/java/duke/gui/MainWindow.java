package duke.gui;

import java.io.IOException;
import java.util.Objects;

import duke.Duke;
import duke.DukeException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Stage stage;
    private Duke duke;

    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/red_default.png"))
    );
    private final Image dukeImageError = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/red_shocked.png"))
    );
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png"))
    );
    private final Image exitImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/exit_image.jpg"))
    );

    /**
     * MainWindow is the window in which all Duke related GUI will exist and operate.
     *
     * @param stage The stage in which all the Duke related GUI will be set from.
     */
    public MainWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.stage = stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        sendOutput(d.getGreeting());
    }

    private void sendOutput(String out) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(out, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        try {
            String response = duke.getResponse(input);
            if (response.equals("EXIT")) {
                handleExit();
                userInput.clear();
                return;
            }

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage),
                    AddClientFormBox.getAddClientForm(this::handleAddClient)
            );
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog("Oops: " + e.getMessage(), dukeImageError)
            );
        }
        userInput.clear();
    }

    private void handleExit() {
        ImageView iv = new ImageView(exitImage);
        iv.setFitHeight(200);
        iv.setFitWidth(200);
        dialogContainer.getChildren().add(iv);

        userInput.setDisable(true);
        sendButton.setText("Exit");
        sendButton.setOnAction(event -> stage.close());
    }

    private void handleAddClient(String clientData) {
        System.out.println(clientData);
    }
}
