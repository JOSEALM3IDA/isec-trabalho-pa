package jogo.logica.command;

import jogo.logica.dados.QuatroEmLinha;

public class JogaFichaCommand extends CommandAdapter {

    int col;

    public JogaFichaCommand(QuatroEmLinha receiver, int col) {
        super(receiver);
        this.col = col;

    }

    @Override
    public boolean execute() {
        if (receiver == null) return false;
        
        receiver.jogarFicha(col);
        receiver.proxJogador();
        return true;
    }

    @Override
    public void undo() {
        if (receiver == null) return;
        receiver.removeFicha(col);
    }

    @Override
    public boolean temUndo() { return true; }

    @Override
    public String toString() { return nomeJogador + " joga ficha em " + (col + 1); }
}
