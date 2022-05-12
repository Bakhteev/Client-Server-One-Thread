package commands;

import interaction.Request;
import interaction.Response;
import models.Person;
import utils.PersonFormatter;

import java.util.LinkedList;


// TODO: ADD LOGGER
public class ShowCommand extends AbstractCommand {
    LinkedList<Person> collection;

    public ShowCommand(LinkedList<Person> collection) {
        super("show", "print to standard output all elements of the collection in string representation.", "");
        this.collection = collection;
    }

    @Override
    public Response execute(Request req) {
        try {
            if (req.getParams() != null) {
                throw new IllegalArgumentException("Using of command: " + getName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
        if (collection.size() == 0) {
//            System.out.println("Collection is empty");
            return new Response(Response.Status.COMPLETED, "", "Collection is empty");
        }
//        System.out.println(PersonFormatter.formatCollection(collection));
        return new Response<>(Response.Status.COMPLETED, "", PersonFormatter.formatCollection(collection));
    }
}
