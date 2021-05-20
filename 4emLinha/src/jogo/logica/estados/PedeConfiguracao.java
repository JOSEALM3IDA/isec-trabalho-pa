package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;
import jogo.logica.dados.jogadores.TipoJogador;

public class PedeConfiguracao extends EstadoAdapter {

    protected PedeConfiguracao(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado adicionarJogador(TipoJogador tipoJogador, String nomeJogador) {
        quatroEmLinha.addJogador(tipoJogador, nomeJogador);
        return quatroEmLinha.isFullJogadores() ? new PedeDecisaoJogada(quatroEmLinha) : new PedeConfiguracao(quatroEmLinha);
    }

    @Override
    public Situacao getSituacao() { return Situacao.PedeConfiguracao; }
}
