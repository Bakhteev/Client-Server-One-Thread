//package commands;
//
//import managers.LinkedListCollectionManager;
//import utils.FileWorker;
//import utils.JSONParser;
//
//import java.io.IOException;
//
//// TODO: ADD LOGGER
//
//public class SaveCommand extends AbstractCommand {
//    FileWorker fileWorker;
//    JSONParser jsonParser;
//    LinkedListCollectionManager collectionManager;
//
//    public SaveCommand(FileWorker fileWorker, JSONParser jsonParser, LinkedListCollectionManager collectionManager) {
//        super("save", "saves collection data to file", "");
//        this.fileWorker = fileWorker;
//        this.jsonParser = jsonParser;
//        this.collectionManager = collectionManager;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try {
//            if (!argument.isEmpty()) {
//                throw new IllegalArgumentException("Using of command: " + getName());
//            }
//            collectionManager.setLastSaveTime();
//            fileWorker.saveFile(jsonParser.toJSON(collectionManager.getCollection()));
//            System.out.println("Collection has successfully saved");
//        } catch (IOException | IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//}
