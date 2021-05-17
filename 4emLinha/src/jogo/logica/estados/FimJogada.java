package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

import java.io.Serializable;

public class FimJogada extends EstadoAdapter {

    protected FimJogada(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado ganharJogo() {
        // TODO
        System.out.println("GANHAR JOGO - WIP");
        return super.ganharJogo();
    }

    @Override
    public Situacao getSituacao() { return Situacao.FimJogada; }
}
