package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;

public abstract class EstadoAdapter implements Estado {
    protected QuatroEmLinha quatroEmLinha;

    public EstadoAdapter(QuatroEmLinha quatroEmLinha) { this.quatroEmLinha = quatroEmLinha; }

    @Override
    public Estado iniciarJogo() { return this; }

    @Override
    public Estado continuarJogo() { return this; }

    @Override
    public Estado verReplay() { return this; }

    @Override
    public Estado adicionarJogador() { return this; }

    @Override
    public Estado adicionarJogador(String nome) { return this; }

    @Override
    public Estado jogar() { return this; }

    @Override
    public Estado undoJogada() { return this; }

    @Override
    public Estado gravarJogo() { return this; }

    @Override
    public Estado desistir() { return this; }

    @Override
    public Estado aceitarMinijogo() { return this; }

    @Override
    public Estado ganharMinijogo() { return this; }

    @Override
    public Estado perderMinijogo() { return this; }

    @Override
    public Estado ganharJogo() { return this; }

    @Override
    public Estado avancar() { return this; }


    @Override
    public abstract Situacao getSituacao();
}
