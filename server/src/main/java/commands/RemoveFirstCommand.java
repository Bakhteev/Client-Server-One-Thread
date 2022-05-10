//package commands;
//// TODO: ADD LOGGER
//import managers.ConsoleClient;
//import managers.LinkedListCollectionManager;
//
//public class RemoveFirstCommand extends AbstractCommand {
//    LinkedListCollectionManager collectionManager;
//
//    public RemoveFirstCommand(LinkedListCollectionManager collectionManager) {
//        super("remove_first", "remove first element of collection", "");
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
//        collectionManager.removeFirstElement();
//        ConsoleClient.printLn("First element was successfully deleted");
//        return true;
//    }
//}
