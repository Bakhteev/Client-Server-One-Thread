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

public class AddIfMinCommand extends AbstractCommand {
    RequestSender writer;
    ResponseHandler reader;
    ClientCommandManager commandManager;

    public AddIfMinCommand(RequestSender writer, ResponseHandler reader, ClientCommandManager commandManager) {
        super("add_if_min", "add a new element to the collection if its value is less than the smallest " +
                "element in this collection.", "{element}");
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
            maker = new PersonMaker(ClientCommandManager.console);
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
