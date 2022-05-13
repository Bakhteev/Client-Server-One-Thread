import interaction.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class RequestSender {
    ObjectOutputStream writer;

    public RequestSender(OutputStream outputStream) throws IOException {
        writer = new ObjectOutputStream(outputStream);
    }

    public void sendResponse(Response<?> response) throws IOException {
        writer.writeObject(response);
        writer.flush();
    }

    public void sendUTF(String message) throws IOException {
        writer.writeUTF(message);
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
