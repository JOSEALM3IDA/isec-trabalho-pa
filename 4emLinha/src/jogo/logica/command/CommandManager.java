package jogo.logica.command;

import java.io.Serializable;
import java.util.Stack;

public class CommandManager implements Serializable {

    private final Stack<Command> historico = new Stack<>();
    private final Stack<Command> redoLista = new Stack<>();

    public void invokeCommand(Command command) {
        redoLista.clear();

        if (command.execute()) {
            historico.push(command);
            System.out.println("historico = " + historico.size());
            return;
        }

        historico.clear();
    }

    public boolean undo() {
        if (historico.size() <= 0) return false;

        Command commandoUndo = historico.pop();
        if (commandoUndo.undo()) {
            redoLista.push(commandoUndo);
            System.out.println("Undo! Historico = " + historico.size());
            return true;
        }

        return false;
    }

    public boolean temUndo() { return !historico.isEmpty(); }

    public int getNumUndosPossivel() { return historico.size(); }

    /*public void redo() {
        if (redoLista.size() <= 0) return;

        Command commandoRedo = redoLista.pop();
        commandoRedo.execute();
        historico.push(commandoRedo);
    }*/
}
