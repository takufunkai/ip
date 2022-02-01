package duke;

import java.io.IOException;

import duke.command.Command;
import duke.storage.SaveHandler;
import duke.textui.TextUi;
import duke.usertask.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Duke is a chat-bot program that is capable of logging tasks which are (optionally)
 * tagged with a deadline/occurrence date.
 * <p>
 * Duke automatically saves the tasks of each session upon successful termination
 * of the session, and restores these tasks on the next launch.
 */
public class Duke extends Application {
    private final TaskList tasks;
    private final TextUi ui;
    private SaveHandler sv = null;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/travis_scott.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/travis_scott_fish.jpg"));

    /**
     * Creates a new Duke chat-bot instance.
     */
    public Duke() {
        this.ui = new TextUi();
        try {
            this.sv = new SaveHandler();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.tasks = new TaskList(100);
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Begins execution of the Duke chat-bot.
     */
    public void run() {
        ui.printGreeting();
        if (sv != null) {
            try {
                sv.restore(tasks);
            } catch (DukeException e) {
                ui.printFromRed("Oops, something went wrong: " + "\n");
                ui.printFromRed("** " + e.getMessage() + "\n");
            }
        }
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.awaitInputFromUser();
                Command cmd = Command.parse(userInput);
                cmd.execute(ui, tasks);
                ui.printWithBuffer("\n");
                isRunning = !cmd.isExit();
            } catch (DukeException e) {
                ui.printFromRed("Oops, something went wrong: " + "\n");
                ui.printFromRed("** " + e.getMessage() + "\n");
            }
        }
        ui.printExitMessage();
        if (sv != null) {
            sv.save(tasks);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
