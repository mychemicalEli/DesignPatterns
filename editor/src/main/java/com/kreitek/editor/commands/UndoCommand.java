package com.kreitek.editor.commands;

import com.kreitek.editor.Command;
import com.kreitek.editor.memento.EditorCaretaker;
import com.kreitek.editor.memento.Memento;

import java.util.ArrayList;

public class UndoCommand implements Command {
    private final EditorCaretaker caretaker;


    public UndoCommand(EditorCaretaker caretaker) {
        this.caretaker = caretaker;
    }


    @Override
    public void execute(ArrayList<String> documentLines) {
        Memento memento = caretaker.pop();

        if (memento != null) {
            documentLines.clear();
            documentLines.addAll(memento.getState());
            System.out.println("Estado restaurado: " + documentLines);
        } else {
            System.out.println("No hay operaciones para deshacer.");
        }
    }
}
