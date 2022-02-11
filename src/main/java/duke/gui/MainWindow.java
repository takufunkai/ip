package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

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

    private DukeWindow dukeWindow;
    private ClientsWindow clientsWindow;
    private Node currentChildWindow;

    /**
     * MainWindow is the window in which all Duke related GUI will exist and operate.
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
        this.duke = new Duke();
        dukeMenuItem.setOnAction(e -> openDukeWindow());
        clientsMenuItem.setOnAction(e -> openClientsWindow());

        this.dukeWindow = new DukeWindow();
        this.dukeWindow.setDuke(this.duke);

        this.clientsWindow = new ClientsWindow();
        this.clientsWindow.setVisible(false);

        this.getChildren().addAll(this.clientsWindow, this.dukeWindow);
        setBottomAnchor(this.dukeWindow, 1.0);
        setBottomAnchor(this.clientsWindow, 1.0);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void openClientsWindow() {
        this.clientsWindow.setVisible(true);
        this.dukeWindow.setVisible(false);
    }

    public void openDukeWindow() {
        this.dukeWindow.setVisible(true);
        this.clientsWindow.setVisible(false);
    }
}
