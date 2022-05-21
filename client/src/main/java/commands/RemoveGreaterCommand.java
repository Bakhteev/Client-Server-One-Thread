package commands;

import communicate.RequestSender;
import communicate.ResponseHandler;
import dto.PersonDto;
import interaction.Request;
import maker.PersonMaker;
import managers.ClientCommandManager;
import workers.ConsoleWorker;

import java.io.IOException;

public class RemoveGreaterCommand extends AbstractCommand {
    RequestSender writer;
    ResponseHandler reader;
    ClientCommandManager commandManager;

    public RemoveGreaterCommand(RequestSender writer, ResponseHandler reader, ClientCommandManager commandManager) {
        super("remove_greater", "remove from the collection all elements greater than the given.",
                "{element}");
        this.writer = writer;
        this.reader = reader;
        this.commandManager = commandManager;
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
        PersonDto dto = new PersonMaker(ClientCommandManager.fileMode ? commandManager.getScanners().getLast() : commandManager.getSc()).makeDto();
        try {
            writer.sendRequest(new Request<>(getName(), "", dto));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result(reader);
    }
}
