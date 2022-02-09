package duke.command.clientcommand;

import duke.DukeException;
import duke.client.Client;
import duke.client.Gender;
import java.util.HashMap;

public class AddCommand extends ClientCommand {
    private HashMap<String, String> data;

    public AddCommand(HashMap<String, String> data) {
        this.data = data;
    }

    /**
     * Executes the expected behaviour of the class.
     *
     * @return The response string of the execution.
     * @throws DukeException If some argument supplied to the command is invalid.
     */
    @Override
    public String execute() throws DukeException {
        Client.ClientBuilder builder = new Client.ClientBuilder(data.get("firstName"), data.get("lastName"));
        if (data.get("phoneNumber") != null) {
            builder.phoneNumber(data.get("phoneNumber"));
        }
        if (data.get("gender") != null) {
            builder.gender(Gender.valueOf(data.get("gender")));
        }
        Client client = builder.build();
        super.clients.add(client);
        return "Successfully created client: " + client;
    }
}
