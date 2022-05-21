package commands; // TODO Fix all

import dto.PersonDto;
import interaction.Request;
import interaction.Response;
import managers.LinkedListCollectionManager;
import models.Person;
import utils.PersonFormatter;
import validators.PersonValidator;

public class AddCommand extends AbstractCommand {
    LinkedListCollectionManager collectionManager;

    public AddCommand(LinkedListCollectionManager collectionManager) {
        super("add", "add a new element to the collection.", "{element}");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request req) {
        PersonDto dto = (PersonDto) req.getBody();
        PersonValidator.checkDtoFields(dto);
        Person person = new Person(
                dto.getName(),
                dto.getCoordinates(),
                dto.getHeight(),
                dto.getWeight(),
                dto.getEyesColor(),
                dto.getHairsColor(),
                dto.getLocation()
        );
        try {
            collectionManager.add(person);
            System.out.println("Person has successfully added");
            return new Response<>(Response.Status.COMPLETED, "", PersonFormatter.format(person) + "Person has successfully added");
        } catch (SecurityException e) {
            return new Response<>(Response.Status.FAILURE, "Person id must be unique");
        }
    }
}
