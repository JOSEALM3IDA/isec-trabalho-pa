package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public class PedeDecisaoJogada extends EstadoAdapter {

    protected PedeDecisaoJogada(QuatroEmLinha quatroEmLinha) { super(quatroEmLinha); }

    @Override
    public Estado jogarFicha(int col) {
        quatroEmLinha.jogarFicha(col);
        return quatroEmLinha.checkFimJogo() ? new FimJogo(quatroEmLinha) : new PedeDecisaoJogada(quatroEmLinha);
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
        // TODO
        System.out.println("ACEITAR MINI JOGO - WIP");
        return super.aceitarMinijogo();
    }

    @Override
    public Situacao getSituacao() { return Situacao.PedeDecisaoJogada; }
}
