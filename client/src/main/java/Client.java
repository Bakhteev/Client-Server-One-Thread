import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

import interaction.Request;
import interaction.Response;
import models.*;


public class Client {
    public static void main(String[] args) {
        try {
            Person p = new Person("igor",
                    new Coordinates(10, 10),
                    100L,
                    11.1F,
                    EyesColor.BLUE,
                    HairsColor.BLACK,
                    new Location(100L, 11, 100.F, "moscow"));
            Person p1 = new Person("igor2",
                    new Coordinates(10, 10),
                    100L,
                    11.1F,
                    EyesColor.BLUE,
                    HairsColor.BLACK,
                    new Location(100L, 11, 100.F, "moscow"));
            Person p2 = new Person("igor",
                    new Coordinates(10, 10),
                    100L,
                    11.1F,
                    EyesColor.BLUE,
                    HairsColor.BLACK,
                    new Location(100L, 11, 100.F, "moscow"));

            Person[] superArr = {p, p1, p2};


            SocketChannel socket = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6789));

            ObjectOutputStream writer = new ObjectOutputStream(socket.socket().getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.socket().getInputStream());

//            writer.writeObject(new Request<>("","",superArr));
//            writer.flush();
//            Response<Person[]> res = (Response<Person[]>) reader.readObject();
//            Person[] o = res.getBody();
//            System.out.println(Arrays.toString(o));
//            System.out.println(reader.readUTF());

//            socket.configureBlocking(false);

//            writer.writeUTF(new Scanner(System.in).nextLine());
            while (socket.isConnected()) {
                System.out.println("Type a command:\n");
                writer.writeObject(new Request<>(new Scanner(System.in).nextLine()));
                writer.flush();
                Response<?> res1 = (Response<?>) reader.readObject();

                System.out.println(res1.getStatus().equals(Response.Status.FAILURE) ? res1.getMessage() : res1.getBody());
            }
//            reader.close();
//            writer.close();
//            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
