package commands;
// TODO ADD LOGGER

import interaction.Request;
import interaction.Response;
import models.Person;

import java.util.LinkedList;

public class CountByHeightCommand extends AbstractCommand {
    LinkedList<Person> collection;

    public CountByHeightCommand(LinkedList<Person> collection) {
        super("count_by_height", "display the number of elements whose height field value is equal to the given one.",
                "height");
        this.collection = collection;
    }


    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() == null) {
                throw new IllegalArgumentException("Using of command: " + getName() + " " + getParameters());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }

        if (collection.size() == 0) {
            System.out.println("collection is empty");
            return new Response<>(Response.Status.FAILURE, "collection is empty");
        }
        try {
            long height = Long.parseLong(req.getParams());
            long result = collection.stream().filter((Person p) -> p.getHeight() == height).count();
            System.out.println("number of elements: " + result);
            return new Response<>(Response.Status.COMPLETED, "", "number of elements: " + result);
        } catch (NumberFormatException e) {
            System.out.println(req.getParams() + " is not a number");
            return new Response<>(Response.Status.FAILURE, req.getParams() + " is not a number");
        }
    }
}
