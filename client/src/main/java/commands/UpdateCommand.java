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

public class UpdateCommand extends AbstractCommand {
    RequestSender writer;
    ResponseHandler reader;
    ClientCommandManager commandManager;

    public UpdateCommand(RequestSender writer, ResponseHandler reader, ClientCommandManager commandManager) {
        super("update", "update the value of the collection element whose id is equal to the given one.",
                "id {element}");
        this.writer = writer;
        this.reader = reader;
        this.commandManager = commandManager;
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
        PersonMaker maker;
        if (ClientCommandManager.fileMode) {
            maker = new PersonMaker(commandManager.getScanners().getLast());
        } else {
            maker = new PersonMaker(commandManager.getSc());
        }
        PersonDto dto = maker.update();
        try {
            writer.sendRequest(new Request<>(getName(), argument, dto));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result(reader);
    }
}
