package commands;

import managers.ConsoleClient;
//TODO: ADD LOGGER, DO SMT WITH CONSOLECLIENT

public class HelpCommand extends AbstractCommand {
    ConsoleClient consoleClient;

    public HelpCommand(ConsoleClient consoleClient) {
        super("help", "display help on available commands.", "");
        this.consoleClient = consoleClient;
    }

    @Override
    public String execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new IllegalArgumentException("Using of command: " + getName());
            StringBuilder sb = new StringBuilder();
            consoleClient.getCommands().forEach((key, value) -> sb.append(key).append(": ").append(value.getDescription()).append(" ").append(
                    "Params: ").append(value.getParameters().isEmpty() ? "none" : value.getParameters()).append("\n"));

            return sb.toString();
        } catch (IllegalArgumentException exception) {
            System.out.println("Использование: '" + getName() + "'");
        }
        return "true";
    }


}
