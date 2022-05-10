//package commands;
//
////TODO: ADD LOGGER
//
//import models.Person;
//
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Map;
//
//public class PrintUniqueLocationCommand extends AbstractCommand {
//
//    LinkedList<Person> collection;
//
//    public PrintUniqueLocationCommand(LinkedList<Person> collection) {
//        super("print_unique_location", "display the unique values of the location field of all elements in the collection.", "");
//        this.collection = collection;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        try{
//            if (collection.size() == 0) {
//                throw new IllegalStateException("Collection is empty");
//            }
//            if (!argument.isEmpty())
//                throw new IllegalArgumentException("Using of command: " + getName());
//        }catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//            return false;
//        }
//        HashMap<String, Integer> map =countLocations();
//        String locationNameToShow = "";
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            if (entry.getValue() == 1) {
//                locationNameToShow = entry.getKey();
//                System.out.println("Unique location is " + entry.getKey());
//            }
//        }
//        if (locationNameToShow.isEmpty())
//            System.out.println("No unique Location");
//        return true;
//    }
//
//    private HashMap<String, Integer> countLocations() {
//        HashMap<String, Integer> map = new HashMap<>();
//        for (Person element : collection) {
//            String locationName = element.getLocation().getName();
//            if (map.containsKey(locationName))
//                map.put(locationName, map.get(locationName) + 1);
//            else
//                map.put(locationName, 1);
//        }
//        return map;
//    }
//}
