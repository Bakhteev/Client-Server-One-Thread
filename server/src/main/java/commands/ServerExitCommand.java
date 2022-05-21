package commands;

import interaction.Request;
import interaction.Response;
import managers.LinkedListCollectionManager;
import modules.CommandWorkerModule;
import utils.FileWorker;
import utils.JSONParser;

import java.io.IOException;

public class ServerExitCommand extends AbstractCommand {
    LinkedListCollectionManager collectionManager;
    FileWorker fileWorker;

    public ServerExitCommand(LinkedListCollectionManager collectionManager, FileWorker fileWorker) {
        super("server_exit", "closes the server and saves progress", "");
        this.collectionManager = collectionManager;
        this.fileWorker = fileWorker;
    }

    @Override
    public Response execute(Request req) {
        try {
            fileWorker.saveFile(JSONParser.toJSON(collectionManager.getCollection()));
            CommandWorkerModule.close();
            System.exit(0);
            return null;
        } catch (SecurityException exception) {
            System.out.println("\u001B[31mFile permission error!\u001B[0m");
            return null;
        } catch (IOException exception) {
            System.out.println("\u001B[31mFailed to save data to file!\u001B[0m");
            return null;
        }
    }
}
