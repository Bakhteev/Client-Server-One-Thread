package commands;

import interaction.Request;
import interaction.Response;
import managers.CommandManager;
//TODO: ADD LOGGER, DO SMT WITH CONSOLECLIENT

public class HelpCommand extends AbstractCommand {
    CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        super("help", "display help on available commands.", "");
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request req) {
        try {
//            if (!argument.isEmpty())
//                throw new IllegalArgumentException("Using of command: " + getName());
            StringBuilder sb = new StringBuilder();
            commandManager.getCommands().forEach((key, value) -> sb.append(key).append(": ").append(value.getDescription()).append(" ").append(
                    "Params: ").append(value.getParameters().isEmpty() ? "none" : value.getParameters()).append("\n"));

            return new Response<>(Response.Status.COMPLETED,"",  sb.toString());
//            return sb.toString();
        } catch (IllegalArgumentException exception) {
            System.out.println("Использование: '" + getName() + "'");
            return new Response<>(Response.Status.FAILURE,"Использование: '" + getName() + "'");
        }
//        return "true";
    }


}
