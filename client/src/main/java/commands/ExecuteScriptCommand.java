package commands;

import managers.ClientCommandManager;
import managers.exceptions.ScriptLoopingException;
import utils.exceptions.NoReadableFileException;
import workers.ConsoleWorker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScriptCommand extends AbstractCommand {


    ClientCommandManager commandManager;

    public ExecuteScriptCommand(ClientCommandManager commandManager) {
        super("execute_script", "read and execute script from file setup.", "file_name");
        this.commandManager = commandManager;
    }

    @Override
    public boolean execute(String argument) {
        ClientCommandManager.setFileMode(true);
        try {
            File file = new File(argument);
            if (argument.isEmpty())
                throw new IllegalArgumentException("Using of command: " + getName() + " " + getParameters());
            if (commandManager.getFiles().contains(file.getAbsolutePath()))
                throw new ScriptLoopingException("Scripts can't be recursive!!!");
            else {
                if (!file.exists()) {
                    throw new FileNotFoundException("File not found.");
                }
                if (!file.canRead()) {
                    throw new NoReadableFileException("File can't be read. Please change access rights");
                }
                commandManager.getScanners().add(new Scanner(file));
                commandManager.getFiles().add(file.getAbsolutePath());
            }
        } catch (IllegalArgumentException | FileNotFoundException | NoReadableFileException e) {
            ConsoleWorker.printError(e.getMessage());
            return false;
        } catch (ScriptLoopingException e) {
            ConsoleWorker.printError(e.getMessage());
            commandManager.startInteractiveMode();
            return false;
        }
        while (commandManager.getScanners().getLast().hasNextLine()) {
            String command = commandManager.getScanners().getLast().nextLine();
            if (command.startsWith("$")) {
                commandManager.executeCommand(command.replace("$ ", ""));
            }

        }
        System.out.println("\nExecuting of " + commandManager.getFiles().getLast() + " has successfully finished\n");
        commandManager.getScanners().removeLast();
        commandManager.getFiles().removeLast();
        ClientCommandManager.setFileMode(false);
        return true;
    }
}
