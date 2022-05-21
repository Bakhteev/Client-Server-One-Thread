package utils;

import utils.exceptions.FileCanNotBeWrittenException;
import utils.exceptions.NoReadableFileException;

import java.io.*;
import java.nio.charset.StandardCharsets;

//TODO: ADD LOGGER
public class FileWorker {
    public String filePath;

    public FileWorker(String filePath) {
        this.filePath = filePath;
    }

    public String readFile() {

        StringBuilder jsonString = new StringBuilder();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File wasn't found. Program automatically created new file");
                }
                saveFile("[]");
            }
            if (!file.canRead()) {
                throw new NoReadableFileException("File can't be read. Please change access rights");
            }
            if (!file.canWrite()) {
                throw new FileCanNotBeWrittenException("File can't be read. Please change access rights");
            }
        } catch (NoReadableFileException | FileCanNotBeWrittenException | IOException e) {
            System.out.println(e.getMessage());
        }
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)
        ) {
            int c;
            while ((c = reader.read()) != -1) {
                jsonString.append((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return String.valueOf(jsonString);
    }

    public void saveFile(String data) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath),
                StandardCharsets.UTF_8);
        writer.write(data);
        writer.flush();
        writer.close();
    }
}
