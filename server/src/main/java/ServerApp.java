import commands.*;
import interaction.Response;
import managers.ConsoleClient;
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
    public static void main(String[] args) {
        FileWorker fileWorker = new FileWorker("D:\\ITMO_Lerning\\Proga\\lab-5\\data\\data.json");
        JSONParser jsonParser = new JSONParser();
        LinkedListCollectionManager collectionManager = new LinkedListCollectionManager();
        collectionManager.loadCollection((jsonParser.JSONParse(fileWorker.readFile(), Person[].class)));

        ConsoleClient consoleClient = new ConsoleClient();
        consoleClient.addCommands(new AbstractCommand[]{
                new HelpCommand(consoleClient),
//                new InfoCommand(collectionManager),
//                new ShowCommand(collectionManager.getCollection()),
//                new AddCommand(collectionManager),
//                new UpdateCommand(collectionManager),
//                new RemoveByIdCommand(collectionManager),
//                new AddIfMinCommand(collectionManager),
//                new SaveCommand(fileWorker, jsonParser, collectionManager),
//                new ExitCommand(),
//                new ExecuteScriptCommand(consoleClient),
//                new ClearCommand(collectionManager),
//                new RemoveGreaterCommand(collectionManager),
//                new PrintDescendingCommand(collectionManager.getCollection()),
//                new PrintUniqueLocationCommand(collectionManager.getCollection()),
//                new CountByHeightCommand(collectionManager.getCollection()),
//                new RemoveFirstCommand(collectionManager),
        });


        ConnectionModule connectionModule = new ConnectionModule(6789);
        while (true) {
            try {
                connectionModule.connect();
                ResponseSenderModule responseSender = new ResponseSenderModule(connectionModule.getClientSocket().getOutputStream());
                RequestHandlerModule requestHandler = new RequestHandlerModule(connectionModule.getClientSocket().getInputStream());

                System.out.println(connectionModule.isConnected());

                Person[] o = (Person[]) requestHandler.readRequest().getBody();
                System.out.println(Arrays.toString(o));
                o[0].setName("bohdan");
                responseSender.sendResponse(new Response<>(o));

                responseSender.sendUTF("hello from server");

                String command = requestHandler.readUTF();
                System.out.println(command);
                responseSender.sendUTF(consoleClient.getCommands().get(command).execute(""));

                responseSender.close();
                requestHandler.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                try {
                    connectionModule.disconnect();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
    }
}
