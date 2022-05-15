import dto.PersonDto;
import interaction.Request;
import interaction.Response;

import java.io.Console;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client class
 */
public class Client {
    private final int port;
    private final String host;
    //    private Input in;
//    private Output out;
    private Logger logger;
    private SocketChannel socket;
    //    private Creator creator;
    private RequestSender writer;
    private ResponseHandler reader;

    public Client(String host, int port) {
        this.port = port;
        this.host = host;
//        in = new Input();
//        out = new Output();
//        creator = new Creator(in, out);
//        new Setup().logger();
//        logger = Logger.getLogger(Client.class.getName());
    }

    public SocketChannel getSocket() {
        return socket;
    }

    /**
     * Connects to the server
     *
     * @return True if connected
     */
    public boolean connect() {
        try {
            socket = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));
            System.out.println("xui");
        } catch (IOException e) {
//            logger.severe("Ошибка подключения к серверу");
            return false;
        }
//        try {
//            out.writeln("Соединение установлено", Output.Colors.GREEN);
//        } catch (OutputException e) {
//            logger.log(Level.SEVERE, "Ошибка записи", e);
//        }
//        logger.finest("Соединение установлено");
        return true;
    }

    /**
     * Setups connection
     *
     * @throws IOException if not setup
     */
    public void setup() {
        try {
            writer = new RequestSender(socket.socket().getOutputStream());
        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Ошибка создания объекта для отправки данных", e);
        }
        try {
            reader = new ResponseHandler(socket.socket().getInputStream());
        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Ошибка создания объекта для получения данных", e);
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
            socket.close();
        } catch (IOException e) {
//            logger.severe("Ошибка закрытия сокета");
        }
    }

    public void waitConnection() {
        int sec = 0;
        while (!socket.isConnected()) {
            try {
                socket = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(host), port));
//                socket.connect(new InetSocketAddress(InetAddress.getByName(host), port));
                setup();
//                out.writeln();
//                out.writeln("Повторное подключение проиведено успешно. Продолжение выполнения");
                return;
            } catch (IOException e) {
//                logger.warning("Ошибка повторного подключения к серверу");
            }
//            out.write("\rОшибка подключения. Ожидание повторного подключения: " + sec + "/60 секунд");
            sec++;
            if (sec > 60) {
//                out.writeln("Клиент не дождался подключения. Завершение работы программы");
                System.exit(0);
            }
//            logger.fine("Ошибка подключения. Ожидание повторного подключения: " + sec + "/60 секунд");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
//                logger.log(Level.SEVERE, "Ошибка приостановки выполнения потока", ex);
            }
        }
    }

    public void run() {
//        try {
        while (getSocket().isConnected()) {
            get();
            waitConnection();
        }
//        }
//        catch (OutputException e) {
//            System.err.println("Ошибка записи");
//        } catch (InputException e) {
//            System.err.println("Ошибка чтения");
//        }
    }

    /**
     * One request
     */
    public void get() {
//        while (true) {
        ConsoleWorker.println("Enter Command: ");
        ConsoleWorker.printSymbol(true);
        Scanner sc = new Scanner(System.in);
        String[] scstr = sc.nextLine().trim().split(" ", 2);

        System.out.println(Arrays.toString(scstr));
        Request request = new Request(scstr[0], scstr.length > 1 ? scstr[1]: null);
        try {
            writer.sendRequest(request);
        } catch (IOException e) {
            close();
            return;
        }
        try {
//            System.out.println("ffffffff");
            String xui = reader.readUTF();
            System.out.println(xui);
            if (xui.equals("exit")) {
                close();
                System.exit(0);
            }
            if (xui.equals("add")) {
                PersonDto dto = new PersonMaker(sc).makeDto();
                writer.sendObject(dto);
            }
            if (xui.equals("update")) {
                PersonDto dto = new PersonMaker(sc).update();
                writer.sendObject(dto);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage() + "xuiiiiiiiii");
//            close();
            return;
        }
        Response res;
        try {
            res = reader.readResponse();
//            System.out.println(res.toString());
            System.out.println(res.toString());
        } catch (IOException | ClassNotFoundException e) {
            close();
            return;
        }
        if (res != null) {
//                res.getReply().forEach(out::println);
            System.out.println(res.getStatus().equals(Response.Status.FAILURE) ? res.getMessage() : res.getBody());
        } else {
//                logger.severe("Внутренняя ошибка сервера. Закрытие подключения");
            close();
            return;
        }
//            logger.info(
//                    "Данные клиента: " + request.getCommandName() + " " + request.getCommandParameter());
//            logger.log(reply.getType().getLevel(), reply.getString());
    }
//    }
}
