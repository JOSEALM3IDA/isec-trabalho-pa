package jogo.logica.command;

import jogo.logica.dados.QuatroEmLinha;

public class AdicionaFichaEspecialCommand extends CommandAdapter {

    public AdicionaFichaEspecialCommand(QuatroEmLinha receiver) { super(receiver); }

    @Override
    public boolean execute() {
        if (receiver == null) return false;

        receiver.adicionaFichaEspecialJogadorAtual();
        return true;
    }

    @Override
    public String toString() { return nomeJogador + " recebe ficha especial"; }
}
