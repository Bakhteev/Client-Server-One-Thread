package modules;

import interaction.Response;
import lombok.Getter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

@Getter
public class ResponseSenderModule {
    private final ObjectOutputStream writer;

    public ResponseSenderModule(OutputStream writer) throws IOException {
        this.writer = new ObjectOutputStream(writer);
    }

    public void sendResponse(Response<?> response) throws IOException {
        writer.writeObject(response);
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}
