package duke.gui;

import java.io.IOException;

import duke.Duke;
import duke.gui.clients.ClientsWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * Class MainWindow is the controller for the MainWindow fxml component. It is responsible for handling the menu bar
 * and is the highest in the hierarchy of nodes.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menu;
    @FXML
    private MenuItem dukeMenuItem;
    @FXML
    private MenuItem clientsMenuItem;

    private Duke duke;

    private final DukeWindow dukeWindow;
    private final ClientsWindow clientsWindow;

    /**
     * Returns an instance of the MainWindow controller.
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dukeMenuItem.setOnAction(e -> openDukeWindow());
        clientsMenuItem.setOnAction(e -> openClientsWindow());

        this.dukeWindow = new DukeWindow();

        this.clientsWindow = new ClientsWindow();
        this.clientsWindow.setVisible(false);

        this.getChildren().addAll(this.clientsWindow, this.dukeWindow);
        setBottomAnchor(this.dukeWindow, 1.0);
        setBottomAnchor(this.clientsWindow, 1.0);
    }

    public void setDuke(Duke d) {
        this.duke = d;
        this.dukeWindow.setDuke(d);
        this.clientsWindow.setDuke(d);
    }

    /**
     * Changes the existing window to the clientsWindow view.
     */
    public void openClientsWindow() {
        this.clientsWindow.setVisible(true);
        this.dukeWindow.setVisible(false);
    }

    /**
     * Changes the existing window to the clientsWindow view.
     */
    public void openDukeWindow() {
        this.dukeWindow.setVisible(true);
        this.clientsWindow.setVisible(false);
    }
}
