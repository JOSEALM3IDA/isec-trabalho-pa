package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public class PedeDecisaoInicio extends EstadoAdapter {

    public PedeDecisaoInicio(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado iniciarJogo() { return new PedeConfiguracao(quatroEmLinha); }

    @Override
    public Estado continuarJogo() {
        // TODO
        System.out.println("CONTINUAR JOGO - WIP");
        return super.continuarJogo();
    }

    @Override
    public Estado verReplay() {
        // TODO
        System.out.println("VER REPLAY - WIP");
        return super.verReplay();
    }

    @Override
    public Situacao getSituacao() { return Situacao.PedeDecisaoInicio; }

}
