package commands;

import communicate.RequestSender;
import communicate.ResponseHandler;
import dto.PersonDto;
import interaction.Request;
import interaction.Response;
import maker.PersonMaker;
import managers.ClientCommandManager;
import workers.ConsoleWorker;

import java.io.IOException;

public class AddCommand extends AbstractCommand {
    RequestSender writer;
    ResponseHandler reader;
    ClientCommandManager commandManager;

    public AddCommand(RequestSender writer, ResponseHandler reader, ClientCommandManager commandManager) {
        super("add", "add a new element to the collection.", "{element}");
        this.writer = writer;
        this.reader = reader;
        this.commandManager = commandManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty())
                throw new IllegalArgumentException("Using of command add: " + getName());

        } catch (IllegalArgumentException e) {
            ConsoleWorker.printError(e.getMessage());
            return false;
        }
        PersonMaker maker;
        if (ClientCommandManager.fileMode) {
            maker = new PersonMaker(commandManager.getScanners().getLast());
        } else {
            maker = new PersonMaker(commandManager.getSc());
        }
        PersonDto dto = maker.makeDto();
        try {
            writer.sendRequest(new Request<>(getName(), null, dto));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result(reader);
    }
}
