//package commands;
//
//import models.Person;
//import utils.PersonFormatter;
//
//import java.util.LinkedList;
//
//
//// TODO: ADD LOGGER
//public class ShowCommand extends AbstractCommand {
//    LinkedList<Person> collection;
//
//    public ShowCommand(LinkedList<Person> collection) {
//        super("show", "print to standard output all elements of the collection in string representation.", "");
//        this.collection = collection;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try {
//            if (!argument.isEmpty()) {
//                throw new IllegalArgumentException("Using of command: " + getName());
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        if (collection.size() == 0) {
//            System.out.println("Collection is empty");
//            return true;
//        }
//        System.out.println(PersonFormatter.formatCollection(collection));
//        return true;
//    }
//}
