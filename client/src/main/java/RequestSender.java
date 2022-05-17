import interaction.Request;
import interaction.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class RequestSender {
    ObjectOutputStream writer;

    public RequestSender(OutputStream outputStream) throws IOException {
        writer = new ObjectOutputStream(outputStream);
    }

    public void sendRequest(Request<?> request) throws IOException {
        writer.writeObject(request);
        writer.flush();
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

    public void setWriter(ObjectOutputStream writer) {
        this.writer = writer;
    }
}
