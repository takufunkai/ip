package duke.client;

import java.util.UUID;

public class Client {
    private final String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber; // TODO: to new class

    public Client(String firstName) {
        assert !firstName.isBlank() : "A client's first name should not be blank.";

        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
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
