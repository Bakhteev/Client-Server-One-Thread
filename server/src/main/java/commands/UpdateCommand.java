//package commands;
//
//import exceptions.PersonNotFoundException;
//import managers.ConsoleClient;
//import managers.LinkedListCollectionManager;
//import models.Person;
//
//import java.util.Arrays;
//import java.util.Locale;
// //TODO: FIX ALL ADD LOGGER
//public class UpdateCommand extends AbstractCommand {
//    private LinkedListCollectionManager collectionManager;
//    private PersonMaker maker;
//
//    public enum Fields {
//        NAME,
//        COORDINATES,
//        HEIGHT,
//        WEIGHT,
//        HAIRSCOLOR,
//        LOCATION;
//
//        static public void showFieldList() {
//            for (int i = 0; i < values().length - 1; i++) {
//                System.out.println(values()[i].toString().toLowerCase(Locale.ROOT));
//            }
//        }
//    }
//
//    public UpdateCommand(LinkedListCollectionManager collectionManager) {
//        super("update", "update the value of the collection element whose id is equal to the given one.",
//                "id {element}");
//        this.collectionManager = collectionManager;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try {
//            if (argument.isEmpty())
//                throw new IllegalArgumentException("Using of command :" + getName() + " " + getParameters());
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        if (ConsoleClient.fileMode) {
//            maker = new PersonMaker(ConsoleClient.getScanners().getLast());
//        } else
//            maker = new PersonMaker(ConsoleClient.scanner);
//        try {
//            Person personToUpdate = collectionManager.getElementById(Integer.parseInt(argument));
//            if (personToUpdate == null) {
//                throw new PersonNotFoundException("Person with id: " + argument + "wasn't found");
//            }
//            if (!ConsoleClient.fileMode) {
//                Fields.showFieldList();
//                System.out.println("Choose param's names: ");
//                System.out.print("> ");
//            }
//            String[] params = ConsoleClient.scanner.readLine().split(",");
//            Arrays.stream(params).forEach(param -> maker.setPersonByFields(personToUpdate, param.replace(">", "").trim()));
//            System.out.println(personToUpdate);
//            return true;
//        } catch (NumberFormatException e) {
//            System.out.println("Wrong id Format: " + argument);
//            return false;
//        } catch (PersonNotFoundException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
//}
