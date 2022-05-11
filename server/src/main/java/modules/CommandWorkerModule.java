package modules;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandWorkerModule implements Runnable {
    RequestHandlerModule reader;
    ResponseSenderModule  writer;
    Socket clientSocket;
    public CommandWorkerModule(Socket clientSocket) {
        this.clientSocket = clientSocket;
//        this.executor = executor;
//        new Setup().logger();
//        logger = Logger.getLogger(Server.class.getName());
    }

    public void setup() {
        try {
            writer = new ResponseSenderModule(new ObjectOutputStream(clientSocket.getOutputStream()));
        } catch (IOException e) {
//            logger.severe("Ошибка создания потока вывода");
        }

        try {
            reader = new RequestHandlerModule(new ObjectInputStream(clientSocket.getInputStream()));
        } catch (IOException e) {
//            logger.severe("Ошибка создания потока ввода");
        }
    }

    @Override
    public void run() {

    }
}
