package communicate;

import client.Client;
import interaction.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class RequestSender {
    ObjectOutputStream writer;

    public RequestSender(OutputStream outputStream) throws IOException {
        writer = new ObjectOutputStream(outputStream);
    }

    public void sendRequest(Request<?> request) throws IOException {
        try {
            writer.writeObject(request);
            writer.flush();
        }
        catch (IOException e){
            Client.waitConnection();
            sendRequest(request);
        }
    }

    public void sendObject(Object object) throws IOException {
        writer.writeObject(object);
        writer.flush();
    }

    public void sendUTF(String message) throws IOException {
        writer.writeUTF(message);
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }

    public void setWriter(OutputStream outputStream) throws IOException {
        this.writer = new ObjectOutputStream(outputStream);
    }
}
