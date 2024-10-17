package com.kreitek.editor;

import com.kreitek.editor.commands.CommandFactory;
import com.kreitek.editor.exceptions.BadCommandException;
import com.kreitek.editor.exceptions.ExitException;
import com.kreitek.editor.memento.EditorCaretaker;
import com.kreitek.editor.memento.Memento;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleEditor implements Editor {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";


    private final ArrayList<String> documentLines = new ArrayList<String>();
    private final EditorCaretaker caretaker = new EditorCaretaker();
    private final CommandFactory commandFactory = new CommandFactory(caretaker);


    @Override
    public void run() {
        boolean exit = false;
        while (!exit) {
            String commandLine = waitForNewCommand();
            Command command;
            try {
                if (!commandLine.equals("undo")) {
                    caretaker.push(getState());
                }
                command = commandFactory.getCommand(commandLine);
                command.execute(documentLines);


            } catch (BadCommandException e) {
                printErrorToConsole("Bad command");
            } catch (ExitException e) {
                exit = true;
            }
            showDocumentLines(documentLines);
            showHelp();
        }
    }

    private void showDocumentLines(ArrayList<String> textLines) {
        if (textLines.size() > 0) {
            setTextColor(TEXT_YELLOW);
            printLnToConsole("START DOCUMENT ==>");
            for (int index = 0; index < textLines.size(); index++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                stringBuilder.append(index);
                stringBuilder.append("] ");
                stringBuilder.append(textLines.get(index));
                printLnToConsole(stringBuilder.toString());
            }
            printLnToConsole("<== END DOCUMENT");
            setTextColor(TEXT_RESET);
        }
    }

    private String waitForNewCommand() {
        printToConsole("Enter a command : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void showHelp() {
        printLnToConsole("To add new line -> a \"your text\"");
        printLnToConsole("To update line  -> u [line number] \"your text\"");
        printLnToConsole("To delete line  -> d [line number]");
        printLnToConsole("To undo changes -> undo ");
    }

    private void printErrorToConsole(String message) {
        setTextColor(TEXT_RED);
        printToConsole(message);
        setTextColor(TEXT_RESET);
    }

    private void setTextColor(String color) {
        System.out.println(color);
    }

    private void printLnToConsole(String message) {
        System.out.println(message);
    }

    private void printToConsole(String message) {
        System.out.print(message);
    }

    public Memento getState() {
        return new Memento(new ArrayList<>(documentLines));
    }

}
