package interaction;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Request<T> implements Serializable {
    String command;
    String params;
    T body;

    public Request(String command){
        this.command = command;
        this.params = null;
        this.body = null;
    }

    public Request(String command, String params){
        this.command = command;
        this.params = params;
        this.body = null;
    }

    public Request(String command, String params, T body){
        this.command = command;
        this.params = params;
        this.body = body;
    }

}
