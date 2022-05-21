package commands;
 // TODO: FIX ALL
 import dto.PersonDto;
 import interaction.Request;
 import interaction.Response;
 import managers.LinkedListCollectionManager;
import models.Person;

import java.util.ArrayList;

public class RemoveGreaterCommand extends AbstractCommand {

    private LinkedListCollectionManager collectionManager;

    public RemoveGreaterCommand(LinkedListCollectionManager collectionManager) {
        super("remove_greater", "remove from the collection all elements greater than the given.",
                "{element}");

        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request req) {
        try {
            if (collectionManager.size() == 0) {
                throw new IllegalArgumentException("Collection is empty");
            }
            if (req.getParams() != null) {
                throw new IllegalArgumentException("Using of command: " + getName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
        PersonDto dto = (PersonDto) req.getBody();
        Person personToCompare = new Person(
                dto.getName(),
                dto.getCoordinates(),
                dto.getHeight(),
                dto.getWeight(),
                dto.getEyesColor(),
                dto.getHairsColor(),
                dto.getLocation()
        );
        ArrayList<Person> listToRemove = new ArrayList<>();

        collectionManager.getCollection().stream()
                .filter((Person person) -> person.getWeight() > personToCompare.getWeight() && person.getHeight() > personToCompare.getHeight())
                .forEach(listToRemove::add);

        listToRemove.forEach(collectionManager::deleteObject);
        return new Response<>(Response.Status.COMPLETED, "", "Elements was successfully deleted");
    }
}
