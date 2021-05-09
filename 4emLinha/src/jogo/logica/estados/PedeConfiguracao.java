package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public class PedeConfiguracao extends EstadoAdapter {

    protected PedeConfiguracao(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado adicionarJogador() {
        quatroEmLinha.adicionarJogador();
        return quatroEmLinha.isFullJogadores() ? new PedeDecisaoJogada(quatroEmLinha) : new PedeConfiguracao(quatroEmLinha);
    }

    @Override
    public Estado adicionarJogador(String nome) {
        quatroEmLinha.adicionarJogador(nome);
        return quatroEmLinha.isFullJogadores() ? new PedeDecisaoJogada(quatroEmLinha) : new PedeConfiguracao(quatroEmLinha);
    }

    @Override
    public Situacao getSituacao() { return Situacao.PedeConfiguracao; }
}
