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
        receiver.jogarFicha(col);
        receiver.proxJogador();
        return true;
    }

    @Override
    public boolean undo() { return receiver.removeFicha(col); }
}
