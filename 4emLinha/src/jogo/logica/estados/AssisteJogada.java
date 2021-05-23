package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinhaGestor;

public class AssisteJogada extends EstadoAdapter {

    protected AssisteJogada(QuatroEmLinhaGestor quatroEmLinhaGestor) { super(quatroEmLinhaGestor); }

    @Override
    public Estado avancar() {
        quatroEmLinhaGestor.executarProximo();
        quatroEmLinhaGestor.checkFimJogo();

        if (quatroEmLinhaGestor.temProximo()) return new AssisteJogada(quatroEmLinhaGestor);

        return new PedeDecisaoInicio(quatroEmLinhaGestor);
    }

    @Override
    public Situacao getSituacao() { return Situacao.AssisteJogada; }
}
