//package commands; // TODO Fix all
//
//import managers.ConsoleClient;
//import managers.LinkedListCollectionManager;
//
//public class AddCommand extends AbstractCommand {
//    LinkedListCollectionManager collectionManager;
//    PersonMaker maker;
//
//    public AddCommand(LinkedListCollectionManager collectionManager) {
//        super("add", "add a new element to the collection.", "{element}");
//        this.collectionManager = collectionManager;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try {
//            if (!argument.isEmpty())
//                throw new IllegalArgumentException("Using of command add: " + getName());
//
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        if (ConsoleClient.fileMode)
//            maker = new PersonMaker(ConsoleClient.getScanners().getLast());
//        else
//            maker = new PersonMaker(ConsoleClient.scanner);
//        collectionManager.add(maker.startMaker());
//        System.out.println("Person has successfully added");
//        return true;
//    }
//}
