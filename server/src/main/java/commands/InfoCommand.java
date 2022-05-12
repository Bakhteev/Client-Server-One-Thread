package commands;

// TODO: ADD LOGGER

import interaction.Request;
import interaction.Response;
import managers.LinkedListCollectionManager;

public class InfoCommand extends AbstractCommand {
    LinkedListCollectionManager collectionManager;

    public InfoCommand(LinkedListCollectionManager collectionManager) {
        super("info", "print information about the collection to standard output.", "");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request req) {
        try {
            if (req.getParams() != null)
                throw new IllegalArgumentException("Using of command: " + getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new Response(Response.Status.FAILURE, e.getMessage());
        }
        String body = "Collection initialization time: " + collectionManager.getInitializationTime() + "\n"
                + "Collection last save time: " + (collectionManager.getLastSaveTime() == null ? "not saved yet" :
                collectionManager.getLastSaveTime()) + "\n"
                + "Collection type: " + collectionManager.getType() + "\n"
                + "Collection size: " + collectionManager.size() + "\n";
        return new Response<>(Response.Status.COMPLETED, "", body);
    }
}
