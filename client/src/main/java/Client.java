import dto.PersonDto;
import interaction.Request;
import interaction.Response;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
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
        while (socket.socket().isClosed() || !socket.socket().isConnected()) {
            try {
                socket = SocketChannel.open(new InetSocketAddress(InetAddress.getByName(host), port));
//                socket.connect(new InetSocketAddress(InetAddress.getByName(host), port));
                setup();
//                writer.setWriter(new ObjectOutputStream(socket.socket().getOutputStream()));
//                reader.setReader(new ObjectInputStream(socket.socket().getInputStream()));

//                out.writeln();
                System.out.println("Повторное подключение произведено успешно. Продолжение выполнения");
                return;
            } catch (IOException e) {
//                logger.warning("Ошибка повторного подключения к серверу");
            }
            System.out.println("\rОшибка подключения. Ожидание повторного подключения: " + sec + "/60 секунд");
//            out.write();
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

//    private Request request;

    private void sendRequest(Request request) throws IOException {
        try {
            writer.sendRequest(request);
            System.out.println(socket.socket().isClosed() + " || " + socket.isConnected());
        } catch (IOException e) {
//            waitConnection();
//            sendRequest(request);
        }

    }


    /**
     * One request
     */
    public void get() {
//        while (true) {
//        ConsoleWorker.println("Enter Command: ");
//        ConsoleWorker.printSymbol(true);
//        Scanner sc = new Scanner(System.in);
//        String[] scstr = sc.nextLine().trim().split(" ", 2);
//
//        System.out.println(Arrays.toString(scstr));
        CommandManager commandManager = new CommandManager();
        Request request = commandManager.startInteractiveMode();
        try {
            sendRequest(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            writer.sendRequest(request);
//        } catch (IOException e) {
//            System.out.println("tyt");
//            while (!getSocket().isConnected()) {
//                waitConnection();
//            }
//            try {
//                writer.sendRequest(request);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
////            try {
////
////            }catch (IOException ioException) {
////                ioException.printStackTrace();
////            }
////            return;
//        }
        try {
            String commandType = reader.readUTF();
//            System.out.println(commandType);
            if (commandType.equals("exit")) {
                close();
                System.exit(0);
            }
            if (commandType.equals("add")) {
                PersonDto dto;
                if (CommandManager.fileMode) {
                    dto = new PersonMaker(commandManager.getScanners().getLast()).makeDto();
                } else {
                    dto = new PersonMaker(commandManager.getScanners().getLast()).makeDto();
                }
                writer.sendObject(dto);
            }
            if (commandType.equals("update")) {
                PersonDto dto;
                if (CommandManager.fileMode) {
                    dto = new PersonMaker(commandManager.getScanners().getLast()).update();
                } else {
                    dto = new PersonMaker(commandManager.getScanners().getLast()).update();
                }
                writer.sendObject(dto);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            close();
            return;
        }
        try {
            Response<?> res = reader.readResponse();
            System.out.println(res.getStatus().equals(Response.Status.FAILURE) ? res.getMessage() : res.getBody());
            return;
        } catch (IOException e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
            close();
            return;
        } catch (ClassNotFoundException e) {
            close();
            return;
        }
//            logger.info(
//                    "Данные клиента: " + request.getCommandName() + " " + request.getCommandParameter());
//            logger.log(reply.getType().getLevel(), reply.getString());
    }
//    }
}
