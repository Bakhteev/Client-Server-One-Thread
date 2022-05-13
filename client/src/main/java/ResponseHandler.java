import interaction.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ResponseHandler {
    ObjectInputStream reader;

    public ResponseHandler(InputStream inputStream) throws IOException {
        reader = new ObjectInputStream(inputStream);
    }

    public String readUTF() throws IOException {
        return reader.readUTF();
    }

    public Request<?> readRequest() throws IOException, ClassNotFoundException {
        return (Request<?>) reader.readObject();
    }

    public void close() throws IOException {
        reader.close();
    }
}
