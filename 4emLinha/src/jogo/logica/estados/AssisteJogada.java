package jogo.logica.estados;

import jogo.logica.QuatroEmLinhaGestor;

public class AssisteJogada extends EstadoAdapter {

    protected AssisteJogada(QuatroEmLinhaGestor quatroEmLinhaGestor) { super(quatroEmLinhaGestor); }

    @Override
    public Estado avancar() {
        // TODO
        System.out.println("AVANCAR REPLAY - WIP");
        return super.avancar();
    }

    @Override
    public Situacao getSituacao() { return Situacao.AssisteJogada; }
}
