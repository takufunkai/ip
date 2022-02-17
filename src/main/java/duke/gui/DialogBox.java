package duke.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        double imgCenter = displayPicture.getFitHeight() / 2;
        displayPicture.setClip(new Circle(imgCenter, imgCenter, imgCenter));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        dialog.setAlignment(Pos.BASELINE_LEFT);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        String styles = db.dialog.getStyle();
        String stylesWithRedBackgroundColor = DialogBox.modifyStyleValue(
                styles, "-fx-background-color", "#fecccc"
        );
        db.dialog.setStyle(stylesWithRedBackgroundColor);
        return db;
    }

    private static String modifyStyleValue(String styles, String key, String value) {
        Map<String, String> stylesMap = Arrays.stream(styles.split(";"))
                .map(string -> string.trim().split(": "))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        stylesMap.put(key, value);
        return stylesMap.keySet().stream()
                .map(k -> String.format("%s: %s;", k, stylesMap.get(k)))
                .collect(Collectors.joining(" "));
    }
}
