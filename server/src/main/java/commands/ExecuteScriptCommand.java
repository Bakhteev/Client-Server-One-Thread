//package commands; // TODO: Think how to do this, ADD LOGGER
//
//import exceptions.ScriptLoopingException;
//import interaction.Request;
//import interaction.Response;
//import jdk.internal.util.xml.impl.Input;
//import managers.CommandManager;
//import managers.ConsoleClient;
//import utils.exceptions.NoReadableFileException;
//
//import java.io.*;
//import java.net.URL;
//import java.nio.file.AccessDeniedException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.regex.Pattern;
//
//public class ExecuteScriptCommand extends AbstractCommand {
//    private CommandManager commandManager;
//
//    public ExecuteScriptCommand(CommandManager commandManager) {
//        super("execute_script", "read and execute script from file setup.", "file_name");
//        this.commandManager = commandManager;
//    }
//
//    @Override
//    public Response execute(Request req) {
//        CommandManager.setFileMode(true);
//        try {
//            File file = new File(req.getParams());
////            if (argument.isEmpty())
////                throw new IllegalArgumentException("Using of command: " + getName() + " " + getParameters());
//            if (commandManager.getFiles().contains(file.getAbsolutePath())) {
//                throw new ScriptLoopingException("Scripts can't be recursive!!!");
//            }
//            else {
//                if (!file.exists()){
//                    throw new FileNotFoundException("File not found.");
//                }
//                if (!file.canRead()) {
//                    throw new NoReadableFileException("File can't be read. Please change access rights");
//                }
//                CommandManager.getScanners().add(new Scanner(file));
//                commandManager.getFiles().add(file.getAbsolutePath());
//            }
//        }catch(IllegalArgumentException | FileNotFoundException | NoReadableFileException e){
//            System.out.println(e.getMessage());
//            return false;
//        }
//        catch (ScriptLoopingException e) {
//            System.out.println(e.getMessage());
//            consoleClient.startInteractiveMode();
//            return false;
//        }
//        while (ConsoleClient.getScanners().getLast().hasNextLine()) {
//            String command = ConsoleClient.getScanners().getLast().nextLine();
//            if (command.startsWith("$"))
//                consoleClient.executeCommand(command.replace("$ ", ""));
//
//        }
//        System.out.println("\nExecuting of " + consoleClient.getFiles().getLast() + " has successfully finished\n");
//        ConsoleClient.getScanners().removeLast();
//        consoleClient.getFiles().removeLast();
//        ConsoleClient.setFileMode(false);
//        return true;
//    }
//
//}
