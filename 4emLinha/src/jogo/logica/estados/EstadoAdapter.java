package jogo.logica.estados;

import jogo.logica.dados.QuatroEmLinha;
import jogo.logica.dados.jogadores.TipoJogador;

import java.io.Serializable;

public abstract class EstadoAdapter implements Estado, Serializable {
    protected QuatroEmLinha quatroEmLinha;

    public EstadoAdapter(QuatroEmLinha quatroEmLinha) { this.quatroEmLinha = quatroEmLinha; }

    @Override
    public Estado iniciarJogo() { return this; }

    @Override
    public Estado verReplay() { return this; }

    @Override
    public Estado adicionarJogador(TipoJogador tipoJogador, String nomeJogador) { return this; }

    @Override
    public Estado jogarFicha(int col) { return this; }

    @Override
    public Estado undoJogada() { return this; }

    @Override
    public Estado desistir() { return this; }

    @Override
    public Estado aceitarMinijogo() { return this; }

    @Override
    public Estado jogarFichaEspecial(int col) { return this; }

    @Override
    public Estado jogarMinijogo(String resposta) { return this; }

    @Override
    public Estado ganharJogo() { return this; }

    @Override
    public Estado avancar() { return this; }


    @Override
    public abstract Situacao getSituacao();
}
