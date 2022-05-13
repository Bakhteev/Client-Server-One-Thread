import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Client class */
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

            socket = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(host), port));
        } catch (IOException e) {
            logger.severe("Ошибка подключения к серверу");
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
        } catch ( IOException e) {
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
                logger.warning("Ошибка повторного подключения к серверу");
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
                logger.log(Level.SEVERE, "Ошибка приостановки выполнения потока", ex);
            }
        }
    }

    public void run() {
//        try {
            while (!getSocket().isConnected()) {
//                get();
                waitConnection();
            }
//        }
//        catch (OutputException e) {
//            System.err.println("Ошибка записи");
//        } catch (InputException e) {
//            System.err.println("Ошибка чтения");
//        }
    }

    /** One request */
//    public void get() throws OutputException, InputException {
//        try {
//            out.write(receiver.readUTF());
//        } catch (ReceiverException e) {
//            close();
//            return;
//        }
//        Request request = new Request(in.readLine());
//        try {
//            sender.writeObject(request);
//        } catch (SenderException e) {
//            close();
//            return;
//        }
//        try {
//            if (receiver.readBoolean()) { // exit
//                close();
//                System.exit(0);
//            }
//        } catch (ReceiverException e) {
//            close();
//            return;
//        }
//        try {
//            if (receiver.readBoolean()) { // requireElement
//                try {
//                    sender.writeObject(creator.create());
//                } catch (CreatorException e) {
//                    logger.log(Level.SEVERE, "Ошибка создания нового элемента", e);
//                    try {
//                        sender.writeObject(null);
//                    } catch (SenderException ex) {
//                        logger.log(Level.SEVERE, "Ошибка отправки null", e);
//                    }
//                } catch (SenderException e) {
//                    logger.log(Level.SEVERE, "Ошибка отправки нового элемента", e);
//                }
//            }
//        } catch (ReceiverException e) {
//            close();
//            return;
//        }
//        Reply reply;
//        try {
//            reply = (Reply) receiver.readObject();
//        } catch (ReceiverException e) {
//            close();
//            return;
//        }
//        if (reply != null) {
//            reply.getReply().forEach(out::println);
//        } else {
//            logger.severe("Внутренняя ошибка сервера. Закрытие подключения");
//            close();
//            return;
//        }
//        logger.info(
//                "Данные клиента: " + request.getCommandName() + " " + request.getCommandParameter());
//        logger.log(reply.getType().getLevel(), reply.getString());
//    }
}
