package managers;

import commands.AbstractCommand;
import exceptions.NoSuchCommandException;

import java.io.Console;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ConsoleClient {

    LinkedHashMap<String, AbstractCommand> commands = new LinkedHashMap<>();
    static public Console scanner = System.console();
    static public boolean fileMode = false;
    private Deque<String> files = new ArrayDeque<>();
    private static Deque<Scanner> scanners = new ArrayDeque<>();

    public void addCommands(AbstractCommand[] commands) {
        for (AbstractCommand command : commands) {
            this.commands.put(command.getName(), command);
        }
    }

    public LinkedHashMap<String, AbstractCommand> getCommands() {
        return commands;
    }

//    public boolean executeCommand(String command) {
//        try {
//            String[] userCommand = command.split(" ", 2);
//            if (!commands.containsKey(userCommand[0])) {
//                throw new NoSuchCommandException("No such command " + userCommand[0]);
//            }
//            return commands.get(userCommand[0]).execute(userCommand.length > 1 ? userCommand[1] : "");
//        } catch (NoSuchCommandException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }

//    public void startInteractiveMode() {
//        while (true) {
//            try {
//                Console console = System.console();
//                String command = console.readLine("\nEnter the command\n$ ").trim();
//                executeCommand(command);
//            } catch (NullPointerException e) {
//                startInteractiveMode();
//            }
//        }
//    }

    public static void setFileMode(boolean fileMode) {
        ConsoleClient.fileMode = fileMode;
    }

    static public void printLn(String argument) {
        System.out.println(argument);
    }

    static public void printParam(String argument) {
        System.out.println("> " + argument);
    }

    public Deque<String> getFiles() {
        return files;
    }

    public static Deque<Scanner> getScanners() {
        return scanners;
    }
}
