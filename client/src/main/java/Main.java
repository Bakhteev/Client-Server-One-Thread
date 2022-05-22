import client.Client;

public class Main {
    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("\rBetter use command \u001B[32mexit\u001B[0m to close client");
                System.out.println("Good bye");
            }
        });

        Client client = new Client(args[0], Integer.parseInt(args[1]));
        if (client.connect()) {
            client.setup();
            client.run();
            client.close();
        }


    }
}
