import client.Client;

public class Main {
    public static void main(String[] args) {
        Client client = new Client(args[0], Integer.parseInt(args[1]));
        if (client.connect()) {
            client.setup();
            client.run();
            client.close();
        }
    }
}
