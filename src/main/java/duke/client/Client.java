package duke.client;

import java.util.UUID;

/**
 * The Client class encapsulates the information associated with a user's Client, such as their personal details and
 * their UUID.
 */
public class Client {
    private final String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber; // TODO: to new class

    /**
     * Creates a new Client object. Only a Client's firstName is required, so it is the only field required in the
     * constructor.
     *
     * @param firstName The first name of the client.
     */
    public Client(String firstName) {
        assert !firstName.isBlank() : "A client's first name should not be blank.";

        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s, Gender: %s, Phone Number: %s",
                this.lastName, this.firstName, this.gender, this.phoneNumber);
    }
}
