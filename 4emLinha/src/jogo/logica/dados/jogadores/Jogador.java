package jogo.logica.dados.jogadores;

import jogo.logica.dados.Tabuleiro;
import jogo.logica.dados.TipoFicha;

import java.io.Serializable;

public abstract class Jogador implements Serializable {

    private final String nome;
    private final TipoFicha ficha;
    private boolean isVencedor = false;

    public Jogador(String nome, TipoFicha ficha) {

        System.out.println("Nome: " + nome + "\n" + ficha);

        this.nome = nome;
        this.ficha = ficha;
    }

    public int getJogadaAutomatica(Tabuleiro tabuleiro) { return -1; }

    public TipoFicha getFicha() { return ficha; }
    public String getNome() { return nome; }

    public boolean isComputador() { return false; };

    @Override
    public String toString() { return nome; }

    public void setVencedor(boolean valor) { isVencedor = valor; }
    public boolean isVencedor() { return isVencedor; }
}
