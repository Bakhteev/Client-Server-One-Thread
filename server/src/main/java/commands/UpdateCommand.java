package commands;

import dto.PersonDto;
import exceptions.PersonNotFoundException;
import interaction.Request;
import interaction.Response;
import managers.LinkedListCollectionManager;
import models.Person;
import utils.PersonFormatter;

import java.util.Arrays;
import java.util.Locale;

//TODO: FIX ALL ADD LOGGER
public class UpdateCommand extends AbstractCommand {
    private LinkedListCollectionManager collectionManager;

    public enum Fields {
        NAME,
        COORDINATES,
        HEIGHT,
        WEIGHT,
        HAIRSCOLOR,
        LOCATION;

        static public void showFieldList() {
            for (int i = 0; i < values().length - 1; i++) {
                System.out.println(values()[i].toString().toLowerCase(Locale.ROOT));
            }
        }
    }

    public UpdateCommand(LinkedListCollectionManager collectionManager) {
        super("update", "update the value of the collection element whose id is equal to the given one.",
                "id {element}");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request req) {
        try {
            if (req.getParams() == null) {
                throw new IllegalArgumentException("Using of command :" + getName() + " " + getParameters());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
//        if (ConsoleClient.fileMode) {
//            maker = new PersonMaker(ConsoleClient.getScanners().getLast());
//        } else
//            maker = new PersonMaker(ConsoleClient.scanner);
        try {
            Person personToUpdate = collectionManager.getElementById(Integer.parseInt(req.getParams()));
            if (personToUpdate == null) {
                throw new PersonNotFoundException("Person with id: " + req.getParams() + "wasn't found");
            }
//            if (!ConsoleClient.fileMode) {
//                Fields.showFieldList();
//                System.out.println("Choose param's names: ");
//                System.out.print("> ");
//            }
//            String[] params = ConsoleClient.scanner.readLine().split(",");
//            Arrays.stream(params).forEach(param -> maker.setPersonByFields(personToUpdate, param.replace(">", "").trim()));
            collectionManager.update(personToUpdate, (PersonDto) req.getBody());
            System.out.println(personToUpdate);
            return new Response<>(Response.Status.COMPLETED, "", PersonFormatter.format(personToUpdate));
        } catch (NumberFormatException e) {
            System.out.println("Wrong id Format: " + req.getParams());
            return new Response<>(Response.Status.FAILURE, "Wrong id Format: " + req.getParams());
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
    }
}
