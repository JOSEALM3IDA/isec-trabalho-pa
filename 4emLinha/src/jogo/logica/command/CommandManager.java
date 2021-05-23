package jogo.logica.command;

import java.io.Serializable;
import java.util.Stack;

public class CommandManager implements Serializable {

    private final Stack<Command> historico = new Stack<>();
    private final Stack<Command> fullHistorico = new Stack<>();

    public void invokeCommand(Command command) {
        if (command instanceof DesfazerJogadaCommand) {
            System.out.println("INVOKE UNDO COMMAND");
            fullHistorico.push(command);
            command.execute();


            System.out.println(fullHistorico);
            return;
        }

        if (command.execute()) {
            fullHistorico.push(command);
            historico.push(command);
            System.out.println("historico = " + historico.size());
            return;
        }

        historico.clear();
    }

    public boolean undo() {
        if (historico.size() <= 0) return false;

        Command commandToUndo = historico.pop();

        invokeCommand(new DesfazerJogadaCommand(commandToUndo));
        return true;
    }

    public boolean temUndo() { return !historico.isEmpty(); }

    public int getNumUndosPossivel() { return historico.size(); }

    public boolean temProximo() { return !fullHistorico.isEmpty(); }

    public void executarProximo() {
        if (!temProximo()) return;

        Command primeiroDaLista = fullHistorico.remove(0);
        primeiroDaLista.execute();
    }
}
