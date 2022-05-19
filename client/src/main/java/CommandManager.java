import interaction.Request;
import interaction.Response;
import lombok.Getter;
import utils.exceptions.NoReadableFileException;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Getter
public class CommandManager {
    static public Console scanner = System.console();
    static public boolean fileMode = false;
    private Deque<String> files = new ArrayDeque<>();
    private static Deque<Scanner> scanners = new ArrayDeque<>();

    private Scanner sc = new Scanner(System.in);
    public Deque<Scanner> getScanners() {
        return scanners;
    }



    public Request startInteractiveMode() {
//            try {
//                Console console = System.console();
//                String command = console.readLine("\nEnter the command\n$ ").trim();
//                executeCommand(command);
//            } catch (NullPointerException e) {
//                startInteractiveMode();
//            }
        String[] scstr;
        ConsoleWorker.println("Enter Command: ");
        ConsoleWorker.printSymbol(true);
//        Scanner sc = new Scanner(System.in);
        scstr = sc.nextLine().trim().split(" ", 2);
        return executeCommand(scstr);
    }

    public static void setFileMode(boolean fileMode) {
        CommandManager.fileMode = fileMode;
    }

    public Request executeCommand(String[] command) {
        return new Request<>(command[0], command.length > 1 ? command[1] : null);

//        try {
////            if (!commands.containsKey(req.getCommand())) {
////                throw new NoSuchCommandException("No such command " + req.getCommand());
////            }
//            commands.get(req.getCommand()).execute(req);
//        } catch (NoSuchCommandException e) {
//            System.out.println(e.getMessage() + " " + "Command manager");
//            return new Response<>(Response.Status.FAILURE, e.getMessage());
//        }
    }

    public boolean executeScript(String argument) {
        setFileMode(true);
        try {
            if (argument.isEmpty()) {
                throw new IllegalArgumentException("Using of command: execute_script file_name");
            }
            File file = new File(argument);
            if (files.contains(file.getAbsolutePath())) {
//                throw new ScriptLoopingException("Scripts can't be recursive!!!");
            } else {
                if (!file.exists()) {
                    throw new FileNotFoundException("File not found.");
                }
                if (!file.canRead()) {
                    throw new NoReadableFileException("File can't be read. Please change access rights");
                }
                scanners.add(new Scanner(file));
                files.add(file.getAbsolutePath());
            }
        } catch (IllegalArgumentException | FileNotFoundException | NoReadableFileException e) {
            System.out.println(e.getMessage());
//            return false;
        }
//        catch (ScriptLoopingException e) {
//            System.out.println(e.getMessage());
//            consoleClient.startInteractiveMode();
//            return false;
//        }
        while (scanners.getLast().hasNextLine()) {
            String command = scanners.getLast().nextLine();
            if (command.startsWith("$")) {
                executeCommand(command.replace("$ ", "").split(" "));
            }
        }
        System.out.println("\nExecuting of " + files.getLast() + " has successfully finished\n");
        scanners.removeLast();
        files.removeLast();
        setFileMode(false);
        return true;
    }
}
