//package commands; // TODO FIX ALL
//
//import comparators.MinPersonComparator;
//import managers.ConsoleClient;
//import managers.LinkedListCollectionManager;
//import models.Person;
//
//import java.util.Optional;
//
//public class AddIfMinCommand extends AbstractCommand {
//
//    private LinkedListCollectionManager collectionManager;
//    private PersonMaker maker;
//
//    public AddIfMinCommand(LinkedListCollectionManager collectionManager) {
//        super("add_if_min", "add a new element to the collection if its value is less than the smallest " +
//                "element in this collection.", "{element}");
//        this.collectionManager = collectionManager;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try {
//            if (!argument.isEmpty()) {
//                throw new IllegalArgumentException("Using of command: " + getName());
//
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        if(ConsoleClient.fileMode) maker = new PersonMaker(ConsoleClient.getScanners().getLast());
//        else maker = new PersonMaker(ConsoleClient.scanner);
//        try {
//            if (collectionManager.size() == 0) {
//                System.out.println("collection is empty");
//                return false;
//            }
//            Optional<Person> minPerson = collectionManager.getCollection().stream().min(new MinPersonComparator());
//            Person candidate = maker.startMaker();
//            if (new MinPersonComparator().compare(minPerson.get(), candidate) > 0) {
//                collectionManager.add(candidate);
//                System.out.println("Person has successfully added");
//            }
//            return true;
//        } catch (NullPointerException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
//}
