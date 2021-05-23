package jogo.logica.estados;

import jogo.logica.QuatroEmLinhaGestor;

public class AssisteJogada extends EstadoAdapter {

    protected AssisteJogada(QuatroEmLinhaGestor quatroEmLinhaGestor) { super(quatroEmLinhaGestor); }

    @Override
    public Estado avancar() {
        quatroEmLinhaGestor.executarProximo();

        return quatroEmLinhaGestor.temProximo() ? new AssisteJogada(quatroEmLinhaGestor) : new PedeDecisaoInicio(quatroEmLinhaGestor);
    }

    @Override
    public Situacao getSituacao() { return Situacao.AssisteJogada; }
}
