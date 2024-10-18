package com.kreitek.editor.editors;

import com.kreitek.editor.interfaces.Editor;

public class EditorFactory {
    public Editor getEditor(String typeEditor) {
        switch (typeEditor != null ? typeEditor.toLowerCase() : "") {
            case "json":
                return new JsonEditor();
            case "text":
                return new TextEditor();
            default:
                String choice = EditorPrompt.promptForEditorType();
                return getEditor(choice);
        }
    }
}
