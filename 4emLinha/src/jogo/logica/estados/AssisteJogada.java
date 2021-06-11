package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinhaGestor;

public class AssisteJogada extends EstadoAdapter {

    protected AssisteJogada(QuatroEmLinhaGestor quatroEmLinhaGestor) {
        super(quatroEmLinhaGestor);
        quatroEmLinhaGestor.setReplayAtivo(true);
    }

    @Override
    public Estado avancar() {
        quatroEmLinhaGestor.executarProximo();

        if (quatroEmLinhaGestor.temProximo()) return new AssisteJogada(quatroEmLinhaGestor);

        return new FimJogo(quatroEmLinhaGestor);
    }

    @Override
    public Situacao getSituacao() { return Situacao.AssisteJogada; }
}
