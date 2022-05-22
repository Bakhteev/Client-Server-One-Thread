package modules;

import lombok.Getter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Getter
public class ConnectionModule {
    private ServerSocket serverSocket;
    private Socket clientSocket = null;

    public ConnectionModule(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void connect() throws IOException {
//        System.out.println("Waiting...");
        clientSocket = serverSocket.accept();
    }

    public void disconnect() throws IOException {
        serverSocket.close();
    }

    public boolean isConnected() {
        return clientSocket.isConnected();
    }
}
