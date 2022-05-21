package managers;

import commands.AbstractCommand;
import exceptions.NoSuchCommandException;
import interaction.Request;
import interaction.Response;

import java.util.LinkedHashMap;

public class ServerCommandManager {
    LinkedHashMap<String, AbstractCommand> commands = new LinkedHashMap<>();
    public void addCommands(AbstractCommand[] commands) {
        for (AbstractCommand command : commands) {
            this.commands.put(command.getName(), command);
        }
    }

    public LinkedHashMap<String, AbstractCommand> getCommands() {
        return commands;
    }

    public Response executeCommand(Request req) {
        try {
            if (!commands.containsKey(req.getCommand())) {
                throw new NoSuchCommandException("No such command " + req.getCommand());
            }
            return commands.get(req.getCommand()).execute(req);
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage() + " " + "Command manager");
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
    }
}
