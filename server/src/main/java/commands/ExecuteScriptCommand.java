//package commands; // TODO: Think how to do this, ADD LOGGER
//
//import exceptions.ScriptLoopingException;
//import managers.ConsoleClient;
//import utils.exceptions.NoReadableFileException;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//public class ExecuteScriptCommand extends AbstractCommand {
//    private ConsoleClient consoleClient;
//
//    public ExecuteScriptCommand(ConsoleClient consoleClient) {
//        super("execute_script", "read and execute script from file setup.", "file_name");
//        this.consoleClient = consoleClient;
//    }
//
//    @Override
//    public boolean execute(String argument) {
//        ConsoleClient.setFileMode(true);
//        try {
//            File file = new File(argument);
//            if (argument.isEmpty())
//                throw new IllegalArgumentException("Using of command: " + getName() + " " + getParameters());
//            if (consoleClient.getFiles().contains(file.getAbsolutePath()))
//                throw new ScriptLoopingException("Scripts can't be recursive!!!");
//            else {
//                if (!file.exists()){
//                    throw new FileNotFoundException("File not found.");
//                }
//                if (!file.canRead()) {
//                    throw new NoReadableFileException("File can't be read. Please change access rights");
//                }
//                ConsoleClient.getScanners().add(new Scanner(file));
//                consoleClient.getFiles().add(file.getAbsolutePath());
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
//}
