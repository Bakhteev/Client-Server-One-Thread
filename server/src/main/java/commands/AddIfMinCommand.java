package commands; // TODO FIX ALL

import comparators.MinPersonComparator;
import dto.PersonDto;
import interaction.Request;
import interaction.Response;
import managers.LinkedListCollectionManager;
import models.Person;

import java.util.Optional;

public class AddIfMinCommand extends AbstractCommand {

    private LinkedListCollectionManager collectionManager;

    public AddIfMinCommand(LinkedListCollectionManager collectionManager) {
        super("add_if_min", "add a new element to the collection if its value is less than the smallest " +
                "element in this collection.", "{element}");
        this.collectionManager = collectionManager;
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
        try {
            if (collectionManager.size() == 0) {
                System.out.println("collection is empty");
                return new Response<>(Response.Status.FAILURE, "Collection is empty");
            }
            Optional<Person> minPerson = collectionManager.getCollection().stream().min(new MinPersonComparator());
            PersonDto dto = (PersonDto) req.getBody();
            Person candidate = new Person(
                    dto.getName(),
                    dto.getCoordinates(),
                    dto.getHeight(),
                    dto.getWeight(),
                    dto.getEyesColor(),
                    dto.getHairsColor(),
                    dto.getLocation()
            );
            if (new MinPersonComparator().compare(minPerson.get(), candidate) > 0) {
                collectionManager.add(candidate);
                System.out.println("Person has successfully added");
                return new Response<>(Response.Status.COMPLETED, "", "Person has successfully added");
            }
            return new Response<>(Response.Status.COMPLETED, "", "Person wasn't added");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
    }
}
