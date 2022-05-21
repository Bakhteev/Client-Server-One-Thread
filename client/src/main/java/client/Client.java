package client;

import commands.*;
import communicate.RequestSender;
import communicate.ResponseHandler;
import managers.ClientCommandManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class Client {
    private static int port;
    private static String host;
    private static SocketChannel socket;
    private static RequestSender writer;
    private static ResponseHandler reader;


    public Client(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public SocketChannel getSocket() {
        return socket;
    }

    public boolean connect() {
        try {
            socket = SocketChannel.open(new InetSocketAddress(host, port));
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    public static void setup() {
        try {
            writer = new RequestSender(socket.socket().getOutputStream());
        } catch (IOException e) {
        }
        try {
            reader = new ResponseHandler(socket.socket().getInputStream());
        } catch (IOException e) {
        }
    }

    public static void close() {
        try {
            writer.close();
        } catch (IOException e) {
        }
        try {
            reader.close();
        } catch (IOException e) {
        }
        try {
            socket.close();
        } catch (IOException e) {
        }
    }

    public static void waitConnection() {
        int sec = 0;
        close();
        while (!socket.socket().isConnected()) {
            try {
                socket = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(host), port));
                reader.setReader(socket.socket().getInputStream());
                writer.setWriter(socket.socket().getOutputStream());
                System.out.println("Повторное подключение произведено успешно. Продолжение выполнения");
                return;
            } catch (IOException e) {
            }
            System.out.println("\rОшибка подключения. Ожидание повторного подключения: " + sec + "/60 секунд");
            sec++;
            if (sec > 60) {
                System.exit(0);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void run() {
        while (!getSocket().socket().isOutputShutdown()) {
            get();
        }
    }


    public void get() {
        ClientCommandManager commandManager = new ClientCommandManager();
        commandManager.addCommands(new AbstractCommand[]{
                new HelpCommand(writer, reader),
                new InfoCommand(writer, reader),
                new CountByHeightCommand(writer, reader),
                new PrintDescendingCommand(writer, reader),
                new AddCommand(writer, reader, commandManager),
                new UpdateCommand(writer, reader, commandManager),
                new ShowCommand(writer, reader),
                new ClearCommand(writer, reader),
                new RemoveGreaterCommand(writer, reader, commandManager),
                new RemoveFirstCommand(writer, reader),
                new RemoveByIdCommand(writer, reader),
                new PrintUniqueLocationCommand(writer, reader),
                new ExecuteScriptCommand(commandManager),
                new ExitCommand(writer, reader),
                new AddIfMinCommand(writer, reader, commandManager)});

        commandManager.startInteractiveMode();

    }
}

