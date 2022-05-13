package commands;

import interaction.Request;
import interaction.Response;
import managers.LinkedListCollectionManager;

//TODO: ADD LOGGER
public class ClearCommand extends AbstractCommand {
    LinkedListCollectionManager collectionManager;

    public ClearCommand(LinkedListCollectionManager collectionManager) {
        super("clear", "clears collection", "");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request req) {
        try {
            if (req.getParams() != null) {
                throw new IllegalArgumentException("Using of command: " + getName());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response(Response.Status.FAILURE, e.getMessage());
        }

        if (collectionManager.size() == 0) {
            System.out.println("Collection is empty");
            return new Response(Response.Status.FAILURE, "Collection is empty");
        } else {
            collectionManager.clearCollection();
            System.out.println("Collection has successfully cleared");
            return new Response(Response.Status.COMPLETED, "", "Collection has successfully cleared");
        }
    }
}
