package commands;
// TODO: ADD LOGGER

import comparators.PersonDescendingOrderComparator;
import interaction.Request;
import interaction.Response;
import models.Person;
import utils.PersonFormatter;

import java.util.LinkedList;

public class PrintDescendingCommand extends AbstractCommand {

    private LinkedList<Person> collection;

    public PrintDescendingCommand(LinkedList<Person> collection) {
        super("print_descending", "display the elements of the collection in descending order.", "");
        this.collection = collection;
    }

    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() != null) {
                throw new IllegalArgumentException("Using of command: " + getName());
            }
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Collection is empty");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
        StringBuilder sb = new StringBuilder();
        collection.stream().sorted(new PersonDescendingOrderComparator()).forEachOrdered(person -> {
            sb.append(PersonFormatter.format(person)).append("\n");
//            System.out.println(PersonFormatter.format(person));
        });
        return new Response<>(Response.Status.COMPLETED, "", sb.toString());
    }
}
