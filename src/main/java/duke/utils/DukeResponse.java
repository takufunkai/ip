package duke.utils;

public class DukeResponse {
    public enum ResponseStatus { SUCCESS, FAIL, PENDING }

    private ResponseStatus status;
    private String message;


    public DukeResponse(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
