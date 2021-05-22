package jogo.logica.estados;

import jogo.logica.QuatroEmLinhaGestor;

public class PedeDecisaoJogada extends EstadoAdapter {

    protected PedeDecisaoJogada(QuatroEmLinhaGestor quatroEmLinhaGestor) { super(quatroEmLinhaGestor); }

    @Override
    public Estado jogarFicha(int col) {
        quatroEmLinhaGestor.jogarFicha(col);
        return quatroEmLinhaGestor.checkFimJogo() ? new FimJogo(quatroEmLinhaGestor) : new PedeDecisaoJogada(quatroEmLinhaGestor);
    }

    @Override
    public Estado undoJogada() {
        // TODO
        System.out.println("UNDO JOGADA - WIP");
        return super.undoJogada();
    }

    @Override
    public Estado desistir() {
        // TODO
        System.out.println("DESISTIR - WIP");
        return super.desistir();
    }

    @Override
    public Estado aceitarMinijogo() {
        quatroEmLinhaGestor.comecarMinijogo();
        return new JogaMinijogo(quatroEmLinhaGestor);
    }

    @Override
    public Estado jogarFichaEspecial(int col) {
        quatroEmLinhaGestor.jogarFichaEspecial(col);
        return new PedeDecisaoJogada(quatroEmLinhaGestor);
    }

    @Override
    public Situacao getSituacao() { return Situacao.PedeDecisaoJogada; }
}
