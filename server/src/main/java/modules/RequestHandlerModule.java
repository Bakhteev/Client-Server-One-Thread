package modules;

import interaction.Request;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

@Getter
public class RequestHandlerModule {
    private final ObjectInputStream reader;

    public RequestHandlerModule(InputStream input) throws IOException {
        this.reader = new ObjectInputStream(input);
    }
    public String readUTF() throws IOException {
        return reader.readUTF();
    }

    public Request<?> readRequest() throws IOException, ClassNotFoundException {
        return (Request<?>) reader.readObject();
    }

    public Object readObject() throws IOException, ClassNotFoundException {
        return reader.readObject();
    }
    public void close() throws IOException {
        reader.close();
    }
}
