package modules;


import commands.AbstractCommand;
import commands.HelpCommand;
import commands.InfoCommand;
import interaction.Request;
import interaction.Response;
import managers.CommandManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandWorkerModule implements Runnable {
    private RequestHandlerModule reader;
    private ResponseSenderModule writer;
    private Socket clientSocket;
    private CommandManager commandManager;

    public CommandWorkerModule(Socket clientSocket, CommandManager commandManager) {
        this.clientSocket = clientSocket;
        this.commandManager = commandManager;
//        this.commandManager.addCommands(new AbstractCommand[]{
//                new HelpCommand(commandManager),
//                new InfoCommand(collectionManager),
////                new ShowCommand(collectionManager.getCollection()),
////                new AddCommand(collectionManager),
////                new UpdateCommand(collectionManager),
////                new RemoveByIdCommand(collectionManager),
////                new AddIfMinCommand(collectionManager),
////                new SaveCommand(fileWorker, jsonParser, collectionManager),
////                new ExitCommand(),
////                new ExecuteScriptCommand(consoleClient),
////                new ClearCommand(collectionManager),
////                new RemoveGreaterCommand(collectionManager),
////                new PrintDescendingCommand(collectionManager.getCollection()),
////                new PrintUniqueLocationCommand(collectionManager.getCollection()),
////                new CountByHeightCommand(collectionManager.getCollection()),
////                new RemoveFirstCommand(collectionManager),
//        });
//        this.executor = executor;
//        new Setup().logger();
//        logger = Logger.getLogger(Server.class.getName());
    }

    public void setup() {
        try {
            writer = new ResponseSenderModule(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage() + " " + "writer");
//            logger.severe("Ошибка создания потока вывода");
        }
        try {
            reader = new RequestHandlerModule(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage() + " reader");
//            logger.severe("Ошибка создания потока ввода");
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Ошибка закрытия потока вывода", e);
        }
        try {
            reader.close();
        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Ошибка закрытия потока ввода", e);
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Ошибка закрытия соединения", e);
        }
    }

    private void handleRequest() {
        Request request = null;
        try {
            request = reader.readRequest();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            close();
        }
        if (request == null) {
            close();
        }
        try {
            assert request != null;
            Response res = commandManager.executeCommand(request);
            writer.sendResponse(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
//        try {
//            out.writeln(
//                    "Подклчен клиент: "
//                            + client.getLocalAddress().toString().substring(1)
//                            + client.getRemoteAddress());
//            logger.finest(
//                    "Подклчен клиент: "
//                            + client.getLocalAddress().toString().substring(1)
//                            + client.getRemoteAddress());
//        } catch (IOException e) {
////            logger.severe("Ошибка получения адреса клиента");
//        }
//        catch (OutputException e) {
////            e.printStackTrace(); // TODO
//        }
        setup();
//        try {
////            out.writeln("Настойка произведена успешно", Output.Colors.GREEN);
//        }
//        catch (OutputException e) {
////            logger.log(Level.SEVERE, "Ошибка записи", e);
//        }
//        logger.config("Настойка произведена успешно");
        while (!clientSocket.isClosed()) {
            handleRequest();
        }
        close();
    }
//}
}
