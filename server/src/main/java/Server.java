import commands.AbstractCommand;
import commands.HelpCommand;
import commands.InfoCommand;
import lombok.Getter;
import managers.CommandManager;
import modules.CommandWorkerModule;
import modules.ConnectionModule;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class Server {
    private final int port;
    //    private Output out;
//    private Logger logger;
    ConnectionModule connectionModule;
    private Executor executor;
    private ServerSocket server;
    private ExecutorService threadPool;
    public Server(int port) {
        this.port = port;
//        this.connectionModule = new ConnectionModule(port);
        this.threadPool = Executors.newFixedThreadPool(1);
    }

    public boolean start() {
        try {
            this.connectionModule = new ConnectionModule(this.port);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Server started on PORT: " + port);
        return true;
    }

    public void connect(CommandManager commandManager){
        while (connectionModule.getServerSocket() != null){
            try {
                connectionModule.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadPool.execute(new CommandWorkerModule(connectionModule.getClientSocket(), commandManager));
        }
    }


}
