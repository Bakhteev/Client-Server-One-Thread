//package commands;
// TODO: FIX ALL
// import managers.ConsoleClient;
// import managers.LinkedListCollectionManager;
//import models.Person;
//
//import java.util.ArrayList;
//
//public class RemoveGreaterCommand extends AbstractCommand {
//
//    private LinkedListCollectionManager collectionManager;
//    private PersonMaker personMaker;
//
//    public RemoveGreaterCommand(LinkedListCollectionManager collectionManager) {
//        super("remove_greater", "remove from the collection all elements greater than the given.",
//                "{element}");
//
//        this.collectionManager = collectionManager;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try {
//            if (collectionManager.size() == 0) {
//                throw new IllegalArgumentException("Collection is empty");
//            }
//            if (!argument.isEmpty()) {
//                throw new IllegalArgumentException("Using of command: " + getName());
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
////        PersonMaker maker = new PersonMaker(ConsoleClient.fileMode ? ConsoleClient.getScanners().getLast() : ConsoleClient.scanner);
//        if(ConsoleClient.fileMode) personMaker = new PersonMaker(ConsoleClient.getScanners().getLast());
//        else personMaker = new PersonMaker(ConsoleClient.scanner);
//        Person personToCompare = personMaker.startMaker();
//        ArrayList<Person> listToRemove = new ArrayList<>();
//
//        collectionManager.getCollection().stream()
//                .filter((Person person) -> person.getWeight() > personToCompare.getWeight() && person.getHeight() > personToCompare.getHeight())
//                .forEach(listToRemove::add);
//
//        listToRemove.forEach(collectionManager::deleteObject);
//        return true;
//    }
//}
