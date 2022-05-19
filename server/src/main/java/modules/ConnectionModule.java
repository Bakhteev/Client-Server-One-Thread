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
//        System.out.println("Server started on PORT: " + serverSocket.getLocalPort());
    }

    public void connect() throws IOException {
        System.out.println("Waiting...");
//        assert serverSocket != null;
        clientSocket = serverSocket.accept();
    }

    public void disconnect() throws IOException {
        serverSocket.close();
    }

    public boolean isConnected() {
        return clientSocket.isConnected();
    }
}
