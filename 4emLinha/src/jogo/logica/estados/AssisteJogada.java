package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public class AssisteJogada extends EstadoAdapter {

    protected AssisteJogada(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado avancar() {
        // TODO
        System.out.println("AVANCAR REPLAY - WIP");
        return super.avancar();
    }

    @Override
    public Situacao getSituacao() { return Situacao.AssisteJogada; }
}
