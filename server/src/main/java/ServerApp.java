import commands.*;
import interaction.Request;
import interaction.Response;
import managers.CommandManager;
import managers.LinkedListCollectionManager;
import models.Person;
import modules.ConnectionModule;
import modules.RequestHandlerModule;
import modules.ResponseSenderModule;
import utils.FileWorker;
import utils.JSONParser;

import java.io.IOException;
import java.util.Arrays;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        FileWorker fileWorker = new FileWorker("D:\\ITMO_Lerning\\Proga\\lab-5\\data\\data.json");
        JSONParser jsonParser = new JSONParser();
        LinkedListCollectionManager collectionManager = new LinkedListCollectionManager();
        collectionManager.loadCollection((jsonParser.JSONParse(fileWorker.readFile(), Person[].class)));

        CommandManager commandManager = new CommandManager();
        commandManager.addCommands(new AbstractCommand[]{
                new HelpCommand(commandManager),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager.getCollection()),
//                new AddCommand(collectionManager),
//                new UpdateCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
//                new AddIfMinCommand(collectionManager),
//                new SaveCommand(fileWorker, jsonParser, collectionManager),
//                new ExitCommand(),
//                new ExecuteScriptCommand(consoleClient),
                new ClearCommand(collectionManager),
//                new RemoveGreaterCommand(collectionManager),
                new PrintDescendingCommand(collectionManager.getCollection()),
                new PrintUniqueLocationCommand(collectionManager.getCollection()),
                new CountByHeightCommand(collectionManager.getCollection()),
                new RemoveFirstCommand(collectionManager),
        });


//        ConnectionModule connectionModule = new ConnectionModule(6789);
//        while (true) {
//            try {
//                connectionModule.connect();
//                ResponseSenderModule responseSender = new ResponseSenderModule(connectionModule.getClientSocket().getOutputStream());
//                RequestHandlerModule requestHandler = new RequestHandlerModule(connectionModule.getClientSocket().getInputStream());
//
//                System.out.println(connectionModule.isConnected());
//
//                Person[] o = (Person[]) requestHandler.readRequest().getBody();
//                System.out.println(Arrays.toString(o));
//                o[0].setName("bohdan");
//                responseSender.sendResponse(new Response<>(Response.Status.COMPLETED, "", o));
//
//                responseSender.sendUTF("hello from server");
//
//                Request<?> req = requestHandler.readRequest();
//                System.out.println(req.getCommand());
//                responseSender.sendResponse(consoleClient.getCommands().get(req.getCommand()).execute(req));
//
//                responseSender.close();
//                requestHandler.close();
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//                try {
//                    connectionModule.disconnect();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                break;
//            }
//        }

        Server server = new Server(6789);
        server.start();
        server.connect(commandManager);
    }
}
