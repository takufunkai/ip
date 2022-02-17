package duke;

import java.io.IOException;
import java.util.Map;

import duke.client.Client;
import duke.client.ClientList;
import duke.client.Gender;
import duke.command.Command;
import duke.storage.TasksStorage;
import duke.usertask.TaskList;
import duke.utils.DukeResponse;

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
    private TasksStorage tasksStorage = null;

    /**
     * Creates a new Duke chat-bot instance.
     */
    public Duke() {
        try {
            this.tasksStorage = new TasksStorage();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.tasks = new TaskList(100);
        this.clients = new ClientList();
        try {
            this.tasksStorage.restore(tasks);
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
     * Returns a response based on the given user input.
     *
     * @param input The input supplied by the user.
     * @return An appropriate DukeResponse for the given input.
     * @throws DukeException If the given input is invalid.
     */
    public DukeResponse getResponse(String input) throws DukeException {
        Command cmd = Command.parse(input, tasks, clients, tasksStorage);
        return cmd.execute();
    }

    /**
     * Adds a client to its list of clients.
     *
     * @param clientData A map of the client's key-value data pairs.
     * @return The created Client object.
     */
    public Client addClient(Map<String, String> clientData) {
        Client client = new Client(clientData.get("firstName"));

        client.setLastName(clientData.get("lastName"));
        client.setPhoneNumber(clientData.get("phoneNumber"));
        client.setGender(Gender.valueOf(clientData.get("gender")));
        this.clients.add(client);
        return client;
    }
}
