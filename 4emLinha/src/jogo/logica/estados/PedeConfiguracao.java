package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public class PedeConfiguracao extends EstadoAdapter {

    protected PedeConfiguracao(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado avancar() { return new PedeDecisaoJogada(quatroEmLinha); }

    @Override
    public Situacao getSituacao() { return Situacao.PedeConfiguracao; }
}
