package duke.command.clientcommand;

import java.util.HashMap;

import duke.DukeException;
import duke.client.ClientList;
import duke.command.Command;


public abstract class ClientCommand extends Command {
    protected ClientList clients;

    protected ClientCommand() {
    }

    public static ClientCommand parse(CommandNames command, String arguments) throws DukeException {
        boolean noArgumentsSupplied = arguments == null || arguments.isBlank();

        if (command == CommandNames.NEWCLIENT) {
            if (noArgumentsSupplied) {
                throw new DukeException("No arguments supplied");
            }

            HashMap<String, String> data = new HashMap<>();
            String[] argumentPair = arguments.split(",");
            for (String ap : argumentPair) {
                String[] keyValuePair = ap.split(":");
                data.put(keyValuePair[0], keyValuePair[1]);
            }
            return new AddCommand(data);
        } else {
            throw new DukeException("Invalid command"); // TODO: add custom command exceptions.
        }
    }

    public ClientCommand supply(ClientList clients) {
        this.clients = clients;
        return this;
    }
}
