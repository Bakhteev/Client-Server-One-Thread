package interaction;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Response<T> implements Serializable {
    Status status;
    String message;
    T body;

    public Response(Status status, String message) {
        this.status = status;
        this.message = message;
        this.body = null;
    }

    public Response(Status status, T body) {
        this.status = status;
        this.message = "";
        this.body = body;
    }

    public Response(Status status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;

    }

    static public enum Status implements Serializable {
        FAILURE,
        COMPLETED,
    }
}
