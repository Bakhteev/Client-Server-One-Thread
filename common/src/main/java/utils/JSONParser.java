package utils;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.util.Collection;

public class JSONParser {

    public <T> T JSONParse(String data, Class<T> classOfT) {
        try {
            Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();
            return gson.fromJson(String.valueOf(data), classOfT);
        } catch (JsonSyntaxException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String toJSON(Collection<?> collection) {
        Gson gson = Converters.registerLocalDateTime(new GsonBuilder().setPrettyPrinting()).create();
        return gson.toJson(collection);
    }
}
