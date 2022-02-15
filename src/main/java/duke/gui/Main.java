package duke.gui;

import duke.Duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/red_default.png"));

    @Override
    public void start(Stage stage) {
        MainWindow mw = new MainWindow();
        Scene scene = new Scene(mw);
        scene.getStylesheets().add(getClass().getResource("/view/DukeWindow.css").toExternalForm());
        stage.setScene(scene);
        mw.setDuke(duke);
        stage.show();

        stage.setTitle("Duke");
        stage.getIcons().add(dukeImage);
    }
}
