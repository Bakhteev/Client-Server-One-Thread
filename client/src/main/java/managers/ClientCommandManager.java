package managers;

import commands.AbstractCommand;
import interaction.Request;
import interaction.Response;
import lombok.Getter;
import managers.exceptions.NoSuchCommandException;
import utils.exceptions.NoReadableFileException;
import workers.ConsoleWorker;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Getter
public class ClientCommandManager {
    LinkedHashMap<String, AbstractCommand> commands = new LinkedHashMap<>();
    static public Console scanner = System.console();
    static public boolean fileMode = false;
    private Deque<String> files = new ArrayDeque<>();
    private static Deque<Scanner> scanners = new ArrayDeque<>();

    private Scanner sc = new Scanner(System.in);



    public Deque<Scanner> getScanners() {
        return scanners;
    }


    public void addCommands(AbstractCommand[] commands) {
        for (AbstractCommand command : commands) {
            this.commands.put(command.getName(), command);
        }
    }


    public void startInteractiveMode() {
//            try {
//                Console console = System.console();
//                String command = console.readLine("\nEnter the command\n$ ").trim();
//                executeCommand(command);
//            } catch (NullPointerException e) {
//                startInteractiveMode();
//            }
        while (true) {
            String scstr;
            ConsoleWorker.println("Enter Command: ");
            ConsoleWorker.printSymbol(true);
//        Scanner sc = new Scanner(System.in);
            scstr = sc.nextLine().trim();
            executeCommand(scstr);
        }
    }

    public static void setFileMode(boolean fileMode) {
        ClientCommandManager.fileMode = fileMode;
    }

    public boolean executeCommand(String command) {
        try {
        String[] userCommand = command.split(" ", 2);
            if (!commands.containsKey(userCommand[0])) {
                throw new NoSuchCommandException("No such command: " + userCommand[0]);
            }
        return commands.get(userCommand[0]).execute(userCommand.length > 1 ? userCommand[1] : "");
        }
        catch (NoSuchCommandException e) {
            ConsoleWorker.printError(e.getMessage());
            return false;
        }
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
                executeCommand(command.replace("$ ", ""));
            }
        }
        System.out.println("\nExecuting of " + files.getLast() + " has successfully finished\n");
        scanners.removeLast();
        files.removeLast();
        setFileMode(false);
        return true;
    }
}
