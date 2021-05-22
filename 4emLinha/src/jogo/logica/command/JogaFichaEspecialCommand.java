package jogo.logica.command;

import jogo.logica.dados.QuatroEmLinha;
import jogo.logica.dados.TipoFicha;

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
        colunaRemovida = receiver.getColuna(col);
        receiver.jogarFichaEspecial(col);
        receiver.proxJogador();
        return true;
    }

    @Override
    public boolean undo() { return receiver.setColuna(col, colunaRemovida); }
}
