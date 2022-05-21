package commands;

import communicate.RequestSender;
import communicate.ResponseHandler;
import interaction.Request;
import workers.ConsoleWorker;

import java.io.IOException;

public class PrintUniqueLocationCommand extends AbstractCommand {
    RequestSender writer;
    ResponseHandler reader;

    public PrintUniqueLocationCommand(RequestSender writer, ResponseHandler reader) {
        super("print_unique_location", "display the unique values of the location field of all elements in the collection.", "");
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
            writer.sendRequest(new Request(getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result(reader);
    }
}
