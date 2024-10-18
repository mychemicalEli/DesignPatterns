package com.kreitek.editor.editors;

import java.util.ArrayList;

public class JsonEditor extends PrintDocumentLines {

    @Override
    public void showDocumentLines(ArrayList<String> textLines) {
        if (textLines.size() > 0) {
            System.out.println("{");
            System.out.println("  \"doc\": [");

            for (int index = 0; index < textLines.size(); index++) {
                String line = textLines.get(index);
                System.out.print(" {");
                System.out.print("\"line\": \"" + index + "\",");
                System.out.print("\"text\": \"" + escapeJson(line) + "\"");
                System.out.println(" }" + (index < textLines.size() - 1 ? "," : ""));
            }

            System.out.println(" ]");
            System.out.println("}");
        } else {
            System.out.println("{ \"doc\": [] }");
        }
    }

    private String escapeJson(String text) {
        return text.replace("\"", "\\\"");
    }
}







