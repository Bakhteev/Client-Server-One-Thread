package commands;
// TODO: ADD LOGGER

import interaction.Request;
import interaction.Response;
import managers.LinkedListCollectionManager;

public class RemoveFirstCommand extends AbstractCommand {
    LinkedListCollectionManager collectionManager;

    public RemoveFirstCommand(LinkedListCollectionManager collectionManager) {
        super("remove_first", "remove first element of collection", "");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response<?> execute(Request req) {
        try {
            if (req.getParams() != null) {
                throw new IllegalArgumentException("Using of command: " + getName());
            }
            if (collectionManager.size() == 0) {
                throw new IllegalArgumentException("Collection is empty");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
        collectionManager.removeFirstElement();
        System.out.println("First element was successfully deleted");
        return new Response<>(Response.Status.COMPLETED, "", "First element was successfully deleted");
    }
}
