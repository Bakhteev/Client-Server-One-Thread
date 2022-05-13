package commands;

//TODO: ADD LOGGER

import interaction.Request;
import interaction.Response;
import models.Person;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PrintUniqueLocationCommand extends AbstractCommand {

    LinkedList<Person> collection;

    public PrintUniqueLocationCommand(LinkedList<Person> collection) {
        super("print_unique_location", "display the unique values of the location field of all elements in the collection.", "");
        this.collection = collection;
    }

    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() != null) {
                throw new IllegalArgumentException("Using of command: " + getName());
            }
            if (collection.size() == 0) {
                throw new IllegalStateException("Collection is empty");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
        HashMap<String, Integer> map = countLocations();
        String locationNameToShow = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                locationNameToShow = entry.getKey();
                System.out.println("Unique location is " + locationNameToShow);
                return new Response<>(Response.Status.COMPLETED, "", "Unique location is " + locationNameToShow);
            }
        }
        System.out.println("No unique Location");
        return new Response<>(Response.Status.COMPLETED, "", "No unique Location");
    }

    private HashMap<String, Integer> countLocations() {
        HashMap<String, Integer> map = new HashMap<>();
        for (Person element : collection) {
            String locationName = element.getLocation().getName();
            if (map.containsKey(locationName)) {
                map.put(locationName, map.get(locationName) + 1);
            } else {
                map.put(locationName, 1);
            }
        }
        return map;
    }
}
