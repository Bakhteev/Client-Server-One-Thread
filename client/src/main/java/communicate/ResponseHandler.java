package communicate;

import interaction.Request;
import interaction.Response;

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

    public Response<?> readResponse() throws IOException, ClassNotFoundException {
        return (Response<?>) reader.readObject();
    }

    public void close() throws IOException {
        reader.close();
    }

    public void setReader(InputStream inputStream) throws IOException {
        this.reader = new ObjectInputStream(inputStream);
    }
}
