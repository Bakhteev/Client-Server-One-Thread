//package commands;
//
//// TODO: ADD LOGGER
//import managers.LinkedListCollectionManager;
//
//public class RemoveByIdCommand extends AbstractCommand {
//    LinkedListCollectionManager collectionManager;
//
//    public RemoveByIdCommand(LinkedListCollectionManager collectionManager) {
//        super("remove_by_id", "remove element from collection by its id.", "id");
//        this.collectionManager = collectionManager;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try {
//            if (argument.isEmpty())
//                throw new IllegalArgumentException("Using of command :" + getName() + " " + getParameters());
//            collectionManager.deleteById(Integer.parseInt(argument));
//            System.out.println("Element was successfully deleted");
//            return true;
//        } catch (NumberFormatException e) {
//            System.out.println("Wrong id format");
//            return false;
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
//}
