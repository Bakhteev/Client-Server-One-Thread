package commands;

import interaction.Request;
import interaction.Response;

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

    abstract public Response execute(Request req);

}
