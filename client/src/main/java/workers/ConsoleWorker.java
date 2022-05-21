package workers;

import java.util.Scanner;

public class ConsoleWorker {

    public static void println(String string){
        System.out.println(string);
    }

    public static void printSymbol(boolean command){
        System.out.print(command ? "$ " : "> ");
    }

    public static void print(String string){
        System.out.print(string);
    }

    public static String readCommand(Scanner scanner){
        return scanner.next().replace("$ ", "");
    }

    public static String readWord(Scanner scanner){
        return scanner.next().replace("> ", "");
    }

    public static void printParam(String str){
        System.out.println("> " + str);
    }

    public static void printError(String string){
        System.out.println("\u001B[31m" + string + "\u001B[0m");
    }
}

