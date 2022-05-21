package commands;

import communicate.ResponseHandler;
import interaction.Request;
import interaction.Response;
import workers.ConsoleWorker;

import java.io.IOException;

public abstract class AbstractCommand {
    private String name;
    private String description;
    private String params;

    public AbstractCommand(String name, String description, String params) {
        this.name = name;
        this.description = description;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getParameters() {
        return params;
    }

    public boolean result(ResponseHandler reader) {
        try {
            Response res = reader.readResponse();
            if (res.getStatus() == Response.Status.FAILURE) {
                ConsoleWorker.printError(res.getMessage());
                return false;
            }
            ConsoleWorker.println((String) res.getBody());
            return true;
        } catch (IOException | ClassNotFoundException e) {
            ConsoleWorker.printError("Something went wrong :3");
            return false;
        }
    }

    abstract public boolean execute(String argument);



}
