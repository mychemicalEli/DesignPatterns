package com.kreitek.editor.memento;

import java.util.ArrayList;
import java.util.List;

public class EditorCaretaker {
    private final List<Memento> history = new ArrayList<>();

    public void push(Memento memento) {
        history.add(memento);
    }

    public Memento pop() {
        if(history.size() > 0){
            Memento memento = history.get(history.size() - 1);
            history.remove(history.size()-1);
            return memento;
        }
        return null;
    }

}
