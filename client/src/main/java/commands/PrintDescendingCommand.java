package commands;

import communicate.RequestSender;
import communicate.ResponseHandler;
import interaction.Request;
import interaction.Response;
import workers.ConsoleWorker;

import java.io.IOException;

public class PrintDescendingCommand extends AbstractCommand {
    RequestSender writer;
    ResponseHandler reader;

    public PrintDescendingCommand(RequestSender writer, ResponseHandler reader) {
        super("print_descending", "display the elements of the collection in descending order.", "");
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) {
                throw new IllegalArgumentException("Using of command: " + getName());
            }
        } catch (IllegalArgumentException e) {
            ConsoleWorker.printError(e.getMessage());
            return false;
        }
        try {
            writer.sendRequest(new Request<>(getName()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return result(reader);
    }
}
