package modules;


import commands.AbstractCommand;
import commands.HelpCommand;
import commands.InfoCommand;
import dto.PersonDto;
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

    public void requireDto(Request req) {
        AbstractCommand command = commandManager.getCommand(req.getCommand());
        if (command == null) {
            return;
        }
        if (req.getCommand().equals("update")) {
            try {
                writer.sendUTF("update");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.sendUTF("add");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PersonDto dto = (PersonDto) reader.readObject();
            req.setBody(dto);
            writer.sendResponse(commandManager.executeCommand(req));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void handleRequest() {
        Request request = null;
        try {
            request = reader.readRequest();
            System.out.println(request.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            close();
        }
        if (request == null) {
            close();
        }
        try {
            assert request != null;
            if (request.getCommand().equals("exit")) {
                writer.sendUTF("exit");
                close();
                return;
            } else if (commandManager.getCommand(request.getCommand()) != null
                    && commandManager.getCommand(request.getCommand()).getParameters().endsWith("{element}")) {
                requireDto(request);
//                return;
            } else {
                writer.sendUTF("");
                Response res = commandManager.executeCommand(request);
                writer.sendResponse(res);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
