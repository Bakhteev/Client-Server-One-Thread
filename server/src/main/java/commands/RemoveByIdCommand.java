package commands;

// TODO: ADD LOGGER
import interaction.Request;
import interaction.Response;
import managers.LinkedListCollectionManager;

public class RemoveByIdCommand extends AbstractCommand {
    LinkedListCollectionManager collectionManager;

    public RemoveByIdCommand(LinkedListCollectionManager collectionManager) {
        super("remove_by_id", "remove element from collection by its id.", "id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() == null) {
                throw new IllegalArgumentException("Using of command :" + getName() + " " + getParameters());
            }
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
        try {
            collectionManager.deleteById(Integer.parseInt(req.getParams()));
            System.out.println("Element was successfully deleted");
            return new Response<>(Response.Status.COMPLETED, "", "Element was successfully deleted");
        } catch (NumberFormatException e) {
            System.out.println("Wrong id format");
            return new Response<>(Response.Status.FAILURE, "Wrong id format");
        }
    }
}
