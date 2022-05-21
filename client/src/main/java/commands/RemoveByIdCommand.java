package commands;

import communicate.RequestSender;
import communicate.ResponseHandler;
import interaction.Request;
import workers.ConsoleWorker;

import java.io.IOException;

public class RemoveByIdCommand extends AbstractCommand {
    RequestSender writer;
    ResponseHandler reader;

    public RemoveByIdCommand(RequestSender writer, ResponseHandler reader) {
        super("remove_by_id", "remove element from collection by its id.", "id");
        this.writer = writer;
        this.reader = reader;
    }


    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) {
                throw new IllegalArgumentException("Using of command :" + getName() + " " + getParameters());
            }
        } catch (IllegalArgumentException e) {
            ConsoleWorker.printError(e.getMessage());
            return false;
        }
        try {
            writer.sendRequest(new Request<>(getName(), argument));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result(reader);
    }
}
