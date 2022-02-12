package duke;

import java.io.IOException;
import java.util.Map;

import duke.client.Client;
import duke.client.ClientList;
import duke.client.Gender;
import duke.command.Command;
import duke.storage.SaveHandler;
import duke.usertask.TaskList;

/**
 * Duke is a chat-bot program that is capable of logging tasks which are (optionally)
 * tagged with a deadline/occurrence date.
 * <p>
 * Duke automatically saves the tasks of each session upon successful termination
 * of the session, and restores these tasks on the next launch.
 */
public class Duke {
    private final TaskList tasks;
    private final ClientList clients;
    private SaveHandler saveHandler = null;

    /**
     * Creates a new Duke chat-bot instance.
     */
    public Duke() {
        try {
            this.saveHandler = new SaveHandler();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.tasks = new TaskList(100);
        this.clients = new ClientList();
        try {
            this.saveHandler.restore(tasks);
        } catch (DukeException e) {
            System.out.println("Failed to restore saved tasks: " + e.getMessage());
        }
    }

    public String getGreeting() {
        return "Hello, I am Red from Among Us.\n\nWe are currently facing a crisis onboard -- there seems to be an "
                + "imposter among us...\n\nMy job is to handle chat requests, so although I might get murdered any "
                + "moment now...\n\n... How can I help you?";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        Command cmd = Command.parse(input, tasks, clients, saveHandler);
        if (cmd.isExit()) {
            return "EXIT";
        }
        return cmd.execute();
    }

    public Client addClient(Map<String, String> clientData) {
        Client client = new Client(clientData.get("firstName"));

        client.setLastName(clientData.get("lastName"));
        client.setPhoneNumber(clientData.get("phoneNumber"));
        client.setGender(Gender.valueOf(clientData.get("gender")));
        this.clients.add(client);
        return client;
    }
}
