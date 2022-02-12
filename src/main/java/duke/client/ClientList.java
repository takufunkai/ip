package duke.client;

import java.util.ArrayList;
import java.util.List;

public class ClientList {
    private final List<Client> clientList;

    public ClientList() {
        this.clientList = new ArrayList<>();
    }

    public void add(Client client) {
        this.clientList.add(client);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Client client : clientList) {
            sb.append(client).append("\n");
        }
        return sb.toString();
    }
}
