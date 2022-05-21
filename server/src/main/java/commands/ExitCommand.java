package commands;

import interaction.Request;
import interaction.Response;
import modules.CommandWorkerModule;

// TODO: ADD SAVE FILE BEFORE EXIT, ADD LOGGER
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "exit program without saving collection into file", "");
    }


    public boolean execute(String arguments) {
        try {
            if (!arguments.isEmpty())
                throw new IllegalArgumentException("Using of command: " + getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Good bye");
        System.exit(0);
        return true;
    }

    @Override
    public Response execute(Request req) {
        return null;
    }
}
