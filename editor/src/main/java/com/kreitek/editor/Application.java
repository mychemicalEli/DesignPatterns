package com.kreitek.editor;

import com.kreitek.editor.editors.EditorFactory;
import com.kreitek.editor.editors.Editor;

public class Application {

    public static void main(String[] args) {
        EditorFactory editorFactory = new EditorFactory();
        String editorType = args.length > 0 ? args[0] : "";
        Editor editor = editorFactory.getEditor(editorType);
        editor.run();
    }
}