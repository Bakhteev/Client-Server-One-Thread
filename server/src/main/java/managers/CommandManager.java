package managers;

import commands.AbstractCommand;
import commands.AddCommand;
import exceptions.NoSuchCommandException;
import interaction.Request;
import interaction.Response;

import java.io.Console;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class CommandManager{
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

    public Response executeCommand(Request req) {
        try {
            if (!commands.containsKey(req.getCommand())) {
                throw new NoSuchCommandException("No such command " + req.getCommand());
            }
            return commands.get(req.getCommand()).execute(req);
        } catch (NoSuchCommandException e) {
            System.out.println(e.getMessage() + " " + "Command manager");
            return new Response<>(Response.Status.FAILURE, e.getMessage());
        }
    }

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
        CommandManager.fileMode = fileMode;
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
