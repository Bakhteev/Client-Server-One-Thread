import commands.*;
import managers.LinkedListCollectionManager;
import managers.ServerCommandManager;
import models.Person;
import utils.FileWorker;
import utils.JSONParser;

import java.io.Console;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileWorker fileWorker;
        try {
            fileWorker = new FileWorker(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            fileWorker = new FileWorker("./data.json");
        }
        JSONParser jsonParser = new JSONParser();
        LinkedListCollectionManager collectionManager = new LinkedListCollectionManager();
        collectionManager.loadCollection((jsonParser.JSONParse(fileWorker.readFile(), Person[].class)));

        ServerCommandManager commandManager = new ServerCommandManager();
        commandManager.addCommands(new AbstractCommand[]{
                new HelpCommand(commandManager),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager.getCollection()),
                new AddCommand(collectionManager),
                new UpdateCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new AddIfMinCommand(collectionManager),
                new ExitCommand(),
                new ExecuteScriptCommand(),
                new ClearCommand(collectionManager),
                new RemoveGreaterCommand(collectionManager),
                new PrintDescendingCommand(collectionManager.getCollection()),
                new PrintUniqueLocationCommand(collectionManager.getCollection()),
                new CountByHeightCommand(collectionManager.getCollection()),
                new RemoveFirstCommand(collectionManager),
        });

        FileWorker finalFileWorker = fileWorker;


        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    finalFileWorker.saveFile(JSONParser.toJSON(collectionManager.getCollection()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Console console = System.console();
                    String input = null;
                    try {
                        input = console.readLine().trim();
                        if (input.equalsIgnoreCase("save")) {
                            new SaveCommand(collectionManager, finalFileWorker).execute(null);
                        }
                        if (input.equalsIgnoreCase("exit")) {
                            new ServerExitCommand(collectionManager, finalFileWorker).execute(null);
                        }
                    } catch (NullPointerException e) {
                        System.out.println("\u001B[31mDon't do this!!! Better use exit command\u001B[0m");
                        System.out.println("\u001B[32mServer is closing\u001B[0m");
                    }
                }
            }
        }).start();


        Server server = new Server(Integer.parseInt(args[0]), commandManager);
        server.start();
        server.connect();


    }
}
