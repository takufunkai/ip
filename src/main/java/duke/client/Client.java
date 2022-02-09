package duke.client;

import java.util.UUID;

public class Client {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final String phoneNumber; // TODO: to new class

    public Client(ClientBuilder builder) {
        this.id = UUID.randomUUID().toString();
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.gender = builder.gender;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s %s, Gender: %s, Phone Number: %s",
                this.id, this.lastName, this.firstName, this.gender, this.phoneNumber);
    }

    public static class ClientBuilder {
        private final String firstName;
        private final String lastName;
        private Gender gender;
        private String phoneNumber;

        public ClientBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public ClientBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public ClientBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
