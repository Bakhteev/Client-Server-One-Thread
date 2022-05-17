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
//        try {
////            if (!argument.isEmpty())
////                throw new IllegalArgumentException("Using of command add: " + getName());
//
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        if (ConsoleClient.fileMode)
//            maker = new PersonMaker(ConsoleClient.getScanners().getLast());
//        else
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
//            maker = new PersonMaker(ConsoleClient.scanner);
        try {
            collectionManager.add(person);
            System.out.println("Person has successfully added");
            return new Response<>(Response.Status.COMPLETED, "Person has successfully added", PersonFormatter.format(person));
        } catch (SecurityException e) {
            return new Response<>(Response.Status.FAILURE, "Person id must be unique");
        }
//        return true;
    }
}
