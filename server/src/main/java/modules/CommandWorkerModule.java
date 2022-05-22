package modules;

import interaction.Request;
import interaction.Response;
import managers.ServerCommandManager;

import java.io.IOException;
import java.net.Socket;

public class CommandWorkerModule implements Runnable {
    private static RequestHandlerModule reader;
    private static ResponseSenderModule writer;
    private static Socket clientSocket;
    private ServerCommandManager commandManager;

    public CommandWorkerModule(Socket clientSocket, ServerCommandManager commandManager) {
        CommandWorkerModule.clientSocket = clientSocket;
        this.commandManager = commandManager;
    }

    public void setup() {
        try {
            writer = new ResponseSenderModule(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            reader = new RequestHandlerModule(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleRequest() {
        Request request = null;
        try {
            request = reader.readRequest();
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
            close();
        }
        if (request == null) {
            close();
            return;
        }
        try {
            if (request.getCommand().equals("exit")) {
                close();
                System.out.println("\u001B[34mСlient closed\u001B[0m");
                return;
            }
            Response<?> res = commandManager.executeCommand(request);
            writer.sendResponse(res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        setup();
        while (!clientSocket.isClosed()) {
            handleRequest();
        }
        close();
    }
}
