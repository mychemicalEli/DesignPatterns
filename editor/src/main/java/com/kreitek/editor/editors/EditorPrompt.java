package com.kreitek.editor.editors;

import java.util.Scanner;

public class EditorPrompt {
    public static String promptForEditorType() {
        String choice;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Select editor type (text/json): ");
            choice = scanner.nextLine().trim().toLowerCase();
        } while (!choice.equals("text") && !choice.equals("json"));

        return choice;
    }
}
