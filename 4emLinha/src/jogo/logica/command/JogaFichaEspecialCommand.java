package jogo.logica.command;

import jogo.logica.dados.QuatroEmLinha;
import jogo.logica.dados.tabuleiro.TipoFicha;

import java.util.List;

public class JogaFichaEspecialCommand extends CommandAdapter {

    int col;
    List<TipoFicha> colunaRemovida;

    public JogaFichaEspecialCommand(QuatroEmLinha receiver, int col) {
        super(receiver);
        this.col = col;
    }

    @Override
    public boolean execute() {
        if (receiver == null) return false;

        colunaRemovida = receiver.getColuna(col);
        receiver.jogarFichaEspecial(col);
        receiver.proxJogador();
        return true;
    }

    @Override
    public void undo() {
        if (receiver == null) return;

        receiver.setColuna(col, colunaRemovida);
    }

    @Override
    public boolean temUndo() { return true; }

    @Override
    public String toString() { return nomeJogador + " joga ficha especial em " + (col + 1); }
}
