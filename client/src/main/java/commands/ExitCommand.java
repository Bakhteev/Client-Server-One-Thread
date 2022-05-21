package commands;

import client.Client;
import communicate.RequestSender;
import communicate.ResponseHandler;
import interaction.Request;
import workers.ConsoleWorker;

import java.io.IOException;

public class ExitCommand extends AbstractCommand {
    RequestSender writer;
    ResponseHandler reader;

    public ExitCommand(RequestSender writer, ResponseHandler reader) {
        super("exit", "exit client side program without saving collection into file", "");
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
        }
        ConsoleWorker.println("Good bye");
        Client.close();
        System.exit(0);
        return true;
    }
}
