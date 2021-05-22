package jogo.logica.estados;

import jogo.logica.QuatroEmLinhaGestor;

public class PedeDecisaoInicio extends EstadoAdapter {

    public PedeDecisaoInicio(QuatroEmLinhaGestor quatroEmLinhaGestor) { super(quatroEmLinhaGestor); }

    @Override
    public Estado iniciarJogo() { return new PedeConfiguracao(quatroEmLinhaGestor); }

    @Override
    public Estado verReplay() {
        // TODO
        System.out.println("VER REPLAY - WIP");
        return super.verReplay();
    }

    @Override
    public Situacao getSituacao() { return Situacao.PedeDecisaoInicio; }

}
