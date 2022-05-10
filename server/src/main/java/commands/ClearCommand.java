//package commands;
//
//import managers.LinkedListCollectionManager;
//
////TODO: ADD LOGGER
//public class ClearCommand extends AbstractCommand {
//    LinkedListCollectionManager collectionManager;
//
//    public ClearCommand(LinkedListCollectionManager collectionManager) {
//        super("clear", "clears collection", "");
//        this.collectionManager = collectionManager;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try {
//            if (!argument.isEmpty()) throw new IllegalArgumentException("Using of command: " + getName());
//        }catch (IllegalArgumentException e ){
//            System.out.println(e.getMessage());
//            return false;
//        }
//
//        if (collectionManager.size() == 0) {
//            System.out.println("Collection is empty");
//            return false;
//        } else
//            collectionManager.clearCollection();
//            System.out.println("Collection has successfully cleared");
//        return true;
//    }
//}
