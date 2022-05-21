import commands.AbstractCommand;
import commands.HelpCommand;
import commands.InfoCommand;
import lombok.Getter;
import managers.ServerCommandManager;
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
    ConnectionModule connectionModule;
    private ExecutorService threadPool;
    private ServerCommandManager commandManager;

    public Server(int port, ServerCommandManager commandManager) {
        this.port = port;
        this.commandManager = commandManager;
        this.threadPool = Executors.newFixedThreadPool(1);
    }

    public boolean start() {
        try {
            this.connectionModule = new ConnectionModule(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server started on PORT: " + port);
        return true;
    }

    public void connect() {
        while (connectionModule.getServerSocket() != null) {
            try {
                connectionModule.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadPool.execute(new CommandWorkerModule(connectionModule.getClientSocket(), commandManager));
        }
    }


}
